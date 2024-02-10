package it.unibo.quiz.questions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The {@code MultipleChoiceQuestion} class represents a multiple-choice question in a quiz.
 * It implements the {@code Question} interface.
 */
public class MultipleChoiceQuestion extends AbstractQuestion {

    /**
     * Constructs a new multiple-choice question with the specified question text.
     *
     * @param question the text of the question
     * @throws IllegalArgumentException if the provided question text is null or empty
     */
    public MultipleChoiceQuestion(final String question) {
        super(question);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addCorrectAnswer(final String answer) {
        return this.correctAnswers.add(answer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addWrongAnswer(final String answer) {
        return this.wrongAnswers.add(answer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeCorrectAnswer(final String answer) {
        return this.correctAnswers.remove(answer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeWrongAnswer(final String answer) {
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
        return Stream.concat(wrongAnswers.stream(), correctAnswers.stream()).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getCorrectAnswers() {
        return Set.copyOf(correctAnswers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getWrongAnswers() {
        return Set.copyOf(wrongAnswers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(question, wrongAnswers, correctAnswers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final MultipleChoiceQuestion other = (MultipleChoiceQuestion) obj;

        // Compare question text
        if (!Objects.equals(question, other.question)) {
            return false;
        }

        // Compare correct answers
        if (!correctAnswers.equals(other.correctAnswers)) {
            return false;
        }

        // Compare wrong answers
        return wrongAnswers.equals(other.wrongAnswers);
    }

}
