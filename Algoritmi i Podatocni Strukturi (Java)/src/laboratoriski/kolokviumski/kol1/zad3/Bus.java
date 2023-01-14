//На автобуската станица во ФинТаун имало N возрасни и M деца кои што сакале да патуваат заедно за соседниот град МинТаун. Цената на еден билет е 100 денари. Ако некој
// возрасен сака да патува со k деца, треба да плати еден билет за него и k-1 билети за децата (за едно дете не мора да плаќа билет). Исто така, возрасен може да се вози и
// сам, во тој случај ќе си плати еден билет за него. Дополнително знаеме дека децата не можат да се возат без да се придружени од некој возрасен.
//Во првиот ред од влезот е даден бројот N. Во вториот ред е даден бројот M. Потребно е да пресметате колкав е минималниот и максималниот број на денари што може да ги платат
// патниците за билети и да ги испечатите во две линии. Во автобусот ќе има најмалку еден возрасен.

package laboratoriski.kolokviumski.kol1.zad3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Bus {

    public static int minAmount(int n, int m){
        ArrayList<Integer> nizaVozrasni = new ArrayList<>();
        for(int i=0;i<n;i++){
            nizaVozrasni.add(1);
        }
        while(m!=0){
            for(int i=0;i<nizaVozrasni.size();i++){
                if(m!=0) {
                    nizaVozrasni.set(i, nizaVozrasni.get(i) + 1);
                    m--;
                }else{
                    break;
                }
            }
        }
        int suma=0;
        for(int i=0;i< nizaVozrasni.size();i++){
            if(nizaVozrasni.get(i)==1){
                suma += 100;
            }else{
                suma += (nizaVozrasni.get(i)-1)*100;
            }
        }
        return suma;
    }

    public static int maxAmount(int n, int m){
        int suma=0;
        ArrayList<Integer> nizaVozrasni = new ArrayList<>();
        if(m!=0) {
            nizaVozrasni.add(1 + (m - 1));
        }else{
            nizaVozrasni.add(1);
        }
        for(int i=1;i<n;i++){
            nizaVozrasni.add(1);
        }
        for(int i=0;i<nizaVozrasni.size();i++){
            suma += nizaVozrasni.get(i)*100;
        }
        return suma;
    }


    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        // Vasiot kod tuka

        System.out.println(minAmount(N,M));
        System.out.println(maxAmount(N,M));
    }

}
