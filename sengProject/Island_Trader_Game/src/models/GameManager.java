package models;

import java.util.ArrayList;

import main.LoadGameData;
import transaction_interface.ManagerInterface;
import validations.InputValidation;

public class GameManager{
	
	// initialization of ui for ManagerInterface
	private final ManagerInterface ui;
	// initialization of trader
	private Trader trader;
	// initialization of data
 	private LoadGameData data;
	// initialization of ships to contain all the ships
	private ArrayList<Ship> ships;
	// initialization of islands contain all the islands
	private ArrayList<Island> islands;
	// initialization of route to calculate routes
	private Route route = new Route();
	// initialization of check to check all the inputs
	private InputValidation check = new InputValidation();
	// initialization of variable to keep a randomly generated number of home island
	private int homeIslandNo;
	// double for total traveling cost
	private double totalTravelCost = 0;
	
	
	/**
	 * this is a constructor for class Game Manager
	 * @param ui to establish link between Ui and game manager
	 * @param data to help game manager build/load all the required game components like islands, ships, shops etc.
	 */
	public GameManager(ManagerInterface ui, LoadGameData data) {
		this.ui = ui;
		this.data = data;
		trader = new Trader();
		ships = data.createAllShips();
		islands = data.createAllIslands(trader);
		data.createEvents();
		
	};
		
	/**
	 * this method randomly selects a number for home island 
	 * @return integer
	 */
	public int setHomeIslandRandomly() {
		int number = (int)Math.floor(Math.random()*(4-0+1)+0);
		homeIslandNo = number;
		return homeIslandNo;
	}
	
	/**
	 * this method checks if the name is valid
	 * @param text String
	 * @return boolean value for validity of name
	 */
	public boolean ValidName(String text) {
		// TODO Auto-generated method stub
		return check.validName(text);
	}
	
	/**
	 * this method returns the name of the current trader
	 * @return String
	 */
	public String getName() {
		return trader.getName();
	}
	
	/**
	 * this method returns current coins trader has
	 * @return double
	 */
	public double getCoins() {
		return trader.getCoins();
	}
	
	/**
	 * this method return current profit status of trader
	 * @return double
	 */
	public double getProfit() {
		return trader.getProfit();
	}
	
	/**
	 * this method return number of days remaining
	 * @return integer
	 */
	public int getDays() {
		return (int) trader.getDays();
	}
	
	/**
	 * this method starts the setup page for GUI
	 */
	public void start() {
		ui.setup(this);		
	}
	
	/**
	 * this method return the current ship trader has
	 * @return type Ship
	 */
	public Ship getShip() {
		return trader.getCurretShip();
	}
	
	/**
	 * this method repairs the ship
	 */
	public void shipRepair() {
		getShip().repairShip(trader);
	}
	
	/**
	 * this method returns an array containing all the ships
	 * @return ArrayList type Ships
	 */
	public ArrayList<Ship> getShips(){
		return ships;
	}

	/**
	 * this method updates details of trader obtained from setup page and starts the game
	 * @param name String
	 * @param days String
	 * @param selectedIndexShip Integer
	 */
	public void onSetupFinished(String name, String days,int selectedIndexShip) {
		homeIslandNo = setHomeIslandRandomly();
		trader.setName(name);
		trader.setDaysToPlay(Integer.parseInt(days));
		trader.setHomeIsland(islands.get(homeIslandNo));
		trader.setCurrentShip(ships.get(selectedIndexShip));
		trader.getCurretShip().setShipOwner(trader);
		
		ui.start();	
	}
	
	/**
	 * this method ends the game and closes all the screens open
	 */
	public void onFinish() {
		// TODO Auto-generated method stub
		ui.quit();
	}

	/**
	 * this method return all the items bought by trader
	 * @return String
	 */
	public String getboughtItems() {
		return trader.getBoughtItems();
	}
	
	/**
	 * this method returns all the items sold by the trader
	 * @return String
	 */
	public String getSoldItems() {
		return trader.getSoldItems();
	}
	
	/**
	 * this method returns the home island of the trader
	 * @return Island
	 */
	public Island getHomeIsland() {
		return trader.gethomeIsland();
	
	}
	
	/**
	 * this method returns the current island of the trader
	 * @return Island
	 */
	public Island getCurrentIsland() {
		return trader.getCurrentIsland();
	}

	/**
	 * this method returns a list of available categories
	 * @return String[]
	 */
	public String[] getItemsByCategory() {
		// TODO Auto-generated method stub
		String[] category = trader.getCurrentIsland().getStore().getAllCategories();
		return category;
	}
	
	/**
	 * this method processes the purchase coin transaction under view profile
	 * it subtracts the days and adds the coins to trader
	 * @param extraCoins Integer
	 * @param days Integer
	 */
	public void purchaseCoins(int extraCoins, int days) {
		trader.setDaysToPlay((int) trader.getDays() - days);
		trader.addCoins(extraCoins);
	}
	
	/**
	 * this method returns the list of items by category provided
	 * @param categoryNumber Integer number of category
	 * @return Item[]
	 */
	public Item[] showProducts(int categoryNumber) {
		String[] category = getItemsByCategory();
		String categoryName = category[categoryNumber];
		ArrayList<Item> products = trader.getCurrentIsland().getStore().getProducts(categoryName);
		
		Item[] product_list = new Item[products.size()];
		for(int j = 0; j < product_list.length; j++) {
			product_list[j] = products.get(j);
		}
		return product_list;
	}
	
	/**
	 * this method returns an array of items by category provided
	 * @param categoryNumber Integer number of category
	 * @return ArrayList of products of type Item
	 */
	public ArrayList<Item> getProducts(int categoryNumber) {
		String[] category = getItemsByCategory();
		String categoryName = category[categoryNumber];
		ArrayList<Item> products = trader.getCurrentIsland().getStore().getProducts(categoryName);
		return products;
	}
	
	/**
	 * this method returns the quantity available of a product at a store
	 * @param itemNo Integer
	 * @param categoryNo Integer
	 * @return Integer
	 */
	public int getMaxQuantityAvailableAtStore(int itemNo, int categoryNo) {
		ArrayList<Item> productList =  getProducts(categoryNo);
		int maxQuantity = productList.get(itemNo).getQuantityAvailble();
		return maxQuantity;
	}
	
	/**
	 * this method calls the purchase process of the trader
	 * @param quantity
	 * @param itemNo
	 * @param categoryNo
	 */
	public void purchase(int quantity, int itemNo, int categoryNo) {
		ArrayList<Item> productList =  getProducts(categoryNo);
		trader.purchase(productList.get(itemNo), quantity);
		
	}

	/**
	 * this method check if the purchase is successful by check the available quantity, coins and storage on the ship.
	 * @param quantity Integer
	 * @param itemNo Integer
	 * @param categoryNo Integer
	 * @return Boolean (True / False)
	 */
	public boolean isPurchaseSuccessful(int quantity, int itemNo, int categoryNo) {
		ArrayList<Item> products = getProducts(categoryNo);
		if (trader.getCoins() >= products.get(itemNo).getSellPrice() * quantity) {
			if (trader.getCurretShip().getAvailableStorage() >= quantity)
				return true;
		}
		return false;
	}

	/**
	 * this method process a sale of an item of trader, takes an index of an item to help the item from an array
	 * and the quantity of item trader would like to sell
	 * @param itemNo Integer
	 * @param quantity Integer
	 */
	public void sell(int itemNo, int quantity) {
		// TODO Auto-generated method stub
		ArrayList<Item> boughtItems = trader.allBoughtItems();
		Item item = boughtItems.get(itemNo);
		trader.sellItem(item, quantity);
	}
	
	/**
	 * this method checks if the sale is successful or not, it checks the available quantity and updates the coins and profit accordingly
	 * @param itemNo Integer
	 * @param quantity Integer
	 * @return Boolean
	 */
	public boolean isSellSuccessful(int itemNo, int quantity) {
		if (trader.allBoughtItems().size() > 0) {
			if (quantity <= trader.allBoughtItems().get(itemNo).getQuantityAvailble())
				return true;
		}
		return false;
	}

	/**
	 * this method returns an Array of islands
	 * @return ArrayList of type Island
	 */
	public ArrayList<Island> getIsalnds() {
		return islands;
	}

	/**
	 * this method returns an Array of Array containing index numbers of islands between start and 
	 * destination islands
	 * @param source Integer
	 * @param destination Integer
	 * @return ArrayList of ArrayList of Integer
	 */
	public ArrayList<ArrayList<Integer>> getRouteByNumber(int source, int destination){
		ArrayList<ArrayList<Integer>> routes = route.calculateAllPaths(source, destination);
		return routes;
	}
	
	/**
	 * this method returns an Array containing the name of the islands instead of index number,
	 * and returns an Array of String containing all the names of islands between start and destination islands.
	 * @param destination Integer for index of destination
	 * @return ArrayList of type String
	 */
	public ArrayList<String> getRoutesByIslandName(int destination) {
		// TODO Auto-generated method stub
		int source = islands.indexOf(getCurrentIsland());
		ArrayList<ArrayList<Integer>> routes = route.calculateAllPaths(source, destination);
		ArrayList<String> source_dest_route = new ArrayList<String>();
		String islandsOnYourWay;
		for (int count=0; count < routes.size(); count++) {
			ArrayList<Integer> subRoute = routes.get(count);
			islandsOnYourWay = islands.get(source).getName();
			for (int index: subRoute) {
				if (index != source) {
					islandsOnYourWay += " -> " + islands.get(index).getName();
				}
			}
			source_dest_route.add(islandsOnYourWay);
		}
		return source_dest_route;
	}


	/**
	 * this method updates current island of trader as he/she travels around
	 * @param islandNo Integer index of island from islands Array
	 */
	public void setCurrentIsland(int islandNo) {
		trader.setCurrentIsland(islands.get(islandNo));
	}
	
	/**
	 * this method works out the total wage for the length of the travel and returns it
	 * @param lengthOfRoute Integer
	 * @return wage - double total wage cost for the length of travel
	 */
	public double getCrewWage(int lengthOfRoute) {
		double wage = trader.getCurretShip().getDailyWage() * lengthOfRoute;
		return wage;
	}
	
	/**
	 * this method calculates the total cost of travel and duration of travel by divide the length of travel by the speed of 
	 * @param lengthOfRoute
	 * @return arrayList of type Double
	 */
	public ArrayList<Double> getTravelCost(int lengthOfRoute){
		double crewWage = getCrewWage(lengthOfRoute);
		double travelDuration = (lengthOfRoute * Island.DISTANCE) / trader.getCurretShip().getSpeed();
		
		ArrayList<Double> result = new ArrayList<Double>();
		result.add(crewWage);
		result.add(travelDuration);
		return result;
	}
	
	/**
	 * this method process the travel cost and deducts the cost from the trader
	 * @param lengthOfRoute
	 */
	public void travelCostYou(int lengthOfRoute) {
		double crewWage = getCrewWage(lengthOfRoute);
		double remainigCoins = trader.getCoins() - crewWage;
		trader.setCoins(remainigCoins);
		double travelDuration = (lengthOfRoute * Island.DISTANCE) / trader.getCurretShip().getSpeed();
		double remainingDays = trader.getDays() - travelDuration;
		trader.setDaysToPlay((int) remainingDays);
		this.totalTravelCost += crewWage;
		trader.updateProfit(-1 * totalTravelCost);
		
	}
	
	/**
	 * this method randomly generates all the possible events that can happen during the travel and returns them in an Array
	 * @param size Integer - length of travel
	 * @return ArrayList of type Event containing all the random events.
	 */
	public ArrayList<Event> randomEvents(int size) {
		// TODO Auto-generated method stub
		ArrayList<Event> events = data.checkForEvents(size);
		//eventType can be in one of "Bad Weather", "Shark Attack", "Defeated Pirates", "Lost Cargo", "Walk Plank", "Rescued Sailors"
		//After occurring an event coins, cargo are different.
		return events;
	}
	
	/**
	 * this method evokes the attack method of every event when sailors come across an event
	 * @param eventType Event
	 * @return String containing outcome of an Event
	 */
	public String attack(Event eventType) {
		String result = eventType.attack(trader);
		return result;
	}
	
	/**
	 * Evokes the gameOver method in Interface to close the current window and open the game over window
	 */
	public void gameOver() { 
		// TODO Auto-generated method stub
		ui.gameOver();
	}
	
	/**
	 * Evokes the goStore method in Interface to close the current window and open the store window
	 */
	public void store() {
		ui.goStore();
	}
	
	/**
	 * Evokes the goTravel method in Interface to close the current window and open the Travel window
	 */
	public void travel() {
		ui.goTravel();
	}
	
	/**
	 * Evokes the profilePage method in Interface to close the current window and open the profile window
	 */
	public void viewProfile() {
		ui.profilePage();
	}
	
	/**
	 * Evokes the backToMainMenu method in Interface to close the current window and open the Welcome window
	 */
	public void backToMainMenu() {
		ui.backToMainMenu();
	}

	/**
	 * Evokes the purchase method in Interface to close the current window and open the purchase window
	 */
	public void goPurchase() {
		// TODO Auto-generated method stub
		ui.purchase();
	}

	/**
	 * Evokes the sell method in Interface to close the current window and open the Sale window
	 */
	public void goSell() {
		// TODO Auto-generated method stub
		ui.sell();
	}
	
	/**
	 * Evokes the eventPopUp method in Interface to close the current window and open the EventPopUp window to show the outcome of Event
	 * @param travelLength
	 */
	public void EventPopUp(int travelLength) {
		ui.eventPopUp(travelLength);
	}

	/**
	 * this method a list of items that are bought by the trader
	 * @return Item[]
	 */
	public Item[] getBoughtList() {
		// TODO Auto-generated method stub
		ArrayList<Item> products = trader.getBoughtList();
		Item[] product_list = new Item[products.size()];
		for(int j = 0; j < product_list.length; j++) {
			product_list[j] = products.get(j);
		}
		return product_list;
	}

	/**
	 * this method returns the quantity of item bought by the customer
	 * @param itemNo
	 * @return
	 */
	public int maxQBoughtItems(int itemNo) {
		// TODO Auto-generated method stub
		Item[] products = getBoughtList();
		return products[itemNo].getQuantityAvailble();
	}


	
}
