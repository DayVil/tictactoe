package de.uni.hannover.swt;

import de.uni.hannover.swt.logic.Game;

public class App {
    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Game game = new Game(WIDTH, HEIGHT);
        System.out.println(game);
    }
}