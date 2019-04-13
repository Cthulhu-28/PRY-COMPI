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
        if(tokenCode<146)
            return color[tokenCode].replace("#", ".c");
        return ".error";
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
            if(str.equals("#3329f3") || str.equals("#3b3838") || str.equals("#958445")){
                builder.append("font-weight: bold;");
            }
            builder.append("}\n");
        }
        builder.append("\nbody{margin:0;\nfont-family: \"Droid Sans Mono\"; font-size:12px;}");
        builder.append("\ntable,tr,td{margin:0;\nfont-family: \"Droid Sans Mono\"; font-size:12px;border: 1px solid #cccccc;\nborder-collapse:collapse;\ntext-align: center;width:20%}");
        return builder.toString();
    }
    public static <String> Set<String> castToSet (String[] array){
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(array));
        return set;
        
    }
    private static final String[] color = new String[]{
                                    /*0*/ "#8d34ca",
                                    /*1*/ "#00b050",
                                    /*2*/ "#00b050",
                                    /*3*/ "#00b050",
                                    /*4*/ "#00b050",
                                    /*5*/ "#00b050",
                                    /*6*/ "#00b050",
                                    /*7*/ "#00b050",
                                    /*8*/ "#00b050",
                                    /*9*/ "#00b050",
                                    /*10*/ "#00b050",
                                    /*11*/ "#00b050",
                                    /*12*/ "#8d34ca",
                                    /*13*/ "#8d34ca",
                                    /*14*/ "#ffa021",
                                    /*15*/ "#ffa021",
                                    /*16*/ "#ffa021",
                                    /*17*/ "#ffa021",
                                    /*18*/ "#bc70b1",
                                    /*19*/ "#bc70b1",
                                    /*20*/ "#8d34ca",
                                    /*21*/ "#8d34ca",
                                    /*22*/ "#c45911",
                                    /*23*/ "#bc70b1",
                                    /*24*/ "#8d34ca",
                                    /*25*/ "#8d34ca",
                                    /*26*/ "#bc70b1",
                                    /*27*/ "#bc70b1",
                                    /*28*/ "#bc70b1",
                                    /*29*/ "#bc70b1",
                                    /*30*/ "#bc70b1",
                                    /*31*/ "#006c00",
                                    /*32*/ "#006c00",
                                    /*33*/ "#006c00",
                                    /*34*/ "#006c00",
                                    /*35*/ "#bc70b1",
                                    /*36*/ "#bc70b1",
                                    /*37*/ "#a84679",
                                    /*38*/ "#a84679",
                                    /*39*/ "#a84679",
                                    /*40*/ "#a84679",
                                    /*41*/ "#a84679",
                                    /*42*/ "#a84679",
                                    /*43*/ "#a84679",
                                    /*44*/ "#a84679",
                                    /*45*/ "#a84679",
                                    /*46*/ "#a84679",
                                    /*47*/ "#a84679",
                                    /*48*/ "#ffa021",
                                    /*49*/ "#a84679",
                                    /*50*/ "#ffa021",
                                    /*51*/ "#bc70b1",
                                    /*52*/ "#8d34ca",
                                    /*53*/ "#8d34ca",
                                    /*54*/ "#354259",
                                    /*55*/ "#bc70b1",
                                    /*56*/ "#354259",
                                    /*57*/ "#354259",
                                    /*58*/ "#8d34ca",
                                    /*59*/ "#354259",
                                    /*60*/ "#bc70b1",
                                    /*61*/ "#354259",
                                    /*62*/ "#354259",
                                    /*63*/ "#3329f3",
                                    /*64*/ "#00b050",
                                    /*65*/ "#3329f3",
                                    /*66*/ "#354259",
                                    /*67*/ "#354259",
                                    /*68*/ "#3329f3",
                                    /*69*/ "#3329f3",
                                    /*70*/ "#3329f3",
                                    /*71*/ "#a80000",
                                    /*72*/ "#dd4f8f",
                                    /*73*/ "#3329f3",
                                    /*74*/ "#3329f3",
                                    /*75*/ "#3b3838",
                                    /*76*/ "#a80000",
                                    /*77*/ "#3b3838",
                                    /*78*/ "#3329f3",
                                    /*79*/ "#8d34ca",
                                    /*80*/ "#3329f3",
                                    /*81*/ "#3329f3",
                                    /*82*/ "#00b050",
                                    /*83*/ "#3329f3",
                                    /*84*/ "#958445",
                                    /*85*/ "#c75151",
                                    /*86*/ "#a80000",
                                    /*87*/ "#6a283c",
                                    /*88*/ "#3329f3",
                                    /*89*/ "#958445",
                                    /*90*/ "#354259",
                                    /*91*/ "#354259",
                                    /*92*/ "#3b3838",
                                    /*93*/ "#c75151",
                                    /*94*/ "#3b3838",
                                    /*95*/ "#a80000",
                                    /*96*/ "#0063da",
                                    /*97*/ "#3b3838",
                                    /*98*/ "#3329f3",
                                    /*99*/ "#a80000",
                                    /*100*/ "#39ffac",
                                    /*101*/ "#000000",
                                    /*102*/ "#3329f3",
                                    /*103*/ "#a80000",
                                    /*104*/ "#70ad47",
                                    /*105*/ "#3329f3",
                                    /*106*/ "#3329f3",
                                    /*107*/ "#8d34ca",
                                    /*108*/ "#c75151",
                                    /*109*/ "#3329f3",
                                    /*110*/ "#eac272",
                                    /*111*/ "#b2d000",
                                    /*112*/ "#a80000",
                                    /*113*/ "#eac272",
                                    /*114*/ "#00b050",
                                    /*115*/ "#00b050",
                                    /*116*/ "#3329f3",
                                    /*117*/ "#3b3838",
                                    /*118*/ "#354259",
                                    /*119*/ "#a80000",
                                    /*120*/ "#00ff00",
                                    /*121*/ "#00b050",
                                    /*122*/ "#c75151",
                                    /*123*/ "#a80000",
                                    /*124*/ "#3329f3",
                                    /*125*/ "#eac272",
                                    /*126*/ "#3329f3",
                                    /*127*/ "#3329f3",
                                    /*128*/ "#3b3838",
                                    /*129*/ "#3329f3",
                                    /*130*/ "#eac272",
                                    /*131*/ "#3329f3",
                                    /*132*/ "#3329f3",
                                    /*133*/ "#b2d000",
                                    /*134*/ "#3329f3",
                                    /*135*/ "#b2d000",
                                    /*136*/ "#eac272",
                                    /*137*/ "#3329f3",
                                    /*138*/ "#958445",
                                    /*139*/ "#3329f3",
                                    /*140*/ "#3329f3",
                                    /*141*/ "#354259",
                                    /*142*/ "#354259",
                                    /*143*/ "#ffffff",
                                    /*144*/ "#c0c0c0",
                                    /*145*/ "#c0c0c0"
                                    };
}
