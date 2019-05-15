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
	public static final int MAX_RIGHT_SIDE = 15;

	/**
	 * Constante que contiene el n�mero m�ximo de follows
	 */
	public static final int MAX_FOLLOWS = 53;
        
        
        public static final int RESET = 231;
	public static final int CONS_TYPE = 232;
	public static final int CONS_DEF = 233;
	public static final int CONS_LIT = 234;
	public static final int TYPE_DEF = 235;
	public static final int TYPE_NORM = 236;
	public static final int REC_TYPE = 237;
	public static final int REC_ID = 238;
	public static final int REC_END = 239;
	public static final int VAR_TYPE = 240;
	public static final int VAR_DEF = 241;
	public static final int ARR_DEF = 242;
	public static final int ARR_SIZE = 243;
	public static final int ARR_TYPE = 244;
	public static final int ARR_VAR = 245;
	public static final int VAR_LIT = 246;
	public static final int PROT_FUNC = 247;
	public static final int FUNC_RET = 248;
	public static final int PROT_PROC = 249;
	public static final int PARAM_TYPE = 250;
	public static final int PARAM_DEF = 251;
	public static final int REF_PARAM = 252;
	public static final int BODY_PROC = 253;
	public static final int FUNC_FLAGS_ON = 254;
	public static final int FUNC_FLAGS_OFF = 255;
	public static final int PROC_FLAGS_ON = 256;
	public static final int PROC_FLAGS_OFF = 257;
	public static final int REG_LIT_OPEN = 258;
	public static final int REG_LIT = 259;
	public static final int REG_LIT_END = 260;
	public static final int ARR_LIT = 261;
	public static final int ARR_LIT_END = 262;
	public static final int ATTR_NEXT = 263;
	public static final int POP_NUMERUS = 264;
	public static final int ADD_ACCESS = 265;
	public static final int ID_STACK = 266;
	public static final int ID_STACK_PUT = 267;
	public static final int LIT_STACK = 268;
	public static final int LIT_STACK_PUT = 269;
	public static final int CHECK_ARRAY = 270;
	public static final int CHECK_PROC = 271;
	public static final int CHECK_PARAM = 272;
	public static final int POP_PARAM = 273;
	public static final int SAVE_POS = 274;
	public static final int PUSH_NUMERUS = 275;
	public static final int POP_DUALIS = 276;
	public static final int PUSH_DUALIS = 277;
	public static final int POP_IMAGO = 278;
	public static final int PUSH_IMAGO = 279;
	public static final int POP_CATENA = 280;
	public static final int POP_FRACTIO = 281;
	public static final int POP_GREGORIUS = 282;
	public static final int PUSH_GREGORIUS = 283;
	public static final int POP_LIBER = 284;
	public static final int PUSH_CATENA = 285;
	public static final int PUSH_LIBER = 286;
	public static final int PUSH_FRACTIO = 287;
	public static final int POP_TYPE = 288;
	public static final int LOOP_FLAGS_ON = 289;
	public static final int LOOP_FLAGS_OFF = 290;
	public static final int CHECK_FOR_ID = 291;
	public static final int POP_ARRAY = 292;
	public static final int CHECK_BREAK = 293;
	public static final int CHECK_CONTINUE = 294;
	public static final int CHECK_RETURN = 295;
	public static final int POP_RETURN = 296;
	public static final int CHECK_REVELLO = 297;
	public static final int PUSH_DEFAULT = 298;
	public static final int POP_DEFAULT = 299;
	public static final int CHECK_DEFAULT = 300;
	public static final int PUSH_ID_SIGLA = 301;
	public static final int POP_ID_SIGLA = 302;
	public static final int TRY_FLAGS_ON = 303;
	public static final int TRY_GLAGS_OFF = 304;
        
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
            return ((231 <= numSimbolo) && (numSimbolo <= 304));
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
