package kolokviumski.kol2.zad3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


class Event{
    String name;
    String location;
    Date date;

    String day,month,hour,year;

    public Event(String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public void parseDate(){
        String [] parts = date.toString().split("\\s+");
        this.day = parts[2];
        this.year = parts[5];
        this.month = parts[1];
        this.hour = parts[3].substring(0,5);

    }

    @Override
    public String toString() {
        parseDate();
        return String.format("%s %s, %s %s at %s, %s", day, month, year, hour,location, name);
    }
}


class WrongDateException extends Exception{
    public WrongDateException(String mess) {
        super(mess);
    }
}

class EventCalendar{
    int year;

    Map<String, Set<Event>> eventsByDay;
    Map<Integer, Integer> eventsInMonth;

    public EventCalendar(int year) {
        this.year = year;
        eventsByDay = new HashMap<>();
        eventsInMonth = new HashMap<>();
        for(int i=1;i<13;i++){
            eventsInMonth.put(i,0);
        }
    }
    public void addEvent(String name, String location, Date date) throws WrongDateException {
        if(date.getYear()+1900!=year){
            throw new WrongDateException(String.format("Wrong date: %s", dString(date)));
        }

        Event newEvent = new Event(name,location,date);


        String [] parts = date.toString().split("\\s+");
        String day = parts[1] + parts[2];

        eventsInMonth.replace(date.getMonth()+1, eventsInMonth.get(date.getMonth()+1)+1);

        eventsByDay.putIfAbsent(day, new TreeSet<>(Comparator.comparing(Event::getDate).thenComparing(Event::getName)));
        eventsByDay.get(day).add(newEvent);
    }

    private String dString(Date date) {
        String [] parts = date.toString().split("\\s+");
        return String.format("%s %s %s %s UTC %s", parts[0], parts[1], parts[2], parts[3], parts[5]);
    }

    public void listEvents(Date date){
        String [] parts = date.toString().split("\\s+");
        String day = parts[1] + parts[2];


        if(eventsByDay.get(day)==null){
            System.out.println("No events on this day!");
            return;
        }

        eventsByDay.get(day).stream()
                .forEach(System.out::println);
    }

    public void listByMonth(){
        for(int i=1;i<13;i++){
            System.out.printf("%d : %d%n",i, eventsInMonth.get(i));
        }
    }
}

public class EventCalendarTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int year = scanner.nextInt();
        scanner.nextLine();
        EventCalendar eventCalendar = new EventCalendar(year);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            String name = parts[0];
            String location = parts[1];
            Date date = df.parse(parts[2]);
            try {
                eventCalendar.addEvent(name, location, date);
            } catch (WrongDateException e) {
                System.out.println(e.getMessage());
            }
        }
        Date date = df.parse(scanner.nextLine());
        eventCalendar.listEvents(date);
        eventCalendar.listByMonth();
    }
}