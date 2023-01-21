package laboratoriski.lab8.zad2;

import java.util.ArrayList;
import java.util.Scanner;


enum typeOfQuestion{
    TRUEFALSE,
    FREEFORM
}

class TriviaQuestion {

    private String question;
    private String answer;
    private int points;
    private typeOfQuestion type;

    public TriviaQuestion(String question, String answer, int points, typeOfQuestion type) {
        this.question = question;
        this.answer = answer;
        this.points = points;
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getPoints() {
        return points;
    }

    public typeOfQuestion getType() {
        return type;
    }
}

class Game {

    private ArrayList<TriviaQuestion> questions;

    public Game() {
        questions = new ArrayList<TriviaQuestion>();
    }

    public void addQuestion(String question, String answer, int points, typeOfQuestion type) {
        TriviaQuestion tQuestion = new TriviaQuestion(question, answer, points, type);
        questions.add(tQuestion);
    }

    public void showQuestion(int index) {
        TriviaQuestion question = questions.get(index);

        System.out.printf("Question %d.  %d points.\n", index+1, question.getPoints());
        System.out.println(question.getQuestion());
        if(question.getType()==typeOfQuestion.TRUEFALSE){
            System.out.println("Enter 'T' for true or 'F' for false.");
        }
    }

    public int numQuestions() {
        return questions.size();
    }

    public TriviaQuestion getQuestion(int index) {
        return questions.get(index);
    }
}

public class TriviaGame {
    public static void main(String[] args) {
        int totalScore = 0;

        Game game = new Game();

        game.addQuestion("The possession of more than two sets of chromosomes is termed?",
                "polyploidy", 3, typeOfQuestion.FREEFORM);
        game.addQuestion("Erling Kagge skiied into the north pole alone on January 7, 1993.",
                "F", 1, typeOfQuestion.TRUEFALSE);
        game.addQuestion("1997 British band that produced 'Tub Thumper'",
                "Chumbawumba", 2, typeOfQuestion.FREEFORM);
        game.addQuestion("I am the geometric figure most like a lost parrot",
                "polygon", 2, typeOfQuestion.FREEFORM);
        game.addQuestion("Generics were introducted to Java starting at version 5.0.",
                "T", 1, typeOfQuestion.TRUEFALSE);



        Scanner scanner = new Scanner(System.in);

        for(int i=0;i<game.numQuestions();i++){
            game.showQuestion(i);
            String answer = scanner.nextLine();

            TriviaQuestion question = game.getQuestion(i);

            if (question.getType() == typeOfQuestion.TRUEFALSE) {
                if (answer.equals(question.getAnswer())){
                    System.out.println("That is correct!  You get " + question.getPoints() + " points.");
                    totalScore += question.getPoints();
                }else{
                    System.out.println("Wrong, the correct answer is " + question.getAnswer());
                }
            }else{
                if (answer.equals(question.getAnswer())) {
                    System.out.println("That is correct!  You get " + question.getPoints() + " points.");
                    totalScore += question.getPoints();
                } else {
                    System.out.println("Wrong, the correct answer is " + question.getAnswer());
                }
            }
            System.out.println("Your score is " + totalScore);
        }
        System.out.println("Game over!  Thanks for playing!");
        }
}

