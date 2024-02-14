package it.unibo.quiz.logic;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import it.unibo.quiz.questions.Question;
import it.unibo.quiz.questions.QuestionProvider;
import it.unibo.quiz.questions.QuestionWithScore;

public class ScoredQuiz implements Quiz {

    private final InfiniteCircularIterator<QuestionWithScore> questions;
    private final Map<QuestionWithScore, Set<String>> answers;

    public ScoredQuiz(final QuestionProvider questionProvider, final int questionNumber) {
        final List<Question> availableQuestions = new LinkedList<>(questionProvider.getQuestions());
        Collections.shuffle(availableQuestions);
        final Set<QuestionWithScore> quiz = new HashSet<>(
                availableQuestions.stream().limit(questionNumber).map(QuestionWithScore::new).toList());

        this.questions = new InfiniteCircularIterator<>(quiz);
        this.answers = new HashMap<>(questionNumber);
        quiz.forEach(q -> answers.put(q, new HashSet<>()));
    }

    @Override
    public boolean setNewAnswers(Question question, Collection<? extends String> givenAnswers) {
        if (answers.containsKey(question)) {
            this.answers.get(question).clear();
            return this.answers.get(question).addAll(givenAnswers);
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

    @Override
    public Map<Question, Set<String>> getGivenAnswers() {
        return Collections.unmodifiableMap(answers);
    }

    @Override
    public Set<String> getGivenAnswers(final Question question) {
        return this.answers.containsKey(question) ? this.answers.get(question) : Set.of();
    }

    @Override
    public void reset() {
        this.answers.values().forEach(Set::clear);
    }

    public double computeScore() {
        return answers.entrySet().stream().mapToDouble(e -> e.getKey().computeScore(e.getValue())).sum();
    }

    public double getMaxScore() {
        return answers.keySet().stream().mapToDouble(QuestionWithScore::getMaxScore).sum();
    }    
}
