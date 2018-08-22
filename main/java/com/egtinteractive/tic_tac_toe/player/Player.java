package com.egtinteractive.tic_tac_toe.player;

public class Player {
    private String name;
    private int points;
    private String sign;

    public Player(String name, int points) {
	this.name = name;
	this.points = points;
    }

    public String getName() {
	return name;
    }

    public int getPoints() {
	return points;
    }

    public String getSign() {
	return sign;
    }

    public void setSign(String sign) {
	this.sign = sign;
    }

    @Override
    public String toString() {
	return (this.name + "  " + this.points);
    }
}
