package it.unibo.quiz.logic;

import it.unibo.quiz.questions.Question;
import java.util.Set;

@FunctionalInterface
public interface QuestionScorer {

    double calculateScore(Question question, Set<String> givenAnswer);
    
}

