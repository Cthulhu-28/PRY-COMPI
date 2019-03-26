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
    UNIDENT;
    
    public static IndentAction[] getActions(int tokenCode){
        switch(tokenCode){
            case 23:
                return toArray(BREAK_LINE_AFTER);
            case 108:
                return toArray(BREAK_LINE_BEFORE,BREAK_LINE,INDENT);
            case 93:
                return toArray(UNIDENT,BREAK_LINE_AFTER);
            case 122:
                return toArray(BREAK_LINE_AFTER,INDENT);
            case 85:
                return toArray(UNIDENT,BREAK_LINE_AFTER);
            default:
                return toArray(SPACE);
        }
    }
    private static IndentAction[] toArray(IndentAction... actions){
        return actions;
    }
}
