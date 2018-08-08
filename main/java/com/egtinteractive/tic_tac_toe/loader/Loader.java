package com.egtinteractive.tic_tac_toe.loader;

import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class Loader {
    private static Loader loader;
    private ArcadeGamesMachine machine;

    private Loader() {
	this.machine = machine.getInstance();
	this.load();
    }

    public static Loader getInstance() {
	if (loader == null) {
	    loader = new Loader();
	}
	return loader;
    }

    private void load() {
	this.machine.waitToTurnOn();
    }
}
