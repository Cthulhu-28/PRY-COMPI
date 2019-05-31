/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import generator.FileGenerator;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import generator.LiteralWriter;
import semantics.literals.ArrayLiteral;
import semantics.literals.SimpleLiteral;
import semantics.identifiers.Type;
import semantics.literals.RecordLiteral;

/**
 *
 * @author admin
 */
public class Test {
    public static void main(String args[]) throws FileNotFoundException{
        System.out.println(LiteralWriter.toFractio("1|0xAB"));
    }
    
    public static boolean t1(List<Integer> arr){
        return arr.stream().allMatch(i-> i == 1);
    }
    public static boolean t2(List<Integer> arr){
        boolean b = true;
        for(Integer i : arr){
            b = i == 1 && b;
        }
        return b;
    }

}
