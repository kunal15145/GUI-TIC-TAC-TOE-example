package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Takeinputfor2users {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JTextField name1 =  new JTextField(20);
    private JTextField name2 = new JTextField(20);
    private JButton button = new JButton("Submit");
    GameBoardforuservsuser game;
    String s,s1;
    Takeinputfor2users()
    {
        label1.setText("Name 1 : ");
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.add(label1);
        panel.add(name1);
        label2.setText("Name 2 : ");
        panel.add(label2);
        panel.add(name2);
        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
        init();
    }

    public void init() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = name1.getText();
                String s1 = name2.getText();
                if(s.isEmpty()||s1.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Name is Empty");
                    Takeinputfor2users takeinputgui = new Takeinputfor2users();
                }
                frame.dispose();
                if((!s.isEmpty())&&(!s1.isEmpty()))
                   game = new GameBoardforuservsuser(s,s1);
            }
        });
    }
}

