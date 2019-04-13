/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printing;

/**
 *
 * @author admin
 */
public enum IndentAction {
    SPACE,
    BREAK_LINE,
    BREAK_LINE_BEFORE,
    BREAK_LINE_AFTER,
    INDENT,
    UNIDENT,
    NONE,
    TO_BEGINING;
    
    public static IndentAction[] getActions(int tokenCode){
        switch(tokenCode){
            case 18:
                return toArray(NONE);
            case 19:
                return toArray(NONE);
            case 23:
                return toArray(BREAK_LINE_AFTER);
            case 28:
                return toArray(NONE);
            case 29:
                return toArray(NONE);
            case 30:
                return toArray(NONE);
            case 35:
                return toArray(NONE);
            case 36:
                return toArray(NONE);
            case 69:
                return toArray(BREAK_LINE_AFTER);
            case 73:
                return toArray(SPACE);
            case 75:
                return toArray(TO_BEGINING,BREAK_LINE,INDENT);
            case 77:
                return toArray(TO_BEGINING,BREAK_LINE,INDENT);
            case 85:
                return toArray(UNIDENT,BREAK_LINE_AFTER);
            case 92:
                return toArray(TO_BEGINING,BREAK_LINE,INDENT);
            case 93:
                return toArray(UNIDENT,BREAK_LINE_AFTER); 
            case 94:
                return toArray(TO_BEGINING,BREAK_LINE,INDENT);
            case 97:
                return toArray(TO_BEGINING,BREAK_LINE,INDENT);
            case 108:
                return toArray(BREAK_LINE_BEFORE,BREAK_LINE,INDENT);
            case 115:
                return toArray(SPACE);
            case 123:
                return toArray(SPACE);
            case 128:
                return toArray(TO_BEGINING,BREAK_LINE,INDENT);   
            case 129:
                return toArray(BREAK_LINE_AFTER);
            case 144:
                return toArray(BREAK_LINE);
            case 145:
                return toArray(NONE);
            default:
                return toArray(SPACE);
        }
    }
    private static IndentAction[] toArray(IndentAction... actions){
        return actions;
    }
}
