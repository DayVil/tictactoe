package de.uni.hannover.swt;

import de.uni.hannover.swt.GUI.RegularBoard;
import de.uni.hannover.swt.logic.Board;
import de.uni.hannover.swt.logic.Game;
import de.uni.hannover.swt.logic.enums.EnumMarks;

import javax.swing.*;

public class App {
    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;

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


        Board testSpiel = new Board(3, 3); // TODO: Du sollt kein direkten zugriff aufs board haben
        testSpiel.setField(1, 0, EnumMarks.OMARK);
        testSpiel.setField(2, 0, EnumMarks.XMARK);
        RegularBoard board = new RegularBoard(testSpiel);


        JFrame f = new JFrame("Tic Tac Toe");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(board);
        f.pack();
        f.setVisible(true);
    }
}