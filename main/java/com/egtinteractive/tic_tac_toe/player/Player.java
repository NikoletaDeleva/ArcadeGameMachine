package com.egtinteractive.tic_tac_toe.player;

public class Player {
    private final String name;
    private int points;

    Player(final String name) {
	this.name = name;
	this.points = 0;
    }

    public String getName() {
	return name;
    }

    public int getPoints() {
	return points;
    }

}
