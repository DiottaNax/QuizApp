package it.unibo.quiz.questions;

import java.util.HashSet;
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
    public String toString() {
        return "{" + question + "\nCorrect answers: " + correctAnswers + "\nWrong Answers: " + wrongAnswers + "\n}";
    }
}
