package de.uni.hannover.swt.logic;


import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

/**
 * This is the api for the GUI to interact with the game. With this interface the game can be managed and the heavy
 * lifting will be left out for the UI.
 */
public interface IGame {
    /**
     * Gets the currently acting player.
     *
     * @return Gets the currently acting player.
     */
    Player getPlayer();

    /**
     * @return Gets the state of the Board.
     */
    EnumMarks[][] getState();

    /**
     * Trys to set the mark of the currently acting player to the given position.
     *
     * @param x The given row
     * @param y The given column
     */
    void setPoint(int x, int y);

    /**
     * Resets the game to the starting state. Meaning every slot is EMPTY and player O is the active player.
     */
    void resetGame();

    /**
     * @return Returns if the game has won via WinInfo.
     */
    WinInfo hasWon();
}