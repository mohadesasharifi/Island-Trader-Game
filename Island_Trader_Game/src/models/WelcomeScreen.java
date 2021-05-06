package models;

import java.util.ArrayList;

import transaction_interface.GameManagerInterface;

public class WelcomeScreen implements GameManagerInterface{
	WelcomeScreenGUI welcomeScreen = WelcomeScreenGUI();
	
	public WelcomeScreen() {
		
	};

	@Override
	public void setUp(GameManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getUserName() {
		return false;
	}

	@Override
	public boolean getDaysToPlay() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chooseShip(ArrayList<Ship> ships) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean readyToPlay() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
