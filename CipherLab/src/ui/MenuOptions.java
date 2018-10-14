package ui;

public enum MenuOptions {
	
	SEND_MESSAGE(1, "Send Message"),
	CHOOSE_ENCRYPTION(2, "Choose Encryption");

	private final int optionNumber;
	private final String optionString;
	

	MenuOptions(int optionNumber, String optionString) {
		this.optionNumber = optionNumber;
		this.optionString = optionString;
	}

	public int optionNumber() {
		return optionNumber;
	}

	public String optionString() {
		return optionString;
	}

	@Override
	public String toString() {
		return "(" + optionNumber + ")" + " " + optionString;
	}
}
