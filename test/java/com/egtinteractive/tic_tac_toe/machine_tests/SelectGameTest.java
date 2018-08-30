package com.egtinteractive.tic_tac_toe.machine_tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class SelectGameTest {

    @DataProvider(name = "arcadeGameMachine")
    public Object[][] getData() {
	return MyDataProviderMachine.getNewArcadeGameMachine();
    }

    @Test(dataProvider = "arcadeGameMachine")
    public void select(final ArcadeGamesMachine machine) {
	machine.putCoins(20);
	assertEquals(machine.getGameType().trim().toLowerCase(), machine.getIo().read().toLowerCase());
    }

}
