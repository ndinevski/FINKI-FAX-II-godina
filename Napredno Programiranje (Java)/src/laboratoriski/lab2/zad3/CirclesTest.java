package laboratoriski.lab2.zad3;

import java.util.*;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class CirclesTest {

    public static void main(String[] args) throws ObjectCanNotBeMovedException {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                try {
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } catch (MovableObjectNotFittableException e) {
                    throw new RuntimeException(e);
                }
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                try {
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");

        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);

        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");

        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);

        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");

        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);


        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");

        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);

        System.out.println(collection.toString());


    }


}


interface Movable {

    public void moveUp();

    public void moveDown();

    public void moveRight();

    public void moveLeft();

    public int getCurrentXPosition();

    public int getCurrentYPosition();

    public TYPE getType();

    public int getRadius();

    public int getXSpeed();
    public int getYSpeed();
}

class MovablePoint implements Movable{

    private int x,y;
    private int xSpeed, ySpeed;
    static TYPE type;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        type = TYPE.POINT;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public TYPE getType(){
        return type;
    }

    @Override
    public int getRadius() {
        return 0;
    }

    @Override
    public void moveUp() {
        this.y += ySpeed;
    }

    @Override
    public void moveDown() {
        this.y -= ySpeed;
    }

    @Override
    public void moveRight() {
        this.x += xSpeed;
    }

    @Override
    public void moveLeft() {
        this.x -= xSpeed;
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    @Override
    public String toString() {
        return "Movable point with coordinates (" + x + "," + y + ")\n";
    }
}


class MovableCircle implements Movable{
    private int radius;
    private MovablePoint center;
    static private TYPE type;

    public MovableCircle(int radius, MovablePoint movablePoint) {
        this.radius = radius;
        this.center = movablePoint;
        type = TYPE.CIRCLE;
    }

    public TYPE getType(){
        return type;
    }

    @Override
    public void moveUp() {
        center.moveUp();
    }

    @Override
    public void moveDown() {
        center.moveDown();
    }

    @Override
    public void moveRight() {
        center.moveRight();
    }

    @Override
    public void moveLeft() {
        center.moveLeft();
    }

    @Override
    public int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }

    public int getRadius(){
        return radius;
    }

    @Override
    public int getXSpeed() {
        return center.getXSpeed();
    }

    @Override
    public int getYSpeed() {
        return center.getYSpeed();
    }

    @Override
    public String toString() {
        return "Movable circle with center coordinates (" + center.getCurrentXPosition() + ","
                + center.getCurrentYPosition() + ") and radius " + radius + "\n";
    }

}

class MovableObjectNotFittableException extends Exception{
    MovableObjectNotFittableException(Movable m){
        super("Movable circle with center ("+ m.getCurrentXPosition() + "," + m.getCurrentYPosition()+ ") and radius "+m.getRadius()+" can not be fitted into the collection");
    }
}

class ObjectCanNotBeMovedException extends Exception{
    int x,y,xSpeed,ySpeed;
    DIRECTION d;
    ObjectCanNotBeMovedException(Movable m, DIRECTION direction){
        x = m.getCurrentXPosition();
        y = m.getCurrentYPosition();
        xSpeed = m.getXSpeed();
        ySpeed = m.getYSpeed();
        d = direction;
    }

    public String getMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("Point (");
        switch (d){
            case UP:
                sb.append(x).append(",").append(y+ySpeed);
                break;
            case DOWN:
                sb.append(x).append(",").append(y-ySpeed);
                break;
            case RIGHT:
                sb.append(x+xSpeed).append(",").append(y);
                break;
            case LEFT:
                sb.append(x-xSpeed).append(",").append(y);

        }
        sb.append(") is out of bounds");
        return sb.toString();
    }
}

class MovablesCollection{
    static int X_MIN = 0;
    static int Y_MIN = 0;
    static int X_MAX;
    static int Y_MAX;
    private ArrayList<Movable> movable = new ArrayList<>();

    MovablesCollection(int x_MAX, int y_MAX){
        MovablesCollection.X_MAX = x_MAX;
        MovablesCollection.Y_MAX = y_MAX;
    }

    public static void setxMax(int x){
        MovablesCollection.X_MAX = x;
    }
    public static void setyMax(int y){
        MovablesCollection.Y_MAX = y;
    }

    public void addMovableObject(Movable m) throws MovableObjectNotFittableException {
        if((m.getCurrentXPosition() + m.getRadius())>=X_MIN && (m.getCurrentXPosition() + m.getRadius()) <= X_MAX && m.getCurrentXPosition() - m.getRadius() >=X_MIN && m.getCurrentXPosition() - m.getRadius()<=X_MAX &&
                (m.getCurrentYPosition() + m.getRadius())>=Y_MIN && (m.getCurrentYPosition()+m.getRadius())<=Y_MAX && m.getCurrentYPosition() - m.getRadius() >=Y_MIN && m.getCurrentYPosition() - m.getRadius()<=Y_MAX){
            movable.add(m);
        }else{
           throw new MovableObjectNotFittableException(m);
        }
    }

    public void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction) throws ObjectCanNotBeMovedException {

        for(int i=0;i< movable.size();i++) {
            try{
            if (movable.get(i).getType().equals(type)) {
                switch (direction) {
                    case UP:
                        if (movable.get(i).getCurrentYPosition() + movable.get(i).getYSpeed() >= Y_MAX || movable.get(i).getCurrentYPosition() + movable.get(i).getYSpeed() <= Y_MIN) {
                            throw new ObjectCanNotBeMovedException(movable.get(i), direction);
                        } else {
                            movable.get(i).moveUp();
                            break;
                        }
                    case DOWN:
                        if (movable.get(i).getCurrentYPosition() - movable.get(i).getYSpeed() >= Y_MAX || movable.get(i).getCurrentYPosition() - movable.get(i).getYSpeed() <= Y_MIN) {
                            throw new ObjectCanNotBeMovedException(movable.get(i), direction);
                        } else {
                            movable.get(i).moveDown();
                            break;
                        }
                    case RIGHT:
                        if (movable.get(i).getCurrentXPosition() + movable.get(i).getXSpeed() >= X_MAX || movable.get(i).getCurrentXPosition() + movable.get(i).getXSpeed() <= X_MIN) {
                            throw new ObjectCanNotBeMovedException(movable.get(i), direction);
                        } else {
                            movable.get(i).moveRight();
                            break;
                        }
                    case LEFT:
                        if (movable.get(i).getCurrentXPosition() - movable.get(i).getXSpeed() >= X_MAX || movable.get(i).getCurrentXPosition() - movable.get(i).getXSpeed() <= X_MIN) {
                            throw new ObjectCanNotBeMovedException(movable.get(i), direction);
                        } else {
                            movable.get(i).moveLeft();
                            break;
                        }
                }
            }
        }catch (ObjectCanNotBeMovedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Collection of movable objects with size ").append(movable.size()).append(":\n");
        for (Movable m:movable) {
            sb.append(m.toString());
        }
        return sb.toString();
    }
}

