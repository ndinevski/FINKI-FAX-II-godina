package laboratoriski.kolokviumski.kol2.zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class MostFrequentSubstring {
    public static void main (String[] args) throws IOException {
        CBHT<String,Integer> tabela = new CBHT<String,Integer>(300);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine().trim();

//        for(int i=0;i<word.length();i++){
//            for(int j=1;j<word.length()-i+1;j++){
//                String subString = word.substring(i,i+j);
//                if(tabela.search(subString)!=null){
//                    Integer value = tabela.search(subString).element.value;
//                    value++;
//                    tabela.delete(subString);
//                    tabela.insert(subString,value);
//                    if(value>highestValue){
//                        highestValue = value;
//                    }
//                    continue;
//                }
//                tabela.insert(subString, 1);
//            }
//        }
//
//
//        System.out.println(tabela.highestValue(highestValue));

        Map<String, Integer> map = new TreeMap<>();
        int highestValue=0;
        for(int i=0;i<word.length();i++){
            for(int j=1;j<word.length()-i+1;j++){
                String subString = word.substring(i,i+j);
                if(map.containsKey(subString)){
                    map.replace(subString, map.get(subString)+1);
                    if(map.get(subString)>highestValue){
                        highestValue = map.get(subString);
                    }
                    continue;
                }
                map.put(subString,1);
            }
        }


        int finalHighestValue1 = highestValue;

        System.out.println(map.keySet().stream()
                .filter(p->map.get(p).equals(finalHighestValue1))
                .min(Comparator.comparing(String::length).reversed().thenComparing(Comparator.naturalOrder()))
                .get());

    }
}

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "(" + key + "," + value + ")";
    }
}

class SLLNode<E> {
    E element;
    SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public String highestValue(int val){

        List<String> list = Arrays.stream(buckets)
                .filter(x -> x.element.value.equals(val))
                .map(p -> (String) p.element.key)
                .sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()).reversed())
                .collect(Collectors.toList());
        return list.get(0);
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}



