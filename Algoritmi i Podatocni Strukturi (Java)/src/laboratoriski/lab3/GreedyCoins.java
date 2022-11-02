package laboratoriski.lab3;

import java.util.Arrays;
import java.util.Scanner;

public class GreedyCoins {
    public static void reverse(int[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        int tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }
    public static int minNumCoins(int coins[], int sum) {
        Arrays.sort(coins);
        reverse(coins);
        int numberOfCoins = 0,numberOfCoin=0;
        for(int i=0;i<coins.length;i++){
            if(coins[i]<=sum){
                numberOfCoin=0;
                numberOfCoin += sum/coins[i];
                sum -= numberOfCoin*coins[i];
                numberOfCoins += numberOfCoin;
            }
        }
        return numberOfCoins;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String coinsStringLine = input.nextLine();
        String coinsString[] = coinsStringLine.split(" ");
        int coins[] = new int[coinsString.length];
        for(int i=0;i<coinsString.length;i++) {
            coins[i] = Integer.parseInt(coinsString[i]);
        }
        int sum = input.nextInt();
        System.out.println(minNumCoins(coins, sum));
    }
}
