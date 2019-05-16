/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author admin
 * @param <K>
 * @param <V>
 */
public abstract class HashMap <K,V> implements Iterable<HashMap.Pair>{
    
    protected final Pair<K,V> table[];
    protected final int SIZE = 31;
    
    public HashMap(){
        table = new Pair[SIZE];
    }
    public void put(K key, V value){
        Pair<K,V> entry = new Pair<>(key,value);
        int hash = hashCode(entry.key);
        if(table[hash]==null){
            table[hash]=entry;
        }else{            
            Pair p = table[hash];
            entry.next=p;
            table[hashCode(entry.key)]=entry;
        }
    }
    private Pair<K,V> findPair(K key){
        Pair<K,V> p = table[hashCode(key)];
        while(p != null){                  
            if(p.key.equals(key))
                return p;
            p = p.next;
        }
        return null;
    }
    public V get(K key){
        Pair<K,V> pair = findPair(key);
        return pair == null ? null : pair.getValue();
    }
    public V remove(K key){
        int hash = hashCode(key);
        if(table[hash] != null){
            if(table[hash].getKey().equals(key)){
                V value = table[hash].getValue();
                table[hash] = table[hash].getNext();
                return value;
            }else{
                Pair<K,V> pair = table[hash];
                while(pair.getNext() != null){
                    if(pair.getNext().getValue().equals(key)){
                        Pair<K,V> removed = pair.getNext();
                        pair.setNext(removed.getNext());
                        removed.setNext(null);
                        return removed.getValue();
                    }
                    pair = pair.getNext();
                }
            }
        }
        return null;
    }
    public boolean contains(K key){
        return get(key) != null;
    }
    public abstract int hashCode(K key);

    @Override
    public Iterator<Pair> iterator() {
       return new HashIterator();
    }
    public class HashIterator implements Iterator<Pair>{
        private int position=0;
        private Pair<K,V> pair;
        private Pair<K,V> found;
        @Override
        public boolean hasNext() {
            return position < (SIZE-1);
        }

        @Override
        public Pair next() {
            while(this.hasNext()){
                if(pair !=null){
                    Pair<K,V> p =found;
                    pair =pair.getNext();
                    if(pair==null)
                        position++;
                    else
                        found = pair;
                    return p;
                }
                if(table[position]!=null){
                    pair = table[position];
                    found = pair;
                    //return next();
                }
                else{
                    position++;
                    //return next();
                }
            }
            return found;         
        }     
    }

    public class Pair<K,V>{
        private final K key;
        private final V value;
        private Pair<K,V> next;
        
        public Pair(K key, V value){
            this.key=key;
            this.value=value;
            this.next=null;
        }
        public Pair<K,V> insert(Pair<K,V> entry){
            entry.next=this;
            return entry;
        }
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Pair<K, V> getNext() {
            return next;
        }

        public void setNext(Pair<K, V> next) {
            this.next = next;
        }
        
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 29 * hash + Objects.hashCode(this.key);
            return hash;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Pair p = (Pair)obj;
            return this.key.equals(p.key);
        }
        
    }
}
