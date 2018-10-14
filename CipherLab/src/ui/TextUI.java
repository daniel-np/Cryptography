package ui;

import java.util.Scanner;

import crypto.Client;
import crypto.Server;

public class TextUI {
	Scanner scanner;
	String input;
	String currentEncryption = EncryptionOptions.NO_ENCRYPTION.optionString();
	Client client;
	Server server;
	public TextUI(Client client, Server server) {
		this.client = client;
		this.server = server;
		scanner = new Scanner(System.in);
	}

	public void mainMenu() {
		while (true) {
			TextMenu.printMenuOptions(currentEncryption);
			menuSelection();
		}
	}

	private void menuSelection() {
		input = scanner.nextLine();
		switch (input) {
		case "1": 
			client.sendAndReceice();
			System.out.println();
			break;
		case "2":
			TextMenu.printEncryptionOptions();
			encryptionSelection();
			break;
		case "":
		default:
			TextMenu.printInvalidOptionMessage();
			break;
		}
	}

	private void encryptionSelection() {
		input = scanner.nextLine();
		boolean isValidInput = false;
		for (EncryptionOptions option : EncryptionOptions.values()) {
			if (input.equals(Integer.toString(option.optionNumber()))) {
				currentEncryption = option.optionString();
				//Set client encryption
				client.setCurrentEncryption(currentEncryption);
				System.out.println(currentEncryption + " set!");
				isValidInput = true;
				System.out.println();
				break;
			}
		}
		if (!isValidInput) {
			TextMenu.printInvalidOptionMessage();
			menuSelection();
		}
	}
}
