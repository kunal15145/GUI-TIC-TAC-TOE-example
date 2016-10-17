package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.*;

public class IntroGui {
    private JFrame mainframe;
    private JLabel headerLabel;
    private JLabel StatusLabul;
    private JPanel controlpanel;
    MediaPlayer mediaplayer;
    public IntroGui() throws URISyntaxException, IOException {
        JFXPanel fxpanel = new JFXPanel();
        prepareGUI();
    }

    private void prepareGUI() throws URISyntaxException, IOException {
        mainframe = new JFrame("Tic-Tac-Toe");
        mainframe.setSize(400,400);
        mainframe.setBackground(Color.BLACK);
        mainframe.setLayout(new GridLayout(3,1));
        headerLabel = new JLabel("",JLabel.CENTER);
        StatusLabul = new JLabel("",JLabel.CENTER);
        StatusLabul.setForeground(Color.RED);
        StatusLabul.setText("TIC-TAC-TOE");
        StatusLabul.setFont(new Font("Sans",Font.ITALIC,30));
        StatusLabul.setSize(350,350);
        StatusLabul.setLayout(new BorderLayout());
        mainframe.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
            }
        });
        controlpanel = new JPanel();
        controlpanel.setLayout(new FlowLayout());
        mainframe.getContentPane().setBackground(Color.DARK_GRAY);
        headerLabel.setOpaque(false);
        StatusLabul.setOpaque(false);
        mainframe.add(headerLabel);
        mainframe.add(StatusLabul,BorderLayout.NORTH);
        mainframe.add(controlpanel);
        mainframe.setVisible(true);
        String bip = "song.mp3";
        Media hit = new Media(Paths.get(bip).toUri().toString());
        mediaplayer = new MediaPlayer(hit);
        mediaplayer.play();
    }
    public void showEvents() {
        controlpanel.setLayout(new BorderLayout());
        controlpanel.setOpaque(true);
        JButton Startbutton = new JButton("Start Game");
        JButton mute = new JButton("Mute");
        JPanel buttonpanel = new JPanel();
        JButton exitButton = new JButton("Exit");
        Startbutton.setActionCommand("Start Game");
        Startbutton.setBorderPainted(false);
        Startbutton.setContentAreaFilled(false);
        Startbutton.setFocusPainted(false);
        Startbutton.setOpaque(true);
        mute.setActionCommand("Mute");
        mute.setBorderPainted(false);
        mute.setContentAreaFilled(false);
        mute.setFocusPainted(false);
        mute.setOpaque(true);
        exitButton.setActionCommand("Exit");
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(true);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(true);
        Startbutton.setBackground(Color.WHITE);
        exitButton.setBackground(Color.WHITE);
        mute.setBackground(Color.WHITE);
        JButton play = new JButton("Play");
        play.setActionCommand("Play");
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);
        play.setFocusPainted(false);
        play.setOpaque(true);
        play.setBackground(Color.WHITE);
        Startbutton.addActionListener(new ButtonClickListener());
        mute.addActionListener(new ButtonClickListener());
        exitButton.addActionListener(new ButtonClickListener());
        buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        play.addActionListener(new ButtonClickListener());
        buttonpanel.add(Startbutton);
        buttonpanel.add(exitButton);
        buttonpanel.add(mute);
        buttonpanel.add(play);
        buttonpanel.setBackground(Color.DARK_GRAY);
        controlpanel.add(buttonpanel,BorderLayout.NORTH);
        controlpanel.setBackground(Color.DARK_GRAY);
        mainframe.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(command.equals("Start Game"))
            {
                StatusLabul.setText("Game initialized");
                mainframe.dispose();
                mediaplayer.dispose();
                try {
                    GameStartGui s = new GameStartGui();
                    s.showEvents();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(command.equals("Mute"))
            {
                mediaplayer.setMute(true);
            }
            if(command.equals("Exit"))
            {
                System.exit(0);
            }
            if(command.equals("Play"))
            {
                mediaplayer.setMute(false);
            }
        }
    }
}
