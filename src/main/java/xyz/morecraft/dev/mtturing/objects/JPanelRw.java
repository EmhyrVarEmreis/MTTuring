package xyz.morecraft.dev.mtturing.objects;

import xyz.morecraft.dev.mtturing.functions.LoadFromRes;

import javax.swing.*;
import java.awt.*;

public class JPanelRw extends JPanel {

    private Image img;

    public JPanelRw() {
        img = LoadFromRes.loadImageAsImage("rw.gif");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 18, 30, null);
        }
    }

}
