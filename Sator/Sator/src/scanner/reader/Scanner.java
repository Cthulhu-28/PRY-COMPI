/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import scanner.automata.FinalStates;
import scanner.automata.State;
import scanner.automata.States;
import scanner.buffer.BufferedStream;

/**
 *
 * @author admin
 */
public class Scanner {
    private BufferedStream buffer;
    
    private Scanner(){}
    
    public static Scanner initScanner(String path) throws FileNotFoundException{
        Scanner s = new Scanner();
        s.buffer = new BufferedStream(path);
        return s;
    }
    public void close() throws IOException{
        buffer.close();
        buffer = null;
    }
    public Token nextToken() throws IOException{
        StringBuilder builder = new StringBuilder();
        State initial = States.q0;
        State s = FinalStates.q0;
     
        while(!initial.isFinal()){
            String input = buffer.readNextChar()+"";
            initial = initial.next(input);
            if(input.equals('\0') && initial.getCode()!=147){
                break;
            }
            if(initial.hasLookedAhead()){
                buffer.returnChar(input.charAt(0));
            }else{
                builder.append(input);
            }
            
            
        }
         
        return new Token(initial.getCode(), builder.toString());
    }
    
}
