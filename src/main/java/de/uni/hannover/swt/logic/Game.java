package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

public class Game {
    private Board _state;
    private Player _turn;
    private boolean _won;

    public Game(int width, int height) {
        _state = new Board(width, height);
        _turn = Player.OPLAYER;
        _won = false;
    }

    public Board getState() {
        return _state;
    }

    public void setPoint(int x, int y) {
        EnumMarks toPlace = _turn == Player.OPLAYER ? EnumMarks.OMARK: EnumMarks.XMARK;
        boolean success = _state.setField(x, y, toPlace);
        if (!success) return;

        _turn = _turn == Player.OPLAYER ? Player.XPLAYER: Player.OPLAYER;
    }

    public Player getRound() {
        return null;
    }

    public boolean hasWon() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Turn: ").append(_turn.toString()).append('\n');
        stringBuilder.append("Has won: ").append(_won).append("\n");

        return stringBuilder.toString();
    }
}
