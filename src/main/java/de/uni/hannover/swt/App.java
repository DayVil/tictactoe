package de.uni.hannover.swt;

import de.uni.hannover.swt.GUI.RegularBoard;
import de.uni.hannover.swt.GUI.TTT;
import de.uni.hannover.swt.logic.Game;

import javax.swing.*;

public class App {
    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Game game = new Game(WIDTH, HEIGHT);
        System.out.println(game);

        RegularBoard board = new RegularBoard();
        JFrame f = new JFrame("Tic Tac Toe");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(board);
        f.pack();
        f.setVisible(true);


    }
}