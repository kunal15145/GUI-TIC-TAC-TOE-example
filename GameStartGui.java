package com.company;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.management.timer.TimerMBean;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Time;

public class GameStartGui {
    JButton User1vsUser2,USER1vsComp,USERvsAI,Aivscomp,exitButton;
    private JFrame mainframe;
    private JLabel headerLabel;
    private JLabel StatusLabul;
    private JPanel controlpanel;
    MediaPlayer mediaplayer;
    public GameStartGui() throws URISyntaxException, IOException {
        JFXPanel fxpanel = new JFXPanel();
        prepareGUI();
    }

    private void prepareGUI() throws URISyntaxException, IOException {
        mainframe = new JFrame("Tic-Tac-Toe");
        mainframe.setSize(600,500);
        mainframe.setBackground(Color.DARK_GRAY);
        mainframe.setLayout(new GridLayout(3,1));
        headerLabel = new JLabel("",JLabel.CENTER);
        StatusLabul = new JLabel("",JLabel.CENTER);
        StatusLabul.setSize(350,350);
        StatusLabul.setForeground(Color.RED);
        StatusLabul.setText("Choose Game Options");
        StatusLabul.setFont(new Font("Sans",Font.ITALIC,30));
        StatusLabul.setLayout(new BorderLayout());
        mainframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlpanel = new JPanel();
        controlpanel.setLayout(new FlowLayout());
        mainframe.getContentPane().setBackground(Color.DARK_GRAY);
        mainframe.add(headerLabel);
        mainframe.add(StatusLabul,BorderLayout.NORTH);
        mainframe.add(controlpanel);
        mainframe.setVisible(true);
        String bip = "song.mp3";
        Media hit = new Media(Paths.get(bip).toUri().toString());
        mediaplayer = new MediaPlayer(hit);
        mediaplayer.play();
    }
    public void showEvents() throws IOException, URISyntaxException {
        JPanel buttonpanel = new JPanel();
        controlpanel.setLayout(new BorderLayout());
        //
        User1vsUser2 = new JButton("USER1 vs USER2");
        User1vsUser2.setActionCommand("1");
        User1vsUser2.setBorderPainted(false);
        User1vsUser2.setContentAreaFilled(false);
        User1vsUser2.setFocusPainted(false);
        User1vsUser2.setOpaque(true);
        User1vsUser2.setBackground(Color.WHITE);
        User1vsUser2.addActionListener(new GameStartGui.ButtonClickListener());
        //
        USER1vsComp = new JButton("USER vs COMP");
        USER1vsComp.setActionCommand("2");
        USER1vsComp.setBorderPainted(false);
        USER1vsComp.setContentAreaFilled(false);
        USER1vsComp.setFocusPainted(false);
        USER1vsComp.setOpaque(true);
        USER1vsComp.setBackground(Color.WHITE);
        USER1vsComp.addActionListener(new GameStartGui.ButtonClickListener());
        //
        Aivscomp = new JButton("COMP vs AI");
        Aivscomp.setActionCommand("3");
        Aivscomp.setBorderPainted(false);
        Aivscomp.setContentAreaFilled(false);
        Aivscomp.setFocusPainted(false);
        Aivscomp.setOpaque(true);
        Aivscomp.setBackground(Color.WHITE);
        Aivscomp.addActionListener(new GameStartGui.ButtonClickListener());
        //
        USERvsAI = new JButton("USER vs AI");
        USERvsAI.setActionCommand("4");
        USERvsAI.setBorderPainted(false);
        USERvsAI.setContentAreaFilled(false);
        USERvsAI.setFocusPainted(false);
        USERvsAI.setOpaque(true);
        USERvsAI.setBackground(Color.WHITE);
        USERvsAI.addActionListener(new GameStartGui.ButtonClickListener());
        //
        exitButton = new JButton("Exit");
        exitButton.setActionCommand("5");
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(true);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(true);
        exitButton.setBackground(Color.WHITE);
        exitButton.addActionListener(new GameStartGui.ButtonClickListener());
        //
        buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonpanel.add(USER1vsComp);
        buttonpanel.add(User1vsUser2);
        buttonpanel.add(Aivscomp);
        buttonpanel.add(USERvsAI);
        buttonpanel.add(exitButton);
        buttonpanel.setBackground(Color.DARK_GRAY);
        controlpanel.add(buttonpanel,BorderLayout.NORTH);
        controlpanel.setBackground(Color.DARK_GRAY);
        mainframe.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(command.equals("1"))
            {
                User1vsUser2.setBackground(Color.RED);
                headerLabel.setForeground(Color.BLUE);
                headerLabel.setFont(new Font("Sans",Font.ITALIC,16));
                headerLabel.setText("Starting Game between Player 1 and Player 2");
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainframe.dispose();
                        mediaplayer.dispose();
                        Takeinputfor2users takeinputGui = new Takeinputfor2users();
                    }
                });
                timer.start();
                timer.setRepeats(false);
                // changes here
            }
            if(command.equals("2"))
            {
                USER1vsComp.setBackground(Color.RED);
                headerLabel.setForeground(Color.BLUE);
                headerLabel.setFont(new Font("Sans",Font.ITALIC,16));
                headerLabel.setText("Starting game between Player and Comp");
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainframe.dispose();
                        mediaplayer.dispose();
                        Takeinputfor1user takeinputfor1user = new Takeinputfor1user();
                    }
                });;
                timer.start();
                timer.setRepeats(false);
            }
            if(command.equals("3"))
            {
                Aivscomp.setBackground(Color.RED);
                headerLabel.setForeground(Color.BLUE);
                headerLabel.setFont(new Font("Sans",Font.ITALIC,16));
                headerLabel.setText("Starting game between Comp and AI");
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainframe.dispose();
                        mediaplayer.dispose();
                        CpuvsAibotGui game = new CpuvsAibotGui();
                        try {
                            game.StartingGame();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                timer.start();
                timer.setRepeats(false);
            }
            if(command.equals("4"))
            {
                USERvsAI.setBackground(Color.RED);
                headerLabel.setForeground(Color.BLUE);
                headerLabel.setFont(new Font("Sans",Font.ITALIC,16));
                headerLabel.setText("Starting Game between Player and AI");
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainframe.dispose();
                        mediaplayer.dispose();
                        Takeinputfor1user takeinputfor1user = new Takeinputfor1user(5);
                    }
                });
                timer.start();
                timer.setRepeats(false);
            }
            if(command.equals("5"))
            {
                exitButton.setBackground(Color.RED);
                System.exit(0);
            }
        }
    }
}

