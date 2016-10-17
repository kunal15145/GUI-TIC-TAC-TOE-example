package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameBoardforuservsuser {
    private JPanel panel = new JPanel();
    private int a,b,c;
    private String player1;
    private JFrame window;
    private String player2;
    private JLabel label = new JLabel(" ",JLabel.CENTER);
    private int altertheoption = 0;
    private JButton button[] = new JButton[9];

    GameBoardforuservsuser(String s, String s1) {
        player1 = new String(s);
        player2 = new String(s1);
        window = new JFrame("Game");
        window.setPreferredSize(new Dimension(600,600));
        window.getContentPane().setBackground(Color.WHITE);
        label.setForeground(Color.BLUE);
        setLayoutofGrid();
    }

    private void setLayoutofGrid() {
        panel.setPreferredSize(new Dimension(450,450));
        panel.setLayout(new GridLayout(3,3));
        intitGrid();
    }

    private void intitGrid() {
        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
            button[i].setText(" ");
            button[i].addActionListener(new buttonlistner());
            panel.add(button[i]);
        }
        resetButton();
        label.setBackground(Color.WHITE);
        window.setSize(500,500);
        window.add(panel,BorderLayout.SOUTH);
        window.add(label,BorderLayout.NORTH);
        window.pack();
        window.setVisible(true);
    }

    private class buttonlistner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonclicked = (JButton)e.getSource();
            if ((altertheoption % 2) == 0) {
                label.setText(player1+" "+" Chance");
                buttonclicked.setFont(new Font("Arial", Font.PLAIN, 45));
                buttonclicked.setText("X");
                buttonclicked.setEnabled(false);
                altertheoption++;
                if(getaltertheaction()%2!=0)
                {
                    label.setText(player2+" "+" Chance");
                }
            } else {
                buttonclicked.setFont(new Font("Arial", Font.PLAIN, 45));
                buttonclicked.setText("O");
                buttonclicked.setEnabled(false);
                altertheoption++;
                if(getaltertheaction()%2==0)
                {
                    label.setText(player1+" "+" Chance");
                }
            }
            if (checkforwin() == true&&(altertheoption%2)==0) {
                button[a].setBackground(Color.RED);
                button[b].setBackground(Color.RED);
                button[c].setBackground(Color.RED);
                String[] options = new String[]{"Yes","No","New Game"};
                int response = JOptionPane.showOptionDialog(null,player2+" Won"+" Do u wannna continue","Result",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
                if(response==0)
                {
                    resetButton();
                }
                else if(response==1)
                {
                    System.exit(0);
                }
                else if(response==2)
                {
                    window.dispose();
                    try {
                        GameStartGui startGui = new GameStartGui();
                        startGui.showEvents();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
            else if(checkforwin()==true&&(altertheoption%2)!=0)
            {
                button[a].setBackground(Color.RED);
                button[b].setBackground(Color.RED);
                button[c].setBackground(Color.RED);
                String[] options = new String[]{"Yes","No","New Game"};
                int response = JOptionPane.showOptionDialog(null,player1+" Won"+" Do u Wanna Continue","Result",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
                if(response==0)
                {
                    resetButton();
                }
                else if(response==1)
                {
                    System.exit(0);
                }
                else if(response==2)
                {
                    window.dispose();
                    try {
                        GameStartGui startGui = new GameStartGui();
                        startGui.showEvents();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            else if(checkStatus()==true)
            {
                String[] options = new String[]{"Yes","No","New Game"};
                int response = JOptionPane.showOptionDialog(null,"Draw"+" Do u wanna Continue","Result",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
                if(response==0)
                {
                    resetButton();
                }
                else if(response==1)
                {
                    System.exit(0);
                }
                else if(response==2)
                {
                    window.dispose();
                    try {
                        GameStartGui startGui = new GameStartGui();
                        startGui.showEvents();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

   private boolean checkStatus() {
        int count  = 0;
        for(int i=0;i<9;i++)
        {
            if(button[i].getText().equals(" "))
            {
                count++;
            }
        }
        if(count==9)
        {
            return false;
        }
        else if(count==0)
            return true;
        else
            return false;
    }

    private void resetButton() {
        altertheoption=0;
        for (int i = 0; i < 9; i++) {
            button[i].setBackground(Color.WHITE);
            button[i].setEnabled(true);
            button[i].setText(" ");
        }
    }

    private boolean checkforwin() {
        //for horizontol wins
        if(checkbuttonstatus(0,1)&&checkbuttonstatus(1,2))
        {
            a=0;
            b=1;
            c=2;
            return true;
        }
        else if(checkbuttonstatus(3,4)&&checkbuttonstatus(4,5))
        {
            a=3;
            b=4;
            c=5;
            return true;
        }
        else if(checkbuttonstatus(6,7)&&checkbuttonstatus(7,8))
        {
            a=6;
            b=7;
            c=8;
            return true;
        }
        //for vertical wins
        else if(checkbuttonstatus(0,3)&&checkbuttonstatus(3,6))
        {
            a=0;
            b=3;
            c=6;
            return true;
        }
        else if(checkbuttonstatus(1,4)&&checkbuttonstatus(4,7))
        {
            a=1;
            b=4;
            c=7;
            return true;
        }
        else if(checkbuttonstatus(2,5)&&checkbuttonstatus(5,8))
        {
            a=2;
            b=5;
            c=8;
            return true;
        }
        else if(checkbuttonstatus(2,5)&&checkbuttonstatus(5,8))
        {
            a=2;
            b=5;
            c=8;
            return true;
        }
        //for diagnol wins
        else if(checkbuttonstatus(0,4)&&checkbuttonstatus(4,8))
        {
            a=0;
            b=4;
            c=8;
            return true;
        }
        else if(checkbuttonstatus(2,4)&&checkbuttonstatus(4,6))
        {
            a=2;
            b=4;
            c=6;
            return true;
        }
        else
            return false;
    }
    private boolean checkbuttonstatus(int a,int b)
    {
        if(button[a].getText().equals(button[b].getText())&&!button[a].getText().equals(" "))
            return true;
        else
            return false;
    }
    int getaltertheaction()
    {
        return altertheoption;
    }
}
