/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.literals;

import java.util.List;
import semantics.identifiers.Type;

/**
 *
 * @author admin
 */
public interface Literal {
    
    public boolean match(Type type);
    
    public boolean matchDimension(Type type);
    
    public boolean matchDimension(Type type, int idx);
    
    public List<Literal> toList();
    
    public Type getType();
    
}
