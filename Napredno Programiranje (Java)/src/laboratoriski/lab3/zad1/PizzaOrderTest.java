package laboratoriski.lab3.zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class OrderLockedException extends Exception{
    public OrderLockedException(String mess){
        super(mess);
    }
}

class EmptyOrder extends Exception{
    public EmptyOrder(String mess){
        super(mess);
    }
}
class ArrayIndexOutOfBоundsException extends Exception{
    public ArrayIndexOutOfBоundsException(String mess){
        super(mess);
    }
}
class InvalidExtraTypeException extends Exception{
    public InvalidExtraTypeException(String mess) {
        super(mess);
    }
}

class InvalidPizzaTypeException extends Exception{
    public InvalidPizzaTypeException(String mess) {
        super(mess);
    }
}

class ItemOutOfStockException extends  Exception{
    public ItemOutOfStockException(String mess){
        super(mess);
    }
}
interface Item{
    int getPrice();
    String getType();
    int getCount();
    void setCount(int count);
}

class ExtraItem implements Item{
    private String type;
    private int count;
    public ExtraItem(String type) throws InvalidExtraTypeException {
        if(type.equals("Ketchup") || type.equals("Coke")){
            this.type = type;
            this.count=1;
        }else{
            throw new InvalidExtraTypeException("Nevaliden tip");
        }
    }
    @Override
    public int getPrice() {
        switch(type){
            case "Ketchup":
                return count*3;
            case "Coke":
                return count*5;
        }
        return 0;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }


}


class PizzaItem implements Item{
    private String type;
    private int count;
    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if(type.equals("Standard") || type.equals("Pepperoni") || type.equals("Vegetarian")){
            this.type = type;
            this.count = 1;
        }else{
            throw new InvalidPizzaTypeException("Nevaliden tip piza");
        }
    }

    @Override
    public int getPrice() {
        switch (type){
            case "Standard":
                return count*10;
            case "Pepperoni":
                return count*12;
            case "Vegetarian":
                return count*8;
        }
        return 0;
    }
    @Override
    public void setCount(int count) {
        this.count = count;
    }
    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getCount() {
        return count;
    }


}

class Order{
    private List<Item> items;
    boolean LOCK;

    public Order() {
        items = new ArrayList<>();
        LOCK = false;
    }

    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
        if(LOCK){
            throw new OrderLockedException("Zakluceni");
        }

        if(count>10){
            throw new ItemOutOfStockException(item.getType() + " is out of stock");
        }
        for(int i=0;i< items.size();i++){
            if(items.get(i).getType().equals(item.getType())){
                items.set(i, item);
                items.get(i).setCount(count);
                return;
            }
        }
        item.setCount(count);
        items.add(item);
    }

    public long getPrice(){
        return items.stream().mapToLong(Item::getPrice).sum();
    }

    public void displayOrder(){
        StringBuilder sb =new StringBuilder();
        for(int i=0;i< items.size();i++){
            System.out.printf("%3d.%-15sx%2d%5d$\n", i+1, items.get(i).getType(), items.get(i).getCount(), items.get(i).getPrice());
        }
        System.out.printf("%-22s%5d$\n", "Total:", getPrice());
    }

    public void removeItem(int idx) throws ArrayIndexOutOfBоundsException, OrderLockedException {
        if(LOCK){
            throw new OrderLockedException("Zakluceni");
        }
        if(idx >= items.size()){
            throw new ArrayIndexOutOfBоundsException(idx + " ne postoi");
        }
        items.remove(idx);
    }

    public void lock() throws EmptyOrder {
        if(items.size()==0){
            throw new EmptyOrder("Prazna lista");
        }
        LOCK = true;
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