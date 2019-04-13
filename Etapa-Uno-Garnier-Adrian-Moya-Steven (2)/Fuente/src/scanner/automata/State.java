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
    public String ALPHABET="\0\1\2\3\4\5\6\7\10\11\12\13\14\15\16\17\20\21\22\23\24\25\26\27\30\31\32\33\34\35\36\37\40!\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\177";
    public String NEXT="abcdefghijklmnopqrstuvwxz0123456789[&|($\0),.-*,@]/";
    
    
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
