/*
 * TerminalsName.java
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
 * Esta clase contiene los nombres de los terminales
 * y los m�todos necesarios para acceder a ella
 */
abstract class TerminalsName
{
	/**
	 * Contiene los nombres de los terminales
	 */
	private static final String[] TERMINAL_NAMES =
	{
		"-",
		"$-",
		"$?",
		"$+",
		"$annus",
		"$annus=",
		"$d",
		"$dies",
		"$dies=",
		"$mensis",
		"$mensis=",
		"$n",
		"%",
		"%=",
		"&",
		"&#",
		"&?",
		"&+",
		"(",
		")",
		"*",
		"*=",
		",",
		".",
		"/",
		"/=",
		":",
		":=",
		"@",
		"@@",
		"[",
		"[#?]",
		"[&?]",
		"[<<]",
		"[>>]",
		"]",
		"{",
		"|-",
		"|*",
		"|*=",
		"|/",
		"|/=",
		"|^",
		"|^=",
		"||",
		"||=",
		"|+",
		"|+=",
		"|<",
		"|-=",
		"|>",
		"}",
		"+",
		"+=",
		"<",
		"<-",
		"<=",
		"=",
		"-=",
		">",
		"->",
		"><",
		">=",
		"aeger",
		"annus",
		"auctum",
		"aut",
		"aut=",
		"autem",
		"captare",
		"casus",
		"catena",
		"catena_lit",
		"certus",
		"claudeo",
		"commutabilis",
		"coniugo",
		"corpus",
		"cum",
		"decrementum",
		"decusis",
		"detrimentum",
		"dies",
		"directus",
		"dito",
		"dixi",
		"dualis",
		"dualis_lit",
		"efficio",
		"est",
		"et",
		"et=",
		"exemplar",
		"finis",
		"firmamentum",
		"fractio",
		"fractio_lit",
		"furibundus",
		"gradus",
		"gregorius",
		"gregorius_lit",
		"IDENTIFICADOR",
		"ign",
		"imago",
		"imago_lit",
		"impono",
		"in",
		"incrementum",
		"initum",
		"itero",
		"lectio",
		"lego",
		"liber",
		"ligo",
		"mensis",
		"mentiri",
		"neco",
		"nomen",
		"non",
		"numerus",
		"numerus_lit",
		"nunc",
		"opus",
		"ordo",
		"panis",
		"patentibus",
		"per",
		"pergo",
		"perpetus",
		"probare",
		"prope",
		"quantus",
		"reditus",
		"rescribo",
		"revello",
		"scribo",
		"scripturam",
		"sigla",
		"sum",
		"tempus",
		"usque",
		"xaut",
		"xaut=",
		" EOF "
	};

	/**
	 * M�todo getTerminalsName
			Obtiene el nombre del terminal
	 * @param terminal
			N�mero del terminal
	 */
	static final String getTerminalsName(int terminal)
	{
		return TERMINAL_NAMES[terminal];
	}
}
