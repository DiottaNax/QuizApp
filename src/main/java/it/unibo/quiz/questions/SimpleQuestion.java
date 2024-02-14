package it.unibo.quiz.questions;

/**
 * A simple implementation of the {@link Question} interface.
 * This class represents a basic question with a single correct answer and multiple wrong answers.
 */
public class SimpleQuestion extends AbstractQuestion {
    
    /**
     * Constructs a SimpleQuestion object with the given question and answer.
     *
     * @param question the text of the question
     * @param answer the correct answer to the question
     * @throws IllegalArgumentException if question or answer is null or empty
     */
    public SimpleQuestion(final String question, final String answer) {
        super(question);
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null or empty in a SimpleQuestion");
        }
        super.correctAnswers.add(answer);
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
    public boolean addWrongAnswer(final String answer) {
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
}
