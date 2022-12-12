package de.uni.hannover.swt;

import de.uni.hannover.swt.GUI.RegularBoard;
import de.uni.hannover.swt.GUI.RegularControl;
import de.uni.hannover.swt.logic.Board;
import de.uni.hannover.swt.logic.Game;
import de.uni.hannover.swt.logic.enums.EnumMarks;

import javax.swing.*;

public class App extends RegularControl {
    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;

    public App(RegularBoard b) {
        super(b);
        System.out.println("testttt.");
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Game game = new Game(WIDTH, HEIGHT);
        game.setPoint(0, 0);
        game.setPoint(0, 1);
        game.setPoint(0, 2);
        game.setPoint(1, 0);
        game.setPoint(2, 0);
        game.setPoint(2, 2);
        game.setPoint(1, 1);
        System.out.println(game);


        RegularBoard board = new RegularBoard();
        RegularControl control = new RegularControl(board);

        JFrame f = new JFrame("Tic Tac Toe");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(control);
        f.pack();
        f.setVisible(true);
    }

    @Override
    public void whenMousePressed(int c, int r) {
        System.out.println("RegularControl: Ein Mausklick wurde erfasst.");
        super.whenMousePressed(c, r);
    }
}