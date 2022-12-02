package kolokviumski.zad19vtoro;

import java.util.ArrayList;
import java.util.Scanner;


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

enum Color {
    RED, GREEN, BLUE
}
interface Scalable{
    void scale(float scaleFactor);
}
interface Stackable{
    float weight();
}
abstract class Shape implements Scalable,Stackable{
    String id;
    Color color;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    @Override
    public void scale(float scaleFactor) {

    }

    @Override
    public float weight() {
        return 0;
    }
    public abstract String getType();
    public abstract double getArea();

    @Override
    public abstract String toString();

    public String getId() {
        return id;
    }
}
class Circle extends Shape{
    float radius;

    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
    }

    @Override
    public String getType() {
        return "Circle";
    }

    @Override
    public double getArea() {
        return (double) radius*radius*Math.PI;
    }

    @Override
    public String toString() {
        return String.format("C: %-5s%-10s%10.2f", id, color.toString(), getArea());
    }

    public float getRadius() {
        return radius;
    }
}
class Square extends Shape{
    float width, height;

    public Square(String id, Color color, float width, float height) {
        super(id, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public String getType() {
        return "Square";
    }

    @Override
    public double getArea() {
        return (double) width*height;
    }
    @Override
    public String toString() {
        return String.format("R: %-5s%-10s%10.2f", id, color.toString(), getArea());
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
class Canvas {
    ArrayList<Shape> shapes= new ArrayList<>();
    void add(String id, Color color, float radius){
        Circle newCircle = new Circle(id,color,radius);
        if(shapes.size()==0){
            shapes.add(newCircle);
        }else{
            int counter=0;
            for (Shape shape : shapes) {
                if (newCircle.getArea() > shape.getArea()) {
                    break;
                }
                counter++;
            }
            shapes.add(counter, newCircle);
        }
    }
    void add(String id, Color color, float width, float height){
        Square newSquare = new Square(id,color,width,height);
        if(shapes.size()==0){
            shapes.add(newSquare);
        }else{
            int counter=0;
            for (Shape shape : shapes) {
                if (newSquare.getArea() > shape.getArea()) {
                    break;
                }
                counter++;
            }
            shapes.add(counter, newSquare);
        }
    }
    void scale(String id, float scaleFactor){
        Shape newShape;
        for(int i=0;i<shapes.size();i++){
            if(shapes.get(i).getId().equals(id)){
                newShape=shapes.get(i);
                if(newShape.getType().equals("Square")){
                    float side1 = ((Square) newShape).getWidth();
                    float side2 = ((Square) newShape).getHeight();
                    shapes.remove(i);
                    add(newShape.getId(), newShape.color, side1*scaleFactor, side2*scaleFactor);
                    break;
                }else{
                    float radius = ((Circle) newShape).getRadius()*scaleFactor;
                    shapes.remove(i);
                    add(newShape.getId(), newShape.color, radius);
                    break;
                }
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Shape i:shapes){
            sb.append(i.toString()).append("\n");
        }
        return sb.toString();
    }
}