package utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

public class Utility {
	Affine affine;
	Des des;
	static String currentEncryption;

	public byte[] chooseEncryption(byte[] plainText) {
		switch (currentEncryption) {
		case "No Encryption":// No encryption
			return plainText;
		case "Affine Cipher":// Affine encryption
			affine = new Affine();
			return affine.encrypt(plainText);
		case "DES":// DES encryption
			try {
				des = new Des();
				return des.encrypt(plainText);
			} catch (InvalidKeyException | ShortBufferException | IllegalBlockSizeException | BadPaddingException
					| NoSuchAlgorithmException | NoSuchPaddingException e) {
				e.printStackTrace();
			}
		}
		System.err.println("Error choosing encryption");
		return null;
	}

	public byte[] chooseDecryption(byte[] encryptedText) {

		switch (currentEncryption) {
		case "No Encryption":// No encryption
			return encryptedText;
		case "Affine Cipher":// Affine encryption
			affine = new Affine();
			return affine.decrypt(encryptedText);
		case "DES":// DES encryption
			try {
				des = new Des();
				return des.decrypt(encryptedText);
			} catch (InvalidKeyException | ShortBufferException | IllegalBlockSizeException | BadPaddingException
					| NoSuchAlgorithmException | NoSuchPaddingException e) {
				e.printStackTrace();
			}
		}
		System.err.println("Error choosing encryption");
		return null;
	}

	public String getCurrentEncryption() {
		return Utility.currentEncryption;
	}

	public static void setCurrentEncryption(String currentEncryption) {
		Utility.currentEncryption = currentEncryption;
	}
}
