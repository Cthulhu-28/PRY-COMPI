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
    private Token CT;
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
        CT = scanner.nextToken();
        stack.push(initial);
        while(CT.getCode() != Gramatica.MARCA_DERECHA && !stack.isEmpty()){
            int EAP = stack.pop();
            if(Gramatica.esTerminal(EAP)){
                if(EAP == CT.getCode())
                    nextToken();
                else{
                    if(CT.hasError())
                        errorLexico(CT);
                    else{
                        nextToken();
                        if(EAP!=CT.getCode())
                            scanner.returnToken(CT);
                    }
                }
            }
            else{                
                int rule = Gramatica.getTablaParsing(EAP-initial, CT.getCode());
                if(rule < 0){
                    error(rule,CT);
                    recoverFromError(EAP, CT);
                }
                else{
                    if(rule==-16)
                        System.out.print("");
                    int i = 0;
                    while (Gramatica.getLadosDerechos(rule, i)>-1 && i < Gramatica.MAX_LADO_DER)
                        stack.push(Gramatica.getLadosDerechos(rule, i++));
                }
            }
        }
        
        if(!stack.isEmpty()){
            int rule = stack.pop();
            if(Gramatica.getTablaParsing(rule-initial, Gramatica.MARCA_DERECHA)<0)
                stack.push(rule);
            if(!stack.isEmpty())
                error(24);
        }
    }
    private void nextToken() throws Exception{
        CT = scanner.nextToken();
        if(CT.isComment())
            nextToken();
    }
    public void error(int code){
        String msg = SyntacticErrors.getError(code);
        System.err.println(msg);
    }
    public void error(int code, Token token) throws Exception{
        String msg = SyntacticErrors.getError(code, token.getIntegerProperty("row"), token.getIntegerProperty("column"));
        System.err.println(msg);
    }
    private void errorTokenExpected(Token token, String expected) throws Exception{
        String msg = SyntacticErrors.getError(-1, token.getIntegerProperty("row"), token.getIntegerProperty("column"), expected , token.getLexeme());
        System.err.println(msg);
    }
    private void errorLexico(Token token){
        System.err.println(token.getErrorMessage());
    }
    private void recoverFromError(int rule, Token token){
        if(stack.isEmpty())
            System.out.print("");
        else{
            if(Gramatica.isSynchTokenOfExpression(rule-Gramatica.NO_TERMINAL_INICIAL, token.getCode())){
                rule = stack.pop();
                while(!stack.isEmpty() && rule != 217)
                    rule = stack.pop();
                if(rule==217)
                    stack.push(rule);
                return;
            }
            while(!Gramatica.isSynchToken(rule-Gramatica.NO_TERMINAL_INICIAL, token.getCode()) && !stack.isEmpty()){
                rule = stack.pop();
                while(Gramatica.esTerminal(rule) && !stack.isEmpty())
                    rule = stack.pop();
                if(rule-Gramatica.NO_TERMINAL_INICIAL==173 || rule==173)
                    System.out.println("");
                
            }
            System.out.println("");
        }         
    }
}  
    

