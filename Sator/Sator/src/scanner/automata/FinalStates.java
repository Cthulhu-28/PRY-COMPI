/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.automata;

interface FinalState extends State{}

/**
 *
 * @author admin
 */
public enum FinalStates implements FinalState{
    
    q1(1){
      @Override
      public State next(String input){
          switch(input){
              case "A": return States.q3;
              case "O": return States.q0;
              default: return q1;
          }
      }
    };
    private final int code;
    
    
    FinalStates(int code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }
    
    @Override
    public abstract State next(String input);
    
}
