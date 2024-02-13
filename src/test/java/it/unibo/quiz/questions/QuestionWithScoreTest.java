package it.unibo.quiz.questions;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class QuestionWithScoreTest {
    
    @Test
    public void testDefaultScorer() {
        QuestionWithScore question = new QuestionWithScore(
                new MultipleChoiceQuestion("Which of these cities are capitals?"), 1);
        List.of("Berlin", "Prague").forEach(question::addCorrectAnswer);
        List.of("Sydney", "Milan", "Rio de Janeiro").forEach(question::addWrongAnswer);

        assertEquals(Double.valueOf(0.5), Double.valueOf(question.computeScore(Set.of("Milan", "Berlin"))));
        assertEquals(Double.valueOf(1), Double.valueOf(question.computeScore(Set.of("Prague", "Berlin"))));
        assertEquals(Double.valueOf(0), Double.valueOf(question.computeScore(Set.of())));
    }
    
    @Test
    public void testCustomizedScorer(){
        final double maxScore = 2;
        final double penalization = 0.25;
        QuestionWithScore question = new QuestionWithScore(
                new MultipleChoiceQuestion("Which of these cities are capitals?"),
                (q, a) -> maxScore / q.getCorrectAnswers().size()
                        * q.getCorrectAnswers().stream().filter(a::contains).count()
                        - penalization * q.getWrongAnswers().stream().filter(a::contains).count(), maxScore);
        List.of("Berlin", "Prague").forEach(question::addCorrectAnswer);
        List.of("Sydney", "Milan", "Rio de Janeiro").forEach(question::addWrongAnswer);
        assertEquals(Double.valueOf(0.5), Double.valueOf(question.computeScore(Set.of("Milan", "Berlin", "Sydney"))));
        assertEquals(Double.valueOf(2), Double.valueOf(question.computeScore(Set.of("Prague", "Berlin"))));
        assertEquals(Double.valueOf(0), Double.valueOf(question.computeScore(Set.of())));
    }

}
