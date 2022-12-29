package kolokviumski.kol1.zad14;

import java.util.ArrayList;
import java.util.Scanner;

enum Color {
    RED, GREEN, BLUE
}
public class ShapesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Canvas canvas = new Canvas();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            int type = Integer.parseInt(parts[0]);
            String id = parts[1];
            if (type == 1) {
                Color color = Color.valueOf(parts[2]);
                float radius = Float.parseFloat(parts[3]);
                canvas.add(id, color, radius);
            } else if (type == 2) {
                Color color = Color.valueOf(parts[2]);
                float width = Float.parseFloat(parts[3]);
                float height = Float.parseFloat(parts[4]);
                canvas.add(id, color, width, height);
            } else if (type == 3) {
                float scaleFactor = Float.parseFloat(parts[2]);
                System.out.println("ORIGNAL:");
                System.out.print(canvas);
                canvas.scale(id, scaleFactor);
                System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
                System.out.print(canvas);
            }

        }
    }
}

interface Scalable{
    void scale(float scaleFactor);
}
interface Stackable{
    double weight();
}

abstract class Shape implements Scalable, Stackable{
    String id;
    Color color;
    float side;
    String type;

    public Shape(String id, Color color, float side) {
        this.id = id;
        this.color = color;
        this.side = side;
    }

    public float getSide() {
        return side;
    }

    public Color col(){
        return color;
    }

    public String getId(){
        return id;
    }

    public String getColor(){
        switch(color){
            case RED:
                return "RED";
            case BLUE:
                return "BLUE";
            case GREEN:
                return "GREEN";
        }
        return "";
    }

    abstract String getType();

}

class Circle extends Shape implements Scalable,Stackable{

    public Circle(String id, Color color, float side) {
        super(id, color, side);
    }

    public float getRadius(){
        return side;
    }

    @Override
    String getType() {
        return "C";
    }

    @Override
    public void scale(float scaleFactor) {
        side = side*scaleFactor;
    }

    @Override
    public double weight() {
        return side*side*Math.PI;
    }
    @Override
    public String toString() {
        return String.format("C: %-5s%10s%10.2s\n", id, getColor(), weight());
    }
}

class Rectangle extends Shape implements Scalable, Stackable{
    float height;

    public Rectangle(String id, Color color, float side, float height) {
        super(id, color, side);
        this.height=height;
    }



    @Override
    String getType() {
        return "R";
    }

    @Override
    public String toString() {
        return String.format("R: %-5s%10s%10.2s\n", id, getColor(), weight());
    }

    @Override
    public void scale(float scaleFactor) {
        side=side*scaleFactor;
        height=height*scaleFactor;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public double weight() {
        return side*height;
    }

    public float getWidth(){
        return side;
    }
}
class Canvas {
    String id;
    Color color;
    ArrayList<Shape> shapes = new ArrayList<>();

    public Canvas() {}

    void add(String id, Color color, float radius){
        if(shapes.size()==0) {
            shapes.add(new Circle(id, color, radius));
        }else{
            Circle c = new Circle(id,color,radius);
            int cnt=0;
            for(int i=0;i<shapes.size();i++){
                if(c.weight()<shapes.get(i).weight()){
                    cnt++;
                }
                break;
            }
            shapes.add(cnt,c);
        }
    }
    void add(String id, Color color, float width, float height){
        if (shapes.size()==0) {
            shapes.add(new Rectangle(id, color, width, height));
        }else{
            Rectangle c = new Rectangle(id,color,width, height);
            int cnt=0;
            for(int i=0;i<shapes.size();i++){
                if(c.weight()<shapes.get(i).weight()){
                    cnt++;
                }
                break;

            }
            shapes.add(cnt,c);
        }
    }



    void scale(String id, float scaleFactor){
        int i;
        for(i=0;i<shapes.size();i++) {
            if (shapes.get(i).getId().equals(id)) {
                shapes.get(i).scale(scaleFactor);
                break;
            }
        }
        Shape newShape = shapes.get(i);
        if(shapes.get(i).getType().equals("Circle")){
            shapes.remove(i);
            float radius = ((Circle) newShape).getRadius();
            add(newShape.getId(), newShape.col(),radius);
        }else{
            shapes.remove(i);
            float width = ((Rectangle) newShape).getWidth();
            float height = ((Rectangle)newShape).getHeight();
            add(newShape.getId(), newShape.col(),width, height);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Shape s: shapes){
            sb.append(s);
        }
        return sb.toString();
    }
}
