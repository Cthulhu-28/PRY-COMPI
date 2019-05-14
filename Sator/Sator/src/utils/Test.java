/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;
import semantics.literals.ArrayLiteral;
import semantics.literals.SimpleLiteral;
import semantics.identifiers.Type;

/**
 *
 * @author admin
 */
public class Test {
    public static void main(String args[]){
        Type t1 = new Type(0, "int");
        Type t2 = new Type(1, "intx");
        
        ArrayLiteral array = new ArrayLiteral();
        
        ArrayLiteral array1 = new ArrayLiteral();
        array1.addLiteral(new SimpleLiteral(t1, "1"));
        array1.addLiteral(new SimpleLiteral(t1, "2"));
        array1.addLiteral(new SimpleLiteral(t1, "3"));
        
        
        ArrayLiteral array2 = new ArrayLiteral();
        array2.addLiteral(new SimpleLiteral(t1, "1"));
        array2.addLiteral(new SimpleLiteral(t1, "2"));
        array2.addLiteral(new SimpleLiteral(t1, "3"));
        array2.addLiteral(array1);
        
        array.addLiteral(array1);
        array.addLiteral(array2);

        Type t = array.getType();
        System.out.println(array1.getType());
        System.out.println(array2.getType());
        System.out.println(array1.getType().equals(array2.getType()));
        System.out.println(t== null ? null : t.toString());
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
