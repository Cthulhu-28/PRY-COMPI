/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.table;

import utils.HashMap;

/**
 *
 * @author admin
 */
public class TypeTable {
    private final HashMap<String,Type> map = new HashMap<String, Type>() {
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
    public TypeTable(){
        for(Type t : TYPES)
            map.put(t.getName(), t);
    }
    public boolean exists(String key){
        return map.contains(key);
    }
    public Type get(String key){
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
    private final Type TYPES[] = {
        new Type(Type.NUMERUS, "numerus"),
        new Type(Type.IMAGO, "imago"),
        new Type(Type.FRACTIO, "fractio"),
        new Type(Type.CATENA,"catena"),
        new Type(Type.LIBER,"liber"),
        new Type(Type.DUALIS,"dualis"),
        new Type(Type.GREGORIUS,"gregorius")
    };
}
