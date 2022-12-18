package de.uni.hannover.swt.GUI;

import de.uni.hannover.swt.logic.IGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
Adds the control to the MVC Pattern.
 */

public class Control extends JPanel {

    private final View gameDrawing; //Drawing of the grid and the entries into the grid
    private JLabel nextPlayerLabel; //Label that shows which players turn it is
    private JButton resetButton; //Reset button to restart the game
    private JPanel controlPanel; //Panel for the control of the game with reset button and information about the player turn
    private JPanel gamePanel; //The big Panel that contains the whole UI and game

    private final int WINDOW_WIDTH = 550, WINDOW_HEIGHT = 600; //Preferences for window size of the game window


    public Control(View b) {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        gameDrawing = b; //The constructor takes the view to control as a parameter and saves it to the attributes of the object

        controlPanel = new JPanel();

        nextPlayerLabel = new JLabel("nextPlayer:");
        setLayout(new BorderLayout());
        controlPanel.add(nextPlayerLabel, BorderLayout.SOUTH);
        nextPlayerLabel.setText(String.valueOf(gameDrawing.getCurrentGame().getPlayer()));

        resetButton = new JButton("Reset");
        controlPanel.add(resetButton, BorderLayout.NORTH);

        gamePanel = new JPanel();


        gamePanel.add(gameDrawing); //adds the view to the panel
        gamePanel.add(controlPanel);

        add(gamePanel);



        gameDrawing.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int c = (int) (e.getPoint().getX() / gameDrawing.POS_SCALING_FACTOR);
                int r = (int) (e.getPoint().getY() / gameDrawing.POS_SCALING_FACTOR);
                whenMousePressed(c, r);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IGame newGame = gameDrawing.getCurrentGame();
                newGame.resetGame();
                gameDrawing.setCurrentGame(newGame);
            }
        });
    }

    public void whenMousePressed(int c, int r) {
        IGame game = gameDrawing.getCurrentGame();
        game.setPoint(r, c);
        gameDrawing.setCurrentGame(game);
        nextPlayerLabel.setText(String.valueOf(game.getPlayer())); //Takes the Player of the new Turn and changes the JLabel to show the name
    }
}