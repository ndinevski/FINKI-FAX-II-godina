//Перо прави трик со карти. Тој има шпил од 51-на карта (некој некогаш не му вратил една) од којшто ви дозволува да влечете карта. Тој, за трикот да биде веродостоен,
// не ја знае картата, но знае на која позиција се наоѓа. Мааната на Перо е тоа што тој не знае регуларно да измеша карти, туку ги зема првите седум карти, им им го
// превртува редоследот (пр. од 1 2 3 4 5 6 7 ги реди во 7 6 5 4 3 2 1), потоа зема една карта од превртените и една од врвот од шпилот и го става на крајот од шпилот,
// така се додека не ги потроши сите седум карти. Со тоа остварува едно мешање на шпил.
//Ваша задача е, да изработите симулцаија на ваквото мешање, такашто за дадена N-та карта т.ш. 1<=N<=51, вие ќе му изброите колку вакви мешања треба тој да направи за на
//врв на шпилот да дојде извлечената карта.


package laboratoriski.kolokviumski.zad6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class card_trick {
    public static int count(int N){
        // Vasiot kod tuka
        int topCard=100,cnt=0;
        ArrayList<Integer> deck = new ArrayList<>();
        for(int i=0;i<51;i++){
            deck.add(i, i+1);
        }
        ArrayList<Integer> mixingCards = new ArrayList<>();
        while(topCard!=N) {
            for (int i = 0; i < 7; i++) {
                mixingCards.add(i, deck.get(0));
                deck.remove(0);
            }
            Collections.reverse(mixingCards);
            for(int i=0;i<7;i++){
                deck.add(mixingCards.get(0));
                mixingCards.remove(0);
                deck.add(deck.get(0));
                deck.remove(0);
            }
            topCard=deck.get(0);
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
