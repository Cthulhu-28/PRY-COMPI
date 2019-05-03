/*
 * Grammar.java
 *
 * 2019/04/18 10:55:45
 *
 * Archivo generado por GikGram 2.0
 *
 * Copyright � Olminsky 2011 Derechos reservados
 * Reproducci�n sin fines de lucro permitida
 */

package parser.grammar;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;

/**
 * Esta clase contiene:
 * - Constantes necesarias para el driver de parsing
 * - Constantes con las rutinas sem�nticas
 * - Y los m�todos necesarios para el driver de parsing
 */
public abstract class Grammar
{
        private static HashSet<Integer> set = new HashSet<>(Arrays.asList(new Integer[]{124,139,109,80,116,127,74,132,134,106,63,129, 23}));
	/* Esta es la �nica clase que se accede fuera del paquete Grammar */

	/**
	 * Constante que contiene el c�digo de familia del terminal de fin de archivo
	 */
	public static final int END_MARKER = 143;

	/**
	 * Constante que contiene el n�mero del no-terminal inicial
	 * (el primer no-terminal que aparece en la gram�tica)
	 */
	public static final int INITIAL_NON_TERMINAL = 144;

	/**
	 * Constante que contiene el n�mero m�ximo de columnas que tiene los lados derechos
	 */
	public static final int MAX_RIGHT_SIDE = 11;

	/**
	 * Constante que contiene el n�mero m�ximo de follows
	 */
	public static final int MAX_FOLLOWS = 110;
        
        
        public static final int CONS_TYPE = 231;
	public static final int CONS_DEF = 232;
	public static final int CONS_LIT = 233;
        public static final int TYPE_DEF = 234;
	public static final int TYPE_NORM = 235;
	public static final int REC_TYPE = 236;
	public static final int REC_ID = 237;
	public static final int REC_END = 238;
        public static final int VAR_TYPE = 239;
	public static final int VAR_DEF = 240;
	public static final int VAR_LIT = 241;
        public static final int REG_LIT = 242;
	public static final int REG_LIT_END = 243;
        
        public static final int LIST_STATEMENT = 217;
        public static final int IMPONO_EXPRESSION = 210;

	/* Constantes con las rutinas sem�nticas */
	/* NO SE DETECTARON S�MBOLOS SEM�NTICOS EN LA GRAM�TICA */

	/**
	 * M�todo isTerminal
			Devuelve true si el s�mbolo es un terminal
			o false de lo contrario
	 * @param numSimbolo
			N�mero de s�mbolo
	 */
	public static final boolean isTerminal(int numSimbolo)
	{
		return ((0 <= numSimbolo) && (numSimbolo <= 143));
	}

	/**
	 * M�todo isNonTerminal
			Devuelve true si el s�mbolo es un no-terminal
			o false de lo contrario
	 * @param numSimbolo
			N�mero de s�mbolo
	 */
	public static final boolean isNonTerminal(int numSimbolo)
	{
		return ((144 <= numSimbolo) && (numSimbolo <= 230));
	}

	/**
	 * M�todo isSemanticSymbol
			Devuelve true si el s�mbolo es un s�mbolo sem�ntico
			(incluyendo los s�mbolos de generaci�n de c�digo)
			o false de lo contrario
	 * @param numSimbolo
			N�mero de s�mbolo
	 */
	public static final boolean isSemanticSymbol(int numSimbolo)
	{
		return ((231 <= numSimbolo) && (numSimbolo <= 243));
	}

	/**
	 * M�todo getParsingTable
			Devuelve el n�mero de regla contenida en la tabla de parsing
	 * @param numNoTerminal
			N�mero del no-terminal
	 * @param numTerminal
			N�mero del terminal
	 */
	public static final int getParsingTable(int numNoTerminal, int numTerminal)
	{
            return ParsingTable.getParsingTable(numNoTerminal, numTerminal);

		
	}

	/**
	 * M�todo getRightSides
			Obtiene un s�mbolo del lado derecho de la regla
	 * @param numRegla
			N�mero de regla
	 * @param numColumna
			N�mero de columna
	 */
	public static final int getRightSides(int numRegla, int numColumna)
	{
		return RightSides.getRightSides(numRegla, numColumna);
	}

	/**
	 * M�todo getTerminalName
			Obtiene el nombre del terminal
	 * @param numTerminal
			N�mero del terminal
	 */
	public static final String getTerminalName(int numTerminal)
	{
		return TerminalsName.getTerminalsName(numTerminal);
	}

	/**
	 * M�todo getFollowsTable
			Obtiene el n�mero de terminal del follow del no-terminal
	 * @param numNoTerminal
			N�mero de no-terminal
	 * @param numColumna
			N�mero de columna
	 */
	public static final int getFollowsTable(int numNoTerminal, int numColumna)
	{
		return FollowsTable.getFollowsTable(numNoTerminal, numColumna);
	}
        public static boolean isSynchToken(int noTerminal, int terminal){
            if(noTerminal==173)
                System.out.println("");
            return IntStream.of(FollowsTable.getFollows(noTerminal)).anyMatch( x -> x==terminal) || FirstsTable.getFirsts(noTerminal, terminal);
        }
        public static boolean isSynchTokenOfExpression(int nonTerminal, int terminal){
            
            return nonTerminal<=72 && nonTerminal>=27 && (set.contains(terminal) || FirstsTable.getFirsts(LIST_STATEMENT-INITIAL_NON_TERMINAL, terminal));
        }
        
}
