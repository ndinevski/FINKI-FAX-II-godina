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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

class RabotnaNedela{

    private int [] casovi;
    private int brNedela;

    public int sumaCasovi(){
        int sum=0;
        for(int i=0;i<5;i++){
            sum+=casovi[i];
        }
        return sum;
    }


    public RabotnaNedela(int[] casovi, int brNedela) {
        super();
        this.casovi = casovi;
        this.brNedela = brNedela;
    }

    public int[] getCasovi() {
        return casovi;
    }

    public void setCasovi(int[] casovi) {
        this.casovi = casovi;
    }

    public int getBrNedela() {
        return brNedela;
    }

    public void setBrNedela(int brNedela) {
        this.brNedela = brNedela;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RabotnaNedela that = (RabotnaNedela) o;
        return brNedela == that.brNedela && Arrays.equals(casovi, that.casovi);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(brNedela);
        result = 31 * result + Arrays.hashCode(casovi);
        return result;
    }

    @Override
    public String toString() {
        return sumaCasovi() + "   ";
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

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public RabotnaNedela[] getNedeli() {
        return nedeli;
    }

    public RabotnaNedela getNedela(int i) {
        return nedeli[i];
    }

    public void setNedeli(RabotnaNedela[] nedeli) {
        this.nedeli = nedeli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rabotnik rabotnik = (Rabotnik) o;
        return Objects.equals(ime, rabotnik.ime) && Arrays.equals(nedeli, rabotnik.nedeli);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ime);
        result = 31 * result + Arrays.hashCode(nedeli);
        return result;
    }

    @Override
    public String toString() {
        String out="";
        out+=(ime + "   ");
        for(int i=0;i<nedeli.length;i++){
            out+= nedeli[i].toString();
        }
        out+= Main.sumNedeli(this) +"\n";
        return out;
    }

}

public class Main {
    public static int sumNedeli(Rabotnik r){
        int suma=0;
        for(int i=0;i<4;i++){
            suma+=r.getNedela(i).sumaCasovi();
        }
        return suma;
    }
    public static Rabotnik najvreden_rabotnik(Rabotnik [] niza) {
        Rabotnik najdobar=niza[0];
        for(int i=1;i<niza.length;i++){
            if(sumNedeli(niza[i])>sumNedeli(najdobar)) {
                najdobar = niza[i];
            }
        }
        return najdobar;
    }
    public static void table(Rabotnik [] niza) {
        System.out.println("Rab   1   2   3   4   Vkupno");
        for(int i=0;i< niza.length;i++){
            System.out.printf(niza[i].toString());
        }
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();

        Rabotnik [] niza = new Rabotnik[n];


        for(int i=0;i<n;i++) {
            String ime = input.next();
            ArrayList<RabotnaNedela> nedeli = new ArrayList<RabotnaNedela>();
            for(int j=0;j<4;j++){
                int [] casa = new int[5];
                for(int k=0;k<5;k++){
                    casa[k] = input.nextInt();
                }
                RabotnaNedela ned = new RabotnaNedela(casa,j+1);
                nedeli.add(ned);
            }
            niza[i] = new Rabotnik(ime, nedeli.toArray(new RabotnaNedela[4]));
        }
        table(niza);
        System.out.println();
        System.out.println("NAJVREDEN RABOTNIK: " + najvreden_rabotnik(niza).getIme());
    }
}

