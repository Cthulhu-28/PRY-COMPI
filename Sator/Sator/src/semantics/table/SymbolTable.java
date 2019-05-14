/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.table;

import semantics.identifiers.Identifier;
import semantics.identifiers.Parameter;
import semantics.identifiers.Type;
import semantics.identifiers.Category;
import semantics.literals.Literal;
import utils.*;

public class SymbolTable extends HashMap<String,Identifier>{

    private Identifier lastInserted;
    
    @Override
    public int hashCode(String key) {
        int hash = 31;
        String str = key.toLowerCase();
        for(int i=0;i<str.length();i++){
            hash = hash*31 + str.charAt(i);
        }
        return Math.abs(hash) % this.SIZE;
    }
    
    public void insert(String key, Category category){
        Identifier identifier = new Identifier();
        identifier.setCategory(category);
        identifier.setName(key);
        put(key, identifier);
    }
    public void insert(String key, Category category, Type type){
        Identifier identifier = new Identifier();
        identifier.setCategory(category);
        identifier.setType(type);
        identifier.setName(key);
        put(key, identifier);
    }
    public void modify(String key, Type type){
        Identifier identifier = get(key);
        if(identifier != null){
            identifier.setType(type);
        }
    }
    public void modify(String key, Category category){
        Identifier identifier = get(key);
        if(identifier != null){
            identifier.setCategory(category);
        }
    }
    public void modify(String key, Literal initialValue){
        Identifier identifier = get(key);
        if(identifier != null){
            identifier.setInitialValue(initialValue);
        }
    }
    public void putParameter(String key, String name,boolean referenced,Type type){
        Identifier identifier = get(key);
        if(identifier != null){
            identifier.getParameters().add(new Parameter(name, type, referenced));
        }
    }
    public boolean constainsParameter(String key, String param){
        Identifier identifier = get(key);
        return identifier != null && identifier.containsParameter(param);
    }
    
}
