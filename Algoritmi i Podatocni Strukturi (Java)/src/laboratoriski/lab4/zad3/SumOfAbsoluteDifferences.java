package laboratoriski.lab4.zad3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {

    static int sumOfAbsoluteDiff(int arr[]){
        int sum=0;
        for(int i=0;i<arr.length-1;i++){
            sum += Math.abs(arr[i] - arr[i+1]);
        }
        return sum;

    }

    static int solve(int numbers[], int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni
        Arrays.sort(numbers);
        int [] smallNumbers = new int[K/2+1];
        int [] bigNumbers = new int[K/2];
        int [] newArray = new int[K];

        int k=0,j=0;
        for(int i=0;i<bigNumbers.length;i++){
            bigNumbers[i] = numbers[N-k-1];
            k++;
        }
        for(int i=0;i<smallNumbers.length;i++){
            smallNumbers[i] = numbers[i];
        }
        k=0;
        for(int i=0;i<K;i++){
            if(i%2==1){
                newArray[i] = bigNumbers[k];
                k++;
            }else{
                newArray[i] = smallNumbers[j];
            }
        }

        return sumOfAbsoluteDiff(newArray);

    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

}