package ui;

public enum EncryptionOptions {
	NO_ENCRYPTION(1, "No Encryption"),
	AFFINE(2,"Affine Cipher"),
	DES(3, "DES");

	private final int optionNumber;
	private final String optionString;
	

	EncryptionOptions(int optionNumber, String optionString) {
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
