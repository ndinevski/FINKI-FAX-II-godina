package kolokviumski.kol1.zad12;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Time{
    int HourIn24;
    int MinutesIn24;
    int HourInAMPM;
    int MinutesInAMPM;
    String AMPM;

    public int getHourIn24() {
        return HourIn24;
    }

    public int getMinutesIn24() {
        return MinutesIn24;
    }

    public Time(String time) throws UnsupportedFormatException, InvalidTimeException {
        String [] split = time.split("[:.]");
        if(split.length<2){
            throw new UnsupportedFormatException(time);
        }
        HourIn24 = Integer.parseInt(split[0]);
        MinutesIn24 = Integer.parseInt(split[1]);
        conversion();
        if(HourIn24>23 || HourIn24 <0 || MinutesIn24>60 || MinutesIn24<0){
            throw new InvalidTimeException(time);
        }
    }

    public void conversion(){
        MinutesInAMPM = MinutesIn24;
        if(HourIn24>=1 && HourIn24<=11){
            HourInAMPM = HourIn24;
            AMPM = "AM";
            return;
        }
        if(HourIn24>=13 && HourIn24<=23){
            HourInAMPM= HourIn24-12;
            AMPM="PM";
            return;
        }
        if(HourIn24==12){
            HourInAMPM = HourIn24;
            AMPM = "PM";
            return;
        }
        if(HourIn24==0){
            HourInAMPM = 12;
            AMPM = "AM";
        }
    }

    public String print24(){
        return String.format("%2d:%02d", HourIn24, MinutesIn24);
    }


    public String printAMPM(){
        return String.format("%2d:%02d %s", HourInAMPM, MinutesInAMPM, AMPM);
    }


}

class UnsupportedFormatException extends Exception{
    public UnsupportedFormatException(String mess) {
        super(mess);
    }

}
class InvalidTimeException extends Exception{
    public InvalidTimeException(String mess){
        super(mess);
    }
}
class TimeTable{
    List<Time> times = new ArrayList<>();
    void readTimes(InputStream inputStream) throws IOException, InvalidTimeException, UnsupportedFormatException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = bf.readLine())!=null && line.length()!=0){
            String [] info = line.split("\\s+");
            for(String s:info){
                times.add(new Time(s));
            }

        }
    }

    public void writeTimes(OutputStream outputStream, TimeFormat format){
        sort();
        PrintWriter pw = new PrintWriter(outputStream);
        if(format == TimeFormat.FORMAT_24){
            for(Time s: times){
                pw.println(s.print24());
            }
        }else{
            for(Time s: times){
                pw.println(s.printAMPM());
            }
        }
        pw.flush();
    }

    public void sort(){
        times = times.stream()
                .sorted(Comparator.comparing(Time::getHourIn24).thenComparing(Time::getMinutesIn24))
                .collect(Collectors.toList());
    }

}




public class TimesTest {

    public static void main(String[] args) {
        TimeTable timeTable = new TimeTable();
        try {
            timeTable.readTimes(System.in);
        } catch (UnsupportedFormatException e) {
            System.out.println("UnsupportedFormatException: " + e.getMessage());
        } catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("24 HOUR FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
        System.out.println("AM/PM FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
    }

}

enum TimeFormat {
    FORMAT_24, FORMAT_AMPM
}