/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author admin
 */
public class Color {

    public static String getCssClass(int tokenCode){
        return color[tokenCode].replace("#", ".c");
    }
    public static String getColor(int tokenCode){
        return color[tokenCode];
    }
    public static String getCSS(){
        StringBuilder builder = new StringBuilder();
        for(String str : castToSet(color)){
            builder.append(".c");
            builder.append(str.replace("#", ""));
            builder.append("{");
            builder.append("color: ");
            builder.append(str);
            builder.append(";");
            builder.append("}\n");
        }
        return builder.toString();
    }
    public static <String> Set<String> castToSet (String[] array){
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(array));
        return set;
        
    }
    private static String[] color = new String[]{
                                    /*0*/ "#8d34ca",
                                    /*1*/ "#8d34ca",
                                    /*2*/ "#8d34ca",
                                    /*3*/ "#8d34ca",
                                    /*4*/ "#8d34ca",
                                    /*5*/ "#8d34ca",
                                    /*6*/ "#8d34ca",
                                    /*7*/ "#8d34ca",
                                    /*8*/ "#8d34ca",
                                    /*9*/ "#8d34ca",
                                    /*10*/ "#8d34ca",
                                    /*11*/ "#8d34ca",
                                    /*12*/ "#8d34ca",
                                    /*13*/ "#8d34ca",
                                    /*14*/ "#8d34ca",
                                    /*15*/ "#8d34ca",
                                    /*16*/ "#8d34ca",
                                    /*17*/ "#8d34ca",
                                    /*18*/ "#8d34ca",
                                    /*19*/ "#8d34ca",
                                    /*20*/ "#8d34ca",
                                    /*21*/ "#8d34ca",
                                    /*22*/ "#8d34ca",
                                    /*23*/ "#8d34ca",
                                    /*24*/ "#8d34ca",
                                    /*25*/ "#8d34ca",
                                    /*26*/ "#8d34ca",
                                    /*27*/ "#8d34ca",
                                    /*28*/ "#8d34ca",
                                    /*29*/ "#8d34ca",
                                    /*30*/ "#8d34ca",
                                    /*31*/ "#8d34ca",
                                    /*32*/ "#8d34ca",
                                    /*33*/ "#8d34ca",
                                    /*34*/ "#8d34ca",
                                    /*35*/ "#8d34ca",
                                    /*36*/ "#8d34ca",
                                    /*37*/ "#8d34ca",
                                    /*38*/ "#8d34ca",
                                    /*39*/ "#8d34ca",
                                    /*40*/ "#8d34ca",
                                    /*41*/ "#8d34ca",
                                    /*42*/ "#8d34ca",
                                    /*43*/ "#8d34ca",
                                    /*44*/ "#8d34ca",
                                    /*45*/ "#8d34ca",
                                    /*46*/ "#8d34ca",
                                    /*47*/ "#8d34ca",
                                    /*48*/ "#8d34ca",
                                    /*49*/ "#8d34ca",
                                    /*50*/ "#8d34ca",
                                    /*51*/ "#8d34ca",
                                    /*52*/ "#8d34ca",
                                    /*53*/ "#8d34ca",
                                    /*54*/ "#8d34ca",
                                    /*55*/ "#8d34ca",
                                    /*56*/ "#8d34ca",
                                    /*57*/ "#8d34ca",
                                    /*58*/ "#8d34ca",
                                    /*59*/ "#8d34ca",
                                    /*60*/ "#8d34ca",
                                    /*61*/ "#8d34ca",
                                    /*62*/ "#8d34ca",
                                    /*63*/ "#8d34ca",
                                    /*64*/ "#8d34ca",
                                    /*65*/ "#8d34ca",
                                    /*66*/ "#8d34ca",
                                    /*67*/ "#8d34ca",
                                    /*68*/ "#8d34ca",
                                    /*69*/ "#8d34ca",
                                    /*70*/ "#8d34ca",
                                    /*71*/ "#8d34ca",
                                    /*72*/ "#8d34ca",
                                    /*73*/ "#8d34ca",
                                    /*74*/ "#8d34ca",
                                    /*75*/ "#8d34ca",
                                    /*76*/ "#8d34ca",
                                    /*77*/ "#8d34ca",
                                    /*78*/ "#8d34ca",
                                    /*79*/ "#8d34ca",
                                    /*80*/ "#8d34ca",
                                    /*81*/ "#8d34ca",
                                    /*82*/ "#8d34ca",
                                    /*83*/ "#8d34ca",
                                    /*84*/ "#8d34ca",
                                    /*85*/ "#8d34ca",
                                    /*86*/ "#8d34ca",
                                    /*87*/ "#8d34ca",
                                    /*88*/ "#8d34ca",
                                    /*89*/ "#8d34ca",
                                    /*90*/ "#8d34ca",
                                    /*91*/ "#8d34ca",
                                    /*92*/ "#8d34ca",
                                    /*93*/ "#8d34ca",
                                    /*94*/ "#8d34ca",
                                    /*95*/ "#8d34ca",
                                    /*96*/ "#8d34ca",
                                    /*97*/ "#8d34ca",
                                    /*98*/ "#8d34ca",
                                    /*99*/ "#8d34ca",
                                    /*100*/ "#8d34ca",
                                    /*101*/ "#8d34ca",
                                    /*102*/ "#8d34ca",
                                    /*103*/ "#8d34ca",
                                    /*104*/ "#8d34ca",
                                    /*105*/ "#8d34ca",
                                    /*106*/ "#8d34ca",
                                    /*107*/ "#8d34ca",
                                    /*108*/ "#8d34ca",
                                    /*109*/ "#8d34ca",
                                    /*110*/ "#8d34ca",
                                    /*111*/ "#8d34ca",
                                    /*112*/ "#8d34ca",
                                    /*113*/ "#8d34ca",
                                    /*114*/ "#8d34ca",
                                    /*115*/ "#8d34ca",
                                    /*116*/ "#8d34ca",
                                    /*117*/ "#8d34ca",
                                    /*118*/ "#8d34ca",
                                    /*119*/ "#8d34ca",
                                    /*120*/ "#8d34ca",
                                    /*121*/ "#8d34ca",
                                    /*122*/ "#8d34ca",
                                    /*123*/ "#8d34ca",
                                    /*124*/ "#8d34ca",
                                    /*125*/ "#8d34ca",
                                    /*126*/ "#8d34ca",
                                    /*127*/ "#8d34ca",
                                    /*128*/ "#8d34ca",
                                    /*129*/ "#8d34ca",
                                    /*130*/ "#8d34ca",
                                    /*131*/ "#8d34ca",
                                    /*132*/ "#8d34ca",
                                    /*133*/ "#8d34ca",
                                    /*134*/ "#8d34ca",
                                    /*135*/ "#8d34ca",
                                    /*136*/ "#8d34ca",
                                    /*137*/ "#8d34ca",
                                    /*138*/ "#8d34ca",
                                    /*139*/ "#8d34ca",
                                    /*140*/ "#8d34ca",
                                    /*141*/ "#8d34ca",
                                    /*142*/ "#8d34ca" 
                                    };
}
