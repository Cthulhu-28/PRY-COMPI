/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.reader;

/**
 *
 * @author admin
 */
public class Token {
    private int code;
    private String lexeme;

    public Token(int code, String lexeme) {
        this.code = code;
        this.lexeme = lexeme;
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
    
    
}
