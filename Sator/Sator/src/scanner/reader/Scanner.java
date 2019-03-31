/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import scanner.automata.FinalStates;
import scanner.automata.Info;
import scanner.automata.State;
import scanner.automata.States;
import scanner.buffer.BufferedStream;

/**
 *
 * @author admin
 */
public class Scanner {
    private BufferedStream buffer;
    private static Scanner scanner;
    private final Set<Integer> integerCodes = new HashSet<>(Arrays.asList(new Integer[]{608,609,611,614,615,616,617,618}));
    private final Set<Integer> escapeCodes = new HashSet<>(Arrays.asList(new Integer[]{606,603}));
    private final Set<Integer> catenaCodes = new HashSet<>(Arrays.asList(new Integer[]{605,607}));
    private final Set<Integer> imagoCodes = new HashSet<>(Arrays.asList(new Integer[]{602,604}));
    private final Set<Integer> gregoriusCodes = new HashSet<>(Arrays.asList(new Integer[]{6110,612,613}));
    private final Set<Integer> fractioCodes = new HashSet<>(Arrays.asList(new Integer[]{610}));
    private final Set<Integer> identifierCodes = new HashSet<>(Arrays.asList(new Integer[]{601}));
    private final Set<Integer> minusAmbiguityCodes = new HashSet<>(Arrays.asList(new Integer[]{19,35,101,72,87,100,104,120,51}));
    private boolean minus;
    private Scanner(){}
    private Scanner(String path) throws FileNotFoundException{
        scanner = new Scanner();
    }
    
    public static synchronized Scanner initScanner(String path) throws Exception{
        if(scanner==null){
            scanner = new Scanner(path);
            scanner.buffer = new BufferedStream(path);
            return scanner;
        }else{
            throw new Exception("GN-6: Debe cerrar el escáner para volver a incializarlo");
        }
    }
    public static synchronized Scanner getScanner() throws Exception{
       if(scanner==null){
           throw new Exception("GN-7: Debe inicializar el escáner antes de usarlo");
       }
       return scanner;
    }
    public void close() throws IOException{
        buffer.close();
        buffer = null;
        scanner = null;
    }
    //Falta lo de toble lectura adelantada, null con sig (ya), validar menos en fecha, largo identificadores
    //Comentarios
    public Token nextToken() throws IOException, Exception{
        int row1,col1,row2,col2;
        row1=row2=col1=col2=0;
        boolean romanNumeral=false;
        boolean withSpace=false;
        boolean comment=false;
        boolean lineComment = false;
        boolean doubleLookAhead=false;
        int blockComments = 0 ;
        StringBuilder builder = new StringBuilder();
        State state = States.q201;
        State last = null;
        
        while(!state.isFinal()){
            String input = Character.toString(buffer.readNextChar());
            if(state == States.q201){
                row1=buffer.getRow();
                col1=buffer.getColumn();
            }
            row2=buffer.getRow();
            col2=buffer.getColumn();
            last = state;
            
            state = state.next(input.toLowerCase());
            if(blockComments>0 && comment && state.getCode()!=201 && state.getCode()!=568 && state.getCode()!=566 && state.getCode()!=565){
                state = States.q567;
            }
            if(("\n\r \t").contains(input) && blockComments>0){
                state = States.q567;
            }
//            if(input.equals("#") && blockComments>0)
//                state = States.q568;
            
            //Revisa una posible transicion hacia identificador
            if(state == null && builder.toString().matches("[A-Za-z]+")){
                String tmp = input;
                
                if(("\n\r \t").contains(tmp)){
                    state = FinalStates.q101;
                }
                if(State.NEXT.contains(tmp)){
                    Info.lookAhead=true;
                    state=FinalStates.q101;
                }
            }
            if(state == null && builder.toString().matches("[0-9]+")){
                String tmp = input;
                
                if(("\n\r \t").contains(tmp)){
                    state = FinalStates.q120;
                }
                if(State.NEXT.contains(tmp)){
                    Info.lookAhead=true;
                    state=FinalStates.q120;
                }
            }
            //En caso de ser null, encuentra un caracter monstruo
            if(state==null){
                if(blockComments>0)
                    state = States.q201;
                else{
                    Token token = reportError(builder, input, last.getCode(), row1, row2, col1, col2);
                    recoverFromError(buffer);
                    return token;
                }
            }

            if( state.getCode()==602 || state.getCode()==605)
                withSpace=true;
            if(state.getCode()==609 || state.getCode()==611){
                doubleLookAhead=true;
            }
            
            //Si hizo una lectura por adelentado, devuelve el caracter la buffer
            //En caso contrario, lo adjunta al posible lexema
            if(state.hasLookedAhead()){
                buffer.returnChar(input.charAt(0));
            }else if (!("\n\r \t").contains(input) || withSpace || comment || lineComment){
                    builder.append(input);
            }
            
            
            if(state.getCode()==632 || state.getCode()==566){
                lineComment=true;
            }
            if(state.getCode()==566){
                comment=true;
                blockComments++;
            }
            if(state.getCode()==567||state.getCode()==568){
                if(builder.toString().endsWith("<#"))
                    blockComments++;
            }
            if(comment && blockComments>=0 && state.getCode()==201){
                blockComments--;
                if(blockComments==0){
                    char c = buffer.readNextChar();
                    buffer.returnChar(c);
                    if(c=='\r')c='\n';
                    if(c=='\n' || c==' ' || c=='\t')builder.append(c+"");
                    return reportarComentario(builder,145);
                }else{
                    state = States.q201;
                }
            }
            if(lineComment && state.getCode()==201 && blockComments==0){
                return reportarComentario(builder,144);
            }
            
            //Verifica si lo que viene empieza con or(posible numero romano)
            if(state == States.q615)
                romanNumeral=true;
            //En caso de haber detectado la lectura de un numerus
            //Si detectó que empezaba con 0r, verifica si es un número romano correcto
            if(romanNumeral && state == FinalStates.q120){
                boolean f = checkRomanNumeral(builder.toString().replace("0r", ""));
                if (!checkRomanNumeral(builder.toString().replace("0r", ""))){
                    Token token;
                    token = new Token(-2, builder.toString(), "Literal de numerus mal formada");
                    token.setProperty("row", row1);
                    token.setProperty("column", col1);
                    return token;
                }      
            }
            if(state == FinalStates.q100){
                if(builder.toString().startsWith("-")){
                    Token token;
                    token = new Token(-7, builder.toString(), "Literal de gregorius mal formada");
                    token.setProperty("row", row1);
                    token.setProperty("column", col1);
                    return token;
                }
            }
            if(state == FinalStates.q101){
                if(builder.toString().length()>16){
                    Token token;
                    token = new Token(-9, builder.toString(), "El identificador "+builder.toString()+" excede el máxmio permitido de 8 caracteres");
                    token.setProperty("row", row1);
                    token.setProperty("column", col1);
                    return token;
                }
            }
            //En caso de hallar en EOF, termina el ciclo
            if(input.equals("\0") && state.getCode()!=143){
                break;
            }
            if(minus && state.getCode()==500){
                minus=false;
                state = FinalStates.q0;
                break;
            }
            if(doubleLookAhead && state.getCode()==120){
                int length = builder.toString().length()-2;
                buffer.returnChar(builder.toString().charAt(length+1));
                builder.replace(length, length+2, "");
            }
        }
        row2 = buffer.getRow();
        col2 = buffer.getColumn();
        if(!state.isFinal()){
            Token token = null;
            token = new Token(-10, builder.toString(), "Final de archivo inesperado");
            token.setProperty("row", row2);
            token.setProperty("column", col2);
            return token;
        }
        if(minusAmbiguityCodes.contains(state.getCode()))
            minus=true;
        else{minus=false;}
        return new Token(state.getCode(), builder.toString());
    }
    private Token reportError(StringBuilder builder, String input, int code, int row1, int row2, int column1, int column2){
        Token token = null;
        if(integerCodes.contains(code)){
            token = new Token(-2, builder.toString(), "Literal de numerus mal formada");
            token.setProperty("row", row1);
            token.setProperty("column", column1);
        }else if(escapeCodes.contains(code)){
            token = new Token(-3, builder.toString(), "Caracter de escapae inválido");
            token.setProperty("row", row2);
            token.setProperty("column", column2);
        }
        else if(catenaCodes.contains(code)){
            token = new Token(-4, builder.toString(), "Literal de catena mal formada");
            token.setProperty("row", row1);
            token.setProperty("column", column1);
        }else if(imagoCodes.contains(code)){
            token = new Token(-5, builder.toString(), "Literal de imago mal formada");
            token.setProperty("row", row1);
            token.setProperty("column", column1);
        } else if(fractioCodes.contains(code)){
            token = new Token(-6, builder.toString(), "Literal de fractio mal formada");
            token.setProperty("row", row1);
            token.setProperty("column", column1);
        } else if(gregoriusCodes.contains(code)){
            token = new Token(-7, builder.toString(), "Literal de gregorius mal formada");
            token.setProperty("row", row1);
            token.setProperty("column", column1);
        } else if(identifierCodes.contains(code)){
            token = new Token(-8, builder.toString(), "Identificador mal formado");
            token.setProperty("row", row1);
            token.setProperty("column", column1);
        }else{
            token = new Token(-1, builder.toString(), "Caracter "+input+" inesperado");
            token.setProperty("row", row2);
            token.setProperty("column", column2);
        }
        return token;
    }
    private Token reportarComentario(StringBuilder builder, int code){
        Token token = new Token(code, builder.toString());
        return token;
    }
    public boolean checkRomanNumeral(String pattern){
        return pattern.toUpperCase().matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }
    private void recoverFromError(BufferedStream buffer) throws Exception{
        String recover = " \n\t\r\0.";
        char c = buffer.readNextChar();
        while(!recover.contains(Character.toString(c)))
            c = buffer.readNextChar();
        buffer.returnChar(c);
    }
    
   
}
