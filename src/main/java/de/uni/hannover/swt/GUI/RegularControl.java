package de.uni.hannover.swt.GUI;

import de.uni.hannover.swt.logic.Game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegularControl extends JPanel {

    private RegularBoard board;

    public RegularControl(RegularBoard b) {
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
        game.setPoint(c, r);
        board.setCurrentGame(game);
        System.out.println(board.getCurrentGame().toString());
    }
}