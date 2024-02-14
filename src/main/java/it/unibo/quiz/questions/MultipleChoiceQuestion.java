package it.unibo.quiz.questions;

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
}
