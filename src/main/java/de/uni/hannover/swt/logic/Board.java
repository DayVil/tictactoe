package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.logic.enums.EnumMarks;

import static de.uni.hannover.swt.App.HEIGHT;
import static de.uni.hannover.swt.App.WIDTH;

public class Board {
    private final EnumMarks[][] _fields;

    public Board(int width, int height) {
        if (width < 0 || height < 0) {
            System.exit(-1);
        }
        _fields = new EnumMarks[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                _fields[i][j] = EnumMarks.EMPTY;
            }
        }
    }


    public EnumMarks[][] getFields() {
        return _fields;
    }

    public boolean setField(int x, int y, EnumMarks mark) {
        if (x < 0 || y < 0) return false;
        if (x >= WIDTH || y >= HEIGHT) return false;
        if (_fields[x][y] != EnumMarks.EMPTY) return false;
        _fields[x][y] = mark;
        return true;
    }

    public EnumMarks[] getDiag(int diag) {
        EnumMarks[] tmp = new EnumMarks[3];

        tmp[0] = diag == 0 ? _fields[0][0] : _fields[0][2];
        tmp[1] = _fields[1][1];
        tmp[2] = diag == 0 ? _fields[2][2] : _fields[2][0];

        return tmp;
    }

    public EnumMarks[] getRow(int rowNum) {
        return _fields[rowNum];
    }

    public EnumMarks[] getCol(int colNum) {
        EnumMarks[] col = new EnumMarks[HEIGHT];
        System.arraycopy(_fields[colNum], 0, col, 0, HEIGHT);
        return col;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                stringBuilder.append(_fields[i][j]).append(" ");
            }
            stringBuilder.append("\n\n");
        }

        return stringBuilder.toString();
    }
}
