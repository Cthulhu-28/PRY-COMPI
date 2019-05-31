/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.literals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import semantics.identifiers.Type;

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

    @Override
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
    @Override
    public String toString(){
        return value;
    }

    @Override
    public List<Literal> toList() {
        List<Literal> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final SimpleLiteral other = (SimpleLiteral) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    
    
   
}
