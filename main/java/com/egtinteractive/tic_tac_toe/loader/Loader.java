package com.egtinteractive.tic_tac_toe.loader;

import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class Loader {
    private static Loader loader = null;
    private ArcadeGamesMachine machine;

    private Loader(ArcadeGamesMachine machine) {
	this.machine = machine;
	this.load();
    }

    public static Loader getInstance(ArcadeGamesMachine machine) {
	if (loader == null) {
	    loader = new Loader(machine);
	}
	return loader;
    }

    private void load() {
	this.machine.waitToTurnOn();
    }
}
