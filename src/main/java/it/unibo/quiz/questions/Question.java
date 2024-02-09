package it.unibo.quiz.questions;

import java.util.Set;

/**
 * The {@code Question} interface represents a quiz question with multiple-choice answers.
 */
public interface Question {

    /**
     * Adds a correct answer to the question.
     *
     * @param answer the correct answer to add
     * @return {@code true} if the correct answer is successfully added, {@code false} otherwise
     */
    boolean addCorrectAnswer(String answer);

    /**
     * Adds a wrong answer to the question.
     *
     * @param answer the wrong answer to add
     * @return {@code true} if the wrong answer is successfully added, {@code false} otherwise
     */
    boolean addWrongAnswer(String answer);

    /**
     * Removes a correct answer from the question.
     *
     * @param answer the correct answer to remove
     * @return {@code true} if the correct answer is successfully removed, {@code false} otherwise
     */
    boolean removeCorrectAnswer(String answer);

    /**
     * Removes a wrong answer from the question.
     *
     * @param answer the wrong answer to remove
     * @return {@code true} if the wrong answer is successfully removed, {@code false} otherwise
     */
    boolean removeWrongAnswer(String answer);

    /**
     * Gets the question text.
     *
     * @return a string containing the text of the question
     */
    String getQuestion();

    /**
     * Gets all answers (both correct and wrong).
     *
     * @return a set containing all answers to the question
     */
    Set<String> getAllAnswers();

    /**
     * Gets the set of correct answers.
     *
     * @return a set containing the correct answers to the question
     */
    Set<String> getCorrectAnswers();

    /**
     * Gets the set of wrong answers.
     *
     * @return a set containing the wrong answers to the question
     */
    Set<String> getWrongAnswers();
}
