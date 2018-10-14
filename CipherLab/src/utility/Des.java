package utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

public class Des {

	SecretKeySpec secretKey;
	Cipher cipher;
	static byte[] key;

	/**
	 * Implements DES/ECB with PKCS5 Padding with Specified key 8 byte long key
	 * 
	 * @param key
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public Des(byte[] key) {
		try {
			// Key must be 8 bytes long
			secretKey = new SecretKeySpec(key, "DES");
			// Transformation ECB
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implements DES/ECB with PKCS5 Padding with a Preset key
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public Des() throws BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

		key = new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef };
		// Key must be 8 bytes long
		secretKey = new SecretKeySpec(key, "DES");
		// Transformation ECB
		cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

	}

	public byte[] encrypt(byte[] plainText)
			throws ShortBufferException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// Initialize encryption mode
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		int textLength = plainText.length;
		byte[] output = new byte[cipher.getOutputSize(textLength)];

		textLength = cipher.update(plainText, 0, textLength, output, 0);
		textLength += cipher.doFinal(output, textLength);

		return output;
	}

	public byte[] decrypt(byte[] cipherText)
			throws InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
		// Initialize decryption mode
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		int textLength = cipherText.length;
		byte[] output = new byte[cipher.getOutputSize(textLength)];

		textLength = cipher.update(cipherText, 0, textLength, output, 0);
		textLength = cipher.doFinal(output, textLength);

		return output;
	}

	public static void main(String args[]) {
		byte[] plainText = "Hello from client".getBytes();
		try {
			Des des = new Des();
			System.out.println(plainText);
			byte[] encryptedText = des.encrypt(plainText);
			System.out.println(new String(encryptedText));
			plainText = des.decrypt(encryptedText);
			System.out.println(new String(plainText));

		} catch (BadPaddingException | InvalidKeyException | ShortBufferException | IllegalBlockSizeException
				| NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}

	}
}
