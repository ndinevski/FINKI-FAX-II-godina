package kolokviumski.kol2.zad16;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


enum COMPARATOR_TYPE {
    NEWEST_FIRST,
    OLDEST_FIRST,
    LOWEST_PRICE_FIRST,
    HIGHEST_PRICE_FIRST,
    MOST_SOLD_FIRST,
    LEAST_SOLD_FIRST
}

class ProductNotFoundException extends Exception {
    ProductNotFoundException(String message) {
        super(message);
    }
}

class Product {
    private String category, id, name;
    private LocalDateTime createdAt;
    private double price;
    private int quantity;

    public Product(String category, String id, String name, LocalDateTime createdAt, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", quantitySold="+quantity +'}';
    }

    public double buy(int quantity) {
        this.quantity+=quantity;
        return quantity*price;
    }

}


class OnlineShop {

    Map<String, List<Product>> productsByCategory;
    Map<String, Product> productsById;
    List<Product> allProducts;

    OnlineShop() {
        productsByCategory = new TreeMap<>();
        productsById = new TreeMap<>();
        allProducts = new ArrayList<>();
    }

    void addProduct(String category, String id, String name, LocalDateTime createdAt, double price){
        Product product = new Product(category,id,name,createdAt,price);
        productsByCategory.putIfAbsent(category, new ArrayList<>());
        productsByCategory.get(category).add(product);

        productsById.put(id, product);

        allProducts.add(product);

    }

    double buyProduct(String id, int quantity) throws ProductNotFoundException{
        if(!productsById.containsKey(id)) {
            throw new ProductNotFoundException(String.format("Product with id %s does not exist in the online shop!", id));
        }
        return productsById.get(id).buy(quantity);
    }

    List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {
        List<Product> listOfCategory;

        if(category == null){
            listOfCategory = allProducts;
        }else {
            listOfCategory = allProducts.stream().filter(p -> p.getCategory().equals(category)).collect(Collectors.toList());
        }
        listOfCategory = sortList(listOfCategory,comparatorType);

        int pages;

        if(listOfCategory.size()%pageSize==0){
            pages=listOfCategory.size()/pageSize;
        }else {
            pages = listOfCategory.size() / pageSize + 1;
        }
        List<List<Product>> result = new ArrayList<>();

        for(int i=0;i<pages;i++){
            if(i*pageSize+pageSize>listOfCategory.size()){
                result.add(listOfCategory.subList(i*pageSize, i*pageSize + (listOfCategory.size()-i*pageSize)));
                break;
            }
            result.add(listOfCategory.subList(i*pageSize, i*pageSize+pageSize));
        }

        return result;
    }

    Comparator<Product> createComparator(COMPARATOR_TYPE comparatorType){
        Comparator<Product> comparatorByPrice = Comparator.comparing(Product::getPrice);
        Comparator<Product> comparatorByDate = Comparator.comparing(Product::getCreatedAt);
        Comparator<Product> comparatorByQuantity = Comparator.comparing(Product::getQuantity);

        if(comparatorType.equals(COMPARATOR_TYPE.LOWEST_PRICE_FIRST)){
            return comparatorByPrice;
        } else if (comparatorType.equals(COMPARATOR_TYPE.HIGHEST_PRICE_FIRST)) {
            return comparatorByPrice.reversed();
        }else if (comparatorType.equals(COMPARATOR_TYPE.LEAST_SOLD_FIRST)) {
            return comparatorByQuantity;
        }else if (comparatorType.equals(COMPARATOR_TYPE.MOST_SOLD_FIRST)){
            return comparatorByQuantity.reversed();
        }else if (comparatorType.equals(COMPARATOR_TYPE.OLDEST_FIRST)) {
            return comparatorByDate;
        }else if (comparatorType.equals(COMPARATOR_TYPE.NEWEST_FIRST)) {
            return comparatorByDate.reversed();
        }
        return null;
    }

    List<Product> sortList(List<Product> list, COMPARATOR_TYPE comparatorType){
        Comparator<Product> comparator = createComparator(comparatorType);

        return list.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

    }

}

public class OnlineShopTest {

    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        double totalAmount = 0.0;
        Scanner sc = new Scanner(System.in);
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equalsIgnoreCase("addproduct")) {
                String category = parts[1];
                String id = parts[2];
                String name = parts[3];
                LocalDateTime createdAt = LocalDateTime.parse(parts[4]);
                double price = Double.parseDouble(parts[5]);
                onlineShop.addProduct(category, id, name, createdAt, price);
            } else if (parts[0].equalsIgnoreCase("buyproduct")) {
                String id = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                try {
                    totalAmount += onlineShop.buyProduct(id, quantity);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String category = parts[1];
                if (category.equalsIgnoreCase("null"))
                    category=null;
                String comparatorString = parts[2];
                int pageSize = Integer.parseInt(parts[3]);
                COMPARATOR_TYPE comparatorType = COMPARATOR_TYPE.valueOf(comparatorString);
                printPages(onlineShop.listProducts(category, comparatorType, pageSize));
            }
        }
        System.out.println("Total revenue of the online shop is: " + totalAmount);

    }

    private static void printPages(List<List<Product>> listProducts) {
        for (int i = 0; i < listProducts.size(); i++) {
            System.out.println("PAGE " + (i + 1));
            listProducts.get(i).forEach(System.out::println);
        }
    }
}

