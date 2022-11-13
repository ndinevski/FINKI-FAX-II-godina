//Луѓето доаѓаат наутро во МВР за да извадат еден или повеќе документи.
//Документите може да бидат:
//1. Лична карта
//2. Пасош
//3. Возачка дозвола
//Кога се отвора шалтерот прво се услужуваат луѓето кои чекаат за лична карта, па потоа оние за пасош и на крај оние за возачка дозвола.
//Секој човек кога ќе дојде си застанува во редицата за соодветната исправа која ја вади (т.е. или во редицата за лични карти или во редицата за
// пасоши или во редицата за возачки дозволи). Доколку еден човек има повеќе документи за вадење прво вади лична карта, па пасош и на крај возачка.
// Така ако еден човек треба да вади и лична карта и возачка дозвола прво застанува во редицата за лични карти и откако ќе заврши таму оди на крајот
// на редицата за возачки дозволи.
//Влез: Првиот ред означува колку луѓе вкупно дошле во МВР. Потоа за секој човек се внесуваат четири реда, во првиот е името и презимето на човекот,
// а во останатите три реда се кажува кој документ соодветно (лична карта, пасош и возачка) треба да се земе, притоа 1 значи дека треба да се земе тој
// документ, 0 значи дека не треба да се земе.
//На пример:
//Aleksandar Aleksandrovski
//1 0 1
//означува дека Александар Александровски ќе вади и лична карта и возачка дозвола, но нема да вади пасош.
//Излез: Ги печати имињата на луѓето по редоследот по кој завршуваат со вадење на документи.

package laboratoriski.lab5.zad3;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

class Gragjanin{
    private String imePrezime;
    private int lKarta, pasos, vozacka;

    public Gragjanin(String imePrezime, int lKarta, int pasos, int vozacka) {
        this.imePrezime = imePrezime;
        this.lKarta = lKarta;
        this.pasos = pasos;
        this.vozacka = vozacka;
    }

    public int getlKarta() {
        return lKarta;
    }

    public int getPasos() {
        return pasos;
    }

    public int getVozacka() {
        return vozacka;
    }

    public void setlKarta(int lKarta) {
        this.lKarta = lKarta;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public void setVozacka(int vozacka) {
        this.vozacka = vozacka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gragjanin gragjanin = (Gragjanin) o;
        return lKarta == gragjanin.lKarta && pasos == gragjanin.pasos && vozacka == gragjanin.vozacka && Objects.equals(imePrezime, gragjanin.imePrezime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imePrezime, lKarta, pasos, vozacka);
    }
}



public class MVR {

    public static Queue<String> novaRedica(Gragjanin [] lugje){
        Queue<Gragjanin> kartaRedica = new LinkedList<>();
        Queue<Gragjanin> pasosRedica = new LinkedList<>();
        Queue<Gragjanin> vozackaRedica = new LinkedList<>();

        for(int i=0;i<lugje.length;i++) {
            if (lugje[i].getlKarta() == 1) {
                kartaRedica.add(lugje[i]);
            }
            if(lugje[i].getPasos()==1 && lugje[i].getlKarta()==0){
                pasosRedica.add(lugje[i]);
            }
            if(lugje[i].getVozacka()==1 && lugje[i].getPasos()==0 && lugje[i].getVozacka()==0){
                vozackaRedica.add(lugje[i]);
            }
        }


    }



    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        int N = Integer.parseInt(br.nextLine());
        Gragjanin [] lugje = new Gragjanin[N];

        for(int i=1;i<=N;i++){
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime,lKarta,pasos,vozacka);
            lugje[i] = covek;
        }

    }
