package com.egtinteractive.tic_tac_toe;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class PutCoinsTest {

    @DataProvider(name = "arcadeGameMachine")
    public Object[][] getData() {
	return MyDataProvider.getNewArcadeGameMachine();
    }

    @Test(dataProvider = "arcadeGameMachine")
    public void f(final ArcadeGamesMachine machine) {

	try {
	    final long coinsPut = machine.putCoins(20);
	    Method methodOne = machine.getClass().getDeclaredMethod("selectGame", new Class[] { String.class });
	    methodOne.setAccessible(true);
	    methodOne.invoke(machine, machine.getIo().read());
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
	    Method methodOne = machine.getClass().getDeclaredMethod("selectGame", new Class[] { String.class });
	    methodOne.setAccessible(true);
	    methodOne.invoke(machine, machine.getIo().read());
	    Method methodTwo = machine.getClass().getDeclaredMethod("getCoins", null);
	    methodTwo.setAccessible(true);
	    assertEquals(coinsPut, methodTwo.invoke(machine));

	} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
		| InvocationTargetException e) {
	    e.printStackTrace();
	}
    }
}
