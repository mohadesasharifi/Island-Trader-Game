package main;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import models.*;
import transaction_interface.GameManagerInterface;
import validations.InputValidation;

public class GameCmdUI implements GameManagerInterface{
	
	private final Scanner scanner;
	
	private GameManager manager;
	
	public GameCmdUI() {
		scanner = new Scanner(System.in);
	}

	@Override
	public void setUp(GameManager tempManager) {
		manager = tempManager;
		
	}
	
	@Override
	public void start() {
		setUp(manager);
	}

	@Override
	public boolean getUserName() {
		System.out.println("Welcome To The Game: TRADER ISLAND\n");
		System.out.println("Please choose your \"Trader\" name:");
		String input = scanner.next();
		while (!manager.setName(input)) {
			System.out.println("\nTrader name must be between 3-15 alphabetical characters:");
			input = scanner.next();
		}
		return true;
	}

	@Override
	public boolean getDaysToPlay() {
		System.out.println("\nPlease choose how many days would you like to play the game.\n"
						+ "You can choose between 20 to 50 days:");
		String input = scanner.next();
		
		while(!(manager.updateDays(input))) {
				System.out.println("\nYou MUST choose between 20 to 50 days.");
				input = scanner.next();
		}
		return false;
	}

	@Override
	public boolean chooseShip(ArrayList<Ship> ships) {
		int index = 1;
		for (Ship ship: ships) {
			System.out.println(String.format("%.2d: %s",index, ship));
			index += 1;
		}
		System.out.println("\nPlease choose a ship from the following:");
		String input = scanner.next();
		while (!manager.updateShip(input)) {
			System.out.println("\nPlease choose from the available options:");
			input = scanner.next();
		}
		
		
		return false;
	}

	@Override
	public boolean readyToPlay() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}



