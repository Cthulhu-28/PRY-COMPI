/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printing;

import java.util.List;
import scanner.reader.Token;

/**
 *
 * @author admin
 */
public class PrettyPrinting {
    List<Token> tokens;
    int tabSize=1;
    int tabs;
    public boolean first;
    StringBuilder builder = new StringBuilder();
    public PrettyPrinting(List<Token> tokens, int tabSize){
        this.tokens=tokens;
        this.tabSize=tabSize;
        first=true;
    }
    
    public void beauty(){

        for(Token t : tokens){
            IndentAction[] actions = IndentAction.getActions(t.getCode());
            processActions(builder, actions, t, 0);
        }
    }
    private void processActions(StringBuilder builder, IndentAction[] actions, Token token, int idx){
        if(idx<actions.length && idx >-1){
            IndentAction action = actions[idx];

            if(action==IndentAction.BREAK_LINE_AFTER){
                builder.append(toSpan(token));
                builder.append("<br>");
                first=true;
            }
            if(action==IndentAction.BREAK_LINE){
                builder.append("<br>");
                first=true;
            }
            if(action==IndentAction.BREAK_LINE_BEFORE){
                builder.append("<br>");
                first=true;
                builder.append((toSpan(token)));
            }
            if(action==IndentAction.INDENT){
                tabs++;
            }
            if(action==IndentAction.UNIDENT){
                tabs--;
            }
            if(action==IndentAction.SPACE){
                builder.append(toSpan(token));
                builder.append("&nbsp");
            }
            processActions(builder, actions, token, idx+1);
        }
    }
    
    private String toSpan(Token token){
        
        StringBuilder builder = new StringBuilder();
        if(first){
            builder.append(getTabs());
            first=false;
        }
        builder.append("<span ");
        builder.append("class=");
        builder.append("\"s");
        builder.append(Color.getCssClass(token.getCode()));
        builder.append("\"");
        builder.append(">");
        builder.append(token.getLexeme());
        builder.append("</span>");
        return builder.toString();
    }
    private String getTabs(){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<tabs;i++){
            for(int j=0;j<tabSize;j++)
                builder.append("&nbsp");
        }
        return builder.toString();
    }
}
