package com.egtinteractive.tic_tac_toe.io;

import java.util.List;
import java.util.Scanner;

import com.egtinteractive.tic_tac_toe.player.Player;

public class ConsoleIO implements IO {
    private final Scanner scanner;

    public ConsoleIO() {
	this.scanner = new Scanner(System.in);
    }

    @Override
    public void write(final String str) {
	System.out.println(str);

    }

    @Override
    public String read() {
	return scanner.nextLine();
    }

    @Override
    public void close() throws Exception {
	scanner.close();

    }

    public void listAll(List<Player> listPlayers) {
	this.write(System.lineSeparator());
	this.write("Top 3 players:");

	for (Player player : listPlayers) {
	    this.write(player.toString());
	}

	this.write(System.lineSeparator());
    }
}
