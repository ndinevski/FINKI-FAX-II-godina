package laboratoriski.lab6.zad2;

import java.util.*;
import java.util.stream.Collectors;

class IntegerList{
    LinkedList<Integer> list = new LinkedList<>();
    public IntegerList() {}

    public IntegerList(Integer [] array) {
        list.addAll(Arrays.asList(array));
    }

    public void add(int el, int idx){
        if(idx>list.size()){
            for(int i=list.size();i<idx;i++){
                list.add(0);
            }
            list.add(el);
            return;
        }
        list.add(idx, el);
    }

    public void shiftRight(int idx, int k){
        int shiftIndex = (idx + k) % list.size();
        Integer element = list.remove(idx);
        list.add(shiftIndex, element);
    }

    public void shiftLeft(int idx, int k){
        int shiftIndex = (idx - k) % list.size();
        if (shiftIndex < 0) {
            shiftIndex = list.size() + shiftIndex;
        }
        Integer element = list.remove(idx);
        list.add(shiftIndex, element);
    }

    public int remove(int idx){
        if(idx<0 || idx>list.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return list.remove(idx);
    }

    public void set(int el, int idx){
        if(idx<0 || idx>list.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        list.set(idx, el);
    }

    public int get(int idx){
        if(idx<0 || idx>list.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return list.get(idx);
    }

    public int size(){
        return list.size();
    }

    public int count(int el){
        return (int) list.stream()
                .filter(p->p.equals(el))
                .count();
    }

    public void removeDuplicates(){
        LinkedList<Integer> newList = new LinkedList<>();
        for(int i=list.size()-1;i>=0;i--){
            if(!newList.contains(list.get(i))){
                newList.addFirst(list.get(i));
            }
        }
        list = newList;
    }

    public int sumFirst(int k){
        return list.stream()
                .limit(k)
                .mapToInt(i->i)
                .sum();
    }

    public int sumLast(int k){
        Collections.reverse(list);
        int sum = sumFirst(k);
        Collections.reverse(list);
        return sum;
    }

    public IntegerList addValue(int value){
        IntegerList newlist = new IntegerList();
        newlist.add(0, list.size()-1);
        for(int i=0;i<list.size();i++){
            newlist.set(list.get(i)+value, i);
        }
        return newlist;
    }


}






public class IntegerListTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //test standard methods
            int subtest = jin.nextInt();
            if ( subtest == 0 ) {
                IntegerList list = new IntegerList();
                while ( true ) {
                    int num = jin.nextInt();
                    if ( num == 0 ) {
                        list.add(jin.nextInt(), jin.nextInt());
                    }
                    if ( num == 1 ) {
                        list.remove(jin.nextInt());
                    }
                    if ( num == 2 ) {
                        print(list);
                    }
                    if ( num == 3 ) {
                        break;
                    }
                }
            }
            if ( subtest == 1 ) {
                int n = jin.nextInt();
                Integer a[] = new Integer[n];
                for ( int i = 0 ; i < n ; ++i ) {
                    a[i] = jin.nextInt();
                }
                IntegerList list = new IntegerList(a);
                print(list);
            }
        }
        if ( k == 1 ) { //test count,remove duplicates, addValue
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    System.out.println(list.count(jin.nextInt()));
                }
                if ( num == 1 ) {
                    list.removeDuplicates();
                }
                if ( num == 2 ) {
                    print(list.addValue(jin.nextInt()));
                }
                if ( num == 3 ) {
                    list.add(jin.nextInt(), jin.nextInt());
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
        if ( k == 2 ) { //test shiftRight, shiftLeft, sumFirst , sumLast
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    list.shiftLeft(jin.nextInt(), jin.nextInt());
                }
                if ( num == 1 ) {
                    list.shiftRight(jin.nextInt(), jin.nextInt());
                }
                if ( num == 2 ) {
                    System.out.println(list.sumFirst(jin.nextInt()));
                }
                if ( num == 3 ) {
                    System.out.println(list.sumLast(jin.nextInt()));
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
    }

    public static void print(IntegerList il) {
        if ( il.size() == 0 ) System.out.print("EMPTY");
        for ( int i = 0 ; i < il.size() ; ++i ) {
            if ( i > 0 ) System.out.print(" ");
            System.out.print(il.get(i));
        }
        System.out.println();
    }

}
