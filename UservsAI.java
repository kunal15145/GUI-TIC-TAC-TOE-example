package com.company;
//changes done
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class UservsAI {
    private String Player;
    private int altertheaction;
    private JPanel panel = new JPanel();
    private int a,b,c;
    private JFrame window;
    private JLabel label = new JLabel(" ",JLabel.CENTER);
    private int altertheoption = 0;
    private JButton button[] = new JButton[9];

    UservsAI(String s)
    {
        JOptionPane.showMessageDialog(null,"User will play with O");
        Player = new String(s);
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
            button[i].addActionListener(new UservsAI.buttonlistner());
            panel.add(button[i]);
        }
        resetButton();
        label.setBackground(Color.WHITE);
        window.setSize(500,500);
        window.add(panel,BorderLayout.SOUTH);
        label.setBackground(Color.RED);
        window.add(label,BorderLayout.NORTH);
        window.getContentPane().setBackground(Color.DARK_GRAY);
        window.pack();
        window.setVisible(true);
    }

    public int getaltertheaction() {
        return altertheaction;
    }

    public class buttonlistner implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonclicked = (JButton)e.getSource();
            label.setText(Player+" "+" Chance");
            buttonclicked.setFont(new Font("Arial", Font.PLAIN, 45));
            buttonclicked.setText("O");
            buttonclicked.setEnabled(false);
            if (checkforwin() == true) {
                button[a].setBackground(Color.RED);
                button[b].setBackground(Color.RED);
                button[c].setBackground(Color.RED);
                String[] options = new String[]{"Yes","No","New Game"};
                int response = JOptionPane.showOptionDialog(null,Player+" Won"+" Do u wanna Continue","Result",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
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
            else
            {
                label.setText("AI's Chance");
                int k = Aichance();
                button[k].setFont(new Font("Arial", Font.PLAIN, 45));
                button[k].setText("X");
                button[k].setEnabled(false);
                try {
                    checkforaiwin();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            label.setText(Player+" "+" Chance");
        }
    }

    private void checkforaiwin() throws InterruptedException {
        if(checkforwin()==true)
        {
            button[a].setBackground(Color.RED);
            button[b].setBackground(Color.RED);
            button[c].setBackground(Color.RED);
            String[] options = new String[]{"Yes","No","New Game"};
            int response = JOptionPane.showOptionDialog(null,"Ai Won "+" Do u wanna Continue","Result",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
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
        else if(checkgridstatus()==false){
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

    private boolean checkgridstatus() {
        int count=0;
        for(int i=0;i<9;i++)
        {
            if(button[i].getText().equals(" "))
            {
                count++;
            }
        }
        if(count==0)
            return false;
        else
            return true;
    }

    private int Aichance() {
        Random r = new Random();
        for(int i=0;i<=6;i+=3)
        {
            if(button[i].getText().equals("X"))
            {
                if(checkbuttonstatus(i,i+1) && button[i+2].getText().equals(" "))
                {
                    return i+2;
                }
                if(checkbuttonstatus(i,i+2) && button[i+1].getText().equals(" "))
                {
                    return i+1;
                }
            }
            if(button[i+1].getText().equals("X"))
            {
                if(checkbuttonstatus(i+1,i+2) && button[i].getText().equals(" "))
                {
                    return i;
                }
            }
        }
        for(int i=0;i<=2;i++)
        {
            if(button[i].getText().equals("X"))
            {
                if(checkbuttonstatus(i,i+3) && button[i+6].getText().equals(" "))
                {
                    return i+6;
                }
                if(checkbuttonstatus(i,i+6) && button[i+3].getText().equals(" "))
                {
                    return i+3;
                }
            }
            if(button[i+3].getText().equals("X"))
            {
                if(checkbuttonstatus(i+3,i+6) && button[i].getText().equals(" "))
                {
                    return i;
                }
            }
        }
        if(checkbuttonstatus(0,4) && button[8].getText().equals(" ") && button[0].getText().equals("O"))
        {
            return 8;
        }
        if(checkbuttonstatus(0,8) && button[4].getText().equals(" ") && button[0].getText().equals("O"))
        {
            return 4;
        }
        if(checkbuttonstatus(4,8) && button[0].getText().equals(" ") && button[4].getText().equals("O"))
        {
            return 0;
        }
        if(checkbuttonstatus(2,4) && button[6].getText().equals(" ") && button[2].getText().equals("O"))
        {
            return 6;
        }
        if(checkbuttonstatus(2,6) && button[4].getText().equals(" ") && button[2].getText().equals("O"))
        {
            return 4;
        }
        if(checkbuttonstatus(4,6) && button[2].getText().equals(" ") && button[4].getText().equals("O"))
        {
            return 2;
        }
        for(int i=0;i<=6;i+=3)
        {
            if(button[i].getText().equals("O"))
            {
                if(checkbuttonstatus(i,i+1) && button[i+2].getText().equals(" "))
                {
                    return i+2;
                }
                if(checkbuttonstatus(i,i+2) && button[i+1].getText().equals(" "))
                {
                    return i+1;
                }
            }
            if(button[i+1].getText().equals("O"))
            {
                if(checkbuttonstatus(i+1,i+2) && button[i].getText().equals(" "))
                {
                    return i;
                }
            }
        }
        for(int i=0;i<=2;i++)
        {
            if(button[i].getText().equals("O"))
            {
                if(checkbuttonstatus(i,i+3) && button[i+6].getText().equals(" "))
                {
                    return i+6;
                }
                if(checkbuttonstatus(i,i+6) && button[i+3].getText().equals(" "))
                {
                    return i+3;
                }
            }
            if(button[i+3].getText().equals("O"))
            {
                if(checkbuttonstatus(i+3,i+6) && button[i].getText().equals(" "))
                {
                    return i;
                }
            }
        }
        if(checkbuttonstatus(0,4) && button[8].getText().equals(" ") && button[0].getText().equals("X"))
        {
            return 8;
        }
        if(checkbuttonstatus(0,8) && button[4].getText().equals(" ") && button[0].getText().equals("X"))
        {
            return 4;
        }
        if(checkbuttonstatus(4,8) && button[0].getText().equals(" ") && button[4].getText().equals("X"))
        {
            return 0;
        }
        if(checkbuttonstatus(2,4) && button[6].getText().equals(" ") && button[2].getText().equals("X"))
        {
            return 6;
        }
        if(checkbuttonstatus(2,6) && button[4].getText().equals(" ") && button[2].getText().equals("X"))
        {
            return 4;
        }
        if(checkbuttonstatus(4,6) && button[2].getText().equals(" ") && button[4].getText().equals("X"))
        {
            return 2;
        }
        int count=0;
        for (int i=0;i<9;i++)
        {
            if(button[i].getText().equals(" "))
            {
                count++;
            }
        }
        if(count==9)
        {
            return 4;
        }
        else if(button[4].getText().equals(" "))
            return 4;
        else if(!button[4].getText().equals(" "))
        {
            Random k = new Random();
            int l = k.nextInt(4);
            if(l==0&&button[1].getText().equals(" "))
                return 1;
            else if(l==1&&button[3].getText().equals(" "))
                return 3;
            else if(l==2&&button[5].getText().equals(" "))
                return 5;
            else if(l==3&&button[7].getText().equals(" "))
                return 7;
        }
        else if(count==6)
        {
            if(button[0].getText().equals(" "))
                return 0;
            else if(button[2].getText().equals(" "))
                return 2;
            else if(button[6].getText().equals(" "))
                return 6;
            else if(button[8].getText().equals(" "))
                return 8;
        }
        else if(!button[4].getText().equals(" "))
        {
            Random k = new Random();
            int l = k.nextInt(4);
            if(l==0&&button[0].getText().equals(" "))
                return 0;
            else if(l==1&&button[2].getText().equals(" "))
                return 2;
            else if(l==2&&button[6].getText().equals(" "))
                return 6;
            else if(l==3&&button[8].getText().equals(" "))
                return 8;
        }
        else if(!button[4].getText().equals(" "))
            return r.nextInt(9);
        int k = r.nextInt(9);
        if(button[k].getText().equals(" "))
            return k;
        else
            return r.nextInt(9);
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
}
