package com.egtinteractive.tic_tac_toe.machine_tests;

import com.egtinteractive.tic_tac_toe.games.Dummy;
import com.egtinteractive.tic_tac_toe.games.GamesLoader;
import com.egtinteractive.tic_tac_toe.io.DumpIO;
import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class MyDataProviderMachine {
    public static Object[][] getNewArcadeGameMachine() {
	GamesLoader gameType = new Dummy();
	return new Object[][] { { new ArcadeGamesMachine(new DumpIO() , gameType) } };
    }
}
