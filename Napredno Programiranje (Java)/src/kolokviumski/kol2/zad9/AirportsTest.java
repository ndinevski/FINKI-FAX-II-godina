package kolokviumski.kol2.zad9;
import java.text.DecimalFormat;
import java.util.*;


class Airport{
    String name, country, code;
    int passengers;

    Set<Flight> flights;

   

    public Airport(String name, String country, String code, int passengers) {
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
        flights = new TreeSet<>(Comparator.comparing(Flight::getTo).thenComparing(Flight::getTime));
    }

    public void addFlight(Flight flight){
        flights.add(flight);
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
    public Set<Flight> getFlights() {
        return flights;
    }

    public String getCode() {
        return code;
    }

    public int getPassengers() {
        return passengers;
    }
}


class Flight{
    String from, to;
    int time, duration;
    int depHour, depMin, arrHour, arrMin, totalH, totalM, day;


    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
        day=0;
        evaluateTime();
    }

    public void evaluateTime(){
        depHour = (time/60);
        depMin = time-depHour*60;
        totalH = (duration/60);
        totalM = duration - totalH*60;
        arrHour = ((time+duration)/60);
        arrMin = (time+duration) - arrHour*60;
        if(arrHour>=24){
            day=1;
        }
        depHour = (time/60)%24;
        totalH = (duration/60)%24;
        arrHour = ((time+duration)/60)%24;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return time == flight.time && duration == flight.duration && Objects.equals(from, flight.from) && Objects.equals(to, flight.to);
    }

    public String getTimeFlight(){
        DecimalFormat df = new DecimalFormat("00");
        if(day==1){
            return String.format("+1d %sh%sm", totalH, df.format(totalM));
        }
        return String.format("%sh%sm", totalH, df.format(totalM));
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("00");
        return String.format("%s-%s %s:%s-%s:%s %s", from, to, df.format(depHour), df.format(depMin), df.format(arrHour), df.format(arrMin), getTimeFlight());
    }
}

class Airports{

    Map<String, Airport> airportsByCode;

    Map<String, Set<Flight>> allFlights;
    List<Flight> flights;


    public Airports() {
        airportsByCode = new HashMap<>();
        allFlights = new HashMap<>();
        flights = new ArrayList<>();
    }

    public void addAirport(String name, String country, String code, int passengers){
        Airport airport = new Airport(name,country,code,passengers);

        airportsByCode.putIfAbsent(code, airport);
    }

    public void addFlights(String from, String to, int time, int duration){
        Flight flight = new Flight(from, to, time, duration);
        if(airportsByCode.containsKey(from)){
            airportsByCode.get(from).addFlight(flight);
        }

        allFlights.putIfAbsent(from, new HashSet<>());
        allFlights.get(from).add(flight);
        flights.add(flight);
    }

    public String showFlightsFromAirport(String code){
        StringBuilder sb = new StringBuilder();
        Airport airport = airportsByCode.get(code);

        sb.append(String.format("%s (%s)\n%s\n%d\n", airport.getName(),airport.getCode(), airport.getCountry(), airport.getPassengers()));

        int i =1;

        for (Flight flight : airport.flights) {
            sb.append(String.format("%d. %s\n", i, flight));
            i++;
        }
        return sb.toString();
    }

    public void showDirectFlightsFromTo(String from, String to){
        allFlights.get(from).stream().filter(p->p.getTo().equals(to))
                .forEach(System.out::println);
        if(allFlights.get(from).stream().noneMatch(p -> p.getTo().equals(to))){
            System.out.printf("No flights from %s to %s\n",from, to);
        }
    }

    public void showDirectFlightsTo(String to){
        flights.stream()
                .filter(p->p.getTo().equals(to))
                .sorted(Comparator.comparing(Flight::getTime).thenComparing(Flight::getFrom))
                .forEach(System.out::println);
    }


}


public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        System.out.printf(airports.showFlightsFromAirport(from));
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}

// vashiot kod ovde


