package de.uni.hannover.swt;

import de.uni.hannover.swt.GUI.View;
import de.uni.hannover.swt.GUI.Control;

import javax.swing.*;

public class App extends Control {
    public static final int MATRIX_SIZE = 3; //sets the size N of the NxN Matrix

    public App(View b) {
        super(b);
    }

    public static void main(String[] args) {
        View board = new View();    //The View Class creates the visuals (like the grid and the grid entry images like X or O) and the Game object (contains the information about the game)
        Control gameControl = new Control(board); //Control takes care of the player entries into the software and manages the User Interface

        JFrame f = new JFrame("Tic Tac Toe"); //Window of the game
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(gameControl); //Adds the control JPanel to the window, size of the window is determined by elements in it and therefore by size of control panel
        f.pack();
        f.setVisible(true);
    }

    @Override
    public void whenMousePressed(int c, int r) {
        super.whenMousePressed(c, r);
    }
}