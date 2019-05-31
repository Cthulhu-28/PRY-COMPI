/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.table;

import semantics.identifiers.Type;
import utils.HashMap;

/**
 *
 * @author 
 */
public class TypeTable {
    private final HashMap<String,Type> map = new HashMap<String, Type>() {
        @Override
        public int hashCode(String key) {
            int hash =0;
            String str = key.toLowerCase();
            for(int i=0;i<str.length();i++){
                hash += str.charAt(i);
                hash += hash << 10;
                hash ^= hash >> 6;
            }
            hash += hash << 3;
            hash ^= hash >> 11;
            hash += hash <<15;
            return Math.abs(hash) % this.SIZE;
        }
    };
    public TypeTable(){
        for(Type t : TYPES)
            map.put(t.getName(), t);
        map.put("$error", new Type(-1, "$error"));
    }
    public boolean exists(String key){
        return map.contains(key);
    }
    public Type get(String key){
        if(key==null)
            return new Type(0, "");
        return map.get(key);
    }
    public Type get(int code){
        if(code<7)
            return TYPES[code];
        else{
            for (HashMap.Pair pair : map) {
                Type t = (Type)pair.getValue();
                if(t.getCode()==code)
                    return t;
            }
        }
        return null;
    }
    public void put(String name, Type type){
        map.put(name, type);
    }
    public static final Type TYPES[] = {
        new Type(Type.NUMERUS, "numerus"),
        new Type(Type.IMAGO, "imago"),
        new Type(Type.FRACTIO, "fractio"),
        new Type(Type.CATENA,"catena"),
        new Type(Type.LIBER,"liber"),
        new Type(Type.DUALIS,"dualis"),
        new Type(Type.GREGORIUS,"gregorius")
    };
}
