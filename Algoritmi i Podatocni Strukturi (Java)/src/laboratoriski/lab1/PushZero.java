//За дадена низа од случајни броеви кои се внесуваат од стандарден влез, да се направи преместување на
//сите нули на крајот на низата. На стандарден излез да се испечати трансформираната низа.

package laboratoriski.lab1;

import java.util.Scanner;

public class PushZero {
    static void pushZerosToEnd(int arr[], int n) {
        int nonZeroElements=0;

        for(int i=0;i<n;i++){
            if(arr[i]!=0){
                arr[nonZeroElements]=arr[i];
                nonZeroElements++;
            }
        }

        for(int i=nonZeroElements;i<n;i++){
            arr[i]=0;
        }
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        pushZerosToEnd(arr, n);
        System.out.println("Transformiranata niza e: ");
        for(int i=0;i<n;i++){
            System.out.printf("%d ", arr[i]);
        }
    }
}

