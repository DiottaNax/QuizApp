package it.unibo.quiz.logic;

import it.unibo.quiz.questions.Question;

public interface Quiz {

    boolean addAnswer(Question question, String givenAnswer);

    boolean removeAnswer(Question question, String givenAnswer);

    Question getNext();
    
    Question getPrevious();
}
