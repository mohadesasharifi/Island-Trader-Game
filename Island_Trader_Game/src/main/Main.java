package main;

import models.*;
import javax.swing.SwingUtilities;

import gamegui.*;
import transaction_interface.*;

public class Main {
	
	
	public static void main(String[] args) {
		LoadGameData data = new LoadGameData();
		ManagerInterface ui;
//		CommandLines command = new CommandLines();
//		command.setTrader();
		ui = new Gui();
		GameManager manager = new GameManager(ui);
        SwingUtilities.invokeLater(() -> manager.start());
	
	}
}
