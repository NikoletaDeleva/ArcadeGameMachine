package com.egtinteractive.tic_tac_toe.launcher;
import com.egtinteractive.tic_tac_toe.io.ConsoleIO;
import com.egtinteractive.tic_tac_toe.loader.Loader;
import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class Demo {
    public static void main(String[] args) {
	@SuppressWarnings("unused")
	Loader loader = Loader.getInstance(new ArcadeGamesMachine(new ConsoleIO()));
    }
}
