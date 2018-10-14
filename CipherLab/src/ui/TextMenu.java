package ui;

public class TextMenu {
	
	public static void printWelcomeMessage() {
		System.out.println("Cryptography Test Client");
	}
	
	public static void printMenuOptions(String currentEncryption) {
		printWelcomeMessage();
		System.out.println("Main Menu" + " (" + currentEncryption + ")");
		for(MenuOptions menuOption:MenuOptions.values()) {
			System.out.println(menuOption);
		}
		
	}
	
	public static void printEncryptionOptions() {
		printWelcomeMessage();
		System.out.println("Encryption Options:");
		for(EncryptionOptions encryptionOption: EncryptionOptions.values()) {
			System.out.println(encryptionOption);
		}
	}
	
	public static void printInvalidOptionMessage() {
		System.err.println("Try a valid option");
	}

}
