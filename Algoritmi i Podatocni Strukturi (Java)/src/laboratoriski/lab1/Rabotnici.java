//За потребите на софтвер за евиденција на работно време, да се дефинира класа RabotnaNedela во која се чуваат бројот
// на работни часови за секој работен ден во неделата (5 дена) и број на неделата.
//Потоа да се дефинира друга класа Rabotnik за кој се чува име (string ) и низа од работни недели (макс 4). За класите
// да се имплементираат соодветните конструктори и методи за правилно извршување на програмата.
//Да се имплементираат следните барања:
//• Метод int sumNedeli(Rabotnik r) кој ќе врати сума од сите работни часови во сите недели за дадениот работник
//• Метод Rabotnik najvreden_rabotnik(Rabotnik [] niza) кој за дадената низа од работници ќе го врати работникот со
// најмногу работни часови (од сите недели)
//• Да се дополни main методот во кој ќе се иницијализира низа од работници и работни недели согласно влезните тест
// примери. Во првиот ред се чита број на работници, а во секој нареден ред се читаат името и работните недели за
// секој работник.
//• На стандарден излез да се испечати низата од работници со помош на методата void table(Rabotnik [] niza) која за
// низата од работници ќе отпечати приказ во следниот формат (за простор при печатење се користат три празни места):

package laboratoriski.lab1;

import java.util.Scanner;

class RabotnaNedela{

    private int [] casovi;
    private int brNedela;

    public RabotnaNedela(int[] casovi, int brNedela) {
        super();
        this.casovi = casovi;
        this.brNedela = brNedela;
    }
    @Override
    public String toString() {

    }

}

class Rabotnik{

    private String ime;
    private RabotnaNedela [] nedeli;

    public Rabotnik(String ime, RabotnaNedela[] nedeli) {
        super();
        this.ime = ime;
        this.nedeli = nedeli;
    }
    @Override
    public String toString() {

    }

}

public class Main {

    public static Rabotnik najvreden_rabotnik(Rabotnik [] niza)
    {

    }
    public static void table(Rabotnik [] niza)
    {

    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        Rabotnik [] niza = new Rabotnik[n];
        for(int i=0;i<n;i++)
        {
            //vasiot kod tuka
        }

        table(niza);
        System.out.println("NAJVREDEN RABOTNIK: " +najvreden_rabotnik(niza).getIme());

    }
}
