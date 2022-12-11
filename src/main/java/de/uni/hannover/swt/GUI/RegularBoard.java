package de.uni.hannover.swt.GUI;

import de.uni.hannover.swt.logic.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RegularBoard extends JPanel {

    private Board game;
    private BufferedImage board, redX, blueCircle;

    public RegularBoard() {
        loadImages();
    }

    public void setGame(Board game) {
        this.game = game;
    }

    private void loadImages() {
/*
        try {
            board = ImageIO.read(getClass().getResourceAsStream("/Images/board.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(board, 0, 0, null);
    }
}
