package laboratoriski.lab7.zad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Lozinki {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        CBHT<String,String> map = new CBHT<>((int) (1.5*N));
        for(int i=1;i<=N;i++){
            String imelozinka = br.readLine();
            String[] pom = imelozinka.split(" ");
            map.insert(pom[0], pom[1]);
        }

        while(true){
            String line = br.readLine();
            if(line.equals("KRAJ")){
                break;
            }
            String [] part = line.split("\\s+");
            SLLNode<MapEntry<String, String>> node = map.search(part[0]);
            if(node==null || !(node.getElement().value.equals(part[1]))){
                System.out.println("Nenajaven");
            }else{
                System.out.println("Najaven");
                break;
            }

        }






    }
}

class CBHT<K extends Comparable<K>,E> {

    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {

        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        SLLNode<MapEntry<K,E>> curr = buckets[b];
        for (; curr != null; curr = curr.succ) {
            if (targetKey.equals(curr.getElement().key)) {
                return curr;
            }
        }
        return null;

    }

    public void insert(K key, E value) {

        MapEntry<K,E> newEntry = new MapEntry<K,E>(key, value);
        int b = hash(key);
        SLLNode<MapEntry<K,E>> curr = buckets[b];
        for (; curr != null; curr = curr.succ) {
            if (key.equals(curr.getElement().key)) {
                curr.setElement(newEntry);
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete (K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b];
        for (; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(curr.getElement().key)) {
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
                temp += curr.getElement().toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry(K key, E value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(K that) {
        return this.key.compareTo(that);
    }

    @Override
    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class SLLNode<E> {

    private E element;
    public SLLNode<E> succ;

    public SLLNode(E element, SLLNode<E> succ) {
        this.setElement(element);
        this.succ = succ;
    }

    @Override
    public String toString() {
        return getElement().toString();
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

}