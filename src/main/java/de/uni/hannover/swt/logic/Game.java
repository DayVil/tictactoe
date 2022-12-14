package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

import java.util.Arrays;

import static de.uni.hannover.swt.App.MATRIX_SIZE;

public class Game {
    private final Board _board;
    private Player _turn;

    public Game() {
        _board = new Board();
        _turn = Player.OPLAYER;
    }

    public EnumMarks[][] getState() {
        return _board.getFields();
    }

    public void setPoint(int x, int y) {
        EnumMarks toPlace = _turn == Player.OPLAYER ? EnumMarks.OMARK : EnumMarks.XMARK;
        boolean success = _board.setField(x, y, toPlace);
        if (!success) return;

        _turn = _turn == Player.OPLAYER ? Player.XPLAYER : Player.OPLAYER;
    }

    public Player getPlayer() {
        return _turn;
    }

    private Player correspondingPlayer(EnumMarks mark) {
        return mark == EnumMarks.OMARK ? Player.OPLAYER: Player.XPLAYER;
    }

    public WinInfo hasWon() {
        var tmpField = _board.getFields();

        /* horizontal check */
        for (int i = 0; i < MATRIX_SIZE; i++) {
            var distinctArr = Arrays.stream(tmpField[i]).distinct();
            if (distinctArr.count() == 1 && tmpField[i][0] != EnumMarks.EMPTY) {
                var mark = tmpField[i][0];
                Board tmpBoard = new Board();
                for (int place = 0; place < MATRIX_SIZE; place++)
                    tmpBoard.setField(i, place, mark);
                System.out.println("WINNER:-----------:\n" + tmpBoard);
                return new WinInfo(true, this.correspondingPlayer(mark), tmpBoard.getFields());
            }
        }

        /* vertical check */
        for (int i = 0; i < MATRIX_SIZE; i++) {
            EnumMarks[] checkArr = new EnumMarks[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++) checkArr[j] = tmpField[i][j];

            var distinctArr = Arrays.stream(checkArr).distinct();
//            if (distinctArr.count() == 1 && tmpField[i][0] != EnumMarks.EMPTY) {
//
//            }
        }

        return new WinInfo(false, Player.NONE, null);
    }

    @Override
    public String toString() {
        return "Turn: " + _turn.toString() + '\n' +
                "Has won: " + this.hasWon() + "\n" +
                "Board:\n" + _board;
    }
}
