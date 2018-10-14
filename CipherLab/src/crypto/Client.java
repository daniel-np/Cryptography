/**
 * 
 */
package crypto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import utility.Utility;

public class Client implements IParent {
	String currentEncryption = "No Encryption";

	public void sendAndReceice() {
		Socket client;
		ObjectOutputStream oos;
		ObjectInputStream ois;

		try {
			client = new Socket("localhost", PORT);

			System.out.println("Client: Connected to Server on " + client.getInetAddress());

			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());

			// send a plaintext message to server
			String plaintxt = "Hello from client";

			// send message to server
			oos.writeObject(encryptMessage(plaintxt.getBytes()));
			oos.flush();

			// receive response from server
			byte[] response = (byte[]) ois.readObject();

			// Print the encrypted message
			System.out.println("Client: Encrypted response from server: " + new String(response));

			// Print the message in UTF-8 format
			String decryptedResponse = new String(decryptMessage(response), "UTF-8");
			System.out.println("Client: Response from server: " + decryptedResponse);

			// close client socket
			client.close();
			ois.close();
			oos.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	Utility utility = new Utility();

	@Override
	public byte[] encryptMessage(byte[] plainText) {
		byte[] cipherText = utility.chooseEncryption(currentEncryption, plainText);
		return cipherText;
	}

	@Override
	public byte[] decryptMessage(byte[] cipherText) {
		byte[] plainText = utility.chooseDecryption(currentEncryption, cipherText);
		return plainText;
	}

	public String getCurrentEncryptionOption() {
		return currentEncryption;
	}

	public void setCurrentEncryption(String currentEncryption) {
		this.currentEncryption = currentEncryption;
	}

}
