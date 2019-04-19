/*
 * GTablaParsinga.java
 *
 * 2019/04/16 19:01:14
 *
 * Archivo generado por GikGram 2.0
 *
 * Copyright � Olminsky 2011 Derechos reservados
 * Reproducci�n sin fines de lucro permitida
 */

package parser.grammar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase contiene la tabla de parsing
 * y los m�todos necesarios para acceder a ella
 */
public abstract class GTablaParsinga
{
        static int table[][]=null;
	/**
	 * Tabla de parsing
	 * Contiene los n�meros de regla que hay que ejecutar
	 * con base a los no-terminales (filas) y los terminales (columnas)
	 */
	private static int[][] TablaParsing()
	{
            String str="";
            int parsing[][]=null;
            try {
                String contents = new String(Files.readAllBytes(Paths.get("parsing.txt")));
                String[] lines = contents.split("\n");
                int cols = lines[0].split(",").length-1;
                parsing = new int[lines.length][cols];
                
                for(int i=0;i<lines.length;i++){
                    String[] line = lines[i].split(",");
                    for(int j=0;j<line.length-1;j++){
                        str = line[j].replace("\r", "");
                        if(str.isEmpty())
                            break;
                        parsing[i][j]=Integer.parseInt(str);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return parsing;
        }

	/**
	 * M�todo getTablaParsing
			Devuelve el n�mero de regla contenida en la tabla de parsing
	 * @param numNoTerminal
			N�mero del no-terminal
	 * @param numTerminal
			N�mero del terminal
	 */
	static final int getTablaParsing(int numNoTerminal, int numTerminal)
	{
            if(table==null)
                table = TablaParsing();
            return table[numNoTerminal][numTerminal];
	}
}
