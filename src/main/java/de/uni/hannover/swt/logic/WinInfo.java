package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

/**
 * WinInfo describes if the game was won by the player, by who and where the winning marks are located. In case where
 * no one has won player and winningField will EMPTY variables.
 *
 * @param won          True if the game was won False if not.
 * @param player       Who won the player? If no one Won EMPTY will be set
 * @param winningField If the game was won, slots with the winning mark will be shown. Else everything will be empty.
 */
public record WinInfo(boolean won, Player player, EnumMarks[][] winningField) {
}