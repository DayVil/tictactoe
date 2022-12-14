package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.App;

import static de.uni.hannover.swt.App.MATRIX_SIZE;

public class Board {
    private final EnumMarks[][] _fields;

    public Board() {
        _fields = new EnumMarks[MATRIX_SIZE][MATRIX_SIZE];

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                _fields[i][j] = EnumMarks.EMPTY;
            }
        }
    }


    public EnumMarks[][] getFields() {
        return _fields;
    }

    public boolean setField(int x, int y, EnumMarks mark) {
        if (x < 0 || y < 0) return false;
        if (x >= MATRIX_SIZE || y >= App.MATRIX_SIZE) return false;
        if (_fields[x][y] != EnumMarks.EMPTY) return false;
        _fields[x][y] = mark;
        return true;
    }

//    public EnumMarks[] getDiag(int diag) {
//        EnumMarks[] tmp = new EnumMarks[3];
//
//        tmp[0] = diag == 0 ? _fields[0][0] : _fields[0][2];
//        tmp[1] = _fields[1][1];
//        tmp[2] = diag == 0 ? _fields[2][2] : _fields[2][0];
//
//        return tmp;
//    }
//
//    public EnumMarks[] getRow(int rowNum) {
//        return _fields[rowNum];
//    }
//
//    public EnumMarks[] getCol(int colNum) {
//        EnumMarks[] col = new EnumMarks[App.MATRIX_SIZE];
//        System.arraycopy(_fields[colNum], 0, col, 0, App.MATRIX_SIZE);
//        return col;
//    }

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
