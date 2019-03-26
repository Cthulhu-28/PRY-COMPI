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
    q0(0){
      @Override
      public State next(String input){
          this.lookAhead=false;
          switch(input){
              case "A": this.lookAhead=true;return q2;
              case "O": return FinalStates.q1;
              default: return q0;
          }
      }
    },
    q2(0){
      @Override
      public State next(String input){
          switch(input){
              case "A": return q0;
              case "O": return q3;
              default: return null;
          }
      }
    },
    q3(0){
      @Override
      public State next(String input){
          switch(input){
              case "A": return FinalStates.q1;
              case "O": return q2;
              default: return q3;
          }
      }
    };
    public int code;
    public boolean lookAhead;
    
    private States(int code){
        this.code=code;
        this.lookAhead=false;
    }
    @Override
    public abstract State next(String input);
    
    @Override
    public int getCode(){
        return this.code;
    }
    
    @Override
    public boolean hasLookedAhead(){
        return this.lookAhead;
    }
    
    @Override
    public boolean isFinal(){return false;}
}
