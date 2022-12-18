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

public class View extends JPanel {

    private IGame currentGame;
    private BufferedImage gridImage, redX, blueCircle, check;
    protected final int POS_SCALING_FACTOR = 170;

    /**
     * Creates a new View which displays the Board which shows a new "Game".
     */
    public View() {
        currentGame = new Game();
        loadImages();
    }

    private void loadImages() {
        try {
            gridImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gridImage.png"));
            redX = ImageIO.read(getClass().getClassLoader().getResourceAsStream("redX.png"));
            blueCircle = ImageIO.read(getClass().getClassLoader().getResourceAsStream("blueCircle.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

        if (currentGame.hasWon().won()) {
            try {
                if (currentGame.hasWon().player() == Player.OPLAYER) {
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

    private BufferedImage selectImage(EnumMarks player) {
        return player == EnumMarks.OMARK ? blueCircle : redX;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MATRIX_SIZE * POS_SCALING_FACTOR, MATRIX_SIZE * POS_SCALING_FACTOR);
    }

    protected IGame getCurrentGame() {
        return currentGame;
    }

    protected void setCurrentGame(IGame newGame) {
        currentGame = newGame;
        repaint();
    }
}