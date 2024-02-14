package it.unibo.quiz.gui;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.quiz.logic.Controller;
import it.unibo.quiz.questions.Question;
import it.unibo.quiz.questions.QuestionWithScore;

public class QuizGui extends JFrame {
    private final Controller controller;
    private final JPanel mainPanel;
    private final JPanel quizPanel;
    private final JPanel questionPanel;
    private final JPanel navigationPanel;
    private final Set<JToggleButton> answerSelectors;
    private final JTextArea questionArea;
    private final JLabel questionScoreLabel = new JLabel("Question Score: ");
    private final JLabel questionNumLabel;
    private final JLabel totalScoreLabel = new JLabel("Total Score: ");

    private Question currentQuestion;
    private double totalScore;
    private boolean ended = false;
    private final int maxQuestion;
    private int curQuestion;
    
    public QuizGui(final Controller controller, int maxQuestion) {
        this.setTitle("Menu");
        try{
            this.setIconImage(ImageIO.read(ClassLoader.getSystemResourceAsStream("it/unibo/logo.png")));
        } catch (IOException e) {
            // No image setted, excpetion handled :)
        }

        this.controller = controller;
        this.maxQuestion = maxQuestion;
        this.curQuestion = 1;
        this.questionNumLabel = new JLabel("" + this.curQuestion + "/" + this.maxQuestion);
        this.setTitle("Telecomunication Network");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Initializing panels
        this.mainPanel = new JPanel(new BorderLayout());

        this.quizPanel = new JPanel();
        this.quizPanel.setLayout(new BoxLayout(this.quizPanel, BoxLayout.Y_AXIS));
        this.mainPanel.add(this.quizPanel, BorderLayout.CENTER);

        this.navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.mainPanel.add(navigationPanel, BorderLayout.SOUTH);

        this.questionPanel = new JPanel();
        this.mainPanel.add(questionPanel, BorderLayout.NORTH);

        //Initializing question panel
        this.questionArea = new JTextArea();
        this.questionArea.setEditable(false);
        this.questionArea.setForeground(Color.BLUE);
        this.questionPanel.add(this.questionArea);


        //Add first quiz to quiz panel
        this.answerSelectors = new HashSet<>();
        this.updateQuestion(this.controller.startQuiz());


        //Add buttons to navigation panel
        Button nextButton = new Button(">");

        Button prevButton = new Button("<");
        Button endButton = new Button("End Quiz");
        nextButton.addActionListener(e -> {
            this.updateQuestion(this.controller.getNextQuestion(this.filterSelectedAnswers()));
            this.curQuestion = curQuestion + 1 > maxQuestion ? 1 : curQuestion + 1;
            this.questionNumLabel.setText("" + this.curQuestion + "/" + this.maxQuestion);
        });

        prevButton.addActionListener(e -> {
            this.updateQuestion(this.controller.getPreviousQuestion(this.filterSelectedAnswers()));
            this.curQuestion = curQuestion - 1 <= 0 ? maxQuestion : curQuestion - 1;
        });

        endButton.addActionListener(this::endButtonRoutine);

        this.navigationPanel.add(questionScoreLabel);
        this.questionScoreLabel.setVisible(false);
        this.questionScoreLabel.setForeground(Color.GREEN);
        this.questionScoreLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        this.navigationPanel.add(prevButton);
        this.navigationPanel.add(questionNumLabel);
        this.navigationPanel.add(nextButton);
        this.navigationPanel.add(endButton);
        this.totalScoreLabel.setVisible(false);
        this.totalScoreLabel.setForeground(Color.MAGENTA);
        this.totalScoreLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        this.navigationPanel.add(totalScoreLabel);
        

        this.add(mainPanel);
        this.setVisible(true);
        final var dim = Toolkit.getDefaultToolkit().getScreenSize();
        final var sw = dim.getWidth();
        final var sh = dim.getHeight();
        this.setSize((int)Math.round(sw / 1.5), (int) Math.round(sh / 2));
        this.setLocation(new Point((int) Math.round(sw / 5), (int) Math.round(sh / 4)));
    }
    
    private void updateQuestion(final Question question) {
        this.currentQuestion = question;
        this.questionArea.setText(currentQuestion.getQuestion());
        this.quizPanel.removeAll();
        this.answerSelectors.clear();
        var selected = this.controller.getGivenAnswers(question);
        currentQuestion.getAllAnswers().stream().map(a -> new JRadioButton(a)).forEach(b -> {
            b.setFocusPainted(false);
            this.answerSelectors.add(b);
            this.quizPanel.add(b);
        });
        this.answerSelectors.stream().filter(b -> selected.contains(b.getText())).forEach(s -> s.setSelected(true));
        this.setBackground(Color.BLUE);
        if (ended) {
            //Displaying green corrected chosen answers            
            this.answerSelectors.stream().filter(s -> selected.contains(s.getText()))
                    .filter(s -> question.getWrongAnswers().contains(s.getText()))
                    .forEach(s -> s.setForeground(Color.RED));
            
            this.answerSelectors.stream().filter(s -> !selected.contains(s.getText()))
                    .filter(s -> question.getCorrectAnswers().contains(s.getText()))
                    .forEach(s -> s.setForeground(Color.RED));

            this.answerSelectors.stream().filter(s -> selected.contains(s.getText()))
                    .filter(s -> question.getCorrectAnswers().contains(s.getText()))
                    .forEach(s -> s.setForeground(Color.GREEN));
                    
            questionScoreLabel.setText("Question Score: " + ((QuestionWithScore) question).computeScore(selected) + " / "
                    + ((QuestionWithScore) question).getMaxScore());            
        }

        this.validate();
        this.repaint();
    }

    private Set<String> filterSelectedAnswers() {
        return this.answerSelectors.stream()
                .filter(JToggleButton::isSelected)
                .map(JToggleButton::getText)
                .collect(Collectors.toSet());
    }

    private void endButtonRoutine(final ActionEvent e){
        this.ended = true;
        this.totalScore = this.controller.endQuiz(this.filterSelectedAnswers());
        this.questionScoreLabel.setVisible(true);
        this.updateQuestion(this.currentQuestion);
        this.totalScoreLabel.setText("Total Score: " + totalScore + "/" + this.controller.getMaxScore());
        this.totalScoreLabel.setVisible(true);
        ((Button) e.getSource()).setEnabled(false);
    }
}
