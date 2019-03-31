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
       int tabs = 4;
       String file = "";
        try {
            List<Token> tokens = new ArrayList<>();
            Scanner scanner = Scanner.initScanner("C:\\Users\\admin\\Documents\\TEC\\2019\\I Semestre\\Compiladores e Interpretes\\Proyecto\\Etapa 0\\Pruebas\\test001.str");
            Token token = scanner.nextToken();
            while(token.getCode()!=143){
                tokens.add(token);
                token = scanner.nextToken();
            }
            tokens.add(token);
            boolean hasError = false;
            for(int i=0;i<tokens.size();i++)
                if(tokens.get(i).hasError()){
                    System.err.println(tokens.get(i).getErrorMessage());
                    hasError=true;
                }else{
                   
                }
            if(!hasError){
                PrettyPrinting printing = new PrettyPrinting(tokens, tabs);
                printing.beauty();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        
        
        
    }
}
