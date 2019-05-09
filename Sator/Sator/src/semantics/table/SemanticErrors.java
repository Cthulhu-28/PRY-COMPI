/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.table;

import parser.*;

/**
 *
 * @author admin
 */
public class SemanticErrors {
    static final String[] ERRORS = {
        /* 1  */ "Tipo {token} desconocido",
        /* 2  */ "El identificador {token} ya se encuentra definido",
        /* 3  */ "Tipos incompatibles: {token1} no puede convertirse a {token2}",
        /* 4  */ "El atributo {token} ya estaba definido",
        /* 5  */ "La dimensión del arreglo debe ser positiva",
        /* 6  */ "Arreglo inválido: se esparaba un arreglo de tipo {token}"
    };
    private static String getError(int code, int row, int col){
        return "SM-"+Math.abs(code)+": "+ERRORS[Math.abs(code)-1] + " en la línea " + row + " columna "+col;
    }
    public static String getError(int code, int row, int col, String tkn1){
        String error = getError(code, row, col);
        return error.replace("{token}", tkn1);
    }
    public static String getError(int code, int row, int col, String tkn1, String tkn2){
        String error = getError(code, row, col);
        return error.replace("{token1}", tkn1).replace("{token2}", tkn2);
    }
}
