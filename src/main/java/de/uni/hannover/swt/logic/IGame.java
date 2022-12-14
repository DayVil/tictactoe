package de.uni.hannover.swt.logic;

import de.uni.hannover.logic.enums.Player;
import de.uni.hannover.logic.enums.EnumMarks;

public interface IGame {
	Player getPlayer();
	EnumMarks[][] getState();
	boolean setPoint(int x, int y);
	WinInfo hasWon();
}