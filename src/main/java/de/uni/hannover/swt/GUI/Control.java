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

    public Control(View b) {
        setPreferredSize(new Dimension(550, 600));

        board = b; //The constructor takes the view to control as a parameter and saves it to the attributes of the object

        nextPlayerLabel = new JLabel("nextPlayer:");
        //nextPlayerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        //nextPlayerLabel.setHorizontalTextPosition(JLabel.LEFT);
        //nextPlayerLabel.setLocation(200,400);
        setLayout(new BorderLayout());
        add(nextPlayerLabel, BorderLayout.SOUTH);
        nextPlayerLabel.setText(String.valueOf(board.getCurrentGame().getPlayer()));

        JPanel controlPanel = new JPanel();


        controlPanel.add(board); //adds the view to the panel
        add(controlPanel);



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
        IGame game = board.getCurrentGame();
        game.setPoint(r, c);
        board.setCurrentGame(game);
        nextPlayerLabel.setText(String.valueOf(game.getPlayer())); //Takes the Player of the new Turn and changes the JLabel to show the name
    }
}