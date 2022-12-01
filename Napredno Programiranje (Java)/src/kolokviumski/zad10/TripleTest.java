package kolokviumski.zad10;

import java.security.InvalidAlgorithmParameterException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TripleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
        System.out.printf("%.2f\n", tInt.max());
        System.out.printf("%.2f\n", tInt.average());
        tInt.sort();
        System.out.println(tInt);
        float fa = scanner.nextFloat();
        float fb = scanner.nextFloat();
        float fc = scanner.nextFloat();
        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
        System.out.printf("%.2f\n", tFloat.max());
        System.out.printf("%.2f\n", tFloat.average());
        tFloat.sort();
        System.out.println(tFloat);
        double da = scanner.nextDouble();
        double db = scanner.nextDouble();
        double dc = scanner.nextDouble();
        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
        System.out.printf("%.2f\n", tDouble.max());
        System.out.printf("%.2f\n", tDouble.average());
        tDouble.sort();
        System.out.println(tDouble);
    }
}
// vasiot kod ovde
class Triple<T extends Number>{
    List<T> numbers;

    public Triple(T a, T b, T c) {
        numbers = new ArrayList<>();
        numbers.add(a);
        numbers.add(b);
        numbers.add(c);
    }

    public double max(){
        return numbers.stream()
                .mapToDouble(Number::doubleValue)
                .max()
                .orElseThrow(NoSuchElementException::new);
    }

    public double average(){
        OptionalDouble avr = numbers.stream()
                .mapToDouble(Number::doubleValue)
                .average();
        return avr.getAsDouble();
    }

    public void sort(){
        List<T> ordered = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        numbers = ordered;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00");
        sb.append(df.format(numbers.get(0))).append(" ").append(df.format(numbers.get(1))).append(" ")
                .append(df.format(numbers.get(2)));
        return sb.toString();
    }
}


