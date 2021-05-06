package models;

import java.util.ArrayList;

import main.LoadGameData;
import transaction_interface.GameManagerInterface;
import validations.InputValidation;

public class GameManager {
	private GameManagerInterface ui;
	private Trader trader;
	private LoadGameData data;
	private final ArrayList<Ship> ships;
	private final ArrayList<Island> islands;
	private Route route = new Route();
	private InputValidation check = new InputValidation();
	
	public GameManager(GameManagerInterface ui) {
		trader = new Trader();
		data = new LoadGameData();
		ships = data.createAllShips();
		islands = data.createAllIslands(trader);
		setHomeIslandRandomly();
		this.ui = ui;
	};
	
	
	public void setHomeIslandRandomly() {
		int number = (int)Math.floor(Math.random()*(4-0+1)+0);
		trader.setHomeIsland(islands.get(number));
		trader.setCurrentIsland(islands.get(number));
	}
	
	
	public void start() {
		ui.getUserName();
		ui.getDaysToPlay();
		ui.chooseShip(ships);
		ui.readyToPlay();
	}
	
	
	public boolean setName(String name) {
		if (check.validName(name)) {
			trader.setName(name);
			return true;
		}else {
			return false;
		}
	}
	
	
	public boolean updateDays(String days) {
		if (check.validNumberInRange(days, 20, 50)) {
			trader.setDaysToPlay(Integer.parseInt(days));
			return true;
		}
		return false;
	}
	
	
	public boolean updateShip(String input) {
		if (check.validNumberInRange(input, 1, 4)) {
			trader.setCurrentShip(ships.get(Integer.parseInt(input)-1));
			return true;
		}
		return false;
	}
	
}
