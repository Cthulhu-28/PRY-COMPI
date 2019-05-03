/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import parser.grammar.Grammar;
import scanner.reader.Scanner;
import scanner.reader.Token;
import semantics.SemanticAnalyzer;
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
    private boolean noErrors = true;
    private SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
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
        while(CT.getCode() != Grammar.END_MARKER && !stack.isEmpty()){
            int EAP = stack.pop();
            if(Grammar.isTerminal(EAP)){
                if(EAP == CT.getCode())
                    nextToken();
                else{  
                    if(CT.hasError()){
                        errorLexico(CT);
                        noErrors=false;
                    }
                    else{
                        Token last = CT;
                        errorTokenExpected(CT, Grammar.getTerminalName(EAP));
                        nextToken();
                        noErrors=false;
                        if(EAP!=CT.getCode()){
                            scanner.returnToken(CT);
                            CT = last;
                        }
                        else
                            nextToken();
                    }
                }
            }else if(Grammar.isSemanticSymbol(EAP)){
                semanticAnalyzer.analyze(EAP, CT);
            }
            else{
                int rule = Grammar.getParsingTable(EAP-initial, CT.getCode());
                if(rule < 0){
                    noErrors=false;
                    error(rule,CT);
                    recoverFromError(EAP, CT);
                }
                else{
                    int i = 0;
                    while (i < Grammar.MAX_RIGHT_SIDE && Grammar.getRightSides(rule, i)>-1)
                        stack.push(Grammar.getRightSides(rule, i++));
                }
            }
        }
        
        if(!stack.isEmpty()){
            int rule = stack.pop();
            if(Grammar.getParsingTable(rule-initial, Grammar.END_MARKER)<0)
                stack.push(rule);
            if(!stack.isEmpty()){
                noErrors=false;
                error(24);
            }
        }
        if(noErrors)
            System.out.println("Compilación exitosa");
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
    private void recoverFromError(int rule, Token token) throws Exception{
        if(stack.isEmpty() && rule == 0)
            stack.push(rule);
        else{
            if(Grammar.isSynchTokenOfExpression(rule-Grammar.INITIAL_NON_TERMINAL, token.getCode())){
                rule = stack.pop();
                while(!stack.isEmpty() && rule != Grammar.LIST_STATEMENT && rule!= Grammar.IMPONO_EXPRESSION )
                    rule = stack.pop();
                if(rule==Grammar.LIST_STATEMENT || rule == Grammar.IMPONO_EXPRESSION)
                    stack.push(rule);
                if(token.getCode()==23)
                    nextToken();
                return;
            }
            else{
                while(!Grammar.isSynchToken(rule-Grammar.INITIAL_NON_TERMINAL, token.getCode()) && !stack.isEmpty()){
                    rule = stack.pop();
                    while(Grammar.isTerminal(rule) && !stack.isEmpty())
                        rule = stack.pop();               
                }
            }
        }         
    }
}  
    

