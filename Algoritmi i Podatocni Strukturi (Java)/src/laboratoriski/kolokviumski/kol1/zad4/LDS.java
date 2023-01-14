//Најди ја најдолгата опаѓачка секвенца во една низа. Броевите во секвенцата не мора да се наоѓаат на последователни индекси во низата.

package laboratoriski.kolokviumski.kol1.zad4;

import java.util.Scanner;


public class LDS {


    private static int calculate(int[] a) {
        int dp[] = new int[a.length];

        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if(a[j] > a[i]) max = Math.max(max, dp[j]);
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(calculate(a));
    }


}
