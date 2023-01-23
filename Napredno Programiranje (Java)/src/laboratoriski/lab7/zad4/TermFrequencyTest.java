package laboratoriski.lab7.zad4;


import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class TermFrequency{
    Map<String, Word> frequencyByWords;
    List<String> linesOfText = new ArrayList<>();

    public TermFrequency(InputStream in, String [] stopWords) throws IOException {
        frequencyByWords = new HashMap<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));

        String line;
        while(true){
            line = bf.readLine();
            if(line==null){
                break;
            }
            linesOfText.add(removeStopWords(cleanOfSigns(line), stopWords));
            if(linesOfText.get(linesOfText.size()-1).equals("")){
                linesOfText.remove(linesOfText.size()-1);
            }
            if(linesOfText.size()==11){
                break;
            }
        }
        fillMap();
    }

    public int countTotal(){
        return frequencyByWords.values().stream().mapToInt(Word::getFrequency).sum();
    }

    public int countDistinct(){
        return frequencyByWords.size();
    }

    public List<String> mostOften(int k) {
        return frequencyByWords.values().stream()
                .sorted(Comparator.comparing(Word::getFrequency).reversed().thenComparing(Word::getName))
                .map(Word::getName)
                .limit(k)
                .collect(Collectors.toList());
    }

    public void fillMap(){
        linesOfText.forEach(line->{
            List<String> wordsInLine = new ArrayList<>(List.of(line.split("\\s+")));
            wordsInLine.forEach(word->{

                frequencyByWords.putIfAbsent(word, new Word(word));
                frequencyByWords.get(word).addFrequency();
            });
        });

    }

    public String removeStopWords(String line, String [] stopWords){
        String [] parts = line.split("\\s+");
        List<String> newLine = new ArrayList<>(List.of(parts));
        List<String> stopWord = new ArrayList<>(List.of(stopWords));
        newLine.removeAll(stopWord);
        return String.join(" ", newLine);
    }


    public String cleanOfSigns(String line){
        line = line.toLowerCase();
        char [] charLine = line.toCharArray();
        StringBuilder newLine = new StringBuilder();
        for(int i=0;i<charLine.length;i++){
            if(Character.isLetter(charLine[i]) || charLine[i]==' '){
                newLine.append(charLine[i]);
            }
        }
        return newLine.toString();
    }
}

class Word{
    private String name;
    private int frequency;

    public Word(String name) {
        this.name = name;
        this.frequency = 1;
    }

    public void addFrequency(){
        frequency++;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return name;
    }
}




public class TermFrequencyTest {
    public static void main(String[] args) throws IOException {
        String[] stop = new String[] { "во", "и", "се", "за", "ќе", "да", "од",
                "ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
                "што", "на", "а", "но", "кој", "ја" };

        TermFrequency tf = new TermFrequency(System.in, stop);
        System.out.println(tf.countTotal());
        System.out.println(tf.countDistinct());
        System.out.println(tf.mostOften(10));

    }
}


