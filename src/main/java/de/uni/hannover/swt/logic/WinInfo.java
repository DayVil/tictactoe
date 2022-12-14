package de.uni.hannover.swt.logic;

import de.uni.hannover.swt.logic.enums.EnumMarks;
import de.uni.hannover.swt.logic.enums.Player;

public record WinInfo(boolean won, Player player, EnumMarks[][] winningField) {}