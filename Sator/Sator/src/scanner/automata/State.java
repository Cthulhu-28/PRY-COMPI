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
    
    /**
     * This causes the automata to move into a another state. In case that the
     * input not lead to a state, it returns null.
     * @param input The input string
     * @return The next state
     */
    public State next(String input);
    /**
     * 
     * @return 
     */
    public int getCode();
    
    public boolean hasLookedAhead();
    
    public boolean isFinal();

}
