package de.uni.hannover.swt.GUI;

import de.uni.hannover.swt.logic.IGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * The Control class adds the view to a JPanel and adds a Reset Button and a JLabel that shows the current player turn
 * It also handles the user entries (putting down marks by clicking on the user Interface
 */
public class Control extends JPanel {

    private final View gameDrawing; //Drawing of the grid and the entries into the grid
    private final JLabel nextPlayerLabel; //Label that shows which players turn it is

    private final int WINDOW_WIDTH = 550, WINDOW_HEIGHT = 600; //Preferences for window size of the game window

    /**
     * Creates a new Control and adds the view <code>gameDrawing</code>, a Reset Button and a player-turn-indicator to the control JPanel
     * @param b View object to control
     */
    public Control(View b) {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)); //Set size for the control JPanel to predefined values. Also sets size for JFrame Window, see App.java

        gameDrawing = b;

        JPanel informationPanel = new JPanel(); //Control Panel contains reset button and nextPlayerLabel for easier layout

        nextPlayerLabel = new JLabel("nextPlayer:"); //nextPlayerLabel shows which players turn it is. Contains text which is placeholder Text, gets overwritten in next lines
        setLayout(new BorderLayout());
        informationPanel.add(nextPlayerLabel, BorderLayout.SOUTH);
        nextPlayerLabel.setText(String.valueOf(gameDrawing.getCurrentGame().getPlayer())); //Set nextPlayerLabel to the current Player of the current Game which is an attribute of the View Class

        JButton resetButton = new JButton("Reset"); //Reset button to restart the game
        informationPanel.add(resetButton, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel(); //Game Panel contains the whole game including control panel and the gameDrawing with the grid and the entries


        gamePanel.add(gameDrawing);
        gamePanel.add(informationPanel);

        add(gamePanel); //Game Panel is added to the


        /*
          adds a Mouse listener which takes the coordinates of the mouse and calculates the position on the grid from them.
         */
        gameDrawing.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int column = (int) (e.getPoint().getX() / gameDrawing.POS_SCALING_FACTOR); //uses the scaling factor to find out which part (row, column) of the grid the player clicked on
                int row = (int) (e.getPoint().getY() / gameDrawing.POS_SCALING_FACTOR);
                whenMousePressed(column, row);
            }
        });

        /*
          adds a listener which reacts when the reset button is pressed and resets the game
         */
        resetButton.addActionListener(e -> {
            IGame newGame = gameDrawing.getCurrentGame(); //gets the current game
            newGame.resetGame();    //resets the game via the resetGame() function of the IGame Interface
            gameDrawing.setCurrentGame(newGame); //updates the gameDrawing so every entry is shown to be EMPTY again
            nextPlayerLabel.setText(String.valueOf(newGame.getPlayer())); //Takes the Player of the new Turn and changes the JLabel to show the name
        });
    }

    /**
     * When the mouse is pressed, the entry of the grid is changed from EMPTY to the Player Enum depending on whos Players turn it is
     *
     * @param column column of the grid that the mouse clicked on
     * @param row row of the grid that the mouse clicked on
     */
    public void whenMousePressed(int column, int row) {
        IGame game = gameDrawing.getCurrentGame(); //gets the current Game
        game.setPoint(row, column); //Changes the entry in the grid at given row and column
        gameDrawing.setCurrentGame(game); //updates the gameDrawing including the grid and the pictures of the entries to the grid
        nextPlayerLabel.setText(String.valueOf(game.getPlayer())); //Takes the Player of the new Turn and changes the JLabel to show the name
    }
}