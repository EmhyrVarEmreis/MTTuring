package xyz.morecraft.dev.mtturing.objects;

import xyz.morecraft.dev.mtturing.functions.LoadFromRes;

import javax.swing.*;
import java.awt.*;

public class JPanelInformation extends JPanel {

    private JLabel l1, l2, l3;
    private Image img1;

    public JPanelInformation() {
        setLayout(null);

        img1 = LoadFromRes.loadImageAsImage("icon_app.png");

        l1 = new JLabel("MT - Calculate Proper Difference");
        l1.setBounds(60, 10, 310, 20);
        l1.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(l1);

        l2 = new JLabel("Created by: Mateusz Stefaniak");
        l2.setBounds(60, 30, 200, 30);
        l2.setFont(new Font("Tahoma", Font.ITALIC | Font.BOLD, 9));
        add(l2);

        l3 = new JLabel("Contact: mateusz.stefaniak@morecraft.pl", JLabel.RIGHT);
        l3.setBounds(170, 70, 220, 30);
        l3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        add(l3);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img1, 15, 15, this);
    }
}
