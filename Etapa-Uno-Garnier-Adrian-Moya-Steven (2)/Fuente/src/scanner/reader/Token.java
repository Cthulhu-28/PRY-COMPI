/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.reader;

import java.util.HashMap;

/**
 *
 * @author admin
 */
public class Token {
    private int code;
    private String lexeme;
    private String errorMessage;
    private HashMap<String,Object> props;

    public Token(int code, String lexeme) {
        this.code = code;
        this.lexeme = lexeme;
        this.props = new HashMap<>();
    }
    public Token(int code, String lexeme, String errorMessage) {
        this.code = code;
        this.lexeme = lexeme;
        this.errorMessage = errorMessage;
        this.props = new HashMap<>();
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }
    public boolean hasError(){
        return getCode() <= -1;
    }
    public int getErrorNumber(){
        return Math.abs(code);
    }
    public int getIntegerProperty(String prop){
        return (int)props.get(prop);
    }
    public String getStringProperty(String prop){
        return (String)props.get(prop);
    }
    public Object getProperty(String prop){
        return props.get(prop);
    }
    @Override
    public String toString() {
        return "Token{" + "code=" + code + ", lexeme=" + lexeme + '}';
    }

    public String getErrorMessage() {
        return "LX-"+getErrorNumber()+": " + errorMessage + " en la línea "+getIntegerProperty("row")+" columna "+getIntegerProperty("column");
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HashMap<String, Object> getProps() {
        return props;
    }

    public void setProperty(String key, Object value) {
        props.put(key, value);
    }
    public boolean isComment(){
        return getCode()>143;
    }
    
}
