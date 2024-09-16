package exam.test.check;

public class EvaluationData {
    int questionNumber;
    String answerKey;
    String answer;

    public EvaluationData(int questionNumber, String answerKey, String answer) {
        this.questionNumber = questionNumber;
        this.answerKey = answerKey;
        this.answer = answer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(String answerKey) {
        this.answerKey = answerKey;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "EvaluationData{" +
                "questionNumber=" + questionNumber +
                ", answerKey='" + answerKey + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
