package kolokviumski.kol2.zad7;

import java.util.*;

class SeatTakenException extends Exception{
    public SeatTakenException() {
        super();
    }
}

class SeatNotAllowedException extends Exception{
    public SeatNotAllowedException() {
        super();
    }
}


class Sector{
    private String code;
    private int avilableSeats;
    private int totalSeats;
    private int typeOfSector;
    private int flag;
    private Map<Integer,Integer> ticketsBySeat;


    public Sector(String code, int avilableSeats) {
        this.code = code;
        this.avilableSeats = avilableSeats;
        this.totalSeats = avilableSeats;
        ticketsBySeat = new HashMap<>();
        typeOfSector = 0;
        flag=0;
    }


    public String getCode() {
        return code;
    }

    public int getAvilableSeats() {
        return avilableSeats;
    }

    public void bookTicket(int seat, int type) throws SeatTakenException, SeatNotAllowedException {

        if(ticketsBySeat.containsKey(seat)){
            throw new SeatTakenException();
        }

        if(type!=0 && flag==0){
            typeOfSector=type;
            flag=1;
        }

        if((type==1 && typeOfSector==2) || (type==2 && typeOfSector==1)){
            throw new SeatNotAllowedException();
        }

        ticketsBySeat.putIfAbsent(seat,type);
        avilableSeats--;

    }

    public double getPercentage(){
        return (1-((double)avilableSeats/totalSeats))*100;

    }

    @Override
    public String toString() {
        return String.format("%s\t%d/%d\t%.1f%%", code, avilableSeats,totalSeats, getPercentage());
    }
}


class Stadium{
    private String name;
    //sektori
    private Map<String, Sector> sectorsByCode;


    public Stadium(String name) {
        this.name = name;
        sectorsByCode = new HashMap<>();
    }

    void createSectors(String[] sectorNames, int[] sizes){
        for(int i=0;i<sectorNames.length;i++){
            Sector sector = new Sector(sectorNames[i], sizes[i]);
            sectorsByCode.putIfAbsent(sector.getCode(), sector);
        }
    }

    void buyTicket(String sectorName, int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        sectorsByCode.get(sectorName).bookTicket(seat, type);
    }

    void showSectors(){
        sectorsByCode.values().stream()
                .sorted(Comparator.comparing(Sector::getAvilableSeats).reversed()
                        .thenComparing(Sector::getCode))
                .forEach(System.out::println);

    }
}









public class StaduimTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] sectorNames = new String[n];
        int[] sectorSizes = new int[n];
        String name = scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            sectorNames[i] = parts[0];
            sectorSizes[i] = Integer.parseInt(parts[1]);
        }
        Stadium stadium = new Stadium(name);
        stadium.createSectors(sectorNames, sectorSizes);
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            try {
                stadium.buyTicket(parts[0], Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]));
            } catch (SeatNotAllowedException e) {
                System.out.println("SeatNotAllowedException");
            } catch (SeatTakenException e) {
                System.out.println("SeatTakenException");
            }
        }
        stadium.showSectors();
    }
}

