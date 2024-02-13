package it.unibo.quiz.questions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractQuestion implements Question{
    protected final String question;
    protected final Set<String> wrongAnswers;
    protected final Set<String> correctAnswers;

    public AbstractQuestion(final String question) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }

        this.question = question;
        this.wrongAnswers = new HashSet<>();
        this.correctAnswers = new HashSet<>();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        AbstractQuestion other = (AbstractQuestion) obj;
        return question.equals(other.question) &&
            wrongAnswers.equals(other.wrongAnswers) &&
            correctAnswers.equals(other.correctAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, wrongAnswers, correctAnswers);
    }


    @Override
    public String toString() {
        return "{" + question + "\nCorrect answers: " + correctAnswers + "\nWrong Answers: " + wrongAnswers + "\n}";
    }
}
