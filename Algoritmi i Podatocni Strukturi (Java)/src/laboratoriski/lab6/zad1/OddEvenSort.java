//Дадена е низа со N природни броеви. Треба да се сортира низата така што во првиот дел од низата ќе бидат
// подредени непарните броеви од неа во растечки редослед, а во вториот дел парните броеви во опаѓачки редослед.
//Во првиот ред од влезот даден е бројот на елементи во низата N, а во вториот ред се дадени броевите. На излез
// треба да се испечати сортираната низа.


package laboratoriski.lab6.zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OddEvenSort {

    static void oddEvenSort(int a[], int n)
    {
        int [] nova = new int[n];
        Arrays.sort(a);

        int k=0;
        for(int i=0;i<n;i++){
            if(a[i]%2==1){
                nova[k] = a[i];
                k++;
            }
        }
        for(int i=n-1;i>=0;i--){
            if(a[i]%2==0){
                nova[k]=a[i];
                k++;
            }
        }

        for(int i=0;i<nova.length;i++){
            a[i] = nova[i];
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
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}