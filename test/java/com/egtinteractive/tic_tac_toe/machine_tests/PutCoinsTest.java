package com.egtinteractive.tic_tac_toe.machine_tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class PutCoinsTest {

    @DataProvider(name = "arcadeGameMachine")
    public Object[][] getData() {
	return MyDataProviderMachine.getNewArcadeGameMachine();
    }

    @Test(dataProvider = "arcadeGameMachine")
    public void coinsInMachine(final ArcadeGamesMachine machine) {
	final long coinsPut = machine.putCoins(20);

	final long returnCoins = machine.service();

	assertEquals(coinsPut, returnCoins);

    }

    @Test(dataProvider = "arcadeGameMachine")
    public void coinsInMachineIfAddNegative(final ArcadeGamesMachine machine) {

	final long coinsPut = machine.putCoins(20);
	machine.putCoins(-5);

	final long returnCoins = machine.service();

	assertEquals(coinsPut, returnCoins);
    }
}
