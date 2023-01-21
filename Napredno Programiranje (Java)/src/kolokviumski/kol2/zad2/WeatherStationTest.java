package kolokviumski.kol2.zad2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


class Measurement{
    private float temperature, wind, humidity, visibility;
    private Date date;


    public Measurement(float temperature, float wind, float humidity, float visibility, Date date) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getWind() {
        return wind;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getVisibility() {
        return visibility;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s", temperature, wind, humidity, visibility, date);
    }
}



class WeatherStation{
    private int days;
    private Measurement mostRecentMeasurement;
    private Set<Measurement> measurementSet;

    public WeatherStation(int days){
        this.days = days;
        measurementSet = new TreeSet<>(Comparator.comparing(Measurement::getDate));
    }

    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date){
        Measurement measurement = new Measurement(temperature, wind, humidity, visibility, date);

        if(measurementSet.isEmpty()){
            mostRecentMeasurement = measurement;
            measurementSet.add(measurement);
            return;
        }

        Calendar recentMeasurement = Calendar.getInstance();
        Calendar thisMeasurement = Calendar.getInstance();
        recentMeasurement.setTime(mostRecentMeasurement.getDate());
        thisMeasurement.setTime(date);

        if(Math.abs(thisMeasurement.getTimeInMillis() - recentMeasurement.getTimeInMillis()) <= 2.5*60*1000){
            return;
        }

        if(measurement.getDate().after(mostRecentMeasurement.getDate())){
            mostRecentMeasurement = measurement;
        }

        measurementSet.add(measurement);
        refreshMeasurements();
    }

    public void refreshMeasurements(){
        List<Measurement> toRemove = new ArrayList<>();
        for (Measurement m : measurementSet) {

            Calendar measurement = Calendar.getInstance();
            measurement.setTime(m.getDate());
            Calendar recentM = Calendar.getInstance();
            recentM.setTime(mostRecentMeasurement.getDate());

            if(Math.abs(recentM.getTimeInMillis()-measurement.getTimeInMillis()) > (long) days*24*60*60*1000){
                toRemove.add(m);
            }
        }

        toRemove.forEach(measurementSet::remove);

    }


    public int total(){
        return measurementSet.size();
    }

    public void status(Date from, Date to){
        if(measurementSet.stream().noneMatch(p->p.getDate().after(from) && p.getDate().before(to))){
            throw new RuntimeException();
        }
        measurementSet.stream()
                .filter(p->(p.getDate().equals(from) || p.getDate().after(from)) && (p.getDate().before(to) || p.getDate().equals(to)))
                .forEach(m->{
                    System.out.printf("%s\n", m);
                });
        System.out.printf("Average temperature: %.2f\n", measurementSet.stream()
                .filter(p->(p.getDate().equals(from) || p.getDate().after(from)) && (p.getDate().before(to) || p.getDate().equals(to)))
                .mapToDouble(Measurement::getTemperature)
                .average()
                .orElse(0.0));
    }


}





public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMeasurment(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}

