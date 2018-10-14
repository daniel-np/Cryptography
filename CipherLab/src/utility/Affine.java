package utility;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Affine {
	int keyA = 5;
	int keyB = 9;
	int inverseA = 32; // Multiplicative inverse
	ArrayList<Character> alphabet;

	public Affine() {
		if (alphabet == null) {
			createAlphabet();
		}
	}
	
	/**
	 * Alphabet of all letters in the English alphabet + space. Does not contain other symbols.
	 * 
	 */
	private ArrayList<Character> createAlphabet() {
		alphabet = new ArrayList<Character>();
		// upper case
		for (int i = 0; i < 26; i++) {
			alphabet.add((char) (65 + i));
		}
		// lower case
		for (int i = 0; i < 26; i++) {
			alphabet.add((char) (97 + i));
		}
		alphabet.add(' ');

		return alphabet;
	}

	private int swapCharacterIndex(char plainTextCharacter) {

		for (int j = 0; j < alphabet.size(); j++) {
			Character a = alphabet.get(j);
			if (a.compareTo(plainTextCharacter) == 0) {
				return j;
			}
		}

		return 0;
	}

	public byte[] encrypt(byte[] plainText) {
		try {
			String plainTextString = new String(plainText, "ASCII");
			char[] plainTextCharArray = plainTextString.toCharArray();
			char[] encryptedTextCharArray = new char[plainTextCharArray.length];

			// ax + b % 52 = c
			// a = first key
			// b = second key
			// x = plainLetterIndex
			// c = encryptedLetterIndex
			// Affine method
			for (int i = 0; i < plainTextCharArray.length; i++) {
				int x = swapCharacterIndex(plainTextCharArray[i]);
				int swapIndex = (keyA * x + keyB) % alphabet.size();
				encryptedTextCharArray[i] = alphabet.get(swapIndex);
			}

			String encryptedString = new String(encryptedTextCharArray);

			return encryptedString.getBytes();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return plainText;
	}

	public byte[] decrypt(byte[] encryptedText) {
		try {

			String encryptedTextString = new String(encryptedText, "ASCII");
			char[] encryptedTextCharArray = encryptedTextString.toCharArray();
			char[] plainTextCharArray = new char[encryptedTextCharArray.length];

			// (c-b)*a^-1 %52 = x
			// a = first key
			// b = second key
			// x = letterIndex
			// c = encryptedLetterIndex
			// Affine method
			for (int i = 0; i < encryptedTextCharArray.length; i++) {
				int c = swapCharacterIndex(encryptedTextCharArray[i]);
				int swapIndex = Math.floorMod((c - keyB) * inverseA, alphabet.size());

				plainTextCharArray[i] = alphabet.get(swapIndex);
			}

			String plainTextString = new String(plainTextCharArray);

			return plainTextString.getBytes();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptedText;
	}
}
