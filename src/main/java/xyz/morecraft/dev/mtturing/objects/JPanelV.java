package xyz.morecraft.dev.mtturing.objects;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JPanelV extends JPanel {

    private BufferedImage img;

    public JPanelV() {
        setBackground(new Color(255, 255, 255));
        setBorder(null);
        setLayout(null);
        img = null;
    }

    public void drawTape(Integer[] tape, int position, int currentState) {
        this.setPreferredSize(new Dimension(tape.length * 40 + 100, 175));
        this.revalidate();
        img = new BufferedImage(tape.length * 40 + 100, 160, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setColor(Color.black);

        g.drawLine(50, 80, 50 + tape.length * 40, 80);
        g.drawLine(50, 130, 50 + tape.length * 40, 130);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        for (int i = 0; i < tape.length; i++) {
            g.drawLine(50 + i * 40, 80, 50 + i * 40, 130);
            g.drawString((tape[i] == 8 ? "B" : (tape[i] == -666 ? "..." : String.valueOf(tape[i]))), (tape[i] == -666 ? 57 : 61) + 40 * i, 117);
        }
        g.drawLine(50 + tape.length * 40, 80, 50 + tape.length * 40, 130);
        g.drawLine(50 + position * 40, 50, 50 + position * 40 + 40, 50);
        g.drawLine(50 + position * 40, 20, 50 + position * 40 + 40, 20);
        g.drawLine(50 + position * 40, 20, 50 + position * 40, 50);
        g.drawLine(50 + position * 40 + 40, 20, 50 + position * 40 + 40, 50);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString("Q" + String.valueOf(currentState), 56 + 40 * position, 42);
        g.setStroke(new BasicStroke(3, BasicStroke.JOIN_MITER, BasicStroke.JOIN_MITER));
        g.drawLine(70 + position * 40, 50, 70 + position * 40, 78);
        g.drawLine(70 + position * 40, 78, 75 + position * 40, 73);
        g.drawLine(70 + position * 40, 78, 65 + position * 40, 73);

        g.dispose();
        repaint();
    }

    public void drawHeadPosition(int position, int currentState) {
        Graphics g = img.getGraphics();
        g.setColor(Color.black);

        g.dispose();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, null);
        }
    }

}
