package models;

import java.util.ArrayList;
import java.util.Scanner;

import main.LoadGameData;
import transaction_interface.GameManagerInterface;
import transaction_interface.ManagerInterface;
import validations.InputValidation;

public class GameManager{
	
	
	private final ManagerInterface ui;
	private Trader trader;
	private LoadGameData data;
	private ArrayList<Ship> ships;
	private ArrayList<Island> islands;
	private Route route = new Route();
	private InputValidation check = new InputValidation();
	private String name;
	private int days;
	private int shipNo = 0;
	private int homeIslandNo;
	private String categoryNumber;
	private ArrayList<Item> selectedCategoryItems;
	private int itemNoToPurchase;
	private String startGame;
	private int currentIslandNo;
	
	public GameManager(ManagerInterface ui) {
		this.ui = ui;
	};
		
	
	public int setHomeIslandRandomly() {
		int number = (int)Math.floor(Math.random()*(4-0+1)+0);
		homeIslandNo = number;
		currentIslandNo = number;
		return homeIslandNo;
	}
	
	public boolean ValidName(String text) {
		// TODO Auto-generated method stub
		return check.validName(text);
	}
	
	public String getName() {
		return name;
	}
	
	public void start() {
		ui.setup(this);		
	}
	
	
	public void onSetupFinished(String name, String days) {
		this.name = name;
		this.days = Integer.parseInt(days);
//		this.shipNo = selectedIndexShip;
		ui.start();
		
	}
	
	public void onFinish() {
		// TODO Auto-generated method stub
		if (ui.confirmQuit()) {
			// If we had any clean up to do before quitting we should do it here before telling
			// the ui to quit.
			ui.quit();
		}
	}


	
	public void setTrader() {
		// TODO Auto-generated method stub
		trader = new Trader();
		homeIslandNo = setHomeIslandRandomly();
		trader.setName(name);
		trader.setDaysToPlay(days);
		trader.setHomeIsland(islands.get(homeIslandNo));
		trader.setCurrentShip(ships.get(shipNo));
		trader.getCurretShip().setShipOwner(trader);
	}


	
	public void viewProfile(ArrayList<Island> Island, Trader trader) {
		// TODO Auto-generated method stub
		
	}


	
	


	
	public void playTheMainGame(ArrayList<Island> island, Trader trader) {
		// TODO Auto-generated method stub
		
	}


	
	public void scanTransactionOrTravel(ArrayList<Island> island, Trader trader) {
		// TODO Auto-generated method stub
		
	}


	
	public void getItemsByCategory(ArrayList<Island> island, Trader trader) {
		// TODO Auto-generated method stub
		
	}


	
	public void scanTransactionType(ArrayList<Island> island, Trader trader) {
		// TODO Auto-generated method stub
		
	}


	
	public void purchase(ArrayList<Island> island, Trader trader) {
		// TODO Auto-generated method stub
		
	}


	
	public void transactionProcess(ArrayList<Island> island, Trader trader) {
		// TODO Auto-generated method stub
		
	}


	
	public void sell(ArrayList<Island> island, Trader trader) {
		// TODO Auto-generated method stub
		
	}


	
	public void destinationIsland(ArrayList<Island> Island, Trader trader) {
		// TODO Auto-generated method stub
		
	}


	
	public void printallpossilberoutes(int source, int destination, ArrayList<Island> island,
			ArrayList<ArrayList<Integer>> routes) {
		// TODO Auto-generated method stub
		
	}


	public void randomEvents(int size) {
		// TODO Auto-generated method stub
		
	}


	public void chooseRoute(int source, int destination, Trader trader, ArrayList<Island> island) {
		// TODO Auto-generated method stub
		
	}


	public String gameOver(Trader trader, String reason) {
		// TODO Auto-generated method stub
		return null;
	}



	public ArrayList<Ship> getShips(){
		return ships;
	}



	



	
}
