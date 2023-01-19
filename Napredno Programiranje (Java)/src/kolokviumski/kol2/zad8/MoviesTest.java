package kolokviumski.kol2.zad8;

import java.util.*;
import java.util.stream.Collectors;


class Movie{
    private String title;
    private List<Integer> ratings;

    public Movie(String title, int [] ratings) {
        this.title = title;
        this.ratings = Arrays.stream(ratings).boxed().collect(Collectors.toList());
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public int numberOfRatings(){
        return ratings.size();
    }

    public double averageRating(){
        return ratings.stream()
                .mapToInt(p->p)
                .average()
                .orElse(0.0);
    }

    public double coefRating(int mostRatings){
        return averageRating()*numberOfRatings()/mostRatings;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f) of %d ratings", title, averageRating(), ratings.size());
    }
}



class MoviesList{
    private List<Movie> allMovies;
    private int mostRatings;

    public MoviesList() {
        allMovies = new ArrayList<>();
        mostRatings=0;
    }

    public void addMovie(String title, int[] ratings){
        Movie movie = new Movie(title, ratings);
        if(ratings.length>mostRatings){
            mostRatings=ratings.length;
        }
        allMovies.add(movie);
    }

    public List<Movie> top10ByAvgRating(){
        return allMovies.stream()
                .sorted(Comparator.comparing(Movie::averageRating).reversed().thenComparing(Movie::getTitle))
                .limit(10)
                .collect(Collectors.toList());

    }

    public List<Movie> top10ByRatingCoef(){

        List<Movie> top10List = allMovies.stream()
                .sorted(Comparator.comparing(p -> p.coefRating(mostRatings)))
                .collect(Collectors.toList());
        Collections.reverse(top10List);
        for(int i=0;i<top10List.size()-1;i++){
            if(top10List.get(i).coefRating(mostRatings) == top10List.get(i+1).coefRating(mostRatings)){
                if(top10List.get(i).getTitle().substring(0, 6).compareTo(top10List.get(i + 1).getTitle().substring(0, 6)) > 0){
                    Movie tmp = top10List.get(i);
                    top10List.set(i, top10List.get(i+1));
                    top10List.set(i+1, tmp);
                }
            }
        }
        return top10List.stream()
                .limit(10)
                .collect(Collectors.toList());
    }
}



public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoviesList moviesList = new MoviesList();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int x = scanner.nextInt();
            int[] ratings = new int[x];
            for (int j = 0; j < x; ++j) {
                ratings[j] = scanner.nextInt();
            }
            scanner.nextLine();
            moviesList.addMovie(title, ratings);
        }
        scanner.close();
        List<Movie> movies = moviesList.top10ByAvgRating();
        System.out.println("=== TOP 10 BY AVERAGE RATING ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        movies = moviesList.top10ByRatingCoef();
        System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}

