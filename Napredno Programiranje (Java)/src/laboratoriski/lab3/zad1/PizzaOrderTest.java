package laboratoriski.lab3.zad1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

interface Item{
    public int getPrice();

    public String getType();
}
class ArrayIndexOutOfBoundsException extends Exception{
    ArrayIndexOutOfBoundsException(int idx){
        super(idx + "is out of bounds\n");
    }
}

class EmptyOrderException extends Exception{
    EmptyOrderException(){
        super("Nema elementi narackata\n");
    }
}
class ItemOutOfStockException extends Exception{
    ItemOutOfStockException(Item item){
        super(item.getType() + "is out of stock\n");
    }
}
class InvalidPizzaTypeException extends Exception{
    InvalidPizzaTypeException(){
        super("Picata ja nema na menito\n");
    }
}
class InvalidExtraTypeException extends Exception{
    InvalidExtraTypeException(){
        super("Extra nema na menito\n");
    }
}
class PizzaItem implements Item{
    private String type;


    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if(!type.equals("Standard") || !type.equals("Pepperoni") || !type.equals("Vegetarian")){
            throw new InvalidPizzaTypeException();
        }else {
            this.type = type;
        }
    }

    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        if(type.equals("Standard")){
            return 10;
        }else if(type.equals("Pepperoni")){
            return 12;
        }else{
            return 8;
        }
    }

}

class ExtraItem implements Item{
    private String type;
    public ExtraItem(String type) throws InvalidExtraTypeException {
        if(!type.equals("Ketchup") || !type.equals("Coke")){
            throw new InvalidExtraTypeException();
        }else {
            this.type = type;
        }
    }

    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        if(type.equals("Ketchup")){
            return 3;
        }else{
            return 5;
        }
    }
}

class Order{
    private ArrayList<Item> items;
    private int [] counts;
    private boolean locked;

    public Order(){
        this.items = new ArrayList<>();
        this.counts = new int[0];
        this.locked = false;
    }

    public void addItem(Item item, int count) throws ItemOutOfStockException {
        if(count>10){
            throw new ItemOutOfStockException(item);
        }
        int flag=0;
        if(!locked) {
            for (int i = 0; i < items.size(); i++) {
                if (item.getType().equals(items.get(i).getType())) {
                    items.set(i, item);
                    counts[i] = count;
                    flag = 1;
                }
            }
            if (flag == 0) {
                items.add(item);
                int[] tmp = new int[counts.length + 1];
                tmp[counts.length] = count;
                counts = new int[counts.length + 1];
                counts = tmp;
            }
        }
    }
    public int getPrice(){
        int sum=0;
        for(int i=0;i< items.size();i++){
            sum+=(items.get(i).getPrice() * counts[i]);
        }
        return sum;
    }

    public void displayOrder(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i< items.size();i++){
            sb.append("  ").append(i+1).append(items.get(i).getType()).append("\tx ")
                    .append(counts[i]).append("   ").append(items.get(i).getPrice()).append("\n");
        }
        sb.append("Total:                                  ").append(getPrice()).append("\n");
        sb.toString();
    }

    public void removeItem(int idx) throws ArrayIndexOutOfBoundsException {
        if(counts.length<=idx){
            throw new ArrayIndexOutOfBoundsException(idx);
        }
        if(!locked) {
            for (int i = idx - 1; i < items.size(); i++) {
                items.set(i, items.get(i + 1));
            }
            items.set(items.size() - 1, null);
        }
    }

    public void lock() throws EmptyOrderException {
        if(counts.length==0){
            throw new EmptyOrderException();
        }
        this.locked = true;
    }

}





public class PizzaOrderTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test Item
            try {
                String type = jin.next();
                String name = jin.next();
                Item item = null;
                if (type.equals("Pizza")) item = new PizzaItem(name);
                else item = new ExtraItem(name);
                System.out.println(item.getPrice());
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
        if (k == 1) { // test simple order
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 2) { // test order with removing
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (jin.hasNextInt()) {
                try {
                    int idx = jin.nextInt();
                    order.removeItem(idx);
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 3) { //test locking & exceptions
            Order order = new Order();
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.addItem(new ExtraItem("Coke"), 1);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.removeItem(0);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
    }

}
