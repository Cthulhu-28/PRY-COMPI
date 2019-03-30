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
    NONE;
    
    public static IndentAction[] getActions(int tokenCode){
        switch(tokenCode){
            case 18:
                return toArray(NONE);
            case 19:
                return toArray(NONE);
            case 28:
                return toArray(NONE);
            case 29:
                return toArray(NONE);
            case 30:
                return toArray(NONE);
            case 35:
                return toArray(NONE);
            case 23:
                return toArray(BREAK_LINE_AFTER);
            case 69:
                return toArray(BREAK_LINE_AFTER);
            case 73:
                return toArray(BREAK_LINE_AFTER);
            case 85:
                return toArray(UNIDENT,BREAK_LINE_AFTER);
            case 93:
                return toArray(UNIDENT,BREAK_LINE_AFTER);
            case 108:
                return toArray(BREAK_LINE_BEFORE,BREAK_LINE,INDENT);
            case 115:
                return toArray(BREAK_LINE_AFTER);
            case 122:
                return toArray(BREAK_LINE_AFTER,INDENT);
            case 129:
                return toArray(BREAK_LINE_AFTER);
           
            default:
                return toArray(SPACE);
        }
    }
    private static IndentAction[] toArray(IndentAction... actions){
        return actions;
    }
}
