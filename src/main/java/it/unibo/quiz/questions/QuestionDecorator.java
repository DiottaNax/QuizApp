package it.unibo.quiz.questions;

import java.util.Set;

public abstract class QuestionDecorator implements Question {
    
    private final Question question;

    public QuestionDecorator(Question question) {
        this.question = question;
    }

    @Override
    public boolean addCorrectAnswer(String answer) {
        return this.question.addCorrectAnswer(answer);
    }

    @Override
    public boolean addWrongAnswer(String answer) {
        return this.question.addWrongAnswer(answer);
    }

    @Override
    public boolean removeCorrectAnswer(String answer) {
        return this.question.removeCorrectAnswer(answer);
    }

    @Override
    public boolean removeWrongAnswer(String answer) {
        return this.question.removeWrongAnswer(answer);
    }

    @Override
    public String getQuestion() {
        return this.question.getQuestion();
    }

    @Override
    public Set<String> getAllAnswers() {
        return this.question.getAllAnswers();
    }

    @Override
    public Set<String> getCorrectAnswers() {
        return this.question.getCorrectAnswers();
    }

    @Override
    public Set<String> getWrongAnswers() {
        return this.question.getWrongAnswers();
    }
}
