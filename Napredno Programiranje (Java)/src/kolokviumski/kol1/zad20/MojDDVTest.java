package kolokviumski.kol1.zad20;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class AmountNotAllowedException extends Exception{
    public AmountNotAllowedException(String mess){
        super(mess);
    }
}

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        try {
            mojDDV.readRecords(System.in);
        } catch (IOException | AmountNotAllowedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

    }
}

abstract class Product{
    private int price;
    private String type;

    public Product(int price){
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    abstract String getType();
    abstract double getTaxPercentage();
    abstract double taxForProduct();
}

class ProductA extends Product{
    private static String type = "A";

    public ProductA(int price) {
        super(price);
    }

    @Override
    String getType() {
        return type;
    }

    @Override
    double getTaxPercentage() {
        return 0.18;
    }

    @Override
    double taxForProduct() {
        return getTaxPercentage()*getPrice();
    }
}
class ProductB extends Product{
    private static String type = "B";

    public ProductB(int price) {
        super(price);
    }

    @Override
    String getType() {
        return type;
    }

    @Override
    double getTaxPercentage() {
        return 0.05;
    }
    @Override
    double taxForProduct() {
        return getTaxPercentage()*getPrice();
    }
}
class ProductV extends Product{
    private static String type = "V";

    public ProductV(int price) {
        super(price);
    }

    @Override
    String getType() {
        return type;
    }

    @Override
    double getTaxPercentage() {
        return 0;
    }
    @Override
    double taxForProduct() {
        return getTaxPercentage()*getPrice();
    }
}

class Reciept{
    private static double RETURN_TAX = 0.15;
    private String id;
    private List<Product> products = new ArrayList<Product>();

    public Reciept(String id, List<Product> products){
        this.id=id;
        this.products=products;
    }

    public int getSumOfAmounts(){
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }

    public double getTaxReturn(){
        return  RETURN_TAX * products.stream()
                .mapToDouble(Product::taxForProduct)
                .sum();
    }

    @Override
    public String toString() {
        return id + " " + getSumOfAmounts() + " "  + String.format("%.2f", getTaxReturn()) + "\n";
    }
}


class MojDDV{
    private List<Reciept> reciepts;

    public MojDDV(){
        this.reciepts = new ArrayList<Reciept>();
    }

    public void readRecords(InputStream inputStream) throws IOException, AmountNotAllowedException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while((line = bf.readLine())!=null && line.length()!=0){
            int addedSum=0,flag=1;
            String [] parts = line.split("\\s+");
            String id = parts[0];
            List<Product> products = new ArrayList<Product>();
            for(int i=1;i<parts.length;i+=2){
                int price = Integer.parseInt(parts[i]);
                String type = parts[i + 1];
                switch (type) {
                    case "A":
                        products.add(new ProductA(price));
                        break;
                    case "B":
                        products.add(new ProductB(price));
                        break;
                    case "V":
                        products.add(new ProductV(price));
                        break;
                    default:
                }
                addedSum += price;
            }
            Reciept toAdd = new Reciept(id, products);
            try{
                if(toAdd.getSumOfAmounts()>30000){
                    throw new AmountNotAllowedException("Receipt with amount " + toAdd.getSumOfAmounts() + " is not allowed to be scanned");
                }
                reciepts.add(toAdd);
            }catch(AmountNotAllowedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void printTaxReturns (OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);

        reciepts.stream().forEach(p-> pw.print(p.toString()));

        pw.flush();
    }



}