package it.unibo.quiz.logic;

import it.unibo.quiz.questions.Question;
import java.util.Set;

/**
 * The Controller interface represents the controller component of the quiz application.
 * It provides methods to manage the quiz flow and retrieve quiz-related information.
 */
public interface Controller {

    /**
     * Retrieves the next question in the quiz.
     *
     * @param currentQuestionAnswers The set of answers given for the current question.
     * @return The next question in the quiz.
     */
    Question getNextQuestion(Set<String> currentQuestionAnswers);

    /**
     * Retrieves the previous question in the quiz.
     *
     * @param currentQuestionAnswers The set of answers given for the current question.
     * @return The previous question in the quiz.
     */
    Question getPreviousQuestion(Set<String> currentQuestionAnswers);

    /**
     * Starts the quiz and retrieves the first question.
     *
     * @param questionNumber The number of the first question to start the quiz.
     * @return The first question in the quiz.
     */
    Question startQuiz();

    /**
     * Calculates the score based on the given answers.
     *
     * @param currentQuestionAnswers The set of answers given for the current question.
     * @return The calculated score.
     */
    double endQuiz(Set<String> currentQuestionAnswers);

    /**
     * Retrieves the maximum possible score for the quiz.
     *
     * @return The maximum possible score.
     */
    double getMaxScore();

    /**
     * Retrives and return given answers to the input question.
     * @param question The question.
     * @return The users's given answers.
     */
    Set<String> getGivenAnswers(Question question);
}

