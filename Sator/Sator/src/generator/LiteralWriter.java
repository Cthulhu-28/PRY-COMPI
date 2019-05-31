/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class LiteralWriter {
    
    public static String toGregorius(String literal){
        String parts[] = literal.split(Pattern.quote("$"));
        short year = Short.parseShort(parts[0]);
        short month = Short.parseShort(parts[1]);
        short day = Short.parseShort(parts[2]);
        return "0"+Integer.toHexString((day+(month<<5)+(year<<9)))+"h";
    }
    public static String toNumerus(String literal){
        if(literal.matches("0x(\\d|\\w)+"))
            return "0"+ literal.replace("0x", "")+"h";
        return literal;
    }
    public static String toCatena(String literal){
        int size = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("?");
        literal=literal.replace("\\n", "\n");
        literal=literal.replace("\\t", "\t");
        literal=literal.replace("\\\'", "\'");
        literal=literal.replace("\\\"", "\"");
        for(int i=1;i<literal.length()-1;i++)
        {
            switch(literal.charAt(i)){
                case '\n':
                    builder.append(",10,13");
                    size+=2;
                    break;
                case '\t':
                    builder.append(",9");
                    size++;
                    break;
                case '\"':
                    builder.append(",34");
                    size++;
                    break;
                case '\'':
                    builder.append(",44");
                    size++;
                    break;
                default:
                    size++;
                    int c = literal.charAt(i);
                    builder.append(",");
                    builder.append(c);
                    break;
            }
        }
        return builder.toString().replace("?", size+"") +","+ (31-size)+" dup (0)";
    }
    
    public static String toImago(String literal){
        literal=literal.replace("\\n", "\n");
        literal=literal.replace("\\t", "\t");
        literal=literal.replace("\\\'", "\'");
        literal=literal.replace("\\\"", "\"");
        switch(literal.charAt(1)){
            case '\n':
                return "10";
            case '\t':
                return "9";
            case '\"':
                return "34";
            case '\'':
                return "44";
            default:        
                 int c = literal.charAt(1);
                return c+"";
        }
    }
    public static String toFractio(String literal){
       String parts[] = literal.split(Pattern.quote("|"));
       return toNumerus(parts[0])+","+toNumerus(parts[1]);
    }
    public static String toDualis(String literal){
        return literal.toLowerCase().equals("veridicus")?"0":"1";
    }
        
    
}
