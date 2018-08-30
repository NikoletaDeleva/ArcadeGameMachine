package com.egtinteractive.tic_tac_toe.games;

import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class Dummy implements GamesLoader {
    final String name;
    final long price;

    public Dummy() {
	this.name = "Dummy";
	this.price = 0;
    }

    @Override
    public void load(ArcadeGamesMachine arcadeGamesMachine) {
	arcadeGamesMachine.endService();
    }

    @Override
    public Game getGame(ArcadeGamesMachine arcadeGamesMachine) {
	return null;
    }

    @Override
    public long getPrice() {
	return price;
    }

    @Override
    public String getName() {
	return name;
    }
}
