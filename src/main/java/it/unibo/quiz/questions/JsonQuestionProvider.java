package it.unibo.quiz.questions;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class JsonQuestionProvider implements QuestionProvider {

    private final Set<Question> questions;

    public JsonQuestionProvider(final InputStream file) {
        questions = readQuestions(file);
    }
    
    private Set<Question> readQuestions(final InputStream file) {
        JSONParser parser = new JSONParser();
        Set<Question> parsed = new HashSet<>();
        try (Reader reader = new InputStreamReader(file, "UTF-8")) {
            JSONArray jsonarray = (JSONArray) parser.parse(reader);
            for (Object o : jsonarray) {
                final JSONObject jo = (JSONObject) o;
                final String question = (String) jo.get("question");
                JSONArray jsonAnswers = (JSONArray) jo.get("wrong");
                @SuppressWarnings("unchecked")
                final String[] wrongAnswers = (String[]) jsonAnswers.toArray(new String[jsonAnswers.size()]);
                jsonAnswers = (JSONArray) jo.get("correct");
                @SuppressWarnings("unchecked")
                final String[] correctAnswers = (String[]) jsonAnswers.toArray(new String[jsonAnswers.size()]);;
                final Question newQuestion;
                if (correctAnswers.length == 1) {
                    newQuestion = new SimpleQuestion(question, correctAnswers[0]);
                } else {
                    newQuestion = new MultipleChoiceQuestion(question);
                    for (final String c : correctAnswers) {
                    newQuestion.addWrongAnswer(c);
                }
                }

                for (final String w : wrongAnswers) {
                    newQuestion.addWrongAnswer(w);
                }
                parsed.add(newQuestion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        }
        
        return parsed;
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(questions);
    }
    
}
