/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import scanner.reader.Token;

/**
 *
 * @author admin
 */
public class PrettyPrinting {
    int[] tokensCount = new int[150];
    List<Token> tokens;
    int tabSize=1;
    int tabs;
    public boolean first;
    boolean coniugo = false;
    private int lineas;
    private int errorees;
    boolean opus = false;
    boolean sigla = true;
    boolean unindentOnFirst = false;
    StringBuilder builder = new StringBuilder();
    public PrettyPrinting(List<Token> tokens, int tabSize, int lines){
        this.tokens=tokens;
        this.tabSize=tabSize;
        this.lineas=lines;
        first=true;
    }
    
    public void beauty(){
        for(int i=0;i<tokens.size();i++){          
            if(tokens.get(i).hasError()){
                errorees++;
                System.err.println(tokens.get(i).getErrorMessage());
            }
            else
                tokensCount[tokens.get(i).getCode()]++;
            IndentAction[] actions = IndentAction.getActions(tokens.get(i).getCode());
            if(i==tokens.size()-1){
                processActions(builder, actions, tokens.get(i),null, 0);
            }else{
                processActions(builder, actions, tokens.get(i),tokens.get(i+1), 0);
            }
        }
        writeHTML(createFullHTML());
        
    }
    private void processActions(StringBuilder builder, IndentAction[] actions, Token token, Token next,int idx){
        if(idx<actions.length && idx >-1){
            boolean unTab = false;
            IndentAction action = actions[idx];
            if(opus){
                opus = false;
                unTab = true;
            }
            if(action==IndentAction.BREAK_LINE_AFTER){
                builder.append(toSpan(token));
                if(next!=null && (next.getCode()!=108 && next.getCode()!=23)){
                    builder.append("<br>");
                    first=true;
                }else{
                     builder.append(" ");
                }
                checknindent();
            }
            if(action==IndentAction.BREAK_LINE){
                 if(token.isComment())
                    builder.append((toSpan(token)));
                builder.append("<br>");
                first=true;
                checknindent();
            }
            if(action==IndentAction.BREAK_LINE_BEFORE){
                builder.append("<br>");
                first=true;
                builder.append((toSpan(token)));
                checknindent();
            }
            if(action==IndentAction.INDENT){
                tabs++;
            }
            if(action==IndentAction.UNIDENT){
                if(!sigla){
                    tabs--;
                }else{
                    sigla = false;
                }
                
            }
            if(action==IndentAction.SPACE){
                
                builder.append(toSpan(token));
                if(!(next!=null && (next.getCode()==19 || next.getCode()==35))){               
                    builder.append("&nbsp");
                } 
              
                if(next!=null && (token.getCode()== 73 || token.getCode()==155 || token.getCode()==115) && (next.getCode()!=108)){
                    builder.append("<br>");
                    tabs++;   
                    first=true;
                    unindentOnFirst=true;
                }
                
                if(coniugo){
                    builder.append("<br>");
                    tabs++;   
                    first=true;
                    coniugo = false;
                }
                if(token.getCode()==76)
                    coniugo=true;
                if(token.getCode()==122 && next !=null && next.getCode() != 108){
                    builder.append("<br>");
                    tabs++;   
                    first=true;
                    opus = true;
                }
                if(token.getCode()==137){
                    sigla=true;
                }
            }
            if(action==IndentAction.TO_BEGINING){
                tabs=0;
                builder.append(toSpan(token));
            }
            if(action==IndentAction.NONE){
                if(token.isComment()){
                    token.setLexeme(token.getLexeme().replaceAll("[\n]", "<br>"));
                    token.setLexeme(token.getLexeme().replaceAll("[ ]", "&nbsp"));
                }
                builder.append(toSpan(token));
                if(token.getCode()==19 && next!=null && next.getCode()!=19)
                    builder.append(" ");
                if(token.getCode()==35 && next!=null && next.getCode()!=35)
                    builder.append(" ");
            }
            if(unTab){
                unTab=false;
                tabs--;
            }
            processActions(builder, actions, token, next, idx+1);
        }
    }
    private void checknindent(){
        if(unindentOnFirst){
            tabs--;
            unindentOnFirst=false;
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
        builder.append("\"");
        if(!token.hasError())
            builder.append(Color.getCssClass(token.getCode()).replace(".", ""));
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
    private String createFullHTML(){
        StringBuilder HTML = new StringBuilder();
        HTML.append("<html>");
        HTML.append("<head>");
        HTML.append("<style>");
        HTML.append("@font-face {\n" +
                    "  font-family: 'Droid Sans Mono';\n" +
                    "  font-style: normal;\n" +
                    "  font-weight: 400;\n" +
                    "  src: local('Droid Sans Mono Regular'), local('DroidSansMono-Regular'), url(https://fonts.gstatic.com/s/droidsansmono/v10/6NUO8FuJNQ2MbkrZ5-J8lKFrp7pRef2r.woff2) format('woff2');\n" +
                    "  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                    "}");
        HTML.append(Color.getCSS());
        HTML.append("</style></head>");
        HTML.append("<body>");
        HTML.append(builder.toString());
        HTML.append("<br>");
        HTML.append(getStatistics());
        HTML.append("</body></html>");
        builder = null;
        return HTML.toString();
    }
    private void writeHTML(String str){
        FileOutputStream outputStream=null;
        try {
            outputStream = new FileOutputStream("Sator.html");
            byte[] strToBytes = str.getBytes();
            outputStream.write(strToBytes);

            outputStream.close();
        } catch (IOException ex) {
            System.err.println("No se pudo crear el archivo de salida");
        } finally {
            try {
                if(outputStream!=null)outputStream.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    public String getStatistics(){
        StringBuilder builder = new StringBuilder();
        builder.append("<table><thead><tr><td><b>#Familia</b></td><td><b>Cantidad de tokens</b></td></tr></thead>");
        builder.append("<tbody>");
        int count=0;
        for(int i =0;i<tokensCount.length;i++){
            if(tokensCount[i]>0 && i<144){
                builder.append("<tr>");
                builder.append("<td>");
                builder.append(i);
                builder.append("</td><td>");
                builder.append(tokensCount[i]);
                builder.append("</td></tr>");
                count+=tokensCount[i];
            }
        }
        builder.append("<tr><td><b>");
        builder.append("Total</b></td>");
        builder.append("<td><b>");
        builder.append(count);
        builder.append("</b></td>");
        builder.append("</table>");
        
        builder.append("<br><table><thead><tr><td><b>Tipo comentario</b></td><td><b>Cantidad</b></td></tr></thead>");
        count=0;
        for(int i =144;i<tokensCount.length;i++){
            if(tokensCount[i]>0){
                builder.append("<tr>");
                builder.append("<td>");
                if(i==144)
                    builder.append("Línea");
                else
                    builder.append("Bloque");
                builder.append("</td><td>");
                builder.append(tokensCount[i]);
                builder.append("</td></tr>");
                count+=tokensCount[i];
            }
        }
        builder.append("<tr><td><b>");
        builder.append("Total</b></td>");
        builder.append("<td><b>");
        builder.append(count);
        builder.append("</b></td>");
        builder.append("</table>");
        if(errorees>0){
            builder.append("<br><table><thead><tr><td><b># Errorres</b></td><td><b>");
            builder.append(errorees);
            builder.append("</b></td></tr></thead></table>");
        }
        if(lineas>0){
            builder.append("<br><table><thead><tr><td><b># Líneas</b></td><td><b>");
            builder.append(lineas);
            builder.append("</b></td></tr></thead></table>");
        }
        return builder.toString();
    }
}
