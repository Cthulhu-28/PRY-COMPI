/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printing;

import java.util.ArrayList;
import java.util.List;
import scanner.automata.FinalStates;
import scanner.automata.State;
import scanner.automata.States;

import scanner.reader.Token;

/**
 *
 * @author admin
 */
public class Program {
    public static void main(String args[]){
  
        State s = States.q201;
        s=s.next("3");
        s=s.next("$");
        s=s.next("5");
        s=s.next("$");
        s=s.next("5");
        s=s.next(" ");
        System.out.println("jj");
//        List<Token> tokens = new ArrayList<>();
        
//        tokens.add(new Token(1, "in"));
//        tokens.add(new Token(1, "("));
//        tokens.add(new Token(1, "a"));
//        tokens.add(new Token(1, ">"));
//        tokens.add(new Token(1, "b"));
//        tokens.add(new Token(1, ")"));
//        tokens.add(new Token(108, "initum"));
//        tokens.add(new Token(101, "a"));
//        tokens.add(new Token(101, "b"));
//        tokens.add(new Token(101, "c"));
//        tokens.add(new Token(23, "."));
//        tokens.add(new Token(101, "a"));
//        tokens.add(new Token(101, "b"));
//        tokens.add(new Token(101, "c"));
//        tokens.add(new Token(23, "."));
//        tokens.add(new Token(93, "finis"));
//        tokens.add(new Token(101, "c"));
//        
//        PrettyPrinting pp = new PrettyPrinting(tokens, 2);
//        pp.beauty();
//        
//        System.out.println(pp.builder.toString());
        
    }
}
