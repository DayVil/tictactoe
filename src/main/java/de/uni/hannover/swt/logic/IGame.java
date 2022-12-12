package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

public interface IGame {
    EnumMarks[][] getState();
    boolean setPoint(int x, int y);
    Player getPlayer();
    WinInfo hasWon();
}
