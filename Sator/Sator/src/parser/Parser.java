/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import parser.grammar.Gramatica;
import scanner.reader.Scanner;
import scanner.reader.Token;
import utils.Stack;

/**
 *
 * @author admin
 */
public class Parser {
    private Scanner scanner;
    private Stack<Integer> stack;
    private static Parser parser=null;
    
    private Parser(){}
    
    private Parser(String path) throws Exception{
        scanner = Scanner.initScanner(path);
        stack = new Stack<>();
    }
    
    public static synchronized Parser initParser(String path) throws Exception{
        if(parser==null)
            parser = new Parser(path);
        return parser;
    }
    
    public void parse() throws Exception{
        int initial = 144;
        Token CT = scanner.nextToken();
        stack.push(initial);
        while(CT.getCode() != Gramatica.MARCA_DERECHA){
            int EAP = stack.pop();
            if(Gramatica.esTerminal(EAP)){
                if(EAP == CT.getCode())
                    CT = scanner.nextToken();
                else
                    error();
            }
            else{
                int rule = Gramatica.getTablaParsing(EAP-initial, CT.getCode());
                if(rule < 0)
                    error();
                else{
                    int i = 0;
                    while (Gramatica.getLadosDerechos(rule, i)>-1 && i < Gramatica.MAX_LADO_DER)
                        stack.push(Gramatica.getLadosDerechos(rule, i++));
                }
            }
        }
    }
    public void error() throws Exception{
        throw new Exception("Error xd");
    }
}
