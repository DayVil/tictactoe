package de.uni.hannover.swt.GUI;

import de.uni.hannover.swt.logic.IGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
Adds the control to the MVC Pattern.
 */

public class Control extends JPanel {

    private final View board; //The view which the control is connected to in the mvc pattern
    private JLabel nextPlayerLabel;

    private final int WINDOW_WIDTH = 550, WINDOW_HEIGHT = 600;


    public Control(View b) {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        board = b; //The constructor takes the view to control as a parameter and saves it to the attributes of the object

        nextPlayerLabel = new JLabel("nextPlayer:");
        setLayout(new BorderLayout());
        add(nextPlayerLabel, BorderLayout.SOUTH);
        nextPlayerLabel.setText(String.valueOf(board.getCurrentGame().getPlayer()));

        JPanel controlPanel = new JPanel();


        controlPanel.add(board); //adds the view to the panel
        add(controlPanel);



        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int c = (int) (e.getPoint().getX() / board.POS_SCALING_FACTOR);
                int r = (int) (e.getPoint().getY() / board.POS_SCALING_FACTOR);
                whenMousePressed(c, r);
            }
        });
    }

    public void whenMousePressed(int c, int r) {
        IGame game = board.getCurrentGame();
        game.setPoint(r, c);
        board.setCurrentGame(game);
        nextPlayerLabel.setText(String.valueOf(game.getPlayer())); //Takes the Player of the new Turn and changes the JLabel to show the name
    }
}