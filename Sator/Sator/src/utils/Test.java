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
import semantics.table.Type;

/**
 *
 * @author admin
 */
public class Test {
    public static void main(String args[]){
        ArrayLiteral l = new ArrayLiteral();
        ArrayLiteral a = new ArrayLiteral();
        ArrayLiteral b = new ArrayLiteral();
        ArrayLiteral c = new ArrayLiteral();
        
        Type t = new Type(0, "aa");
        Type t2 = new Type(1, "cc");
        t2.addDimension(3);
        t2.addDimension(4);
        
        a.addLiteral(new SimpleLiteral(t, "1"));
        a.addLiteral(new SimpleLiteral(t, "1"));
        a.addLiteral(new SimpleLiteral(t, "1"));
        a.addLiteral(new SimpleLiteral(t, "1"));
        
        b.addLiteral(new SimpleLiteral(t, "1"));
        b.addLiteral(new SimpleLiteral(t, "1"));
        b.addLiteral(new SimpleLiteral(t, "1"));
        b.addLiteral(new SimpleLiteral(t, "1"));
        
        
        c.addLiteral(new SimpleLiteral(t, "1"));
        c.addLiteral(new SimpleLiteral(t, "1"));
        c.addLiteral(new SimpleLiteral(t, "1"));
        c.addLiteral(new SimpleLiteral(t, "1"));
        
        l.addLiteral(a);
        l.addLiteral(b);
        l.addLiteral(c);
        
        System.out.println(l.matchDimension(t2));
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
