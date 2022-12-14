package de.uni.hannover.swt.logic;

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

//    public boolean hasWon() {
//        EnumMarks[] omarks = new EnumMarks[]{EnumMarks.OMARK, EnumMarks.OMARK, EnumMarks.OMARK};
//        EnumMarks[] xmarks = new EnumMarks[]{EnumMarks.XMARK, EnumMarks.XMARK, EnumMarks.XMARK};
//
//        for (int i = 0; i < MATRIX_SIZE; i++) {
//            var tmpRow = _board.getRow(i);
//            if (Arrays.equals(omarks, tmpRow) || Arrays.equals(xmarks, tmpRow)) return true;
//        }
//
//        for (int i = 0; i < App.MATRIX_SIZE; i++) {
//            var tmpCol = _board.getCol(i);
//            if (Arrays.equals(omarks, tmpCol) || Arrays.equals(xmarks, tmpCol)) return true;
//        }
//
//        final int amountDiags = 2;
//        for (int i = 0; i < amountDiags; i++) {
//            var tmpDiag = _board.getDiag(i);
//            if (Arrays.equals(omarks, tmpDiag) || Arrays.equals(xmarks, tmpDiag)) return true;
//        }
//
//        return false;
//    }
    private Player correspondingPlayer(EnumMarks mark) {
        return mark == EnumMarks.OMARK ? Player.OPLAYER: Player.XPLAYER;
    }

    public WinInfo hasWon() {
        var tmpField = _board.getFields();

        /* horizontal check */
        for (int i = 0; i < MATRIX_SIZE; i++) {
            var distinctArr = Arrays.stream(tmpField[i]).distinct();
            if (distinctArr.count() == 1) {
                var mark = tmpField[i][0];
                Board tmpBoard = new Board();
                for (int place = 0; place < MATRIX_SIZE; place++)
                    tmpBoard.setField(place, 0, mark);
                return new WinInfo(true, this.correspondingPlayer(mark), tmpBoard.getFields());
            }
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
