package it.unibo.quiz.gui;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * Custom button class implemented mostly using of ChatGPT for a quick implementation.
 * This button provides a customized appearance with rounded corners, hover effects, and custom colors.
 * The design is inspired by modern UI trends.
 * 
 * @author chatGPT
 * @implNote This class was not reviewed and can contain bugs or errors
 */
public class Button extends JButton {

    private Color backgroundColor = Color.LIGHT_GRAY;
    //By deafult it is a shade of blue
    private Color hoverColor = new Color(0x0067FF);
    //By default it is a shade of cyan
    private Color pressedColor = new Color(0xCBD8EC);
    private Color textColor = Color.BLACK;
    private int arc = 50;

    public Button(String text) {
        super(text);
        setForeground(textColor);
        setBorderPainted(false);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(backgroundColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColor);
            }
        });

        this.setFocusPainted(getFocusTraversalKeysEnabled());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2d.setColor(pressedColor);
        } else {
            g2d.setColor(backgroundColor);
        }

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
        g2d.fill(roundedRectangle);

        g2d.dispose();
        super.paintComponent(g);
        this.setFocusPainted(false);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        float borderWidth = (float) 0.4;

        if (getModel().isRollover()) {
            g2d.setColor(hoverColor);
        } else {
            g2d.setColor(Color.BLACK);
        }

        g2d.setStroke(new BasicStroke(borderWidth));

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth() - borderWidth,
                getHeight() - borderWidth, arc, arc);
        g2d.draw(roundedRectangle);

        g2d.dispose();
    }


    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        setBackground(backgroundColor);
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public Color getPressedColor() {
        return pressedColor;
    }

    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        setForeground(textColor);
    }
}

