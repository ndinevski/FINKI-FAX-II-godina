package kolokviumski.kol2.zad6;

import java.util.*;

class Person{
    private String city,code,name;
    private int age;

    public Person(String city, String code, String name, int age) {
        this.city = city;
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", code, name, age);
    }
}

class Audition{
    Map<String, Map<String, Person>> map;

    public Audition() {
        map = new HashMap<>();
    }

    void addParticpant(String city, String code, String name, int age){

        Person newPerson = new Person(city,code,name,age);

        map.putIfAbsent(city, new HashMap<>());

        map.get(city).putIfAbsent(code, newPerson);

    }

    void listByCity(String city){
        Comparator<Person> comparator = Comparator.comparing(Person::getName).thenComparing(Person::getAge);

        map.get(city).values()
                .stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }
}



public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticpant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}


