package main;

import models.GameManager;
import models.WelcomeScreen;
import transaction_interface.GameManagerInterface;

public class Main {
	
	
	public static void main(String[] args) {
		//LoadGameData data = new LoadGameData();
		
		if (args.length > 0 && (args[0].equals("cmd"))) {
			CommandLines command = new CommandLines();
			command.play();
		}else {
			GameManagerInterface ui = new WelcomeScreen();
			GameManager manager = new GameManager(ui);
			manager.start();
		}
		
	}
}
