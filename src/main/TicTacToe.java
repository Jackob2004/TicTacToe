package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class TicTacToe { // class for game logic
    private MyFrame frame;

    private boolean turn = true;

    private String[][] gameBoard;

    private boolean gameOn = true;

    public TicTacToe(MyFrame frame){
        this.frame = frame;
        gameBoard = new String[3][3];
    }

    public void editButton(int num, int x, int y){ // drawing x or o
        JButton button = frame.getButtons()[num];
        if(turn){
            if(!button.getText().isBlank() || !gameOn) return;
            button.setText("X");
            button.setForeground(Color.RED);
            turn = false;
            saveChanges(x,y,"X");
        } else {
            if(!button.getText().isBlank() || !gameOn) return;
            button.setText("O");
            button.setForeground(Color.BLUE);
            turn = true;
            saveChanges(x,y,"O");
        }
    }

    public void saveChanges(int x, int y, String player){ // saving player moves
        gameBoard[y][x] = player;
        if(turn) frame.getLabel().setText("X turn");
        else frame.getLabel().setText("O turn");
        checkGameStatus();
    }

    public void checkGameStatus(){ // checking if game is over
        String check = "";
        for (int i = 0; i < 8; i++) {
            check = "";
            check = switch (i) {
                case 0 -> gameBoard[0][0] + gameBoard[0][1] + gameBoard[0][2];
                case 1 -> gameBoard[1][0] + gameBoard[1][1] + gameBoard[1][2];
                case 2 -> gameBoard[2][0] + gameBoard[2][1] + gameBoard[2][2];
                case 3 -> gameBoard[0][0] + gameBoard[1][0] + gameBoard[2][0];
                case 4 -> gameBoard[0][1] + gameBoard[1][1] + gameBoard[2][1];
                case 5 -> gameBoard[0][2] + gameBoard[1][2] + gameBoard[2][2];
                case 6 -> gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2];
                case 7 -> gameBoard[0][2] + gameBoard[1][1] + gameBoard[2][0];
                default -> check;
            };
            if(check.equals("XXX")){
                endGame(i,"X WON");
                return;
            } else if (check.equals("OOO")){
                endGame(i,"O WON");
                return;
            }
        }
        draw();
    }

    public void endGame(int i,String winner){ // ending game
        frame.getLabel().setText(winner);
        gameOn = false;

        switch (i) {
            case 0 -> setButtonBackground(0, 3, 1);
            case 1 -> setButtonBackground(3, 6, 1);
            case 2 -> setButtonBackground(6, 9, 1);
            case 3 -> setButtonBackground(0, 7, 3);
            case 4 -> setButtonBackground(1, 8, 3);
            case 5 -> setButtonBackground(2, 9, 3);
            case 6 -> setButtonBackground(0, 9, 4);
            case 7 -> setButtonBackground(2, 7, 2);
        }
        playAgain();
    }

    public void setButtonBackground(int start, int until, int add){ // changing buttons color
        for (int j = start; j < until; j+=add) {
            JButton button = frame.getButtons()[j];
            button.setBackground(Color.green);
        }
    }

    private void playAgain() { // showing window with 3 options, so you can clear the board and play again, end game or do nothing
        int a = JOptionPane.showConfirmDialog(frame.getFrame(),"Wanna play again ?");

        if(a==JOptionPane.YES_OPTION){
            for (int i = 0; i < frame.getButtons().length; i++) {
                JButton button = frame.getButtons()[i];
                button.setBackground(Color.white);
                button.setForeground(Color.white);
                button.setText("");
            }

            for (String[] strings : gameBoard) Arrays.fill(strings, "k"); // looping through whole multidimensional array

            turn = true;
            gameOn = true;

            frame.getLabel().setText("X turn");
        } else if (a==JOptionPane.NO_OPTION) frame.getFrame().dispatchEvent(new WindowEvent(frame.getFrame(),WindowEvent.WINDOW_CLOSING));
    }


    private void draw() { // if no one won
        int check = 0;
        for (JButton buttons : frame.getButtons()){
            if (!buttons.getText().isBlank()) check++;
        }
        if(check==9){
            frame.getLabel().setText("Draw");
            playAgain();
        }
    }
}
