package de.uni.hannover.swt.GUI;

import javax.swing.*;

public class TTT {

    public static void main(String[] args) {
        RegularBoard board = new RegularBoard();
        JFrame f = new JFrame("Tic Tac Toe");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(board);
        f.pack();
        f.setVisible(true);
    }
}
