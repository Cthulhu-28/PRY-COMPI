/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printing;

import java.util.ArrayList;
import java.util.List;
import scanner.reader.Scanner;

import scanner.reader.Token;

/**
 *
 * @author admin
 */
public class Program {
    public static void main(String args[]){
        try {
            if(args.length<2){
                throw new Exception("Dos parámetros esperados");
            }
            String file = args[0];
            int tabs = Integer.parseInt(args[1]);
            List<Token> tokens = new ArrayList<>();
            Scanner scanner = Scanner.initScanner(file);
            Token token = scanner.nextToken();
            while(token.getCode()!=143){
                tokens.add(token);
                token = scanner.nextToken();
            }
            tokens.add(token);
            PrettyPrinting printing = new PrettyPrinting(tokens, tabs,scanner.getReadLines());
            printing.beauty();      
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
