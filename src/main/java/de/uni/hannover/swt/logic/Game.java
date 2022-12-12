package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

import java.util.Arrays;

import static de.uni.hannover.swt.App.HEIGHT;
import static de.uni.hannover.swt.App.WIDTH;

public class Game {
    private final Board _board;
    private Player _turn;

    public Game(int width, int height) {
        _board = new Board(width, height);
        _turn = Player.OPLAYER;
    }

    public Board getState() {
        return _board;
    }

    public void setPoint(int x, int y) {
        EnumMarks toPlace = _turn == Player.OPLAYER ? EnumMarks.OMARK : EnumMarks.XMARK;
        boolean success = _board.setField(x, y, toPlace);
        if (!success) return;

        _turn = _turn == Player.OPLAYER ? Player.XPLAYER : Player.OPLAYER;
    }

    public Player getRound() {
        return _turn;
    }

    public boolean hasWon() {
        EnumMarks[] omarks = new EnumMarks[]{EnumMarks.OMARK, EnumMarks.OMARK, EnumMarks.OMARK};
        EnumMarks[] xmarks = new EnumMarks[]{EnumMarks.XMARK, EnumMarks.XMARK, EnumMarks.XMARK};

        for (int i = 0; i < WIDTH; i++) {
            var tmpRow = _board.getRow(i);
            if (Arrays.equals(omarks, tmpRow) || Arrays.equals(xmarks, tmpRow)) return true;
        }

        for (int i = 0; i < HEIGHT; i++) {
            var tmpCol = _board.getCol(i);
            if (Arrays.equals(omarks, tmpCol) || Arrays.equals(xmarks, tmpCol)) return true;
        }

        final int amountDiags = 2;
        for (int i = 0; i < amountDiags; i++) {
            var tmpDiag = _board.getDiag(i);
            if (Arrays.equals(omarks, tmpDiag) || Arrays.equals(xmarks, tmpDiag)) return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Turn: " + _turn.toString() + '\n' +
                "Has won: " + this.hasWon() + "\n" +
                "Board:\n" + _board;
    }
}
