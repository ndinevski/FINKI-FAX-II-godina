package laboratoriski.lab7.zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;


class Word implements Comparable<Word> {
    String english;

    public Word(String english) {
        this.english = english;
    }

    public String getEnglish() {
        return english;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(english, word.english);
    }

    @Override
    public int hashCode() {
        return Objects.hash(english);
    }

    @Override
    public int compareTo(Word o) {
        return 0;
    }
}



public class Preveduvac {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        OBHT<Word,String> map = new OBHT<Word,String>((int) (2*N));


        for(int i=0;i<N;i++){
            String [] parts = bf.readLine().split("\\s+");
            map.insert(new Word(parts[1]),parts[0]);
        }

        while(true){
            String line = bf.readLine();
            if(line.equals("KRAJ")){
                break;
            }

            Word word = new Word(line);

            int index = map.search(word);
            if(index==-1){
                System.out.println("/");
            }else{
                System.out.println(map.getValue(index));
            }

        }

    }
}



class OBHT<K extends Comparable<K>,E> {

    private MapEntry<K,E> buckets[];

    private static final MapEntry former = new MapEntry(null, null);

    private int occupancy = 0;

    private static final int NONE = -1;

    @SuppressWarnings("unchecked")
    public OBHT (int m) {
        // Construct an empty OBHT with m buckets.
        buckets = (MapEntry<K,E>[]) new MapEntry[m];
    }


    private int hash (K key) {
        // Translate key to an index of the array buckets.
        String keys = key.toString();
        int sum = 0;
        for (int i=0; i < keys.length(); ++i)
            sum += (i * i * keys.charAt(i));
        return sum % buckets.length;
    }

    public E getValue(int index) {
        return buckets[index].value;
    }

    public int search (K targetKey) {
        // Find which if any bucket of this OBHT is occupied by an entry whose key
        // is equal to targetKey. Return the index of that bucket.
        int b = hash(targetKey); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else
            {
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length)
                    return NONE;

            }
        }
    }

    public void insert (K key, E val) {
        // Insert the entry <key, val> into this OBHT.
        MapEntry<K,E> newEntry = new MapEntry<K,E>(key, val);
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            }
            else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            }
            else
            {
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length)
                    return;

            }
        }
    }

    @SuppressWarnings("unchecked")
    public void delete (K key) {
        // Delete the entry (if any) whose key is equal to key from this OBHT.
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null) {

                return;
            }
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;//(MapEntry<K,E>)former;
                return;
            }
            else{
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length)
                    return;

            }
        }
    }

    public String toString () {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            if (buckets[i] == null)
                temp += "\n";
            else if (buckets[i] == former)
                temp += "former\n";
            else
                temp += buckets[i] + "\n";
        }
        return temp;
    }


    public OBHT<K,E> clone () {
        OBHT<K,E> copy = new OBHT<K,E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K,E> e = buckets[i];
            if (e != null&&e != former)
                copy.buckets[i] = new MapEntry<K,E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
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