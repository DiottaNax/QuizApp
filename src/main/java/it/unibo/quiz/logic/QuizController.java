package it.unibo.quiz.logic;

import java.util.Set;

import it.unibo.quiz.questions.Question;

public class QuizController implements Controller {

    private final ScoredQuiz quiz;
    private boolean started;
    private boolean ended;
    private Question current;

    public QuizController(final ScoredQuiz quiz) {
        this.quiz = quiz;
        this.ended = false;
        this.started = false;
    }

    @Override
    public Question getNextQuestion(Set<String> currentQuestionAnswers) {
        if (!started && !ended) {
            throw new IllegalStateException("Quiz is not started yet.");
        }
        this.quiz.setNewAnswers(this.current, currentQuestionAnswers);
        this.current = quiz.getNext();

        return this.current;
    }

    @Override
    public Question getPreviousQuestion(Set<String> currentQuestionAnswers) {
        if (!started && !ended) {
            throw new IllegalStateException("Quiz is not started yet.");
        }

        this.quiz.setNewAnswers(this.current, currentQuestionAnswers);
        this.current = quiz.getPrevious();

        return this.current;
    }

    @Override
    public Question startQuiz() {
        this.current = quiz.getNext();
        this.started = true;
        this.ended = false;
        return this.current;
    }

    @Override
    public double endQuiz(Set<String> currentQuestionAnswers) {
        this.quiz.setNewAnswers(this.current, currentQuestionAnswers);
        this.started = false;
        this.ended = true;
        return this.quiz.computeScore();
    }

    @Override
    public double getMaxScore() {
        return this.quiz.getMaxScore();
    }

    @Override
    public Set<String> getGivenAnswers(final Question question) {
        return this.quiz.getGivenAnswers(question);
    }
}
