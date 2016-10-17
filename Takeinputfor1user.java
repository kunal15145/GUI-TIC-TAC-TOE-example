package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Takeinputfor1user {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel label1 = new JLabel();
    private JTextField name1 =  new JTextField(20);
    private JButton button = new JButton("Submit");
    GameBoardforUservsCpuGui game;
    UservsAI game1;
    String s;
    Takeinputfor1user()
    {
        label1.setText("Name 1 : ");
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.add(label1);
        panel.add(name1);
        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
        init();
    }
    Takeinputfor1user(int s)
    {
        label1.setText("Name 1 : ");
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.add(label1);
        panel.add(name1);
        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
        init1();
    }

    private void init1() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = name1.getText();
                if(s.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Name is Empty");
                    Takeinputfor1user takeinputgui = new Takeinputfor1user(5);
                }
                frame.dispose();
                if((!s.isEmpty()))
                    game1 = new UservsAI(s);
            }
        });
    }

    private void init() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = name1.getText();
                if(s.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Name is Empty");
                    Takeinputfor1user takeinputgui = new Takeinputfor1user();
                }
                frame.dispose();
                if((!s.isEmpty()))
                    game = new GameBoardforUservsCpuGui(s);
            }
        });
    }
}
