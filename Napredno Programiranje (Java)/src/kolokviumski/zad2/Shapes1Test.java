package kolokviumski.zad2;

import java.io.*;
import java.util.*;

public class Shapes1Test {

    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        try {
            System.out.println(shapesApplication.readCanvases(System.in));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}

class Square{
    private int a;

    public Square(int a){
        this.a=a;
    }

    public int getA() {
        return a;
    }

    public int getPerimeter(){
        return 4*a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return a == square.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }
}

//prozorec
class Canvases{
    private ArrayList<Square> squares = new ArrayList<Square>();
    private String id;

    public Canvases(String id, ArrayList<Square> squares){
        this.id=id;
        this.squares=squares;
    }

    public String getId() {
        return id;
    }

    public int totalPerimeter(){
        int sum=0;
        for(Square i:squares){
            sum+=i.getPerimeter();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(" ");
        sb.append(squares.size()).append(" ");
        sb.append(totalPerimeter()).append("\n");
        return sb.toString();
    }
}

class ShapesApplication{
    ArrayList<Canvases> canvases;

    public ShapesApplication(){
        canvases = new ArrayList<>();
    }

    public int readCanvases(InputStream inputStream) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int cnt=0;
        while((line = bf.readLine())!=null && line.length()!=0){
            String [] info = line.split(" ");
            String ime = info[0];
            ArrayList<Square> squares = new ArrayList<>();
            for(int i=1;i<info.length;i++){
                squares.add(new Square(Integer.parseInt(info[i])));
                cnt++;
            }
            Canvases canvas = new Canvases(ime, squares);
            canvases.add(canvas);
        }
        return cnt;
    }

    public void printLargestCanvasTo (OutputStream outputStream){
        Canvases max = canvases.stream()
                        .max(Comparator.comparing(Canvases::totalPerimeter))
                        .orElseThrow(NoSuchElementException::new);

        System.out.print(max);
    }

}
