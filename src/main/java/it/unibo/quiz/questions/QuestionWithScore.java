package it.unibo.quiz.questions;

import java.util.Set;

import it.unibo.quiz.logic.QuestionScorer;

public class QuestionWithScore extends QuestionDecorator {

    private final QuestionScorer scorer;
    private final double maxScore;

    public QuestionWithScore(Question question, double maxScore) {
        super(question);
        /**
         * The default scorer takes the max score and computes the result as res=maxScore/correctAnswers * guessedAnswer
         */
        this.maxScore = maxScore;
        this.scorer = (q, a) -> maxScore / q.getCorrectAnswers().size()
                * getCorrectAnswers().stream().filter(a::contains).count();
    }

    public QuestionWithScore(final Question question, final QuestionScorer scorer, final double maxScore) {
        super(question);
        this.scorer = scorer;
        this.maxScore = maxScore;
    }

    public QuestionWithScore(Question question) {
        this(question, 1);
    }

    public double computeScore(Set<String> givenAnswers) {
        return scorer.calculateScore(this, givenAnswers);
    }

    public double getMaxScore() {
        return maxScore;
    }
}
