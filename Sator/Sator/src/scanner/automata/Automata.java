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
public class Automata {
    //private final State initialState = States.q0;
    
    public static boolean test(String input){
        State state;
        int index =0;
        for(state = States.q0;index<input.length();index++)
            state = state.next(input.charAt(index)+"");
        return state instanceof FinalStates;
    }
    
    public static void main(String[] args){
        if(test("O"))
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
