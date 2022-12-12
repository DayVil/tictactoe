package de.uni.hannover.swt.GUI;

import de.uni.hannover.swt.logic.Board;
import de.uni.hannover.swt.logic.Game;
import de.uni.hannover.swt.logic.IGame;
import de.uni.hannover.swt.logic.enums.EnumMarks;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RegularBoard extends JPanel {

    private Game currentGame;
    private BufferedImage board, redX, blueCircle;
    protected final int UNIT = 170;
    private final int GAME_ROWS = 3, GAME_COLS = 3;

    /**
     * Creats a new View which displays the Board which shows a new "Game".
     */
    public RegularBoard() {
        currentGame = new Game(GAME_ROWS, GAME_COLS);
        loadImages();
    }

    private void loadImages() {
        try {
            board = ImageIO.read(getClass().getClassLoader().getResourceAsStream("board.png"));
            redX = ImageIO.read(getClass().getClassLoader().getResourceAsStream("redX.png"));
            blueCircle = ImageIO.read(getClass().getClassLoader().getResourceAsStream("blueCircle.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(board, 0, 0, null);

        for (byte r = 0; r < GAME_ROWS; r++) {
            for (byte c = 0; c < GAME_COLS; c++) {
                if (currentGame.getState()[c][r] != EnumMarks.EMPTY) {
                    g.drawImage(selectImage(currentGame.getState()[c][r]), r * UNIT, c * UNIT, null);
                }
            }
        }
    }

    private BufferedImage selectImage(EnumMarks player) {
        if (player == EnumMarks.OMARK) {
            return blueCircle;
        }
        return redX;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GAME_COLS * UNIT, GAME_ROWS * UNIT);
    }

    protected Game getCurrentGame() {
        return currentGame;
    }

    protected void setCurrentGame(Game newGame) {
        currentGame = newGame;
        repaint();
    }
}