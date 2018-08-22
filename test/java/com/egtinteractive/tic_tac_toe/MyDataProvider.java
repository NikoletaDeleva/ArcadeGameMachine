package com.egtinteractive.tic_tac_toe;

import com.egtinteractive.tic_tac_toe.io.DumpIO;
import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class MyDataProvider {
    public static Object[][] getNewArcadeGameMachine() {
	return new Object[][] { { new ArcadeGamesMachine(new DumpIO()) } };
    }
}
