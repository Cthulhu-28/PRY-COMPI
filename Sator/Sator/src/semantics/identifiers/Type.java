/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.identifiers;

import java.util.ArrayList;
import java.util.List;
import utils.HashMap;

/**
 * Represents the type of a lieteral or identifier. It contains
 * all the needed attributes to declare a specific type
 * @author Steven Moya
 */
public class Type {
    
    /**
     * Unique code that identifes the type
     */
    private int code;
    /**
     * Unique name that identifies the type
     */
    private String name;
    /**
     * For custom types it represents the in which is based
     */
    private Type baseType;
    private Type Parent;
    /**
     * Stores sequentally the dimension of the type if it's an array
     */
    private List<Integer> dimension;
    /**
     * Stores the attributes of the record
     */
    private HashMap<String,Type> attributes;
    /**
     * Stores the order type of the record's attributes
     */
    private List<Type> typeOrder;
    public Type(){
        code = -1;
    }
    public Type(int code, String name){
        this.code = code;
        this.name = name;
    }

    public Type(int code, String name, Type baseType) {
        this.code = code;
        this.name = name;
        this.baseType = baseType;
    }

    public Type(int code, String name, Type baseType, Type Parent) {
        this.code = code;
        this.name = name;
        this.baseType = baseType;
        this.Parent = Parent;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getBaseType() {
        return baseType;
    }
    public Type getBaseTypeDepth(){
        Type t = getBaseType();
        while(t!=null && t.code>=7){
            t = t.getBaseType();
        }
        return t;
    }

    public void setBaseType(Type baseType) {
        this.baseType = baseType;
    }

    public Type getParent() {
        return Parent;
    }

    public void setParent(Type Parent) {
        this.Parent = Parent;
    }

    public List<Integer> getDimension() {
        return dimension;
    }

    public void setDimension(List<Integer> dimension) {
        this.dimension = dimension;
    }
    public void addDimension(int dimension){
        if(this.dimension==null)
            this.dimension = new ArrayList<>();
        this.dimension.add(dimension);
    }
    public void addAtrribute(Type type){
        if(attributes==null){
            typeOrder = new ArrayList<>();
            attributes = new HashMap<String, Type>() {
                @Override
                public int hashCode(String key) {
                    int hash = 31;
                    String str = key.toLowerCase();
                    for(int i=0;i<str.length();i++){
                        hash = hash*31 + str.charAt(i);
                    }
                    return hash % this.SIZE;
                }
            };
        }
        attributes.put(type.getName(), type);
        typeOrder.add(type);
    }
    
    public boolean matchType(List<Type> list){
        boolean matched = true;
        if(typeOrder != null && typeOrder.size() != list.size())
            return false;
        for(int i=0;i<typeOrder.size();i++)
            matched = matched && typeOrder.get(i).equals(list.get(i));
        return matched;
    }
    public boolean constainsAttribute(String name){
        return attributes != null && attributes.contains(name);
    }
    public Type getAttribute(String name){
        return attributes == null ? null : attributes.get(name);
    }
    public boolean isRecord(){
        return attributes != null;
    }
    public boolean isArray(){
        return dimension != null;
    }
    @Override
    public String toString(){
        String str = this.name;
        if(dimension != null){
            str = baseType.toString();
            str = this.dimension.stream().map((i) -> "["+i+"]").reduce(str, String::concat);
        }
        if(typeOrder != null){
            str = "<-";
            int i=0;
            for(;i<typeOrder.size()-1;i++){
                str += typeOrder.get(i).getBaseType().toString() + ",";
            }
            str += typeOrder.get(i).getBaseType().toString() +  "->";
        }           
        return str;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Type other = (Type) obj;
        if(this.code == other.code)
            return true;
        if(this.typeOrder!=null){
            if(other.typeOrder==null)
                return false;
            if(this.typeOrder.equals(other.typeOrder))
                return this.baseType.equals(other.baseType);
        }else{
            if(other.typeOrder != null)
                return false;
        }
        if(this.dimension!=null){
            if(other.dimension==null)
                return false;
            if(this.dimension.equals(other.dimension))
                return this.baseType.equals(other.baseType);
        }else{
            if(other.dimension != null)
                return false;
        }
        return this.equals(other.baseType) || other.equals(this.baseType);
    }
    
    public static int nextCode(){
        return codes++;
    }
    
    private static int codes = 7;
    public static final int NUMERUS = 0;
    public static final int IMAGO = 1;
    public static final int FRACTIO = 2;
    public static final int CATENA = 3;
    public static final int LIBER = 4;
    public static final int DUALIS = 5;
    public static final int GREGORIUS = 6;
    
    public static final int TYPE_TO_TOKEN[] = {120, 104, 96, 72, -1, 87, 100};
    
    public static String typeName(int tokenCode){
        switch(tokenCode){
            case 120: return "numerus";
            case 104: return "imago";
            case 96: return "fractio";
            case 72: return "catena";
            case 87: return "dualis";
            case 100: return "gregorius";
        }
        return null;
    }
    
    private static final byte TYPE_COMPATIBILY[][] =  {    /*N I F C L D G*/  
                                                       /*N*/{1,1,1,1,0,1,1},
                                                       /*I*/{1,1,1,1,0,1,1},
                                                       /*F*/{1,1,1,1,0,1,1},
                                                       /*C*/{1,1,1,1,0,1,1},
                                                       /*L*/{0,0,0,0,1,0,0},
                                                       /*D*/{1,1,1,1,0,1,1},
                                                       /*G*/{1,1,1,1,0,1,1},
                                                       };
    public static boolean isCompatible(int from, int to){
        return from < 7 && to < 7 &&  TYPE_COMPATIBILY[from][to] == 1;
    }
}
