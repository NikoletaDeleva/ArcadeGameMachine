package com.egtinteractive.tic_tac_toe.machine_tests;

import com.egtinteractive.tic_tac_toe.io.DumpIO;
import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class MyDataProviderMachine {
    public static Object[][] getNewArcadeGameMachine() {
	return new Object[][] { { new ArcadeGamesMachine(new DumpIO()) } };
    }
}
