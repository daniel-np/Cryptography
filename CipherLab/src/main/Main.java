package main;

import crypto.Client;
import crypto.Server;
import ui.TextUI;

public class Main {

	
	
	public static void main(String[] args) {
		Server server = new Server();
		Client client = new Client();
		
		server.start();
		TextUI textUI = new TextUI(client, server);
		textUI.mainMenu();
	}

}
