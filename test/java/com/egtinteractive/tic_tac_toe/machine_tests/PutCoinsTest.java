package com.egtinteractive.tic_tac_toe.machine_tests;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class PutCoinsTest {

    @DataProvider(name = "arcadeGameMachine")
    public Object[][] getData() {
	return MyDataProviderMachine.getNewArcadeGameMachine();
    }

    @Test(dataProvider = "arcadeGameMachine")
    public void f(final ArcadeGamesMachine machine) {

	try {
	    final long coinsPut = machine.putCoins(20);

	    Method methodTwo = machine.getClass().getDeclaredMethod("getCoins", null);
	    methodTwo.setAccessible(true);
	    
	    assertEquals(coinsPut, methodTwo.invoke(machine));

	} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
		| InvocationTargetException e) {
	    e.printStackTrace();
	}
    }

    @Test(dataProvider = "arcadeGameMachine")
    public void fOne(final ArcadeGamesMachine machine) {

	try {
	    final long coinsPut = machine.putCoins(20);
	    machine.putCoins(-5);

	    Method methodTwo = machine.getClass().getDeclaredMethod("getCoins", null);
	    methodTwo.setAccessible(true);
	    
	    assertEquals(coinsPut, methodTwo.invoke(machine));

	} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
		| InvocationTargetException e) {
	    e.printStackTrace();
	}
    }
}
