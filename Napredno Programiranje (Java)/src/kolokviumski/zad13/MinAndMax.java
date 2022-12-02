package kolokviumski.zad13;

import java.util.Scanner;

public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}

class MinMax<T extends Comparable<T>>{
    T max;
    T min;
    private int COUNTER=0;

    private int MIN_COUNT=0;
    private int MAX_COUNT=0;
    public MinMax() {}
    public void update(T element) {
        if (COUNTER == 0) {
            min = element;
            max = element;
        }
        ++COUNTER;
        if (element.compareTo(min) < 0) {
            MIN_COUNT = 1;
            min = element;
        } else {
            if (element.compareTo(min) == 0) {
                MIN_COUNT++;
            }
        }
        if (element.compareTo(max) > 0) {
            MAX_COUNT = 1;
            max = element;
        } else {
            if (element.compareTo(max) == 0) {
                MAX_COUNT++;
            }
        }
    }


    public T max (){
        return max;
    }

    public T min() {
        return min;
    }

    @Override
    public String toString() {
        return min +" "+ max +" "+ (COUNTER-(MIN_COUNT+MAX_COUNT))+"\n";
    }
}

