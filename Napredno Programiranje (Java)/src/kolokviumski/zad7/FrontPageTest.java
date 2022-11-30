package kolokviumski.zad7;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FrontPageTest {
    public static void main(String[] args) {
        // Reading
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        Category[] categories = new Category[parts.length];
        for (int i = 0; i < categories.length; ++i) {
            categories[i] = new Category(parts[i]);
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        FrontPage frontPage = new FrontPage(categories);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            cal = Calendar.getInstance();
            int min = scanner.nextInt();
            cal.add(Calendar.MINUTE, -min);
            Date date = cal.getTime();
            scanner.nextLine();
            String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
            frontPage.addNewsItem(tni);
        }

        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -min);
            scanner.nextLine();
            Date date = cal.getTime();
            String url = scanner.nextLine();
            int views = scanner.nextInt();
            scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
            frontPage.addNewsItem(mni);
        }
        // Execution
        String category = scanner.nextLine();
        System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }

        try {
            System.out.println(frontPage.listByCategoryName(category).size());
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(String mess){
        super(mess);
    }
}

// Vasiot kod ovde

class Category{
    private String category;

    public Category(String category) {
        this.category = category;
    }

    public String getCat() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return Objects.equals(category, category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}



abstract class NewsItem{
    private String title;
    private Date datum;
    private Category category;

    public NewsItem(String title, Date datum, Category category) {
        this.title = title;
        this.datum = datum;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public Date getDatum() {
        return datum;
    }

    public Category getCategory() {
        return category;
    }

    public String getCategoryString(){
        return category.getCat();
    }

    int getMinutesDifference(){
        long differenceMs;
        Date timeNow = new Date();
        differenceMs = Math.abs(timeNow.getTime() - datum.getTime());
        long differenceM = TimeUnit.MINUTES.convert(differenceMs, TimeUnit.MILLISECONDS);
        return (int) differenceM;
    }

    abstract String getTeaser();
}

class TextNewsItem extends NewsItem{
    String body;

    public TextNewsItem(String title, Date datum, Category category, String body) {
        super(title, datum, category);
        this.body = body;
    }


    @Override
    public String getTeaser() {
        if(body.length()>80) {
            return getTitle() + "\n" + getMinutesDifference() + "\n" + String.format("%s", body.substring(0, 80)) + "\n";
        }else{
            return getTitle() + "\n" + getMinutesDifference() + "\n" + String.format("%s", body) + "\n";
        }
    }
}

class MediaNewsItem extends NewsItem{
    String url;
    int views;

    public MediaNewsItem(String title, Date datum, Category category, String url, int views) {
        super(title, datum, category);
        this.url=url;
        this.views=views;
    }


    @Override
    public String getTeaser() {
        return getTitle() + "\n" + getMinutesDifference()+ "\n" + url + "\n" + views + "\n";
    }


}



class FrontPage{
    private Category [] categories;
    private ArrayList<NewsItem> news;

    public FrontPage(Category [] categories){
        this.categories = categories;
        news = new ArrayList<>();
    }

    public void addNewsItem(NewsItem item){
        news.add(item);
    }

    public List<NewsItem> listByCategory(Category category){
        return news.stream()
                .filter(p-> p.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        if(Arrays.stream(categories).anyMatch(p-> p.getCat().equals(category))){
            return news.stream()
                    .filter(p->p.getCategory().getCat().equals(category))
                    .collect(Collectors.toList());
        }else{
            throw new CategoryNotFoundException("Category "+category + " was not found\n");
        }

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(NewsItem n: news){
            sb.append(n.getTeaser());
        }
        return sb.toString();
    }
}