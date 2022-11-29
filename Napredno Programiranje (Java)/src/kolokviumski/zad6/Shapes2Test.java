package kolokviumski.zad6;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Shapes2Test {

    public static void main(String[] args){

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        try {
            shapesApplication.readCanvases(System.in);
        } catch (IOException | IrregularCanvasException e) {
            throw new RuntimeException(e);
        }

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);


    }
}

class IrregularCanvasException extends Exception{

    public IrregularCanvasException(String id, Double maxArea){
        super("Canvas " + id + " has a shape with area larger than 10000.00");
    }
}

interface Shapes{
    public double getArea();
    public String getType();
}

class Square implements Shapes{
    private int a;
    final private String type="S";

    public Square(int a){
        this.a=a;
    }


    @Override
    public double getArea() {
        return a*a;
    }

    @Override
    public String getType() {
        return type;
    }
}

class Circle implements Shapes{

    private int r;
    final private String type="C";

    public Circle(int r){
        this.r=r;
    }

    @Override
    public double getArea() {
        return r*r*Math.PI;
    }

    @Override
    public String getType() {
        return type;
    }
}

class Canvases{
    private String id;
    private Double maxArea;

    private Double minArea, averageArea;
    private ArrayList<Shapes> shapes = new ArrayList<Shapes>();

    public Canvases(String id, ArrayList<Shapes> shapes){
        this.id=id;
        this.shapes = shapes;
    }

    public long getTotalShapes(){
        return shapes.stream().count();
    }

    public long getTotalCircles(){
        return shapes.stream().filter(p -> p.getType().equals("C")).count();
    }

    public long getTotalSquares(){
        return shapes.stream().filter(p -> p.getType().equals("S")).count();
    }

    public Double getMinArea(){

        Shapes s = shapes.stream()
                .min(Comparator.comparing(Shapes::getArea))
                .orElseThrow(NoSuchElementException::new);

        return s.getArea();
    }
    public Double getMaxArea(){

        Shapes s = shapes.stream()
                .max(Comparator.comparing(Shapes::getArea))
                .orElseThrow(NoSuchElementException::new);

        return s.getArea();
    }

    public Double getAverageArea(){
        return shapes.stream()
                .mapToDouble(Shapes::getArea)
                .average()
                .orElseThrow(NoSuchElementException::new);
    }

    public Double getSum(){
        Double d = shapes.stream()
                .mapToDouble(Shapes::getArea)
                .sum();
        return d;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return id + " " + getTotalShapes() + " " + getTotalCircles() + " " + getTotalSquares() + " " +
                df.format(getMinArea()) + " " +  df.format(getMaxArea())+ " " +  df.format(getAverageArea());
    }
}

class ShapesApplication{
    private static double maxArea;
    private ArrayList<Canvases> canvases = new ArrayList<Canvases>();

    public ShapesApplication(double maxArea){
        this.maxArea = maxArea;
    }

    public static double getMaxArea() {
        return maxArea;
    }

    public void readCanvases(InputStream in) throws IOException, IrregularCanvasException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        String line;
        while((line = bf.readLine())!=null && line.length()!=0){
            String [] parts = line.split("\\s+");
            String id = parts[0];
            ArrayList<Shapes> shapes = new ArrayList<>();
            int flag=1;

            for (int i = 1; i < parts.length; i++) {
                try {
                    if (i % 2 == 1) {
                        if (parts[i].equals("C")) {
                            shapes.add(new Circle(Integer.parseInt(parts[i + 1])));
                        } else if (parts[i].equals("S")) {
                            shapes.add(new Square(Integer.parseInt(parts[i + 1])));
                        }
                        if(shapes.get(shapes.size()-1).getArea() > maxArea){
                            throw new IrregularCanvasException(id, maxArea);
                        }
                    }
                }catch(IrregularCanvasException e){
                    flag=0;
                    System.out.println(e.getMessage());
                }
            }

            if(flag==1) {
                canvases.add(new Canvases(id, shapes));
            }
        }
    }

    public void printCanvases(OutputStream out) {
        PrintWriter pw = new PrintWriter(out);

        List<Canvases> ordered = canvases.stream()
                .sorted(Comparator.comparing(Canvases::getSum).reversed()).toList();
        IntStream.range(0, ordered.size())
                .forEach( i-> pw.println(ordered.get(i).toString()));
        pw.flush();
    }
}
