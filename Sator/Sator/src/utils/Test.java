/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
/**
 *
 * @author admin
 */
public class Test {
    public static void main(String args[]){
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>() {
            @Override
            public int hashCode(Integer key) {
                return key<2?2:key;
            }
        };
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 5);
        for(HashMap.Pair p : map){
            System.out.println(p.getKey());
        }
    }
    

}
