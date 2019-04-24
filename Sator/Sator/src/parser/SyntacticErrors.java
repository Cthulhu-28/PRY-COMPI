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
        "Se esperaba el {tkn1} y se recibió {tkn2}",
        "El programa debe comenzar con la clásula nomen",
        "Sección del programa mal definida",
        "Variable o constante esperada",
        "Definición de tipo incorrecta",
        "Tipo de dato esperado",
        "Operador de asignación incorrecto",
        "Definición ilegal de un arreglo",
        "Definición ilegal de rutina",
        "Declaración de parámetro esperada",
        "Modificador ilegal",
        "No se ha definido el cuerpo de la rutina",
        "Tipo irreconocible",
        "Se esperaba una literal",
        "Terminador esperado",
        "Expresion esperada",
        "Separador de argumentos esperado",
        "Modo de archivo ilegal",
        "Operador ternario esperado",
        "Sentencia condicional imcompleta, se esperaba el bloque de la primera condición",
        "Setencia esperada",
        "Sentencia condicional incompleta, se espereaba  bloque optativo",
        "Se esperaba un caso"
    };
    public static String getError(int code){
        return "ST"+Math.abs(code)+": "+ERRORS[Math.abs(code)];
    }
    public static String getError(int code, int row, int col){
        return "ST"+Math.abs(code)+": "+ERRORS[Math.abs(code)] + "en la línea " + row + " columna "+col;
    }
    public static String getError(int code, int row, int col, String tkn1, String tkn2){
        String error = getError(code, row, col);
        return error.replace("{tkn1}", tkn1).replace("{tkn2}", tkn2);
    }
}
