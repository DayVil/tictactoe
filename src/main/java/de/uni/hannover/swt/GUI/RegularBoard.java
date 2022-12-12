package de.uni.hannover.swt.GUI;

import de.uni.hannover.swt.logic.Board;
import de.uni.hannover.swt.logic.enums.EnumMarks;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RegularBoard extends JPanel {

    private Board game;
    private BufferedImage board, redX, blueCircle;
    private final int UNIT = 170;
    private final int GAME_ROWS = 3, GAME_COLS = 3;

    public RegularBoard() {

    }

    /**
     * Creats a new View which displays the Board which shows the current "game".
     * @param game The game to view.
     */
    public RegularBoard(Board game) {
        setGame(game);
        loadImages();
    }

    public void setGame(Board game) {
        this.game = game;
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
                if (game.getFields()[r][c] != EnumMarks.EMPTY) {
                    g.drawImage(selectImage(game.getFields()[r][c]), r*UNIT, c*UNIT, null);
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

    /**
     * Displays the game which is given.
     * @param game The Board, which should display the game.
     */
    public void setBoard(Board game) {
        this.game = game;
    }

    /**
     * Call this Methode to update the View.
     * TODO not working
     * use setBoard
     */
    public void update() {
        this.game = game;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GAME_COLS*UNIT, GAME_ROWS*UNIT);
    }
}
