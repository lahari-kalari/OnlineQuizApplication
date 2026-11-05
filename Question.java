
public class Question {

    String questionText;
    String[] options;
    String correctAnswer;

    // Constructor
    public Question(String questionText,
            String[] options,
            String correctAnswer) {

        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}
