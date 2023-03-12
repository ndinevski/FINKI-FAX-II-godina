package kolokviumski.ispitni.zad9;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    String index;
    List<Integer> points;

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
    }

    public String getIndex() {
        return index;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public Double getAverage(){
        return (points.stream().mapToInt(i->i).sum())/10.0;
    }

    public boolean isPassed(){
        if(points.size()<8){
            return false;
        }else{
            return true;
        }
    }

    public String stringPassed(){
        if(isPassed()) {
            return "YES";
        }else{
            return "NO";
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", index, stringPassed(), getAverage());
    }
}

class LabExercises{
    Map<String, Student> studentsByIndex;
    Map<Integer, List<Double>> averagesByYear;

    public LabExercises() {
        studentsByIndex = new TreeMap<>();
        averagesByYear = new HashMap<>();
    }

    public void addStudent (Student student){
        studentsByIndex.put(student.getIndex(), student);

        if(student.isPassed()){
            int year = Math.abs(Integer.parseInt(String.valueOf(student.getIndex().charAt(1))) - 10);
            averagesByYear.putIfAbsent(year, new ArrayList<>());
            averagesByYear.get(year).add(student.getAverage());
        }
    }

    public void printByAveragePoints (boolean ascending, int n){
        Comparator<Student> comparator = Comparator.comparing(Student::getAverage).thenComparing(Student::getIndex);
        if(!ascending){
            comparator = comparator.reversed();
        }
        studentsByIndex.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(comparator))
                .map(Map.Entry::getValue)
                .limit(n)
                .forEach(System.out::println);
    }

    public List<Student> failedStudents (){
        return studentsByIndex.values().stream()
                .sorted(Comparator.comparing(Student::getIndex).thenComparing(Student::getAverage))
                .filter(p->!p.isPassed())
                .collect(Collectors.toList());

    }

    public Map<Integer,Double> getStatisticsByYear() {
        Map<Integer, Double> statisticsYear = new HashMap<>();

        averagesByYear.entrySet().stream()
                .forEach(entry->{
                    statisticsYear.put(entry.getKey(), entry.getValue().stream()
                            .mapToDouble(p->p).average().orElse(0.0));
                });
        return statisticsYear;
    }
}








public class LabExercisesTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabExercises labExercises = new LabExercises();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            String index = parts[0];
            List<Integer> points = Arrays.stream(parts).skip(1)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            labExercises.addStudent(new Student(index, points));
        }

        System.out.println("===printByAveragePoints (ascending)===");
        labExercises.printByAveragePoints(true, 100);
        System.out.println("===printByAveragePoints (descending)===");
        labExercises.printByAveragePoints(false, 100);
        System.out.println("===failed students===");
        labExercises.failedStudents().forEach(System.out::println);
        System.out.println("===statistics by year");
        labExercises.getStatisticsByYear().entrySet().stream()
                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);

    }
}