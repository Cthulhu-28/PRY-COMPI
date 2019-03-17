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
public enum States implements State{
    q0{
      @Override
      public State next(String input){
          switch(input){
              case "A": return q2;
              case "O": return FinalStates.q1;
              default: return q0;
          }
      }
    },
    q2{
      @Override
      public State next(String input){
          switch(input){
              case "A": return q0;
              case "O": return q3;
              default: return q3;
          }
      }
    },
    q3{
      @Override
      public State next(String input){
          switch(input){
              case "A": return FinalStates.q1;
              case "O": return q2;
              default: return q3;
          }
      }
    };

    @Override
    public abstract State next(String input);
}
