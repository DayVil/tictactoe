package de.uni.hannover.swt.GUI;

import de.uni.hannover.swt.logic.Game;

import de.uni.hannover.swt.logic.IGame;
import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static de.uni.hannover.swt.App.MATRIX_SIZE;


/**
 * The view class takes care of visualizing the game in a JPanel
 */
public class View extends JPanel {

    private IGame currentGame;
    private BufferedImage gridImage, redX, blueCircle, check;
    protected final int POS_SCALING_FACTOR = 170; //Scaling Factor to calculate the xy coordinates of the visuals from the grid column and row

    /**
     * Creates a new View which displays the Board which shows a new "Game".
     */
    public View() {
        currentGame = new Game();
        loadImages();
    }

    /**
     * Load images from files to be used in drawing the game in the user interface
     */
    private void loadImages() { //Import images being used for displaying grid and player X and O marks
        try {
            gridImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gridImage.png"));
            redX = ImageIO.read(getClass().getClassLoader().getResourceAsStream("redX.png"));
            blueCircle = ImageIO.read(getClass().getClassLoader().getResourceAsStream("blueCircle.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /*

     */

    /**
     * Draw the grid and pictures for entries of players according to the information stored in currentGame.
     * If a game was won, additional symbols are drawn, that show corresponding winning symbols
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gridImage, 0, 0, null);

        for (byte r = 0; r < MATRIX_SIZE; r++) {
            for (byte c = 0; c < MATRIX_SIZE; c++) {
                if (currentGame.getState()[c][r] != EnumMarks.EMPTY) {
                    g.drawImage(selectImage(currentGame.getState()[c][r]), r * POS_SCALING_FACTOR, c * POS_SCALING_FACTOR, null);
                }
            }
        }

        if (currentGame.hasWon().won()) { //If a Game was won, draw the additional win markers
            try {
                if (currentGame.hasWon().player() == Player.OPLAYER) {  //decide which win marker is used depending on the player who won (X or O)
                    check = ImageIO.read(getClass().getClassLoader().getResourceAsStream("check.png"));
                } else {
                    check = ImageIO.read(getClass().getClassLoader().getResourceAsStream("orangeCircle.png"));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (byte r = 0; r < MATRIX_SIZE; r++) {
                for (byte c = 0; c < MATRIX_SIZE; c++) {
                    if (currentGame.hasWon().winningField()[c][r] != EnumMarks.EMPTY) {
                        g.drawImage(check, r * POS_SCALING_FACTOR, c * POS_SCALING_FACTOR, null);
                    }
                }
            }
        }
    }

    /**
     * Choose which image to use for marking a grid entry based on the enum, OMARK -> blue circle, XMARK -> redX
     *
     * @param player EnumMark of the player which has made the entry
     * @return Returns Image of a circle or an X corresponding to the player
     */
    private BufferedImage selectImage(EnumMarks player) {
        return player == EnumMarks.OMARK ? blueCircle : redX;
    }


    /**
     * gives the Size of the visuals based on the scaling factor constant and the size of the grid
     *
     * @return Returns the Dimensions of the visuals
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MATRIX_SIZE * POS_SCALING_FACTOR, MATRIX_SIZE * POS_SCALING_FACTOR);
    }

    /**
     * get the current game that the View is currently displaying
     * @return returns the Game that the View is currently displaying
     */
    protected IGame getCurrentGame() {
        return currentGame;
    }

    /**
     * Set the game that the View is representing
     *
     * @param newGame updated game that is supposed to be drawn
     */
    protected void setCurrentGame(IGame newGame) {
        currentGame = newGame;
        repaint(); //update/redraw the visuals when the game has changed (e.g. a player has made a move)
    }
}