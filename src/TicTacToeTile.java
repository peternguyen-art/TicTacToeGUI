
import javax.swing.JButton;
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wulft
 */
public class TicTacToeTile extends JButton
{
    private int row;
    private int col;
    private char state = ' ';

    public TicTacToeTile(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);

        setFont(new java.awt.Font("Times New Roman", 3, 14));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getState() {
        return state;
    }

    public void setState(char newState) {
        this.state = newState;
        setText(String.valueOf(newState));
    }
}


