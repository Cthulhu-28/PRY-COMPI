/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.literals;

import java.util.ArrayList;
import java.util.List;
import semantics.identifiers.Type;

/**
 *
 * @author Steven Moya
 */
public class RecordLiteral implements Literal{
    
    private final List<Literal> literals = new ArrayList<>();
    
    public void addLiteral(Literal literal){
        literals.add(literal);
    }
    
    public int leafCount(){
        return literals.size();
    }
    
    @Override
    public boolean match(Type type){
        return getType().equals(type);
    }
    @Override
    public boolean matchDimension(Type type){
        return type.getTypeOrder().size() == literals.size();
    }
    @Override
    public boolean matchDimension(Type type, int idx){
        return type.getTypeOrder().size() == literals.size();
    }
    @Override
    public Type getType(){
        Type type = new Type(Type.nextCode(), "");
        char c='a';
        for(Literal l : literals){
            Type t = new Type(Type.nextCode(),(c++)+"");
           t.setBaseType(l.getType());
            type.addAtrribute(t);
        }
        return type;
    }
}
