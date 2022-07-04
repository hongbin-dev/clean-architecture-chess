package view;

import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputCommand() {
		return SCANNER.nextLine();
	}

	public static String inputCommand(String text) {
		System.out.println(text);
		return inputCommand();
	}
}
