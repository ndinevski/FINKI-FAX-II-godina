package laboratoriski.lab6.zad1;

import java.util.*;
import java.util.stream.IntStream;


class SuperString{
    private LinkedList<String> list;
    private LinkedList<String> toRemove;
    private String concat;
    private int reversed;
    private int flag;

    public SuperString(){
        list = new LinkedList<>();
        toRemove = new LinkedList<>();
        reversed = 0;
        flag=0;
    }

    public void append(String s){
        list.add(s);
        toRemove.add(s);
    }

    public void insert(String s){
        list.addFirst(s);
        toRemove.add(s);
    }

    public void concatString(){
        StringBuilder combined = new StringBuilder();
        for (String s1 : list) {
            combined.append(s1);
        }
        concat = combined.toString();
    }

    public boolean contains(String s){
        concatString();
        return concat.contains(s);
    }

    public void reverseWords(LinkedList<String > listt){
        for (int i=0;i<listt.size();i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(listt.get(i));
            sb.reverse();
            listt.set(i,sb.toString());
        }
    }
    public void reverse(){
        Collections.reverse(list);
        reverseWords(list);
        reverseWords(toRemove);
        reversed++;
    }

    @Override
    public String toString() {
        concatString();
        return concat;
    }

    public void removeLast(int k){
        for(int i=0;i<k;i++){
            String remove = toRemove.remove(toRemove.size()-1);
            list.remove(remove);
        }
    }
}












public class SuperStringTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (  k == 0 ) {
            SuperString s = new SuperString();
            while ( true ) {
                int command = jin.nextInt();
                if ( command == 0 ) {//append(String s)
                    s.append(jin.next());
                }
                if ( command == 1 ) {//insert(String s)
                    s.insert(jin.next());
                }
                if ( command == 2 ) {//contains(String s)
                    System.out.println(s.contains(jin.next()));
                }
                if ( command == 3 ) {//reverse()
                    s.reverse();
                }
                if ( command == 4 ) {//toString()
                    System.out.println(s);
                }
                if ( command == 5 ) {//removeLast(int k)
                    s.removeLast(jin.nextInt());
                }
                if ( command == 6 ) {//end
                    break;
                }
            }
        }
    }

}
