package com.egtinteractive.tic_tac_toe.machine_tests;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

	try {
	    machine.putCoins(20);

	    Field field = machine.getClass().getDeclaredField("gameType");
	    field.setAccessible(true);
	    assertEquals(field.get(machine).toString().trim().toLowerCase(), machine.getIo().read().toLowerCase());

	} catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
	    e.printStackTrace();
	}
    }

    @Test(dataProvider = "arcadeGameMachine")
    public void returnAfterSelect(final ArcadeGamesMachine machine) {

	try {
	    machine.putCoins(20);

	    Method methodTwo = machine.getClass().getDeclaredMethod("getCoins", null);
	    methodTwo.setAccessible(true);

	    Method methodThree = machine.getClass().getDeclaredMethod("returnCoinsToCustomer", null);
	    methodThree.setAccessible(true);

	    assertEquals(methodThree.invoke(machine), methodTwo.invoke(machine));

	} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
		| InvocationTargetException e) {
	    e.printStackTrace();
	}
    }

}
