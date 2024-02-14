package it.unibo.quiz.questions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public String getQuestion() {
        return this.question;
    }

    @Override
    public Set<String> getCorrectAnswers() {
        return Collections.unmodifiableSet(correctAnswers);
    }

    @Override
    public Set<String> getWrongAnswers() {
        return Collections.unmodifiableSet(wrongAnswers);
    }

    @Override
    public Set<String> getAllAnswers() {
        return Stream.concat(correctAnswers.stream(), wrongAnswers.stream())
                .collect(Collectors.toUnmodifiableSet());
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
