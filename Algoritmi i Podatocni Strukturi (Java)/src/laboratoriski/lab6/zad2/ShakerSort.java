//Дадена е низа со N природни броеви. Треба да се сортира низата со помош на таканареченото shaker
// (cocktail) сортирање. Ова сортирање е варијација на сортирањето со меурчиња (bubble sort) со тоа што во
// секоја итерација низата се изминува два пати. Во првото поминување најмалиот елемент се поместува на почетокот
// на низата, а при второто најголемиот елемент се поместува на крајот.
//Во првиот ред од влезот даден е бројот на елементи во низата N, а во вториот ред се дадени броевите. На излез
// треба да се испечати низата по секое изминување во посебен ред.

package laboratoriski.lab6.zad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ShakerSort {

    static void shakerSort(int a[], int n)
    {
        int tmp;
        for(int i=0;i<n;i++){
            boolean change = false;


            for (int j = n-1; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                    change = true;
                }
            }
            for (int k = 0; k < n - 1; k++) {
                System.out.print(a[k] + " ");
            }
            System.out.println(a[n - 1]);


            for (int j = i; j < n - 1 ; j++) {
                if (a[j] > a[j + 1]) {
                    tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    change = true;
                }
            }
            for (int k = 0; k < n - 1; k++) {
                System.out.print(a[k] + " ");
            }
            System.out.println(a[n - 1]);


            if (!change){
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}
