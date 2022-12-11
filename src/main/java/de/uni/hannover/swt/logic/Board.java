package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.logic.enums.EnumMarks;

public class Board {
    private EnumMarks[][] _fields;
    private int _width;
    private int _height;

    public Board(int width, int height) {
        if (width < 0 || height < 0) {
            System.exit(-1);
        }
        _width = width;
        _height = height;
        _fields = new EnumMarks[width][height];
    }

    public EnumMarks[][] getFields() {
        return _fields;
    }

    public boolean setField(int x, int y, EnumMarks mark) {
        if (x < 0 || y < 0) return false;
        if (x >= _width || y>=_height) return false;
        if (_fields[x][y] != EnumMarks.EMPTY) return false;
        _fields[x][y] = mark;
        return true;
    }
}
