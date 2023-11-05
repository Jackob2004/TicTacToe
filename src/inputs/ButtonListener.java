package inputs;

import main.MyFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private MyFrame frame;
    public ButtonListener(MyFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "0" -> frame.getTicTacToe().editButton(0, 0, 0);
            case "1" -> frame.getTicTacToe().editButton(1, 1, 0);
            case "2" -> frame.getTicTacToe().editButton(2, 2, 0);
            case "3" -> frame.getTicTacToe().editButton(3, 0, 1);
            case "4" -> frame.getTicTacToe().editButton(4, 1, 1);
            case "5" -> frame.getTicTacToe().editButton(5, 2, 1);
            case "6" -> frame.getTicTacToe().editButton(6, 0, 2);
            case "7" -> frame.getTicTacToe().editButton(7, 1, 2);
            case "8" -> frame.getTicTacToe().editButton(8, 2, 2);
        }
    }
}
