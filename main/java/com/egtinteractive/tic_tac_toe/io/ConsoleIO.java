package com.egtinteractive.tic_tac_toe.io;

import java.util.Scanner;

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
    public String readString() {
	return scanner.next();
    }

    @Override
    public int readPosition() {
	return scanner.nextInt();
    }
    
    @Override
    public void close() throws Exception {
	scanner.close();

    }
}
