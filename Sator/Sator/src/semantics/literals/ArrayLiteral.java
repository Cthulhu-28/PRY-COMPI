/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.literals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import semantics.identifiers.Type;

/**
 *
 * @author Steven Moya
 */
public class ArrayLiteral implements Literal{
    
    private final List<Literal> literals = new ArrayList<>();
    
    public void addLiteral(Literal literal){
        literals.add(literal);
    }
    
    public int leafCount(){
        return literals.size();
    }
    
    @Override
    public boolean match(Type type){
        return literals.stream().allMatch(l -> l.match(type));
    }
    @Override
    public boolean matchDimension(Type type){
        return matchDimension(type, 0);
    }
    @Override
    public boolean matchDimension(Type type, int idx){
        if(idx<type.getDimension().size()){
            return leafCount()==type.getDimension().get(idx) && literals.stream().allMatch(l->l.matchDimension(type, idx+1));
        }
        return false;
    }
    @Override
    public Type getType(){
        Type type = new Type(Type.nextCode(), "");
        Type t=null;
        boolean valid = true;
        type.addDimension(leafCount());
        if(leafCount()>0)
            t = literals.get(0).getType();
        for(int i=1;i<literals.size() && t != null;i++){
            valid = valid && t.equals(literals.get(i).getType());
        }
        if(!valid)
            return null;
        if(t!=null){
            if(t.isArray()){
                for(Integer i : t.getDimension())
                    type.addDimension(i);
                type.setBaseType(t.getBaseType());
            }else
                type.setBaseType(t);
        }
        return type;
    }
    @Override
    public String toString(){
        return "{"+String.join(",", toList().stream().map(l->l.toString()).collect(Collectors.toList()))+"}";
    }
    @Override
    public List<Literal> toList(){
        List<Literal> list = new ArrayList<>();
        for(Literal l : literals){
            list.addAll(l.toList());
        }
        return list;
    }
}
