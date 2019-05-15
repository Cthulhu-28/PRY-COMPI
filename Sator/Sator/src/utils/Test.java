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
import semantics.literals.RecordLiteral;

/**
 *
 * @author admin
 */
public class Test {
    public static void main(String args[]){
        
        
        
        
        
        Type t1 = new Type(0, "int");
        Type t2 = new Type(1, "bool");
        RecordLiteral record = new RecordLiteral();
        RecordLiteral record1 = new RecordLiteral();
        
        record.addLiteral(new SimpleLiteral(t1, "1"));
        record.addLiteral(new SimpleLiteral(t2, "false"));
        
        record1.addLiteral(new SimpleLiteral(t1, "1"));
        record1.addLiteral(new SimpleLiteral(t2, "false"));
        record.addLiteral(record1);
        
        Type t3 = new Type(677, "JAJA");
        Type t4 = new Type(45, "yu");
        t4.setBaseType(t1);
        Type t5 = new Type(425, "yud");
        t5.setBaseType(t2);
        t3.addAtrribute(t4);
        t3.addAtrribute(t5);
        
        
        Type t6 = new Type(6277, "JAwJA");
        Type t7 = new Type(4511, "yuwwd");
        t7.setBaseType(t1);
        Type t8 = new Type(42895, "yu2d");
        t8.setBaseType(t2);
        t6.addAtrribute(t7);
        t6.addAtrribute(t8);
        
        Type t9 = new Type(6433, "sdfw");
        t9.setBaseType(t6);
        t3.addAtrribute(t9);
        
        System.out.println(t3);
        
        System.out.println(record.getType());
        System.out.println(record.getType().equals(t3));
        System.out.println(record.match(t3));
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
