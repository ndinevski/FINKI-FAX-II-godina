package kolokviumski.kol1.zad1;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.*;
import java.util.stream.Collectors;

public class F1Test {



    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        try {
            f1Race.readResults(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        f1Race.printSorted(System.out);
    }

}
class Lap{
    private String time;
    private int mm, ss, nnn;

    public Lap(String time) {
        this.time=time;
        String [] times =time.split(":");

        this.mm= Integer.parseInt(times[0]);
        this.ss = Integer.parseInt(times[1]);
        this.nnn = Integer.parseInt(times[2]);
    }
    public int getMm() {
        return mm;
    }

    public int getSs() {
        return ss;
    }

    public int getNnn() {
        return nnn;
    }

    public boolean isBetterLapThan(Lap other){
        if(this.mm<other.mm){
            return true;
        }else if(this.mm>other.mm){
            return false;
        }else{
            if(this.ss<other.ss){
                return true;
            }else if(this.ss>other.ss){
                return false;
            }else{
                if(this.nnn<other.nnn){
                    return true;
                }else if(this.nnn>other.nnn){
                    return false;
                }else{
                    return true;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lap lap = (Lap) o;
        return mm == lap.mm && ss == lap.ss && nnn == lap.nnn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mm, ss, nnn);
    }

    @Override
    public String toString() {
        return this.time;
    }
}

class Racer{
    private String ime;
    private Lap lap1,lap2,lap3;
    private int bestTime;

    public Racer(String racer){
        String [] splits = racer.split(" ");
        this.ime = splits[0];
        this.lap1 = new Lap(splits[1]);
        this.lap2 = new Lap(splits[2]);
        this.lap3 = new Lap(splits[3]);
        bestTime = this.bestLap().getMm()*60000 + this.bestLap().getSs()*1000 + this.bestLap().getNnn();
    }

    public String getName(){
        return ime;
    }
    public int getBestTime() {
        return bestTime;
    }

    public Lap bestLap(){
        if(lap1.isBetterLapThan(lap2) && lap1.isBetterLapThan(lap3)){
            return lap1;
        }else if(lap2.isBetterLapThan(lap1) && lap2.isBetterLapThan(lap3)){
            return lap2;
        }else{
            return lap3;
        }
    }


    @Override
    public String toString() {
        return ime +" "+ lap1 +" "+ lap2 +" "+ lap3 +"\n";
    }
}


class F1Race {
    // vashiot kod ovde
    private ArrayList<Racer> racers;
    public F1Race(){
        racers = new ArrayList<>();
    }

    public void readResults(InputStream inputStream) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//
//        while(scanner.nextLine().length()==0){
//            racers.add(new Racer(scanner.nextLine()));
//        }
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = bf.readLine())!=null && line.length()!=0){
            racers.add(new Racer(line));
        }
    }


    public void printSorted(OutputStream outputStream){
        List<Racer> ordered = racers.stream()
                .sorted(Comparator.comparing(Racer::getBestTime))
                .collect(Collectors.toList());

        for(int i=0;i< ordered.size();i++){
            System.out.printf("%d. %-11s %s \n",  i + 1, ordered.get(i).getName(), ordered.get(i).bestLap());
        }
    }

}