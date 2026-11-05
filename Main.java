
import java.util.Scanner;

public class Main {

    public static void displayWelcome() {

        System.out.println("==============================");
        System.out.println("     ONLINE QUIZ APPLICATION");
        System.out.println("==============================");
    }

    public static void askQuestions(
            Question[] questions,
            String[] userAnswers,
            Scanner sc) {

        for (int i = 0; i < questions.length; i++) {

            System.out.println("\nQ" + (i + 1) + ". "
                    + questions[i].questionText);

            for (int j = 0; j < questions[i].options.length; j++) {
                System.out.println(questions[i].options[j]);
            }

            System.out.print("Enter your answer (A/B/C/D): ");

            String answer = sc.nextLine().trim().toUpperCase();

            while (!answer.equals("A") &&
                    !answer.equals("B") &&
                    !answer.equals("C") &&
                    !answer.equals("D")) {

                System.out.println("Invalid answer! Try again.");
                System.out.print("Enter A, B, C, or D: ");

                answer = sc.nextLine().trim().toUpperCase();
            }

            userAnswers[i] = answer;
        }
    }

    public static int calculateScore(
            Question[] questions,
            String[] userAnswers) {

        int score = 0;

        for (int i = 0; i < questions.length; i++) {

            if (userAnswers[i].equals(
                    questions[i].correctAnswer)) {

                score++;
            }
        }

        return score;
    }

    public static void displayResult(
            String name, int score, int totalQuestions) {

        int wrongAnswers = totalQuestions - score;

        double percentage = (score * 100.0) / totalQuestions;

        System.out.println("\n========== QUIZ RESULT ==========");
        System.out.println("Participant: " + name);
        System.out.println("Total Questions: " + totalQuestions);
        System.out.println("Correct Answers: " + score);
        System.out.println("Wrong Answers: " + wrongAnswers);

        System.out.printf("Percentage: %.2f%%\n", percentage);

        if (percentage >= 80) {
            System.out.println("Performance: Excellent!");
        } else if (percentage >= 60) {
            System.out.println("Performance: Good!");
        } else if (percentage >= 40) {
            System.out.println("Performance: Average!");
        } else {
            System.out.println("Performance: Needs Improvement!");
        }

        System.out.println("=================================");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Question[] questions = {

                new Question(
                        "Which language is used for Android development?",
                        new String[] {
                                "A. Java", "B. HTML", "C. CSS", "D. SQL"
                        },
                        "A"),

                new Question(
                        "Who developed Java?",
                        new String[] {
                                "A. Microsoft", "B. Sun Microsystems",
                                "C. Google", "D. Apple"
                        },
                        "B"),

                new Question(
                        "Which keyword is used to create a class in Java?",
                        new String[] {
                                "A. method", "B. public",
                                "C. class", "D. static"
                        },
                        "C")
        };

        displayWelcome();

        System.out.print("Enter your name: ");
        String name = sc.nextLine().trim();

        while (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            System.out.print("Enter your name again: ");
            name = sc.nextLine().trim();
        }

        char playAgain;

        do {

            // Create a fresh answer array for every attempt
            String[] userAnswers = new String[questions.length];

            System.out.println("\nStarting Quiz...");

            askQuestions(questions, userAnswers, sc);

            int score = calculateScore(questions, userAnswers);

            displayResult(name, score, questions.length);

            // Ask whether the participant wants to replay
            System.out.print("\nDo you want to play again? (Y/N): ");

            String choice = sc.nextLine().trim().toUpperCase();

            while (!choice.equals("Y") && !choice.equals("N")) {

                System.out.println("Invalid choice! Enter Y or N.");
                System.out.print("Try again: ");

                choice = sc.nextLine().trim().toUpperCase();
            }

            playAgain = choice.charAt(0);

        } while (playAgain == 'Y');

        System.out.println("\nThank you for playing, " + name + "!");
        System.out.println("Exiting Quiz Application...");

        sc.close();
    }
}
