package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.App;
import de.uni.hannover.swt.logic.enums.EnumMarks;

import static de.uni.hannover.swt.App.MATRIX_SIZE;

/**
 * The board class is responsible for managing the information about the grid and the players entries into the grid.
 * The constructor creates a NxN Matrix which represents the grid of the tictactoe game and
 * fills it with the "Empty" Enum Mark. The Class allows to set single entries with the xy coordinates and the wanted
 * content in form of enum marks or get the current entries of the whole grid in form of an 2-dimensional array of enum marks
 */
public class Board {

    /* the slots on the board */
    private final EnumMarks[][] _fields;

    /**
     * Initialises the board with empty marks
     */
    public Board() {
        _fields = new EnumMarks[MATRIX_SIZE][MATRIX_SIZE];

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                _fields[i][j] = EnumMarks.EMPTY;
            }
        }
    }

    /**
     * @return returns the state of the board
     */
    public EnumMarks[][] getFields() {
        return _fields;
    }

    /**
     * Sets the given mark at the given position. If the position is invalid the mark won't be set and the function
     * returns false for describing failure. Else the position is valid the mark is set and the function returns true
     * describing the success.
     *
     * @param x    horizontal position of the matrix.
     * @param y    vertical position of the matrix.
     * @param mark the mark that will be set.
     * @return returns true on success and false on failure
     */
    public boolean setField(int x, int y, EnumMarks mark) {
        if (x < 0 || y < 0) return false;
        if (x >= MATRIX_SIZE || y >= App.MATRIX_SIZE) return false;
        if (_fields[x][y] != EnumMarks.EMPTY) return false;
        _fields[x][y] = mark;
        return true;
    }

    /**
     * Converts the class into a readable String.
     *
     * @return String representation of the class.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < App.MATRIX_SIZE; j++) {
                stringBuilder.append(_fields[i][j]).append(" ");
            }
            stringBuilder.append("\n\n");
        }

        return stringBuilder.toString();
    }
}
