/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics;

import parser.*;

/**
 *
 * @author admin
 */
public class SemanticErrors {
    static final String[] ERRORS = {
        /* 1  */ "Tipo {token1} desconocido",
        /* 2  */ "El identificador {token1} ya se encuentra definido",
        /* 3  */ "Tipos incompatibles: {token1} no puede convertirse a {token2}",
        /* 4  */ "El atributo {token1} ya estaba definido",
        /* 5  */ "La dimensión del arreglo debe ser positiva",
        /* 6  */ "Arreglo inválido: se esparaba un arreglo de tipo {token}",
        /* 7  */ "El parámetro {token1} ya se encuentra definido",
        /* 8  */ "El identificador {token1} no existe",
        /* 9  */ "Acceso incorrecto: {token1} no posee el atributo {token2}",
        /* 10 */ "Uso incorrecto: el identificador {token1} no es una rutina",
        /* 11 */ "Uso incorrecto: la rutina no se puede llamar con los tipos dados. Se recibió {token1} y se esperaba {token2}",
        /* 12 */ "{token1} no posee valor de retorno",
        /* 13 */ "La definición de la rutina {token1} no coincide con su protoipo",
        /* 14 */ "Inicio ilegal de expresión: la sentencia reditus solo puede usarse dentro de funciones",
        /* 15 */ "La función {token1} debe tener al menos una sentencia de retorno",
        /* 16 */ "Se detectó mas de una sentencia detrimentum",
        /* 17 */ "Inicio ilegal de expresión: la sentencia neco solo debe usarse en: aeger, panis, tempus, itero, decusis y procedimientos",
        /* 18 */ "Inicio ilegal de expresión: la sentencia pergo solo debe usarse en: panis, tempus, itero y decusis",
        /* 19 */ "Inicio ilegal de expresión: la sentencia revello solo debe usarse en: probare"
    };
    private static String getError(int code, int row, int col){
        return "SM-"+Math.abs(code)+": "+ERRORS[Math.abs(code)-1] + ", en la línea " + row + " columna "+col;
    }
    public static String getError(int code, int row, int col, String tkn1){
        String error = getError(code, row, col);
        return error.replace("{token1}", tkn1);
    }
    public static String getError(int code, int row, int col, String tkn1, String tkn2){
        String error = getError(code, row, col);
        return error.replace("{token1}", tkn1).replace("{token2}", tkn2);
    }
    public static String getError(int code, int row, int col, String... tokens){
        String error = getError(code, row, col);
        for(int i=0;i<tokens.length;i++)
            error = error.replace("{token"+(i+1)+"}", tokens[i]);
        return error;
    }
}
