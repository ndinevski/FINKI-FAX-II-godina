package kolokviumski.kol2.zad10;

import java.util.*;
import java.util.stream.Collectors;


class Name{
    String name;
    String nameIgnoreCase;
    int occurrence;
    Set<Character> uniqueLetters;

    public Name(String name) {
        this.name = name;
        nameIgnoreCase = name.toLowerCase();
        uniqueLetters = new HashSet<>();
        occurrence=1;
    }

    public String getName() {
        return name;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void addOccurrence(){
        occurrence++;
    }

    public int uniqueLetters() {
        for(int i=0;i<nameIgnoreCase.length();i++) {
            uniqueLetters.add(nameIgnoreCase.charAt(i));
        }
        return uniqueLetters.size();
    }


    @Override
    public String toString() {
        return String.format("%s (%d) %d", name, occurrence, uniqueLetters());
    }
}


class Names{
    Map<String, Name> numberOfTimesName;
    List<Name> namesLessThanLength;

    public Names() {
        numberOfTimesName = new TreeMap<>();
    }

    public void addName(String name){
        if(!numberOfTimesName.containsKey(name)){
            Name person = new Name(name);
            numberOfTimesName.putIfAbsent(name, person);
        }else{
            numberOfTimesName.get(name).addOccurrence();
        }
    }

    public void printN(int n){
        numberOfTimesName.values().stream()
                .filter(name->name.getOccurrence()>=n)
                .forEach(System.out::println);

    }

    public String findName(int len, int x) {
        namesLessThanLength = new ArrayList<>(numberOfTimesName.values());
        namesLessThanLength = namesLessThanLength.stream()
                .filter(name->name.getName().length()<len)
                .collect(Collectors.toList());
        int index = x%namesLessThanLength.size();
        return namesLessThanLength.get(index).getName();
    }
}




public class NamesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Names names = new Names();
        for (int i = 0; i < n; ++i) {
            String name = scanner.nextLine();
            names.addName(name);
        }
        n = scanner.nextInt();
        System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
        names.printN(n);
        System.out.println("===== FIND NAME =====");
        int len = scanner.nextInt();
        int index = scanner.nextInt();
        System.out.println(names.findName(len, index));
        scanner.close();

    }
}
