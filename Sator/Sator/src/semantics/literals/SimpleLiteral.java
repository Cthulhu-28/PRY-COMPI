/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.literals;

import semantics.table.Type;

/**
 *
 * @author admin
 */
public class SimpleLiteral implements Literal{
    private Type type;
    private String value;

    public SimpleLiteral(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public boolean match(Type type){
        return this.type.equals(type);
    }
    @Override
    public boolean matchDimension(Type type){
        return type ==null || type.getDimension() == null;
    }
    @Override
    public boolean matchDimension(Type type, int idx){
        return idx >= type.getDimension().size();
    }
}
