/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author admin
 */
public class SyntacticErrors {
    static final String[] ERRORS = {
        /* 1  */ "Se esperaba el {tkn1} y se recibió {tkn2}",
        /* 2  */ "El programa debe comenzar con la clásula nomen",
        /* 3  */ "Sección del programa mal definida",
        /* 4  */ "Variable o constante esperada",
        /* 5  */ "Definición de tipo incorrecta",
        /* 6  */ "Tipo de dato esperado",
        /* 7  */ "Operador de asignación incorrecto",
        /* 8  */ "Definición ilegal de un arreglo",
        /* 9  */ "Definición ilegal de rutina",
        /* 10 */ "Declaración de parámetro esperada",
        /* 11 */ "Modificador ilegal",
        /* 12 */ "No se ha definido el cuerpo de la rutina",
        /* 13 */ "Tipo irreconocible",
        /* 14 */ "Se esperaba una literal",
        /* 15 */ "Terminador esperado",
        /* 16 */ "Expresion esperada",
        /* 17 */ "Separador de argumentos esperado",
        /* 18 */ "Modo de archivo ilegal",
        /* 19 */ "Operador ternario esperado",
        /* 20 */ "Sentencia condicional imcompleta, se esperaba el bloque de la primera condición",
        /* 21 */ "Setencia esperada",
        /* 22 */ "Sentencia condicional incompleta, se espereaba  bloque optativo",
        /* 23 */ "Se esperaba un caso",
        /* 24 */ "Final de archivo inesperado"
    };
    public static String getError(int code){
        return "ST"+Math.abs(code)+": "+ERRORS[Math.abs(code)-1];
    }
    public static String getError(int code, int row, int col){
        return "ST"+Math.abs(code)+": "+ERRORS[Math.abs(code)-1] + "en la línea " + row + " columna "+col;
    }
    public static String getError(int code, int row, int col, String tkn1, String tkn2){
        String error = getError(code, row, col);
        return error.replace("{tkn1}", tkn1).replace("{tkn2}", tkn2);
    }
}
