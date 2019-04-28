/*
 * RightSides.java
 *
 * 2019/04/18 10:55:45
 *
 * Archivo generado por GikGram 2.0
 *
 * Copyright � Olminsky 2011 Derechos reservados
 * Reproducci�n sin fines de lucro permitida
 */

package parser.grammar;

/**
 * Esta clase contiene la tabla de lados derechos
 * y los m�todos necesarios para acceder a ella
 */
abstract class RightSides
{
	/**
	 * Tabla de lados derechos
	 * Contiene el lado derecho de las reglas de la gram�tica
	 */
	private static final int[][] RIGHT_SIDES =
	{
		{217,94,163,157,151,148,145,23,101,117,-1},
		{146,128,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{146,23,147,167,84,101,166,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{167,84,101,22,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{149,97,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{149,23,150,138,101,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{166,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{85,155,23,101,166,76,-1,-1,-1,-1,-1},
		{152,75,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{152,23,153,154,101,166,-1,-1,-1,-1,-1},
		{152,23,153,154,101,85,155,23,101,166,76},
		{152,23,154,101,166,68,156,35,120,30,123},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{153,154,101,22,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{167,89,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{155,23,101,166,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{156,35,120,30,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{158,92,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
/*HERE*/		{158,23,166,26,19,159,18,101,88,-1,-1},
		{158,23,19,159,18,101,83,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{160,161,101,166,162,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{160,161,101,166,162,23,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{161,101,22,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{102,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{164,77,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
	/*here*/	{164,165,23,166,26,19,159,18,101,88,-1},
		{164,165,23,19,159,18,101,83,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{93,217,108,151,-1,-1,-1,-1,-1,-1,-1},
		{119,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{103,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{71,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{86,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{112,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{95,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{99,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{101,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{120,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{104,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{72,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{87,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{96,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{168,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{169,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{100,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{60,170,167,55,-1,-1,-1,-1,-1,-1,-1},
		{51,170,167,36,-1,-1,-1,-1,-1,-1,-1},
		{170,167,22,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{171,101,28,-1,-1,-1,-1,-1,-1,-1,-1},
		{171,35,207,30,-1,-1,-1,-1,-1,-1,-1},
		{173,101,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{19,210,18,-1,-1,-1,-1,-1,-1,-1,-1},
		{167,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{171,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{171,19,174,18,-1,-1,-1,-1,-1,-1,-1},
		{172,29,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{175,210,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{175,210,22,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{172,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{176,177,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{107,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{79,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{118,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{131,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{34,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{33,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{32,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{31,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{15,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{44,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{42,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{64,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{114,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{82,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{121,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{176,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{178,110,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{178,130,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{180,178,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{179,181,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{17,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{183,179,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{182,184,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{24,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{38,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{40,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{186,182,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{185,187,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{52,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{46,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{37,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{189,185,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{188,190,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{54,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{59,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{62,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{56,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{192,188,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{191,193,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{57,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{61,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{195,191,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{194,90,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{197,194,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{196,141,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{199,196,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{198,66,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{201,198,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{200,202,206,125,-1,-1,-1,-1,-1,-1,-1},
		{200,202,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{113,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{136,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{204,200,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{203,205,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{133,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{111,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{135,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{208,203,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{207,209,207,14,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{50,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{48,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{211,207,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{166,105,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{213,210,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{210,214,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{53,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{21,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{58,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{25,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{13,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{47,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{49,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{45,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{43,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{41,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{39,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{91,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{142,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{67,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{27,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{5,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{23,212,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{215,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{221,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{219,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{23,220,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{229,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{230,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{217,216,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{93,217,108,-1,-1,-1,-1,-1,-1,-1,-1},
		{216,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{218,122,207,98,207,65,207,27,101,124,-1},
		{218,122,207,139,-1,-1,-1,-1,-1,-1,-1},
		{23,207,140,218,109,-1,-1,-1,-1,-1,-1},
		{218,122,101,126,207,78,207,80,-1,-1,-1},
		{116,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{127,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{74,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{207,132,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{134,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{23,222,207,106,-1,-1,-1,-1,-1,-1,-1},
		{93,228,227,217,122,167,70,108,207,63,-1},
		{223,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{225,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{224,218,73,-1,-1,-1,-1,-1,-1,-1,-1},
		{218,115,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{226,218,115,-1,-1,-1,-1,-1,-1,-1,-1},
		{218,73,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
	/*here*/	{227,217,122,167,70,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{217,81,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{85,217,122,101,137,-1,-1,-1,-1,-1,-1},
		{218,69,218,129,-1,-1,-1,-1,-1,-1,-1}
	};

	/**
	 * M�todo getRightSides
			Obtiene un s�mbolo del lado derecho de la regla
	 * @param numRule
			N�mero de regla
	 * @param column
			N�mero de columna
	 */
	static final int getRightSides(int numRule, int column)
	{
            return RIGHT_SIDES[numRule][column];  
	}
}
