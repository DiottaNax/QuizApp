package it.unibo.quiz.gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleSlider extends JSlider {

    public CircleSlider() {
        super();
        this.addUI();
    }

    public CircleSlider(final BoundedRangeModel brm) {
        super(brm);
        this.addUI();
    }

    public CircleSlider(final int orientation) {
        super(orientation);
        this.addUI();
    }

    public CircleSlider(final int min, final int max) {
        super(min, max);
        this.addUI();
    }

    public CircleSlider(final int min, final int max, int value) {
        super(min, max, value);
        this.addUI();
    }

    public CircleSlider(final int orientation, final int min, final int max, int value) {
        super(orientation, min, max, value);
        this.addUI();
    }

    private void addUI() {
        this.setUI(new CircleSliderUI(this));
        this.addChangeListener(e -> repaint()); // Aggiunge un listener per intercettare gli eventi di cambiamento
    }

    public class CircleSliderUI extends BasicSliderUI {

            public CircleSliderUI(JSlider b) {
            super(b);
        }

        public void paintThumb(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int thumbWidth = 16;
            int thumbHeight = 16;

            int thumbX = thumbRect.x + (thumbRect.width - thumbWidth) / 2;
            int thumbY = thumbRect.y + (thumbRect.height - thumbHeight) / 2;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLUE); // Colore del cerchio

            Ellipse2D thumb = new Ellipse2D.Double(thumbX, thumbY, thumbWidth, thumbHeight);
            g2d.fill(thumb);
        }

        @Override
        protected Dimension getThumbSize() {
            return new Dimension(16, 16); // Imposta le dimensioni del cerchio
        }
    }
}

