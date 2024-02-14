package it.unibo.quiz.logic;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import it.unibo.quiz.questions.Question;

public interface Quiz {

    boolean setNewAnswers(Question question, Collection<? extends String> givenAnswers);

    Question getNext();
    
    Question getPrevious();

    Map<Question, Set<String>> getGivenAnswers();

    Set<String> getGivenAnswers(Question question);

    void reset();
}
