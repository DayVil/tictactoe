package de.uni.hannover.swt.GUI;

import de.uni.hannover.swt.logic.Game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Control extends JPanel {

    private final View board;

    public Control(View b) {
        board = b;

        JPanel panel = new JPanel();
        panel.add(board);
        add(panel);

        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int c = (int) (e.getPoint().getX() / board.UNIT);
                int r = (int) (e.getPoint().getY() / board.UNIT);
                whenMousePressed(c, r);
            }
        });
    }

    public void whenMousePressed(int c, int r) {
        Game game = board.getCurrentGame();
        game.setPoint(r, c);
        board.setCurrentGame(game);
    }
}