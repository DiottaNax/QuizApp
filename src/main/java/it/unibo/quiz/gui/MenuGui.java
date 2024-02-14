package it.unibo.quiz.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import it.unibo.quiz.logic.QuizController;
import it.unibo.quiz.logic.ScoredQuiz;
import it.unibo.quiz.questions.QuestionProvider;

public class MenuGui extends JFrame {

    private final JLabel numQuestionLabel;
    private final CircleSlider numQuestionSlider;
    private final QuestionProvider qProvider;

    private int questionNumber; 

    public MenuGui(final QuestionProvider questionProvider) {
        this.setTitle("Menu");
        try{
            this.setIconImage(ImageIO.read(ClassLoader.getSystemResourceAsStream("it/unibo/logo.png")));
        } catch (IOException e) {
            // No image setted, exception handled :)
        }
        int maxQuestion = questionProvider.getQuestions().size();
        this.numQuestionSlider = new CircleSlider(1, maxQuestion);
        this.numQuestionLabel = new JLabel("" + this.numQuestionSlider.getValue());
        this.numQuestionSlider
                .addChangeListener(e -> this.numQuestionLabel.setText("" + this.numQuestionSlider.getValue()));
        this.numQuestionSlider.setFocusable(false);
        this.numQuestionSlider.setRequestFocusEnabled(false);
        this.questionNumber = 5;
        this.qProvider = questionProvider;


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JTextArea description = new JTextArea(
                "Hi!\nWelcome in QuizApp, this is the default implementation of the GUI.\n"
                + "The default quiz is on Telecomunication Network, made to practice for an exam."
                + "\nSee the documentation to change questions.\nEnjoy!");
        description.setEditable(false);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setFont(new Font(Font.DIALOG, 3, 14));
        mainPanel.add(description);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        var startButton = new Button("Start");
        startButton.addActionListener(e -> this.onStartClick());
        panel.add(new JLabel("Question in the quiz:"));
        panel.add(this.numQuestionLabel);
        panel.add(this.numQuestionSlider);
        panel.add(startButton);

        final Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        final long sw = Math.round(screenDim.getWidth());
        final long sh = Math.round(screenDim.getHeight());
        this.setLocation(new Point((int) sw / 3, (int) sh / 3));
        this.setSize(new Dimension((int) sw / 3, (int) sh / 3));
        panel.setSize(this.getSize());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel.add(panel);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
    
    private void onStartClick() {
        this.questionNumber = this.numQuestionSlider.getValue();
        
        SwingUtilities.invokeLater(
                () -> new QuizGui(
                        new QuizController(new ScoredQuiz(this.qProvider, this.questionNumber)), this.questionNumber));

        this.setVisible(false);
        this.dispose();
    }
}
