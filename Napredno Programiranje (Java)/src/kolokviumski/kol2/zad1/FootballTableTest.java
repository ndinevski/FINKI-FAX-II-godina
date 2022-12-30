package kolokviumski.kol2.zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Team{
    private String name;
    private int playedMatches=0;
    private int wonMatches=0;
    private int lostMatches=0;
    private int drawMatches=0;


    private int points=0;
    private int goalDifference=0;

    public Team(String name) {
        this.name = name;
    }

    public void evaluateResult(int a){
        if(a==0){
            drawMatches++;
            points++;
        }else if(a==1){
            wonMatches++;
            points+=3;
        }else if(a==-1){
            lostMatches++;
        }
        playedMatches++;
    }

    public String getName() {
        return name;
    }

    public  int getPlayedMatches() {
        return playedMatches;
    }

    public  int getWonMatches() {
        return wonMatches;
    }

    public  int getLostMatches() {
        return lostMatches;
    }

    public  int getDrawMatches() {
        return drawMatches;
    }

    public int getPoints() {
        return points;
    }

    public void setGoalDifference(int scoredGoals, int concededGoals) {
        goalDifference -= (scoredGoals-concededGoals);
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    @Override
    public String toString() {
        return String.format("%-15s%5d%5d%5d%5d%5d\n", name, playedMatches, wonMatches, drawMatches, lostMatches, points);
    }
}

class FootballTable{

    Map<String, Team> teams;

    public FootballTable() {
        teams = new HashMap<>();
    }

    public int evaluateScore(int firstTeam, int secondTeam){
        if(firstTeam==secondTeam){
            return 0;
        }else if(firstTeam>secondTeam){
            return 1;
        }else{
            return -1;
        }
    }

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals){
        int resultHomeTeam = evaluateScore(homeGoals,awayGoals);
        int resultAwayTeam = evaluateScore(awayGoals, homeGoals);

        Team hTeam = null;
        if(teams.containsKey(homeTeam)){
            hTeam = teams.get(homeTeam);
        }else{
            hTeam = new Team(homeTeam);
            teams.put(homeTeam, hTeam);
        }
        hTeam.evaluateResult(resultHomeTeam);
        hTeam.setGoalDifference(homeGoals,awayGoals);

        Team aTeam = null;
        if(teams.containsKey(awayTeam)){
            aTeam = teams.get(awayTeam);
        }else{
            aTeam = new Team(awayTeam);
            teams.put(awayTeam, aTeam);
        }
        aTeam.evaluateResult(resultAwayTeam);
        aTeam.setGoalDifference(awayGoals,homeGoals);
    }

    public void printTable(){

        List<Team> list = teams.values().stream()
                .sorted(Comparator.comparing(Team::getPoints).reversed().thenComparing(Team::getGoalDifference).thenComparing(Team::getName))
                .collect(Collectors.toList());

        IntStream.range(0,list.size())
                .forEach(i->{
                    System.out.printf("%2d. %s",i+1, list.get(i));
                });
    }


}





public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .map(line -> line.split(";"))
                .forEach(parts -> table.addGame(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}

// Your code here

