//За дадена низа од N (1≤N≤50) природни броеви, да се најде бројот кој е најблиску до нивниот просек. Ако постојат два броја со исто растојание до просекот,
// да се врати помалиот од нив. На пример за низата 1, 2, 3, 4, 5 просекот е (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3, што значи дека бројот кој треба да се врати
// и е најблиску до просекот е 3.
//За низата 1, 2, 3, 4, 5, 6 просекот е 3.5 и двата броја 3 и 4 се на исто растојание од просекот. Точната вредност која треба да се врати е помалиот од нив,а тоа е 3.
//Во низата може да има дупликати.
//Влез: Првиот број од влезот е бројот на елементи во низата N, а потоа во секој ред се дадени броевите.
//Излез: Најдениот број кој што е најблиску до просекот.
//Внимавајте:
//1. Даден е целосниот код на структурата којашто треба да се користи. Дадена е и тест класата ArrayMeanValue.java, со целосно имплементиран input и output.
// Потребно е да се менува само во рамки на int brojDoProsek(Array<Integer arr) функцијата.
//2. Не смее да менувате во main функцијата !



package laboratoriski.lab2;

import java.util.Scanner;

import static java.lang.Math.abs;

@SuppressWarnings("unchecked")
class Array<E> {
    private E data[]; // declared to be an Object since it would be too
    // complicated with generics
    private int size;

    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public void insertLast(E o) {
        //check if there is enough capacity, and if not - resize
        if(size + 1 > data.length)
            this.resize();
        data[size++] = o;
    }

    public void insert(int position, E o) {
        // before all we check if position is within range
        if (position >= 0 && position <= size) {
            //check if there is enough capacity, and if not - resize
            if(size + 1 > data.length)
                this.resize();
            //copy the data, before doing the insertion
            for(int i=size;i>position;i--) {
                data[i] = data[i-1];
            }
            data[position] = o;
            size++;
        } else {
            System.out.println("Ne mozhe da se vmetne element na taa pozicija");
        }
    }

    public void set(int position, E o) {
        if (position >= 0 && position < size)
            data[position] = o;
        else
            System.out.println("Ne moze da se vmetne element na dadenata pozicija");
    }

    public E get(int position) {
        if (position >= 0 && position < size)
            return data[position];
        else
            System.out.println("Ne e validna dadenata pozicija");
        return null;
    }

    public int find(E o) {
        for (int i = 0; i < size; i++) {
            if(o.equals(data[i]))
                return i;
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public void delete(int position) {
        // before all we check if position is within range
        if (position >= 0 && position < size) {
            // first resize the storage array
            E[] newData = (E[]) new Object[size - 1];
            // copy the data prior to the delition
            for (int i = 0; i < position; i++)
                newData[i] = data[i];
            // move the data after the deletion
            for (int i = position + 1; i < size; i++)
                newData[i - 1] = data[i];
            // replace the storage with the new array
            data = newData;
            size--;
        }
    }

    public void resize() {
        // first resize the storage array
        E[] newData = (E[]) new Object[size*2];
        // copy the data
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        // replace the storage with the new array
        this.data = newData;
    }

    @Override
    public String toString() {
        String ret = new String();
        if(size>0) {
            ret = "{";
            ret += data[0];
            for(int i=1;i<size;i++) {
                ret += "," + data[i];
            }
            ret+="}";
            return ret;
        }
        else {
            ret = "Prazna niza!";
        }
        return ret;
    }

}

public class ArrayMeanValue {

    //todo: implement function
    public static int brojDoProsek(Array<Integer> arr) {

        //calculate average
        double sumOfElements = 0.0;
        for(int i=0;i<arr.getSize();i++){
            sumOfElements+=arr.get(i);
        }
        double arrayAverage = sumOfElements / arr.getSize();

        double closestDistance = 1000;
        double distanceFromAverage;
        int indexOfClosestElement = 0, indexOfSecondClosestElement = 0;
        int flag=0;

        //find index of closest element to average
        for(int i=0;i<arr.getSize();i++){
            distanceFromAverage = abs(arr.get(i) - arrayAverage);


            if(distanceFromAverage < closestDistance){
                closestDistance = distanceFromAverage;
                indexOfClosestElement = i;
            }else if( distanceFromAverage == closestDistance){ //if there are two elements
                indexOfSecondClosestElement = i;
                flag=1;
            }
        }

        //find smaller element from the two with the same distance
        if(flag>0){
            if(arr.get(indexOfClosestElement).compareTo(arr.get(indexOfSecondClosestElement)) > 0){
                   indexOfClosestElement = indexOfSecondClosestElement;
            }
        }
        return arr.get(indexOfClosestElement);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        Array<Integer> arr = new Array<>(N);

        for(int i=0;i<N;i++) {
            arr.insertLast(input.nextInt());
        }

        System.out.println(brojDoProsek(arr));
    }
}
