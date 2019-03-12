/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.automata;

/**
 * 
 * @author admin
 */
public interface State {
    public String ALPHABET="";
    
    public State next(String input);
}
