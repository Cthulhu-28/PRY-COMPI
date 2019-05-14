/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.identifiers;

/**
 *
 * @author admin
 */
public class Parameter {
    private String name;
    private boolean referenced;
    private Type type;
    
    public Parameter(String name, Type type){
        this.name = name;
        this.type = type;
        this.referenced = false;
    }
    public Parameter(String name, Type type, boolean referenced){
        this.name = name;
        this.type = type;
        this.referenced = referenced;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReferenced() {
        return referenced;
    }

    public void setReferenced(boolean referenced) {
        this.referenced = referenced;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
}
