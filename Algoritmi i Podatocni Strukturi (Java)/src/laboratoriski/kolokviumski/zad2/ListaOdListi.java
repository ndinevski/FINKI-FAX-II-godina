//Дадена е двојно поврзана листа од двојно поврзани листи. Да се најде сума на секоја од подлистите, а потоа производ на овие суми
//Влез:
//Број N кој кажува колку листи има
//Број М кој кажува колку елементи има во секоја листа
//Во следните М линии се податоците 1<=A<=1000 за секоја од листите
//Излез:
//Еден број што е производот на сумите од низите. Со седум децимали.
package laboratoriski.kolokviumski.zad2;
import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return "<-" + element.toString() + "->";
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

}

public class ListaOdListi {

    public static long findMagicNumber(DLL<DLL<Integer>> list) {
        DLLNode<Integer> tmp = new DLLNode<>(0,null,null);
        long proizvod = 1;
        while(list.length()!=0){
            long suma=0;
            while(list.getFirst().element.length()!=0){
                suma+=list.getFirst().element.getFirst().element;
                list.getFirst().element.deleteFirst();
            }
            list.deleteFirst();
            proizvod *= suma;
        }
        return proizvod;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        DLL<DLL<Integer>> list = new DLL<DLL<Integer>>();
        for (int i = 0; i < n; i++) {
            DLL<Integer> tmp = new DLL<Integer>();
            for (int j = 0; j < m; j++) {
                tmp.insertLast(in.nextInt());
            }
            list.insertLast(tmp);
        }
        in.close();
        System.out.println(findMagicNumber(list));
    }

}