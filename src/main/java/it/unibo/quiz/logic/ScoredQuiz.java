package it.unibo.quiz.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.quiz.questions.Question;
import it.unibo.quiz.questions.QuestionProvider;
import it.unibo.quiz.questions.QuestionWithScore;

public class ScoredQuiz implements Quiz {

    private final InfiniteCircularIterator<QuestionWithScore> questions;
    private final Map<QuestionWithScore, Set<String>> answers;

    public ScoredQuiz(final QuestionProvider questionProvider, final int questionNumber) {
        final Set<QuestionWithScore> quiz = questionProvider.getQuestions().stream().limit(questionNumber)
                .map(QuestionWithScore::new).collect(Collectors.toSet());
        this.questions = new InfiniteCircularIterator<>(quiz);
        this.answers = new HashMap<>(questionNumber);
        quiz.forEach(q -> answers.put(q, new HashSet<>()));
    }

    @Override
    public boolean addAnswer(Question question, String givenAnswer) {
        if (answers.containsKey(question)) {
            return this.answers.get(question).add(givenAnswer);
        }

        return false;
    }

    @Override
    public boolean removeAnswer(Question question, String givenAnswer) {
        if (answers.containsKey(question)) {
            return this.answers.get(question).remove(givenAnswer);
        }

        return false;
    }


    @Override
    public Question getNext() {
        return questions.next();
    }

    @Override
    public Question getPrevious() {
        return questions.previous();
    }

    public double computeScore() {
        return answers.entrySet().stream().mapToDouble(e -> e.getKey().computeScore(e.getValue())).sum();
    }

    public double getMaxScore() {
        return answers.keySet().stream().mapToDouble(QuestionWithScore::getMaxScore).sum();
    }
    
}
