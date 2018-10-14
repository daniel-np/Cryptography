/**
 * 
 */
package crypto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import utility.Utility;

public class Server implements IParent, Runnable {
	private Thread t;

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void run() {
		Server server = new Server();
		server.setup();
		// Wait for requests
		while (true) {
			server.receiveAndSend();
		}
	}

	private void setup() {

	}

	public void receiveAndSend() {
		ServerSocket server;
		Socket client;
		ObjectOutputStream oos;
		ObjectInputStream ois;

		try {
			server = new ServerSocket(PORT);
//			System.out.println("Waiting for requests from client...");
			client = server.accept();
			System.out.println("Server: Connected to client at the address: " + client.getInetAddress());

			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());

			// Receive message from the client
			byte[] clientMsg = (byte[]) ois.readObject();

			// Print the encrypted message
			System.out.println("Server: Encrypted message from client: " + new String(clientMsg));
			
			// Print the message in UTF-8 format
			String decryptedMessage = new String(decryptMessage(clientMsg), "UTF-8");
			System.out.println("Server: Message from Client: " + decryptedMessage);

			// Create a response to client if message received
			String response = "No message received";

			if (ois != null) {
				response = "Message received from client";

				// Send the plaintext response message to the client
				byte[] cipherText = encryptMessage(response.getBytes());

				oos.writeObject(cipherText);
				// oos.writeObject(response.getBytes());
				oos.flush();
			}

			// Close Client and Server sockets
			client.close();
			server.close();
			oos.close();
			ois.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	Utility utility;
	@Override
	public byte[] encryptMessage(byte[] plainText) {
		utility = new Utility();
		byte[] cipherText = utility.chooseEncryption(plainText);
		return cipherText;
	}

	@Override
	public byte[] decryptMessage(byte[] cipherText) {
		utility = new Utility();
		byte[] plainText = utility.chooseDecryption(cipherText);
		return plainText;
	}
}
