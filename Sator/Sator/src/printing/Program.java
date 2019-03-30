/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printing;

import java.util.ArrayList;
import java.util.List;
import scanner.automata.Info;
import scanner.reader.Scanner;

import scanner.reader.Token;

/**
 *
 * @author admin
 */
public class Program {
    public static void main(String args[]){
       
        try {
            Info.lookAhead=false;
            List<Token> tokens = new ArrayList<>();
            Scanner scanner = Scanner.initScanner("C:\\Proyectos\\4.str");
            Token token = scanner.nextToken();
            while(token.getCode()!=143){
                tokens.add(token);
                token = scanner.nextToken();
            }
            tokens.add(token);
            int r =4;
            for(Token t:tokens){
                if(t.hasError())
                    System.err.println(t.getErrorMessage());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        
        
        
    }
}
