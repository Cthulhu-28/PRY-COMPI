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
	public static final int MAX_RIGHT_SIDE = 16;

	/**
	 * Constante que contiene el n�mero m�ximo de follows
	 */
	public static final int MAX_FOLLOWS = 65;
        
        
        public static final int CG_INIT = 231;
	public static final int CG_DATA_SEGMENT = 232;
	public static final int RESET = 233;
	public static final int CG_CODE_SEG = 234;
	public static final int CG_CLOSE = 235;
	public static final int CONS_TYPE = 236;
	public static final int CONS_DEF = 237;
	public static final int CONS_LIT = 238;
	public static final int CG_WRITE_VAR = 239;
	public static final int TYPE_DEF = 240;
	public static final int TYPE_NORM = 241;
	public static final int REC_TYPE = 242;
	public static final int REC_ID = 243;
	public static final int REC_END = 244;
	public static final int VAR_TYPE = 245;
	public static final int VAR_DEF = 246;
	public static final int ARR_DEF = 247;
	public static final int ARR_SIZE = 248;
	public static final int ARR_TYPE = 249;
	public static final int ARR_VAR = 250;
	public static final int VAR_LIT = 251;
	public static final int PROT_FUNC = 252;
	public static final int FUNC_RET = 253;
	public static final int PROT_PROC = 254;
	public static final int PARAM_TYPE = 255;
	public static final int PARAM_DEF = 256;
	public static final int REF_PARAM = 257;
	public static final int BODY_PROC = 258;
	public static final int FUNC_FLAGS_ON = 259;
	public static final int FUNC_FLAGS_OFF = 260;
	public static final int PROC_FLAGS_ON = 261;
	public static final int PROC_FLAGS_OFF = 262;
	public static final int REG_LIT_OPEN = 263;
	public static final int REG_LIT = 264;
	public static final int REG_LIT_END = 265;
	public static final int ARR_LIT = 266;
	public static final int ARR_LIT_END = 267;
	public static final int ATTR_NEXT = 268;
	public static final int POP_NUMERUS = 269;
	public static final int ADD_ACCESS = 270;
	public static final int ID_STACK = 271;
	public static final int ID_STACK_PUT = 272;
	public static final int CG_WRITE_STACK = 273;
	public static final int LIT_STACK = 274;
	public static final int LIT_STACK_PUT = 275;
	public static final int CG_WRITE_LIT = 276;
	public static final int CHECK_ARRAY = 277;
	public static final int CHECK_PROC = 278;
	public static final int CHECK_PARAM = 279;
	public static final int POP_PARAM = 280;
	public static final int SAVE_POS = 281;
	public static final int PUSH_NUMERUS = 282;
	public static final int CG_INC = 283;
	public static final int CG_DEC = 284;
	public static final int POP_DUALIS = 285;
	public static final int PUSH_DUALIS = 286;
	public static final int CG_NON = 287;
	public static final int POP_IMAGO = 288;
	public static final int PUSH_IMAGO = 289;
	public static final int CG_UPPER = 290;
	public static final int CG_LOWER = 291;
	public static final int CG_ALPHA = 292;
	public static final int CG_DIGIT = 293;
	public static final int POP_CATENA = 294;
	public static final int CG_LENGTH = 295;
	public static final int POP_FRACTIO = 296;
	public static final int CG_FLOOR = 297;
	public static final int CG_ROUND = 298;
	public static final int POP_GREGORIUS = 299;
	public static final int CG_ANNUS = 300;
	public static final int CG_MENSIS = 301;
	public static final int CG_DIES = 302;
	public static final int PUSH_GREGORIUS = 303;
	public static final int CG_NUNCS = 304;
	public static final int CG_DAY = 305;
	public static final int CG_WEEK = 306;
	public static final int CG_LEAP = 307;
	public static final int POP_LIBER = 308;
	public static final int PUSH_CATENA = 309;
	public static final int PUSH_LIBER = 310;
	public static final int CG_CONCAT = 311;
	public static final int CG_CONTAINS = 312;
	public static final int CG_MULT = 313;
	public static final int CG_DIV = 314;
	public static final int CG_MOD = 315;
	public static final int PUSH_FRACTIO = 316;
	public static final int CG_FRAC_MUL = 317;
	public static final int GG_FRAC_DIV = 318;
	public static final int CG_PLUS = 319;
	public static final int CG_SUB = 320;
	public static final int CG_FRAC_PLUS = 321;
	public static final int CG_FRAC_MINUS = 322;
	public static final int CG_DATE_ADD = 323;
	public static final int CG_DATE_MINU = 324;
	public static final int CG_LESS = 325;
	public static final int CG_GREATER = 326;
	public static final int CG_GREATER_EQ = 327;
	public static final int CG_LESS_EQ = 328;
	public static final int CG_EQ = 329;
	public static final int CG_DIFF = 330;
	public static final int CG_AND = 331;
	public static final int CG_XOR = 332;
	public static final int CG_OR = 333;
	public static final int CG_SET_YEAR = 334;
	public static final int CG_SET_MONTH = 335;
	public static final int CG_SET_DAY = 336;
	public static final int CHECK_IS_MEMORY = 337;
	public static final int POP_TYPE = 338;
	public static final int LOOP_FLAGS_ON = 339;
	public static final int LOOP_FLAGS_OFF = 340;
	public static final int CHECK_FOR_ID = 341;
	public static final int POP_ARRAY = 342;
	public static final int CHECK_BREAK = 343;
	public static final int CHECK_CONTINUE = 344;
	public static final int CHECK_RETURN = 345;
	public static final int POP_RETURN = 346;
	public static final int CHECK_REVELLO = 347;
	public static final int PUSH_DEFAULT = 348;
	public static final int POP_DEFAULT = 349;
	public static final int CHECK_DEFAULT = 350;
	public static final int PUSH_ID_SIGLA = 351;
	public static final int POP_ID_SIGLA = 352;
	public static final int TRY_FLAGS_ON = 353;
	public static final int TRY_GLAGS_OFF = 354;
        
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
            return ((231 <= numSimbolo) && (numSimbolo <= 354));
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
