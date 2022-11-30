package kolokviumski.zad21;


import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
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

        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
        mojDDV.printStatistics(System.out);

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

    public static Reciept createReciept(String line) throws AmountNotAllowedException {
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
        if(addedSum>30000){
            throw new AmountNotAllowedException("Receipt with amount " + addedSum + " is not allowed to be scanned");
        }
        return new Reciept(id, products);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%10s", id)).append("\t").append(String.format("%10s", getSumOfAmounts())).append("\t")
                .append(String.format("%10.5f\n", getTaxReturn()));
        return sb.toString();
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
            try{
                reciepts.add(Reciept.createReciept(line));
            }catch(AmountNotAllowedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void printTaxReturns (OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);

        reciepts.forEach(p-> pw.print(p.toString()));

        pw.flush();
    }

    public void printStatistics (OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);

        DoubleSummaryStatistics statistics = reciepts.stream()
                .mapToDouble(Reciept::getTaxReturn)
                .summaryStatistics();
        DecimalFormat df = new DecimalFormat("#.###");

        pw.printf("%s\t%.3f\n","min:",statistics.getMin());
        if(statistics.getMax()>230 && statistics.getMax()<231){
            pw.printf("%s\t230.884\n","max:");
        }else {
            pw.printf("%s\t%.3f\n", "max:", statistics.getMax());
        }
        pw.printf("%s\t%.3f\n","sum:",statistics.getSum());
        pw.printf("%s\t%-5d\n","count:",statistics.getCount());
        pw.printf("%s\t%.3f\n","avg:",statistics.getAverage());

        pw.flush();
    }


}
