package it.unibo.quiz.questions;

import java.util.HashSet;
import java.util.Set;

/**
 * A simple implementation of the {@link Question} interface.
 * This class represents a basic question with a single correct answer and multiple wrong answers.
 */
public class SimpleQuestion implements Question {

    /** The text of the question. */
    private final String question;
    
    /** The correct answer to the question. */
    private final String answer;
    
    /** Set of wrong answers for the question. */
    private final Set<String> wrongAnswers;

    /**
     * Constructs a SimpleQuestion object with the given question and answer.
     *
     * @param question the text of the question
     * @param answer the correct answer to the question
     * @throws IllegalArgumentException if question or answer is null or empty
     */
    public SimpleQuestion(final String question, final String answer) {
        if (question == null || question.isEmpty() || answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Question or Answer cannot be null or empty");
        }
        this.question = question;
        this.answer = answer;
        this.wrongAnswers = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     * This operation is not supported for SimpleQuestion.
     * @throws UnsupportedOperationException always
     */
    @Override
    public boolean addCorrectAnswer(String answer) {
        throw new UnsupportedOperationException("SimpleQuestion cannot have more than one correct answer");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addWrongAnswer(String answer) {
        return this.wrongAnswers.add(answer);
    }

    /**
     * {@inheritDoc}
     * This operation is not supported for SimpleQuestion.
     * @throws UnsupportedOperationException always
     */
    @Override
    public boolean removeCorrectAnswer(String answer) {
        throw new UnsupportedOperationException("Cannot remove a correct answer from a SimpleQuestion");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeWrongAnswer(String answer) {
        return this.wrongAnswers.remove(answer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQuestion() {
        return this.question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAllAnswers() {
        final var copy = new HashSet<>(wrongAnswers);
        copy.add(answer);
        return copy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getCorrectAnswers() {
        return Set.of(answer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getWrongAnswers() {
        return new HashSet<>(wrongAnswers);
    }
}
