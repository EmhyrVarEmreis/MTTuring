package xyz.morecraft.dev.mtturing.main;

import xyz.morecraft.dev.mtturing.core.MTRwT;
import xyz.morecraft.dev.mtturing.functions.LoadFromRes;
import xyz.morecraft.dev.mtturing.objects.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener, ChangeListener {

    private JTextField tfm, tfn;
    private JPanel p1, p2, p3, p4, pW;
    private JLabel jl1, jl2, jl3, jlc1, jlc1b, jlc2, jlc2b, jlc3, jlc3b, jlc4, jlc5, jlc5b, jp4l1, jp1l1, jp1l2;
    public JPanelV pRys;
    private JPanelRw pr;
    private JScrollPane sc1, sc2;
    private JTextArea ta1;
    private JButton bStart;
    private JMenuBar menuBar;
    private JMenu mHelp;
    private JMenuItem mHelpHelp, mHelpAbout;
    private MTRwT mtt = null;
    private JSlider js1;
    private JCheckBox ch1;
    public static String appPathRes = "";
    public static Main app;

    public Main() {
        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Turing Machine - Calculate Proper Difference");
        setUILookAndFeel();
        setIconImage(LoadFromRes.loadImageAsImage("icon_app.png"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });

        menuBar = new JMenuBar();

        mHelp = new JMenu("Help");
        mHelp.setIcon(LoadFromRes.loadImageAsIconImage("Help.png"));
        mHelpHelp = new JMenuItem("Manual...", LoadFromRes.loadImageAsIconImage("Help_book.png"));
        mHelpHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        mHelpHelp.addActionListener(this);
        mHelp.add(mHelpHelp);
        mHelpAbout = new JMenuItem("About app...", LoadFromRes.loadImageAsIconImage("Help_book.png"));
        mHelpAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        mHelpAbout.addActionListener(this);
        mHelp.add(mHelpAbout);

        menuBar.add(mHelp);

        setJMenuBar(menuBar);

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBorder(BorderFactory.createTitledBorder(null, "START", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 11)));
        p1.setBounds(0, 0, 794, 67);

        bStart = new JButton("S T A R T !");
        bStart.setBounds(10, 15, 300, 40);
        bStart.addActionListener(this);
        bStart.setFont(new Font("Tahoma", Font.BOLD, 14));

        tfm = new JTextField();
        tfm.setDocument(new JTextFieldLimit(3, true));
        tfm.setBounds(335, 15, 40, 20);
        tfm.setText("8");

        tfn = new JTextField();
        tfn.setDocument(new JTextFieldLimit(3, true));
        tfn.setBounds(335, 35, 40, 20);
        tfn.setText("5");

        jl2 = new JLabel("m:");
        jl2.setBounds(315, 15, 20, 20);

        jl3 = new JLabel("n:");
        jl3.setBounds(315, 35, 20, 20);

        js1 = new JSlider(JSlider.HORIZONTAL, 20, 500, 300);
        js1.setBounds(495, 15, 290, 22);
        js1.addChangeListener(this);
        js1.setToolTipText(String.valueOf(js1.getValue()) + "ms");

        jp1l1 = new JLabel("Interval: ");
        jp1l1.setBounds(685, 35, 50, 20);

        jp1l2 = new JLabel(String.valueOf(js1.getValue()) + "ms");
        jp1l2.setBounds(740, 35, 50, 20);

        jl1 = new JLabel("INTERVAL:");
        jl1.setFont(new Font("Tahoma", Font.BOLD, 12));
        jl1.setBounds(415, 15, 75, 20);

        p1.add(jp1l1);
        p1.add(jp1l2);
        p1.add(jl1);
        p1.add(jl2);
        p1.add(jl3);
        p1.add(js1);
        p1.add(bStart);
        p1.add(tfm);
        p1.add(tfn);

        add(p1);

        p2 = new JPanel();
        p2.setLayout(null);
        p2.setBorder(BorderFactory.createTitledBorder(null, "V I S U A L I S A T I O N", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma",
                Font.BOLD, 11)));
        p2.setBounds(0, 65, 794, 200);

        pRys = new JPanelV();

        sc1 = new JScrollPane(pRys, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sc1.setBounds(10, 15, 774, 175);

        p2.add(sc1);

        add(p2);

        p3 = new JPanel();
        p3.setLayout(null);
        p3.setBorder(BorderFactory.createTitledBorder(null, "Result interpretation", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma",
                Font.BOLD, 11)));
        p3.setBounds(0, 263, 572, 107);

        jlc1 = new JLabel("Proper result:");
        jlc1.setFont(new Font("Tahoma", Font.BOLD, 12));
        jlc1.setBounds(10, 15, 250, 20);

        jlc1b = new JLabel("-");
        jlc1b.setFont(new Font("Tahoma", Font.BOLD, 12));
        jlc1b.setBounds(270, 15, 100, 20);

        jlc2 = new JLabel("Count of zeros on tape:");
        jlc2.setFont(new Font("Tahoma", Font.BOLD, 12));
        jlc2.setBounds(10, 35, 250, 20);

        jlc2b = new JLabel("-");
        jlc2b.setFont(new Font("Tahoma", Font.BOLD, 12));
        jlc2b.setBounds(270, 35, 100, 20);

        jlc3 = new JLabel("Stan MT:");
        jlc3.setFont(new Font("Tahoma", Font.BOLD, 12));
        jlc3.setBounds(10, 55, 250, 20);

        jlc3b = new JLabel("-");
        jlc3b.setFont(new Font("Tahoma", Font.BOLD, 12));
        jlc3b.setBounds(270, 55, 100, 20);

        jlc5 = new JLabel("Transitions count:");
        jlc5.setFont(new Font("Tahoma", Font.BOLD, 12));
        jlc5.setBounds(10, 75, 250, 20);

        jlc5b = new JLabel("-");
        jlc5b.setFont(new Font("Tahoma", Font.BOLD, 12));
        jlc5b.setBounds(270, 75, 100, 20);

        pW = new JPanel();
        pW.setBounds(481, 15, 80, 80);
        pW.setBackground(new Color(140, 140, 140));

        jlc4 = new JLabel("VERDICT:");
        jlc4.setFont(new Font("Tahoma", Font.BOLD, 16));
        jlc4.setBounds(373, 45, 90, 20);

        p3.add(jlc1);
        p3.add(jlc1b);
        p3.add(jlc2);
        p3.add(jlc2b);
        p3.add(jlc3);
        p3.add(jlc3b);
        p3.add(jlc5);
        p3.add(jlc5b);
        p3.add(jlc4);
        p3.add(pW);

        add(p3);

        p4 = new JPanel();
        p4.setLayout(null);
        p4.setBorder(BorderFactory.createTitledBorder(null, "Console", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 11)));
        p4.setBounds(0, 368, 794, 179);
        // p3.setBounds(0, 0, 394, 500);

        ta1 = new JTextArea();
        ta1.setBackground(new Color(238, 238, 238));
        ta1.setFont(new Font("Arial", Font.PLAIN, 10));
        ta1.setEditable(false);

        sc2 = new JScrollPane(ta1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sc2.setBounds(10, 15, 774, 134);

        jp4l1 = new JLabel("Symbol wrapped with '|' marks head position.", JLabel.RIGHT);
        jp4l1.setBounds(485, 152, 300, 20);

        ch1 = new JCheckBox("Automatic scrolling");
        ch1.setSelected(true);
        ch1.setBounds(10, 152, 200, 20);

        p4.add(jp4l1);
        p4.add(ch1);
        p4.add(sc2);

        add(p4);

        pr = new JPanelRw();
        pr.setLayout(null);
        pr.setBorder(BorderFactory.createTitledBorder(null, "Proper difference", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 11)));
        pr.setBounds(574, 263, 220, 107);

        add(pr);

        appPathRes = cutResPath(LoadFromRes.getURLOfResource("Help_book.png"));

    }

    public static void main(String[] args) {
        app = new Main();
        app.setVisible(true);
    }

    public void setCountOfZerosOnTape(int i) {
        jlc2b.setText(String.valueOf(i));
    }

    public void setTransitionCount(int i) {
        jlc5b.setText(String.valueOf(i));
    }

    public void setMachineState(int i) {
        jlc3b.setText("Q" + String.valueOf(i));
    }

    public void setVerdict(boolean b) {
        if (b) {
            pW.setBackground(new Color(30, 255, 30));
        } else {
            pW.setBackground(new Color(255, 30, 30));
        }
    }

    public void setEnabledControl(boolean b) {
        bStart.setEnabled(b);
        tfm.setEnabled(b);
        tfn.setEnabled(b);
    }

    public void addToMainConsole(String s) {
        ta1.append(s + "\n");
        if (ch1.isSelected()) {
            ta1.setCaretPosition(ta1.getDocument().getLength());
        }
    }

    public static void setUILookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void makeInformationAbout() {
        JDialog infAb = new JDialog(this, true);
        infAb.setTitle("About app");
        infAb.setSize(400, 120);
        infAb.setResizable(false);
        infAb.setLocationRelativeTo(this);
        infAb.add(new JPanelInformation());
        infAb.setVisible(true);
    }

    private void makeHelp() {
        JDialog infAb = new JDialog(this, true);
        infAb.setTitle("Description");
        infAb.setSize(750, 500);
        infAb.setResizable(false);
        infAb.setLocationRelativeTo(this);
        infAb.add(new JPanelHelp());
        infAb.setVisible(true);
    }

    private void startMain() {
        ta1.setText("");
        jlc1b.setText(tfm.getText()
                + " - "
                + tfn.getText()
                + " = "
                + (Integer.valueOf(tfm.getText()) < Integer.valueOf(tfn.getText()) ? "0" : String.valueOf(Integer.valueOf(tfm.getText())
                - Integer.valueOf(tfn.getText()))));
        mtt = new MTRwT(Integer.valueOf(tfm.getText()), Integer.valueOf(tfn.getText()));
        mtt.setTimer(js1.getValue());
        mtt.start();
        pW.setBackground(new Color(210, 210, 210));
        setEnabledControl(false);
    }

    private String cutResPath(String s) {
        return s.substring(0, s.lastIndexOf("/"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();

        if (o == bStart) {
            startMain();
        } else if (o == mHelpAbout) {
            makeInformationAbout();
        } else if (o == mHelpHelp) {
            makeHelp();
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (source == js1) {
            if (mtt != null) {
                mtt.setTimer(source.getValue());
            }
            source.setToolTipText(String.valueOf(source.getValue()) + "ms");
            jp1l2.setText(String.valueOf(source.getValue()) + "ms");
        }
    }

}
