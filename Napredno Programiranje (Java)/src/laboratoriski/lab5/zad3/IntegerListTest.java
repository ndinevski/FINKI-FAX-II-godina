package laboratoriski.lab5.zad3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class IntegerList{
    ArrayList<Integer> list;


    public IntegerList() {
        this.list = new ArrayList<>();
    }

    public IntegerList(Integer [] numbers){
        for(int i=0;i< numbers.length;i++){
            list.add(numbers[i]);
        }
    }
    public void add(int el, int idx){
        if(idx<=list.size()){
            list.add(idx, el);
        }else {
            for (int i = 0; i < idx - list.size(); i++) {
                list.add(0);
            }
            list.add(el);
        }

    }

    public void add(int t){
        list.add(t);
    }

    public int remove(int idx){
        validateIndex(idx);
       return list.remove(idx);
    }

    public void set(int el, int idx){
        validateIndex(idx);
        list.set(idx, el);
    }

    public int get(int idx){
        validateIndex(idx);
        return list.get(idx);
    }

    public int size(){
        return list.size();
    }

    public long count(int el){
        return list.stream().filter(p->p==el).count();
    }

    public void removeDuplicates(){
        for (int i = 0; i < list.size(); i++) {
            Integer integer = list.get(i);
            for (int j = 0; j < i; j++) {
                if (integer.equals(list.get(j))) {
                    list.remove(j);
                    i--;
                    break;
                }
            }
        }
    }

    public int sumFirst(int k){
        return IntStream.range(0,k).map(i->list.get(i)).sum();
    }

    public int sumLast(int k){
        return IntStream.range(list.size(),k).map(i->list.get(i)).sum();
    }

    void shiftRight(int idx, int k){
        validateIndex(idx);
        int shiftIndex = (idx + k) % list.size();
        Integer element = list.remove(idx);
        list.add(shiftIndex, element);
    }
    void shiftLeft(int idx, int k){
        validateIndex(idx);
        int shift = (idx - k) % list.size();
        if (shift < 0) {
            shift = list.size() + shift;
        }
        Integer element = list.remove(idx);
        list.add(shift, element);
    }

    public IntegerList addValue(int value){
        return new IntegerList(
                list.stream()
                        .map(p-> p+value)
                        .collect(Collectors.toList()));
    }
    IntegerList(List<Integer> list) {
        this.list = (ArrayList<Integer>) list;
    }
    public boolean validateIndex(int index) {
        if (index<0 || index>list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }

}














public class IntegerListTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test standard methods
            int subtest = jin.nextInt();
            if (subtest == 0) {
                IntegerList list = new IntegerList();
                while (true) {
                    int num = jin.nextInt();
                    if (num == 0) {
                        list.add(jin.nextInt(), jin.nextInt());
                    }
                    if (num == 1) {
                        list.remove(jin.nextInt());
                    }
                    if (num == 2) {
                        print(list);
                    }
                    if (num == 3) {
                        break;
                    }
                }
            }
            if (subtest == 1) {
                int n = jin.nextInt();
                Integer a[] = new Integer[n];
                for (int i = 0; i < n; ++i) {
                    a[i] = jin.nextInt();
                }
                IntegerList list = new IntegerList(a);
                print(list);
            }
        }
        if (k == 1) { //test count,remove duplicates, addValue
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for (int i = 0; i < n; ++i) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while (true) {
                int num = jin.nextInt();
                if (num == 0) { //count
                    System.out.println(list.count(jin.nextInt()));
                }
                if (num == 1) {
                    list.removeDuplicates();
                }
                if (num == 2) {
                    print(list.addValue(jin.nextInt()));
                }
                if (num == 3) {
                    list.add(jin.nextInt(), jin.nextInt());
                }
                if (num == 4) {
                    print(list);
                }
                if (num == 5) {
                    break;
                }
            }
        }
        if (k == 2) { //test shiftRight, shiftLeft, sumFirst, sumLast
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for (int i = 0; i < n; ++i) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while (true) {
                int num = jin.nextInt();
                if (num == 0) { //count
                    list.shiftLeft(jin.nextInt(), jin.nextInt());
                }
                if (num == 1) {
                    list.shiftRight(jin.nextInt(), jin.nextInt());
                }
                if (num == 2) {
                    System.out.println(list.sumFirst(jin.nextInt()));
                }
                if (num == 3) {
                    System.out.println(list.sumLast(jin.nextInt()));
                }
                if (num == 4) {
                    print(list);
                }
                if (num == 5) {
                    break;
                }
            }
        }
    }

    public static void print(IntegerList il) {
        if (il.size() == 0) System.out.print("EMPTY");
        for (int i = 0; i < il.size(); ++i) {
            if (i > 0) System.out.print(" ");
            System.out.print(il.get(i));
        }
        System.out.println();
    }

}