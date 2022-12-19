package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

import java.util.Arrays;

import static de.uni.hannover.swt.App.MATRIX_SIZE;

/**
 * This is the Brain of the entire Logic. It controls and manages the entire game flow and calculates which player is
 * playing, which players are active and if the game was won or not. Also, the state of the board is controlled here.
 */
public class Game implements IGame {
    /* The default sate if no one won */
    private final WinInfo DEFAULT_STATE = new WinInfo(false, Player.NONE, null);
    /* The board where the game is being played on */
    private Board _board;
    /* Which player is currently active */
    private Player _turn;
    /* The state of the game */
    private WinInfo _won;

    /**
     * Initializes the game.
     */
    public Game() {
        this.init();
    }

    /**
     * Initialises a game where Player O starts and the board is empty
     */
    private void init() {
        _board = new Board();
        _turn = Player.OPLAYER;
        _won = DEFAULT_STATE;
    }

    /**
     * @return Gets the state of the Board.
     */
    @Override
    public EnumMarks[][] getState() {
        return _board.getFields();
    }

    /**
     * Trys to set the mark of the currently acting player to the given position.
     *
     * @param x The given row
     * @param y The given column
     */
    @Override
    public void setPoint(int x, int y) {
        if (_won.won()) return;
        EnumMarks toPlace = _turn == Player.OPLAYER ? EnumMarks.OMARK : EnumMarks.XMARK;
        boolean success = _board.setField(x, y, toPlace);
        if (!success) return;

        _turn = _turn == Player.OPLAYER ? Player.XPLAYER : Player.OPLAYER;
        _won = winCheck();
    }

    /**
     * Resets the game to the starting state. Meaning every slot is EMPTY and player O is the active player.
     */
    @Override
    public void resetGame() {
        this.init();
    }

    /**
     * Gets the currently acting player.
     *
     * @return Gets the currently acting player.
     */
    @Override
    public Player getPlayer() {
        return _turn;
    }

    /**
     * Converts the mark to the corresponding player.
     * OMARK => OPLAYER
     * XMARK => XPLYER
     *
     * @param mark Mark to be converted
     * @return Returns the Player to the mark
     */
    private Player correspondingPlayer(EnumMarks mark) {
        return mark == EnumMarks.OMARK ? Player.OPLAYER : Player.XPLAYER;
    }

    /**
     * Checks the rows of the board for a winning state.
     *
     * @param tmpField a copy of the currently playing field
     * @return Returns if the game has won via WinInfo.
     */
    private WinInfo horizontalWinCheck(EnumMarks[][] tmpField) {
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
        return DEFAULT_STATE;
    }

    /**
     * Checks the columns of the board for a winning state.
     *
     * @param tmpField a copy of the currently playing field
     * @return Returns if the game has won via WinInfo.
     */
    private WinInfo verticalWinCheck(EnumMarks[][] tmpField) {
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
        return DEFAULT_STATE;
    }

    /**
     * Checks the diagonals of the board for a winning state.
     *
     * @param tmpField a copy of the currently playing field
     * @return Returns if the game has won via WinInfo.
     */
    private WinInfo diagonalCheck(EnumMarks[][] tmpField) {
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

        return DEFAULT_STATE;
    }

    /**
     * Checks the board for a winning state.
     *
     * @return Returns if the game has won via WinInfo.
     */
    private WinInfo winCheck() {
        var tmpField = _board.getFields();
        WinInfo wonState;
        /* horizontal check */
        wonState = horizontalWinCheck(tmpField);
        if (wonState.won()) return wonState;

        /* vertical check */
        wonState = verticalWinCheck(tmpField);
        if (wonState.won()) return wonState;

        /* diagonal check */
        wonState = diagonalCheck(tmpField);

        /* If all checks fail return false*/
        return wonState;
    }

    /**
     * @return Returns if the game has won via WinInfo.
     */
    @Override
    public WinInfo hasWon() {
        return _won;
    }

    /**
     * Creates a readable String representation of the current game.
     *
     * @return Returns the string representation of the game.
     */
    @Override
    public String toString() {
        return "Turn: " + _turn.toString() + '\n' + "Has won: " + this.hasWon() + "\n" + "Board:\n" + _board;
    }
}
