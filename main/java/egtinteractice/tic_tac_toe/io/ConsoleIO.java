package egtinteractice.tic_tac_toe.io;

import java.util.Scanner;

public class ConsoleIO implements IO {
    private final Scanner scanner;

    public ConsoleIO() {
	this.scanner = new Scanner(System.in);
    }

    public void write(final String str) {
	System.err.println(str);

    }

    public String read() {
	return scanner.nextLine();
    }

    @Override
    public void close() throws Exception {
	scanner.close();

    }
}
