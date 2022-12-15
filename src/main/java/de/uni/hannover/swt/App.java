package de.uni.hannover.swt;

import de.uni.hannover.swt.GUI.View;
import de.uni.hannover.swt.GUI.Control;

import javax.swing.*;

public class App extends Control {
    public static final int MATRIX_SIZE = 3;

    public App(View b) {
        super(b);
    }

    public static void main(String[] args) {
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
        super.whenMousePressed(c, r);
    }
}