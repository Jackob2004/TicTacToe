package main;

import inputs.ButtonListener;

import javax.swing.*;
import java.awt.*;

public class MyFrame { // displaying game layout
    private JFrame frame;

    private JButton button;

    private JLabel label;

    private JButton[] buttons;

    private ButtonListener buttonListener;

    private TicTacToe ticTacToe;
    MyFrame(){
        frame = new JFrame("TicTacToe"); // basic frame setup
        frame.setSize(616,679);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        frame.setVisible(true);

        buttonListener = new ButtonListener(this);
        ticTacToe = new TicTacToe(this);
        initialiseButtons();
        initialiseLabel();
    }

    private void initialiseButtons(){ // initialising buttons, so we can access them and listen to actions
        buttons = new JButton[9];
        int index = 0;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                button = new JButton();
                button.setActionCommand(String.valueOf(index));
                button.setBounds(i*200,40+j*200,200,200);
                button.setFont(new Font("Arial", Font.PLAIN, 100));
                button.setBackground(Color.white);
                button.addActionListener(buttonListener);

                frame.add(button);
                buttons[index] = button;
                index++;
            }
        }
    }

    public void initialiseLabel(){ // initialising top bar, so we can use it for displaying game info
        label = new JLabel();
        label.setBounds(0,0,600,40);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.GREEN);
        label.setText("X turn");

        frame.add(label);
    }

    public JLabel getLabel() { return label; }

    public JButton[] getButtons() { return buttons; }

    public JFrame getFrame() { return frame; }

    public TicTacToe getTicTacToe() { return ticTacToe; }
}