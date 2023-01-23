package laboratoriski.lab7.zad1;

import java.util.*;
import java.util.stream.Collectors;

class Scheduler<T>{
    T t;
    Date date;
    Date now;
    Map<Date, T> objectsByDate;
    List<T> list;

    public Scheduler() {
        objectsByDate = new TreeMap<>();
        list = new ArrayList<>();
        now = new Date();
    }

    public void add(Date d, T t){
        objectsByDate.put(d, t);
    }

    public boolean remove(Date d){
        if(objectsByDate.containsKey(d)){
            objectsByDate.remove(d);
            return true;
        }
        return false;
    }

    public <Т> ArrayList<Т> getAll(Date begin, Date end){
        return (ArrayList<Т>) objectsByDate.keySet().stream()
                .filter(p-> p.after(begin) && p.before(end))
                .map(k->objectsByDate.get(k))
                .collect(Collectors.toList());
    }

    public T getFirst(){
        list = objectsByDate.values().stream().collect(Collectors.toList());
        return list.get(0);
    }

    public T getLast(){
        list = objectsByDate.values().stream().collect(Collectors.toList());
        return list.get(list.size()-1);
    }

    public T next(){
        List<Date> list1 = objectsByDate.keySet().stream()
                .filter(k -> k.after(now))
                .collect(Collectors.toList());
        return objectsByDate.get(list1.get(0));
    }

    public T last(){
        List<Date> list1 = objectsByDate.keySet().stream()
                .filter(k -> k.before(now))
                .collect(Collectors.toList());
        return objectsByDate.get(list1.get(list1.size()-1));
    }

}

public class SchedulerTest {
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            Scheduler<String> scheduler = new Scheduler<String>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime()-7200000), jin.next());
            scheduler.add(new Date(now.getTime()-3600000), jin.next());
            scheduler.add(new Date(now.getTime()-14400000), jin.next());
            scheduler.add(new Date(now.getTime()+7200000), jin.next());
            scheduler.add(new Date(now.getTime()+14400000), jin.next());
            scheduler.add(new Date(now.getTime()+3600000), jin.next());
            scheduler.add(new Date(now.getTime()+18000000), jin.next());
            System.out.println(scheduler.getFirst());
            System.out.println(scheduler.getLast());
        }
        if ( k == 3 ) { //test Scheduler with String
            Scheduler<String> scheduler = new Scheduler<String>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime()-7200000), jin.next());
            scheduler.add(new Date(now.getTime()-3600000), jin.next());
            scheduler.add(new Date(now.getTime()-14400000), jin.next());
            scheduler.add(new Date(now.getTime()+7200000), jin.next());
            scheduler.add(new Date(now.getTime()+14400000), jin.next());
            scheduler.add(new Date(now.getTime()+3600000), jin.next());
            scheduler.add(new Date(now.getTime()+18000000), jin.next());
            System.out.println(scheduler.next());
            System.out.println(scheduler.last());
            ArrayList<String> res = scheduler.getAll(new Date(now.getTime()-10000000), new Date(now.getTime()+17000000));
            Collections.sort(res);
            for ( String t : res ) {
                System.out.print(t+" , ");
            }
        }
        if ( k == 4 ) {//test Scheduler with ints complex
            Scheduler<Integer> scheduler = new Scheduler<Integer>();
            int counter = 0;
            ArrayList<Date> to_remove = new ArrayList<Date>();

            while ( jin.hasNextLong() ) {
                Date d = new Date(jin.nextLong());
                int i = jin.nextInt();
                if ( (counter&7) == 0 ) {
                    to_remove.add(d);
                }
                scheduler.add(d,i);
                ++counter;
            }
            jin.next();

            while ( jin.hasNextLong() ) {
                Date l = new Date(jin.nextLong());
                Date h = new Date(jin.nextLong());
                ArrayList<Integer> res = scheduler.getAll(l,h);
                Collections.sort(res);
                System.out.println(l+" <: "+print(res)+" >: "+h);
            }
            System.out.println("test");
            ArrayList<Integer> res = scheduler.getAll(new Date(0),new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
            for ( Date d : to_remove ) {
                scheduler.remove(d);
            }
            res = scheduler.getAll(new Date(0),new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
        }
    }

    private static <T> String print(ArrayList<T> res) {
        if ( res == null || res.size() == 0 ) return "NONE";
        StringBuffer sb = new StringBuffer();
        for ( T t : res ) {
            sb.append(t+" , ");
        }
        return sb.substring(0, sb.length()-3);
    }


}
