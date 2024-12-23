package main;

import models.*;
import javax.swing.SwingUtilities;

import gamegui.*;
import transaction_interface.*;

/**
 * this model is the entry point for the game, i
 * @author Zeus Khan
 *
 */

public class Main {
	
	/**
	 * this method starts GUI or Command line depending on the arguments it's called with
	 * @param args
	 */
	public static void main(String[] args) {
		LoadGameData data = new LoadGameData();
		ManagerInterface ui;
		if (args.length > 0 && (args[0].equals("cmd"))) {
			//TODO
			//Cmd command = new Cmd();
			//command.setTrader();
		}else {
			ui = new Gui();
			GameManager manager = new GameManager(ui, data);
	        SwingUtilities.invokeLater(() -> manager.start());
		}
	}
}