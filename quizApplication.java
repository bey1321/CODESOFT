import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

//Display a summary of correct/incorrect answers

public class quizApplication {
    public static void main(String[] args) {
        Question q1 = new Question("What is the capital of India?", "Mumbai", "Delhi", "Kolkata", "Chennai", 'B');
        Question q2 = new Question("What is the capital of USA?", "New York", "Washington DC", "Los Angeles", "Chicago", 'B');
        Question q3 = new Question("What is the capital of UK?", "Manchester", "London", "Birmingham", "Liverpool", 'B');
        Question q4 = new Question("What is the capital of Australia?", "Sydney", "Melbourne", "Canberra", "Brisbane", 'C');
        Question q5 = new Question("What is the capital of Japan?", "Tokyo", "Osaka", "Kyoto", "Hiroshima", 'A');

        System.out.println("\n--------------------------------------\n");
        System.out.println("Welcome to the Quiz Application\n");
        System.out.println("You have a limited time to answer each question.\n");

        Scanner input = new Scanner(System.in);  // Declare Scanner once

        for (Question q : Question.questions) {

            final boolean[] answered = {false};  // Track if the question is answered
            final boolean[] timeExpired = {false};
            int time = 10; // Set time for each question in seconds
            Timer timer = new Timer();

            TimerTask task = new TimerTask() {
                int seconds = time;
            
                public void run() {
                    if (seconds > 0) {
                        System.out.print("\rTime remaining: " + seconds + " seconds. Enter your answer: ");
                        seconds--;
                    } else{
                        System.out.println("\nTime's up! Correct answer: " + q.correctAnswer);
                        answered[0] = true;  // Mark as answered
                        timeExpired[0] = true;
                        timer.cancel();
                        timer.purge();
                    }
                }
            };         
            
            q.displayQuestion();

            timer.scheduleAtFixedRate(task, 0, 1000); // Schedule the timer task

            if(timeExpired[0])
                break;

            // Check if time has expired before allowing the user to answer
            if (!answered[0]) {                
                System.out.print("\nEnter your answer: ");
                char answer = input.next().charAt(0);
                q.checkAnswer(answer);
                answered[0] = true;  // Mark as answered
            }

            timer.cancel();  // Cancel the timer once answered
            timer.purge();   // Clean up the timer
            System.out.println(); // Move to the next line after each question
        }

        input.close();  // Close the Scanner
        System.out.println("You got " + Question.numberOfCorrectAnswers + " out of " + Question.numberOfQuestions + " questions correct.");
        System.out.println("You scored " + (Question.numberOfCorrectAnswers * 100 / Question.numberOfQuestions) + "%");
        System.out.println("\nThank you for playing the Quiz Application");
        System.out.println("\n--------------------------------------\n");
    }
}

class Question {
    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    char correctAnswer;
    static ArrayList<Question> questions = new ArrayList<>();
    static int numberOfCorrectAnswers = 0;
    static int numberOfQuestions = 0;

    Question(String question, String option1, String option2, String option3, String option4, char correctAnswer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
        questions.add(this);
        numberOfQuestions++;
    }

    public void displayQuestion() {
        System.out.println(question);
        System.out.println("A. " + option1);
        System.out.println("B. " + option2);
        System.out.println("C. " + option3);
        System.out.println("D. " + option4);
    }

    public void checkAnswer(char userAnswer) {
        userAnswer = Character.toUpperCase(userAnswer);

        if (correctAnswer == userAnswer) {
            System.out.println("\nCorrect!");
            numberOfCorrectAnswers++;
        } else {
            System.out.println("\nWrong. The correct answer is " + correctAnswer);
        }

        System.out.println("\n-----------------------------------\n");
    }
}
