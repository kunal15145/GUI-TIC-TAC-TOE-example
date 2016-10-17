package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CpuvsAibotGui {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private JButton button[] = new JButton[9];
    private int a,b,c;
    private int altertheoption;
    CpuvsAibotGui()
    {
        frame = new JFrame("Game");
        frame.setPreferredSize(new Dimension(600,600));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setForeground(Color.BLUE);
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
            panel.add(button[i]);
        }
        resetButton();
        label.setBackground(Color.WHITE);
        frame.setSize(500,500);
        frame.add(panel,BorderLayout.SOUTH);
        frame.add(label,BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    private void resetButton() {
        altertheoption=0;
        for (int i = 0; i < 9; i++) {
            button[i].setBackground(Color.WHITE);
            button[i].setEnabled(true);
            button[i].setText(" ");
        }
    }
    public int getaltertheoption()

    {
        return altertheoption;
    }

    public void StartingGame() throws InterruptedException {
        resetButton();
        Timer timer=null,timer1=null;
        JOptionPane.showMessageDialog(null,"User will play with O");
        if(checkforwin()==false&&checkgridstatus()==true) {
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        cpuchance();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            timer.start();
            timer1 = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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
            });
            timer1.start();
        }
    }

    private void checkforaiwin() throws InterruptedException {
        if(checkforwin()==true)
        {
            button[a].setBackground(Color.RED);
            button[b].setBackground(Color.RED);
            button[c].setBackground(Color.RED);
            JOptionPane.showMessageDialog(null," AI "+" "+"Won");
            int k=  JOptionPane.showConfirmDialog(null,
                    "Continue", "Wanna Continue ", JOptionPane.YES_NO_OPTION);
            if(k==JOptionPane.YES_OPTION)
            {
                resetButton();
                StartingGame();
            }
            else
                System.exit(0);
        }
        else if(checkgridstatus()==false){
            JOptionPane.showMessageDialog(null,"Draw");
            int k=  JOptionPane.showConfirmDialog(null,
                    "Continue", "Wanna Continue ", JOptionPane.YES_NO_OPTION);
            if(k==JOptionPane.YES_OPTION)
            {
                resetButton();
                StartingGame();
            }
            else
                System.exit(0);
        }
    }

    private void cpuchance() throws InterruptedException {
        Random r = new Random();
        int d = r.nextInt(9);
        if(!button[d].getText().equals(" "))
        {
            cpuchance();
        }
        else
        {
            button[d].setFont(new Font("Arial", Font.PLAIN, 45));
            button[d].setText("O");
            button[d].setEnabled(false);
            if(checkforwin()==true)
            {
                button[a].setBackground(Color.RED);
                button[b].setBackground(Color.RED);
                button[c].setBackground(Color.RED);
                JOptionPane.showMessageDialog(null," Comp"+" "+"Won");
                int k=  JOptionPane.showConfirmDialog(null,
                        "Continue", "Wanna Continue ", JOptionPane.YES_NO_OPTION);
                if(k==JOptionPane.YES_OPTION)
                {
                    resetButton();
                    StartingGame();
                }
                else
                    System.exit(0);
            }
            else if(checkgridstatus()==false){
                JOptionPane.showMessageDialog(null,"Draw");
                int k=  JOptionPane.showConfirmDialog(null,
                        "Continue", "Wanna Continue ", JOptionPane.YES_NO_OPTION);
                if(k==JOptionPane.YES_OPTION)
                {
                    resetButton();
                    StartingGame();
                }
                else
                    System.exit(0);
            }
        }
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
        if(!button[4].getText().equals(" ")){
            return r.nextInt(9);
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
        return r.nextInt(9);
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
    private boolean checkbuttonstatus(int a, int b) {
        if(button[a].getText().equals(button[b].getText())&&!button[a].getText().equals(" "))
            return true;
        else
            return false;
    }
}
