/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @param <V>
 */
public class SymbolTable<V> extends HashMap<String,V>{

    
    @Override
    public int hashCode(String key) {
        int hash = 31;
        String str = key.toLowerCase();
        for(int i=0;i<str.length();i++){
            hash = hash*31 + str.charAt(i);
        }
        return hash % this.SIZE;
    }
    
    
}
