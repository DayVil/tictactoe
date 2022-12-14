package de.uni.hannover.swt.logic;


import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

public interface IGame {
	Player getPlayer();
	EnumMarks[][] getState();
	boolean setPoint(int x, int y);
	WinInfo hasWon();
}