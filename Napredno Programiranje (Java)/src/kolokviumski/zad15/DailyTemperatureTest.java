package kolokviumski.zad15;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * I partial exam 2016
 */
public class DailyTemperatureTest {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        try {
            dailyTemperatures.readTemperatures(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');
    }
}

// Vashiot kod ovde





class Day{
    String dayNum;
    List<Integer> temperatures= new ArrayList<>();
    String type;

    public Day(String dayNum, List<Integer> temperatures, String type) {
        this.dayNum = dayNum;
        this.temperatures = temperatures;
        this.type = type;
    }
    public int getDayNum(){
        return Integer.parseInt(dayNum);
    }

    public static Day createDay(String line){
        String [] info = line.split("\\s+");
        String num = info[0];
        String type = "";
        type+=info[1].charAt(info[1].length()-1);
        List<Integer> temperatures = new ArrayList<>();
        for(int i=1;i<info.length;i++){
            temperatures.add(Integer.parseInt(info[i].substring(0,info[i].length()-1)));
        }
        return new Day(num, temperatures, type);
    }



    public String printC(){
        DoubleSummaryStatistics stats = temperatures.stream()
                .mapToDouble(Integer::doubleValue)
                .summaryStatistics();
        if(type.equals("C")){
            return String.format("%3s: Count:%4d Min:%7.2fC Max:%7.2fC Avg:%7.2fC",
                    dayNum, stats.getCount(), stats.getMin(),stats.getMax(),stats.getAverage());
        }else{
            double min = (stats.getMin()-32)*5/9;
            double max = (stats.getMax()-32)*5/9;
            double avr = (stats.getAverage()-32)*5/9;
            return String.format("%3s: Count:%4d Min:%7.2fC Max:%7.2fC Avg:%7.2fC",
                    dayNum, stats.getCount(), min, max, avr);
        }

    }
    public String printF(){
        DoubleSummaryStatistics stats = temperatures.stream()
                .mapToDouble(Integer::doubleValue)
                .summaryStatistics();
        if(type.equals("F")){
            return String.format("%3s: Count:%4d Min:%7.2fF Max:%7.2fF Avg:%7.2fF",
                    dayNum, stats.getCount(), stats.getMin(),stats.getMax(),stats.getAverage());
        }else{
            double min = (stats.getMin()*9)/5 + 32;
            double max = (stats.getMax()*9)/5 + 32;
            double avr = (stats.getAverage()*9)/5 + 32;
            return String.format("%3s: Count:%4d Min:%7.2fF Max:%7.2fF Avg:%7.2fF",
                    dayNum, stats.getCount(), min, max, avr);
        }

    }

}



class DailyTemperatures{
    List<Day> days;

    public DailyTemperatures() {
        days = new ArrayList<>();
    }

    void readTemperatures(InputStream inputStream) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = bf.readLine())!=null && line.length()!=0){
            days.add(Day.createDay(line));
        }
    }

    void writeDailyStats(OutputStream outputStream, char scale){
        PrintWriter pw = new PrintWriter(outputStream);
        days = days.stream().sorted(Comparator.comparing(Day::getDayNum)).collect(Collectors.toList());
        if(scale=='C') {
            for (Day d : days) {
                pw.println(d.printC());
            }
        }else {
            for (Day d : days) {
                pw.println(d.printF());
            }
        }
        pw.flush();
    }


}