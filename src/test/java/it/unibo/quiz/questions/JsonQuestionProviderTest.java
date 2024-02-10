package it.unibo.quiz.questions;

import java.io.InputStream;
import java.util.Set;

import it.unibo.quiz.questions.JsonQuestionProvider;
import it.unibo.quiz.questions.Question;

public class JsonQuestionProviderTest {

    public static void main(String[] args) {
        // Carica il file JSON
        InputStream jsonFile = ClassLoader.getSystemResourceAsStream("it/unibo/questions.json");

        // Crea un JsonQuestionProvider
        JsonQuestionProvider provider = new JsonQuestionProvider(jsonFile);

        // Ottieni le domande
        Set<Question> questions = provider.getQuestions();

        // Stampa le domande
        for (Question question : questions) {
            System.out.println(question);
        }
    }
}

