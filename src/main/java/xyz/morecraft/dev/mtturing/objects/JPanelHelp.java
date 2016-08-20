package xyz.morecraft.dev.mtturing.objects;

import xyz.morecraft.dev.mtturing.main.Main;

import javax.swing.*;
import java.util.Scanner;

public class JPanelHelp extends JPanel {

    private JEditorPane jep1;
    private JScrollPane sc1;
    private Scanner scanner;

    public JPanelHelp() {
        setLayout(null);

        jep1 = new JEditorPane();
        jep1.setEditable(false);
        jep1.setContentType("text/html");

        scanner = new Scanner(JPanelHelp.class.getResourceAsStream("/help.html"), "UTF-8");
        String text = scanner.useDelimiter("\\A").next();

        text = text.replace("RWRWRWRW", Main.appPathRes + "/rw.gif");
        text = text.replace("RWRURAR", Main.appPathRes + "/uz.jpg");

        jep1.setText(text);

        jep1.setCaretPosition(0);

        sc1 = new JScrollPane(jep1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        sc1.setBounds(0, 0, 745, 474);

        add(sc1);
    }

}
