/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.literals;

import java.util.ArrayList;
import java.util.List;
import semantics.table.Type;

/**
 *
 * @author admin
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
}
