package de.uni.hannover.swt;

import de.uni.hannover.swt.GUI.View;
import de.uni.hannover.swt.GUI.Control;
import de.uni.hannover.swt.logic.Game;

import javax.swing.*;

public class App extends Control {
    public static final int MATRIX_SIZE = 3;

    public App(View b) {
        super(b);
        System.out.println("testttt.");
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Game game = new Game();
        game.setPoint(0, 0);
        game.setPoint(0, 1);
        game.setPoint(0, 2);
        game.setPoint(1, 0);
        game.setPoint(2, 0);
        game.setPoint(2, 2);
        game.setPoint(1, 1);
        System.out.println(game);


        View board = new View();
        Control control = new Control(board);

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