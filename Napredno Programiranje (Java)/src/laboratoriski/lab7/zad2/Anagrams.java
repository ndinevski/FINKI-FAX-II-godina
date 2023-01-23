package laboratoriski.lab7.zad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Word{
    String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int lenght(){
        return word.length();
    }

    public boolean isAnagramTo(Word other){
        if(other.lenght()!=word.length()){
            return false;
        }

        char [] word1 = word.toCharArray();
        char [] word2 = other.toString().toCharArray();

        Arrays.sort(word1);
        Arrays.sort(word2);

        return Arrays.equals(word1, word2);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String toString() {
        return word;
    }
}


public class Anagrams {

    public static void main(String[] args) {
        try {
            findAll(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void findAll(InputStream inputStream) throws IOException {
        Map<Word, List<Word>> anagramsByWord = new TreeMap<>(Comparator.comparing(Word::getWord));

        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

        String word;
        word = bf.readLine();
        Word word1 = new Word(word);
        anagramsByWord.put(word1, new ArrayList<>());
        anagramsByWord.get(word1).add(word1);

        while((word=bf.readLine())!=null && word.length()!=0){
            Word word2 = new Word(word);
            List<Word> matchingWord = anagramsByWord.keySet().stream().filter(key -> key.isAnagramTo(word2)).collect(Collectors.toList());


            if(matchingWord.size()==0){
                anagramsByWord.put(word2, new ArrayList<>());
                anagramsByWord.get(word2).add(word2);
            }else{
                anagramsByWord.get(matchingWord.get(0)).add(word2);
            }
        }

        anagramsByWord.values().stream()
                .forEach(list->{
                    for(int i=0;i<list.size()-1;i++){
                        System.out.print(list.get(i) + " ");
                    }
                    System.out.println(list.get(list.size()-1));
                });

    }
}
