package laboratoriski.lab7.zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Apteka {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        Map<ItemName, Item> map = new HashMap<ItemName, Item>();


        for(int i=0;i<N;i++){
            String [] parts = bf.readLine().split("\\s+");
            String name = parts[0];
            int positiveList = Integer.parseInt(parts[1]);
            int price = Integer.parseInt(parts[2]);
            int quantity = Integer.parseInt(parts[3]);
            ItemName item = new ItemName(name.toUpperCase());
            Item drug = new Item(name.toUpperCase(), positiveList, price, quantity);
            map.put(item, drug);
        }
        String line;
        while(!(line = bf.readLine()).equals("KRAJ")){
            line = line.toUpperCase();
            int amount = Integer.parseInt(bf.readLine());
            ItemName name = new ItemName(line);
            Item item = null;
            if(!map.containsKey(name)){
                System.out.println("Nema takov lek");
                continue;
            }else{
                item = map.get(name);
                System.out.println(item);
            }

            if(amount > item.getAmount()){
                System.out.println("Nema dovolno lekovi");
            }else{
                item.buy(amount);
                System.out.println("Napravena naracka");
            }
        }


    }
}


class ItemName{
    private String name;
    public ItemName(String name) {
        this.name=name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemName itemName = (ItemName) o;
        return Objects.equals(name, itemName.name);
    }

    public int ASCII(char c){
        return (int) c;
    }

    @Override
    public int hashCode() {
        return (29*(29*(ASCII(name.charAt(0)))+ASCII(name.charAt(1)))+ASCII(name.charAt(2)))%102780;
    }
}

class Item{
    private String name;
    private int positiveList;
    private int price;
    private int quantity;

    public Item(){}

    public Item(String name, int positiveList, int price, int quantity) {
        this.name = name;
        this.positiveList = positiveList;
        this.price = price;
        this.quantity = quantity;
    }

    public String pos(){
        if(positiveList==1){
            return "POZ";
        }else{
            return "NEG";
        }
    }

    public void buy(int amount){
        this.quantity -= amount;
    }

    public int getAmount(){
        return quantity;
    }

    @Override
    public String toString() {
        return name + "\n" + pos() + "\n"
                + price + "\n" + quantity;
    }
}