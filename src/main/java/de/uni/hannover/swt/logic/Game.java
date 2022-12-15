package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

import java.util.Arrays;

import static de.uni.hannover.swt.App.MATRIX_SIZE;

public class Game {
    private final Board _board;
    private Player _turn;

    private WinInfo _won;

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
//        winCheck();
    }

    public Player getPlayer() {
        return _turn;
    }

    private Player correspondingPlayer(EnumMarks mark) {
        return mark == EnumMarks.OMARK ? Player.OPLAYER: Player.XPLAYER;
    }

//    private void winCheck() {
//        var tmpField = _board.getFields();
//
//        /* horizontal check */
//        for (int i = 0; i < MATRIX_SIZE; i++) {
//            var distinctArr = Arrays.stream(tmpField[i]).distinct();
//            if (distinctArr.count() == 1 && tmpField[i][0] != EnumMarks.EMPTY) {
//                var mark = tmpField[i][0];
//                Board tmpBoard = new Board();
//                for (int place = 0; place < MATRIX_SIZE; place++)
//                    tmpBoard.setField(i, place, mark);
//                System.out.println("WINNER:-----------:\n" + tmpBoard);
//                _won = new WinInfo(true, this.correspondingPlayer(mark), tmpBoard.getFields());
//            }
//        }
//
//        /* vertical check */
//        for (int i = 0; i < MATRIX_SIZE; i++) {
//            EnumMarks[] checkArr = new EnumMarks[MATRIX_SIZE];
//            for (int j = 0; j < MATRIX_SIZE; j++) checkArr[j] = tmpField[j][i];
//
//            var distinctArr = Arrays.stream(checkArr).distinct();
//            if (distinctArr.count() == 1 && checkArr[0] != EnumMarks.EMPTY) {
//                Board tmpBoard = new Board();
//                var mark = checkArr[0];
//
//                for (int j = 0; j < MATRIX_SIZE; j++) tmpBoard.setField(j, i, mark);
//                System.out.println("WINNER:-----------:\n" + tmpBoard);
//                _won = new WinInfo(true, this.correspondingPlayer(mark), tmpBoard.getFields());
//            }
//        }
//
//        /* diagonal check */
//        var diag1 = tmpField[0][0];
//        var diag2 = tmpField[0][2];
//
//        Board tmpBoard = new Board();
//        if (diag1 != EnumMarks.EMPTY) {
//            if (diag1 == tmpField[1][1] && diag1 == tmpField[2][2]) {
//                tmpBoard.setField(0, 0, diag1);
//                tmpBoard.setField(1, 1, diag1);
//                tmpBoard.setField(2, 2, diag1);
//                System.out.println("WINNER:-----------:\n" + tmpBoard);
//                _won =  new WinInfo(true, this.correspondingPlayer(diag1), tmpBoard.getFields());
//            }
//        }
//
//        if (diag2 != EnumMarks.EMPTY) {
//            if (diag2 == tmpField[1][1] && diag2 == tmpField[2][0]) {
//                tmpBoard.setField(0, 2, diag2);
//                tmpBoard.setField(1, 1, diag2);
//                tmpBoard.setField(2, 0, diag2);
//                System.out.println("WINNER:-----------:\n" + tmpBoard);
//                _won = new WinInfo(true, this.correspondingPlayer(diag2), tmpBoard.getFields());
//            }
//        }
//
//        /* If all checks fail return false*/
//        _won = new WinInfo(false, Player.NONE, null);
//    }

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
            for (int j = 0; j < MATRIX_SIZE; j++) checkArr[j] = tmpField[j][i];

            var distinctArr = Arrays.stream(checkArr).distinct();
            if (distinctArr.count() == 1 && checkArr[0] != EnumMarks.EMPTY) {
                Board tmpBoard = new Board();
                var mark = checkArr[0];

                for (int j = 0; j < MATRIX_SIZE; j++) tmpBoard.setField(j, i, mark);
                System.out.println("WINNER:-----------:\n" + tmpBoard);
                return new WinInfo(true, this.correspondingPlayer(mark), tmpBoard.getFields());
            }
        }

        /* diagonal check */
        var diag1 = tmpField[0][0];
        var diag2 = tmpField[0][2];

        Board tmpBoard = new Board();
        if (diag1 != EnumMarks.EMPTY) {
            if (diag1 == tmpField[1][1] && diag1 == tmpField[2][2]) {
                tmpBoard.setField(0, 0, diag1);
                tmpBoard.setField(1, 1, diag1);
                tmpBoard.setField(2, 2, diag1);
                System.out.println("WINNER:-----------:\n" + tmpBoard);
                return new WinInfo(true, this.correspondingPlayer(diag1), tmpBoard.getFields());
            }
        }

        if (diag2 != EnumMarks.EMPTY) {
            if (diag2 == tmpField[1][1] && diag2 == tmpField[2][0]) {
                tmpBoard.setField(0, 2, diag2);
                tmpBoard.setField(1, 1, diag2);
                tmpBoard.setField(2, 0, diag2);
                System.out.println("WINNER:-----------:\n" + tmpBoard);
                return new WinInfo(true, this.correspondingPlayer(diag2), tmpBoard.getFields());
            }
        }

        /* If all checks fail return false*/
        return new WinInfo(false, Player.NONE, null);
    }

    @Override
    public String toString() {
        return "Turn: " + _turn.toString() + '\n' +
                "Has won: " + this.hasWon() + "\n" +
                "Board:\n" + _board;
    }
}
