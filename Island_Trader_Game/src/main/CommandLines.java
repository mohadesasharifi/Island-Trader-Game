package main;

import validations.InputValidation;

import java.text.NumberFormat;
import java.util.*;
import models.*;
import transaction_interface.GameManagerInterface;
import transaction_interface.ManagerInterface;

/**
 * Command-line operations of the Game Island Trader
 * 
 * @author Mohadesa Sharifi
 * @author Zahid khan
 *
 */

public class CommandLines implements ManagerInterface{
	// Instance of class InputValidation with variable check to check all the user inputs
	InputValidation check = new InputValidation();
	
	private Route route = new Route();
	
	private Trader trader;
	// String variable for User name
	private String name;
	
	// Integer variable for number of days user wants to play the game
	private int days;
	
	// Integer variable for Users' ship number
	private int shipNo = 0;
	
	// Integer variable for user's home-island
	private int homeIslandNo;
	
	// String variable for ?
	private String categoryNumber;
	
	// Array List for Item ?
	private ArrayList<Item> selectedCategoryItems;
	
	// Integer variable for number of item purchased
	private int itemNoToPurchase;
	
	// String variable to start or end the game
	private String startGame;
	
	private Scanner scanner = new Scanner(System.in);
	
	
	// Integer variable for number of current Island
	private int currentIslandNo;
	/**
	 * Constructor for class Command lines
	 */
	public CommandLines() {};
	
	
	@Override
	public void setup(GameManager gameManager) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean confirmQuit() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showError(String error) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * this method returns user's name
	 * @return returns users' name is String format
	 */
	public String geTraderName(){
		return name;
	}
	
	
	/**
	 * this method returns number of days to play the game
	 * @return integer number of days
	 */
	public int getDaystoPlayGame() {
		return days;
	}
	
	
	/**
	 * this method returns User's ship number.
	 * @return integer ship number
	 */
	public int getShipNo() {
		return shipNo;
	}
	
	
	/**
	 * this method randomly chooses a number for home island
	 */
	public int setHomeIslandRandomly() {
		int number = (int)Math.floor(Math.random()*(4-0+1)+0);
		homeIslandNo = number;
		currentIslandNo = number;
		return homeIslandNo;
	}
	
	/**
	 * this method returns Users home-island
	 * @return integer - Island number
	 */
	public int getHomeIslandNo() {
		return homeIslandNo;
	}
	
	
	public String getStartGame() {
		return startGame;
	}
	
	
	public void setTrader() {
		trader = new Trader();
		LoadGameData data = new LoadGameData();
		final ArrayList<Ship> ships = data.createAllShips();
		final ArrayList<Island> Island = data.createAllIslands(trader);
		setHomeIslandRandomly();
		
		String name = setName();
		int days = daysToPlay();
		int ship = chooseShip(ships);
		System.out.println("\nYour ships' details are:\n"+ ships.get(ship));
		trader.setName(name);
		trader.setDaysToPlay(days);
		trader.setCurrentShip(ships.get(ship));
		trader.getCurretShip().setShipOwner(trader);
		trader.setHomeIsland(Island.get(homeIslandNo));
		
		start(Island, ships);
		
		if (getStartGame().equalsIgnoreCase("Y")) {
			playTheMainGame(Island, trader);
		}else {
			System.out.println("Thank you for Playing");
		}
	}
	
	
	/**
	 * this method prompts user to add name, and check if it's valid using the {@link InputValidation} class.
	 * @param island Array of all the Island
	 * @param ship Array of all the ships
	 */
	public String setName() {
		System.out.println("Welcome To The Game: TRADER ISLAND\n");
		System.out.println("Please choose your \"Trader\" name:");
		
		name = scanner.next();
		
		while(!(check.validName(name))) {
			System.out.println("\nTrader name must be between 3-15 alphabetical characters:");
			name = scanner.next();
		}
		return name;
	}
	
	
	/**
	 * Prompts user to add number of days he'd like to play the game
	 * @return string "You have {number of days} days today sail."
	 */
	public int daysToPlay() {
		String tempInput;
		System.out.println("\nPlease choose how many days would you like to play the game.\n"
				+ "You can choose between 20 to 50 days:");
		tempInput = scanner.next();
		
		while(!(check.validNumberInRange(tempInput, 20, 50))) {
				System.out.println("\nYou MUST choose between 20 to 50 days.");
				tempInput = scanner.next();
		}
		days = Integer.parseInt(tempInput);
		return days;
	}
	
	
	/**
	 * lists all the ships for User to choose from
	 * @param ships An Array containing all the ships
	 * @return a chosen ship
	 */
	public int chooseShip(ArrayList<Ship> ships){
		String tempShip;
		System.out.println("\nPlease choose a ship from the following:");
		for(int number=0; number< ships.size(); number++) {
			System.out.println(number+1 +":\n" +ships.get(number));	
		}

		tempShip = scanner.next();
		while(!(check.validNumberInRange(tempShip, 1, 4))) {
			System.out.println("\nPlease choose from the available options:");
			tempShip = scanner.next();
		}
		shipNo = Integer.parseInt(tempShip) - 1;
		return shipNo;
		//scanner.close();
	}
	
	
	/**
	 * this method set's up the trader and sets startGame to "Y" or "Q"
	 * @param island an Array of all the Island
	 * @param ship an Array of all the Ships
	 */
	public void start(ArrayList<Island> island, ArrayList<Ship> ship) {
		System.out.println("\nPlease enter \"Y\" to begin the game and \"Q\" to exit the game:");
		String input = scanner.next();
		while(input.equalsIgnoreCase("Y") == false) {
			if (input.equalsIgnoreCase("Q")) {
				break;
			}
			System.out.println("\nPlease enter \"Y\" to begin the game and \"Q\" to exit the game:");
			input = scanner.next();
		}
		if (input.equalsIgnoreCase("Y")) {
			System.out.println("\nDear " + name+ ", Welcome to the " + trader.gethomeIsland().getName()
							+ "\nThis " + trader.gethomeIsland().getIslandDescription()
							+ "\nYour ship is "+ trader.getCurretShip().getName() + " and You have " 
							+ days + " days to travel." );
			startGame = input;
		}else {
			startGame = input;
		}
	}
	
	/**
	 * starts the game
	 * @param island an Array of all the Island
	 * @param trader an Array of all the ships
	 */
	public void playTheMainGame(ArrayList<Island> island, Trader trader) {
		scanTransactionOrTravel(island, trader);
		
	}
	
	
	public void viewProfile(ArrayList<Island> Island, Trader trader) {
		System.out.println("\n"+trader.getProfile());
		System.out.println("1: To view all bought Item\n"
						+ "2: To view all sold Item\n"
						+ "3: To view ship Status\n"
						+ "4: To view all ship upgrades\n"
						+ "5: To Repair you ship\n"
						+ "E: To exit the inventory");
		String input = scanner.next();
		while (!input.equalsIgnoreCase("E")) {
			if (input.equalsIgnoreCase("1")) {
				String result = trader.getBoughtItems();
				if (result.length() > 0) {
					System.out.println(result);
				}else {
					System.out.println("You have not bought anything yet.\n");
				}
			}else if (input.equalsIgnoreCase("2")) {
				String result = trader.getSoldItems();
				if (result.length() > 0) {
					System.out.println(result);
				}else {
					System.out.println("You have not sold anything yet.\n");
				}
			}else if (input.equalsIgnoreCase("3")) {
				System.out.println(trader.getCurretShip().shipStatus());
			}else if (input.equalsIgnoreCase("4")) {
				if (trader.getCurretShip().showUpgrades().length() > 0) {
					System.out.println(trader.getCurretShip().showUpgrades());
				}else {
					System.out.println("You have no upgrades are installed.\n");
				}
			}else if (input.equalsIgnoreCase("5")) {
				System.out.println(trader.getCurretShip().repairShip(trader));
			}
			
			if (check.validNumberInRange(input, 1, 5)) {
				System.out.println("Please choose from the options below:\n"
						+ "1: To view all bought Item\n"
						+ "2: To view all sold Item\n"
						+ "3: To view ship Status\n"
						+ "4: To view all ship upgrades\n"
						+ "5: To Repair you ship\n"
						+ "E: To exit the inventory");
			}else {
				System.out.println("Please choose from the options below:\n");
			}
			input = scanner.next();
		}
		scanTransactionOrTravel(Island, trader);
	}
	
	
	
	
	
	/**
	 * 
	 * @param island
	 */
	public void getItemsByCategory(ArrayList<Island> island, Trader trader) {
		System.out.println("Welcome to "+ trader.getCurrentIsland().getStore().getName()+" store.");
		String[] category = trader.getCurrentIsland().getStore().getAllCategories();
		
		System.out.println("Please choose from the available categories or enter \"E\" for exting the store:");
		for(int number =0;  number < category.length; number ++)  {
			System.out.println(number+1 +": " + category[number]);
		}
		
		categoryNumber = scanner.next();
		while(!(check.validNumberInRange(categoryNumber, 1, 4))) {
			if (categoryNumber.equalsIgnoreCase("E")) {
				System.out.println("You're now exiting the store.");
				scanTransactionOrTravel(island, trader);
				break;
			}else {
				System.out.println("Please choose from the available options:");
				categoryNumber = scanner.next();
			}
		}
		
		if (!(categoryNumber.equalsIgnoreCase("E"))) {
			selectedCategoryItems = trader.getCurrentIsland().getStore().getProducts(category[Integer.parseInt(categoryNumber) -1]);
			int count = 1;
			System.out.println("\nPlease choose an item to purchase from the store or enter \"E\" to exit the store:");
			for(Item item: selectedCategoryItems) {
				System.out.println(count + ": " + item.storesSaleDispaly());
				count+=1;
			}
		}
	}
	
	
	
	/**
	 * user can choose whether to make a transaction or travel 
	 * if user chooses to travel then destinationIsland method is called 
	 * if user chooses to make another transaction scanTransactionType is called.
	 * @param island
	 * @param trader
	 */
	public void scanTransactionOrTravel(ArrayList<Island> island, Trader trader) {
		System.out.println(
				"\nDear "+trader.getName()+" you can go to "+trader.getCurrentIsland().getStore().getName()+" Store or travel to other Island."+
				"\n1: Travel to other Island\n"
				+ "2: Visit "+trader.getCurrentIsland().getStore().getName()+" Store on "+ trader.getCurrentIsland().getName() + "\n"
				+ "P: To view your profile\n"
				+ "Q: To end the game ");

		String input = scanner.next();
		while(!(check.validNumberInRange(input, 1, 2))) {
			if(input.equalsIgnoreCase("Q")) {
				System.out.println(gameOver(trader, ""));
				break;
			}else if(input.equalsIgnoreCase("P")){
				break;
			}
			else{
				System.out.println("Please select from available options:");
				input = scanner.next();
			}
		}
		if(input.equalsIgnoreCase("P")) {
			viewProfile(island, trader);
		}else if (!(input.equalsIgnoreCase("Q"))) {
			if(input.equalsIgnoreCase("1") && days > 2) {
				if(trader.getCurretShip().getDurabilityPercentage() < 100) {
					System.out.println("\nYou have been redirected to your profie\n"
							+ "Please repair your ship before setting sail\n");
					this.viewProfile(island, trader);
				}else {
					destinationIsland(island, trader);
				}
			}
			else {
				scanTransactionType(island, trader);
			}
		}
	}
	
	
	/**
	 * user can purchase or sell. 
	 * @param island
	 * @param trader
	 */
	public void scanTransactionType(ArrayList<Island> island, Trader trader) {
		
		System.out.println("1: To make purchase\n2: To sell an item\nE: To exit the store");

		String input = scanner.next();
		while(!(check.validNumberInRange(input, 1, 2))) {
			if (input.equalsIgnoreCase("E")) {
				System.out.println("You're now exiting the store.");
				scanTransactionOrTravel(island, trader);
				break;
			}else {
				System.out.println("1: To make purchase\n2: To sell an item\nE: To exit the store");
				input = scanner.next();
			}
		}
		if (!(input.equalsIgnoreCase("E"))) {
			if(input.equalsIgnoreCase("1")) {
				purchase(island, trader);
			}
			else {
				if (trader.allBoughtItems().size() == 0) {
					System.out.println("You inventory is Empty.\n");
					System.out.println("You're now exiting the store.");
					scanTransactionOrTravel(island, trader);
					
				}else {
					sell(island, trader);
				}
			}
		}
	}
	
	
	
	/**
	 * trader make a purchase from a store where his ship is docked.
	 * @param island
	 * @param trader
	 */
	public void purchase(ArrayList<Island> island, Trader trader) {
		getItemsByCategory(island, trader);//This method prints all the available categories and Item from the selected category
		transactionProcess(island, trader);
	}
	
	
	public void transactionProcess(ArrayList<Island> island, Trader trader) {
		System.out.println("\nPlease choose an item you'd like to purchase or enter \"E\" to exit the store:");
		String input = scanner.next();
		while(!(check.validNumberInRange(input, 1, selectedCategoryItems.size()))) {
			if (input.equalsIgnoreCase("E")) {
				System.out.println("You are going back to the menu\n");
				scanTransactionType(island, trader);
				break;
			}else {
				System.out.println("Please choose from the available options or enter \"E\" to cancel the transaction");
				input = scanner.next();
			}
		}
		if (!(input.equalsIgnoreCase("E"))){
			itemNoToPurchase = Integer.parseInt(input);
			System.out.println("Please enter the quantity you'd like to purchase or \"E\" to cancel the transaction");
			int maxQuantity = selectedCategoryItems.get(itemNoToPurchase-1).getQuantityAvailble();
			String quantity = scanner.next();
			while(!(check.validNumberInRange(quantity, 0, maxQuantity))) {
				if (quantity.equalsIgnoreCase("E")) {
					System.out.println("Transaction cancelled\n");
					break;
				}else {
					System.out.println("Please eneter a valid quantity or \"E\" to cancel the transaction");
					quantity = scanner.next();
				}
			}
			if (!(quantity.equalsIgnoreCase("E"))) {
				Item item = selectedCategoryItems.get(itemNoToPurchase -1);
				trader.purchase(item , Integer.parseInt(quantity));
				
				Item boughtItem = trader.findItem(item.getProductName());
				if (boughtItem != null) {
					System.out.println("Inventory is updated:");
					System.out.println(boughtItem.buyDetails());
				}
				
				NumberFormat currency = NumberFormat.getCurrencyInstance();
				System.out.println("Remaining coins: "+ currency.format(trader.getCoins())
								 + "\t Remaining cargo capacity: "+trader.getCurretShip().getAvailableStorage()+"kg\n");
			}
		}
			transactionProcess(island, trader);
	}
	
	
	/**
	 * Trader sell item to a store in current island.
	 * @param island
	 * @param trader
	 */
	public void sell(ArrayList<Island> island, Trader trader) {
		System.out.println(trader.getCurrentIsland().getStore().getName()+" Stores' buy prices:");
		for (Item product: trader.allBoughtItems()) {
			System.out.println(trader.getCurrentIsland().getStore().findProduct(product));
		}
		System.out.println("\nChoose an item you'd like to sell or enter \"E\" to exit the store");
		int count = 0; 
		for(Item item: trader.allBoughtItems()) {
			count ++;
			System.out.println(count + ": " + item.buyDetails());
			
		}
		
		String itemNo = scanner.next();
		
		while(!(check.validNumberInRange(itemNo, 0, trader.allBoughtItems().size()))) {
			if (itemNo.equalsIgnoreCase("E")) {
				System.out.println("You are going back to the menu\n");
				scanTransactionType(island, trader);
				break;
			}else {
				System.out.println("Please enter a valid number");
				itemNo = scanner.next();
			}
		}
		
		if (!(itemNo.equalsIgnoreCase("E"))) {
			int itemNoToSell = Integer.parseInt(itemNo);
			Item itemToSell = trader.allBoughtItems().get(itemNoToSell-1);
			
			if (itemToSell.getQuantityAvailble() > 0) {
				System.out.println("Please enter the quantity you want to sell");
				String quantity = scanner.next();
				int maxQuantity = itemToSell.getQuantityAvailble();
				while(!(check.validNumberInRange(quantity, 1, maxQuantity))) {
					if (quantity.equalsIgnoreCase("E")) {
						System.out.println("Transaction Cancelled");
						scanTransactionType(island, trader);
						break;
					}else {
						System.out.println("Please enter a valid number");
						quantity = scanner.next();
					}
				}
				
				trader.sellItem(itemToSell, Integer.parseInt(quantity));
				System.out.println("Item sold:\n" + trader.getSoldItems());
				System.out.println(String.format("You have %.2f coins remained.", trader.getCoins()));
				System.out.println(String.format("Profit made so far: %.2f coins\n", trader.getProfit()));
			}else {
				System.out.println(String.format("You don't have enough %s to make a sale", itemToSell.getProductName()));
			}
			scanTransactionType(island, trader);
		}
	}
	
	
	
	
	public void destinationIsland(ArrayList<Island> Island, Trader trader) {
		System.out.println("You can travel/sail to 4 other Island from "+ Island.get(currentIslandNo).getName()+ " to buy and sell Item.\n"
				+ "Choose one of the following Island to travel to:");
		for (int count=0; count < 5; count++) {
			if(Island.get(count).getName().equalsIgnoreCase(Island.get(currentIslandNo).getName())) {
				System.out.println(count + ": " + Island.get(count).getName() + " (Current Island)");
			}else {
				System.out.println(count + ": " + Island.get(count).getName());
			}
		}
		System.out.println("E: To cancel the route");
		String islandNo = scanner.next();
		
		while(!(check.validNumberInRange(islandNo, 0, 4)) || Integer.parseInt(islandNo) == currentIslandNo) {
			if (islandNo.equalsIgnoreCase("E")) {
				System.out.println("Route cancelled.");
				scanTransactionOrTravel(Island, trader);
			}else {
				System.out.println("Your current island is " + Island.get(currentIslandNo).getName() + ". Please choose a different island");
				islandNo = scanner.next();
			}
		}
		if(!(islandNo.equalsIgnoreCase("E"))) {
			chooseRoute(currentIslandNo, Integer.parseInt(islandNo), trader, Island);
		}
	}
	
	
	
	public void printallpossilberoutes(int source, int destination, ArrayList<Island> island, ArrayList<ArrayList<Integer>> routes){
		double travelDuration = 0.0;
		System.out.println("Source: " + trader.getCurrentIsland().getName() + " Destination: " + island.get(destination).getName()+ "\n");
		for (int count=0; count < routes.size();count++) {
			ArrayList<Integer> subRoute = (ArrayList<Integer>)routes.get(count);
			String islandNameOnRoute = island.get(source).getName();
			for (int index: subRoute) {
				if (index != source) {
					islandNameOnRoute += " -> " + island.get(index).getName();
				}
			}
			travelDuration = (subRoute.size() * Island.DISTANCE) / trader.getCurretShip().getSpeed();
			if (subRoute.size() == 2) {
				islandNameOnRoute += "\n(Morelikely to get attack by Priates and bad-weather or sharks)";
			} else if (subRoute.size() == 3) {
				islandNameOnRoute += "\n(likely to get attack by Priates or bad-weather or sharks)";
			} else if (subRoute.size() >= 4) {
				islandNameOnRoute += "\n(likely to encounter no event or maximum of 1 event)";
			}
			System.out.println(count + ": " + islandNameOnRoute);
			System.out.println(String.format("Travel Duration: %.2f Days\n", travelDuration)); 
		}
	}
	
	
	
	public void randomEvents(int size) {
		LoadGameData data = new LoadGameData();
		ArrayList<Event> events = data.checkForEvents(size);
		if (events != null) {
			for (Event event: events) {
				if(trader.getCurretShip().getDurability() > 0) {
					if (event != null) {
						event.attack(trader);
					}else {
						System.out.println("You've completed your journey without encountring any bad event\n");
					}
				}
			}
		}
	}
	
	
	public void chooseRoute(int source, int destination, Trader trader, ArrayList<Island> island) {
		ArrayList<ArrayList<Integer>> routes = route.calculateAllPaths(source, destination);
		printallpossilberoutes(source, destination, island, routes);
		System.out.println("Please choose a route to travel to your destination");
		String chosenRoute = scanner.next();
		while(!(check.validNumberInRange(chosenRoute, 0, routes.size()))) {
			System.out.println("Please enter a valid number.");
			chosenRoute = scanner.next();
		}
		int selected = Integer.parseInt(chosenRoute);
		
		String tempString = "";
		for (int nums: routes.get(selected)) {
			if (tempString.length() > 0) {
				tempString += " -> ";
			}
			tempString += island.get(nums).getName();
		}
		ArrayList<Integer> myRoute = (ArrayList<Integer>) routes.get(Integer.parseInt(chosenRoute));
		double daysToTravel = myRoute.size();
		double wage = trader.getCurretShip().getDailyWage() * daysToTravel;
		System.out.println("\nYour route to the "+island.get(myRoute.size()-1).getName()+" is:\n" + tempString +"\n");
		if ( myRoute.size() <= days && wage <= trader.getCoins()) {
			randomEvents(myRoute.size());
			
			if(trader.getCurretShip().getDurability() > 0) {
				days -= daysToTravel;
				trader.setDaysToPlay(days);
				trader.addTotalTravelCost(wage);
				System.out.println(String.format("you have %d remaining days to travel\n"
												+ "Wage paid for current trip is %.2f\n"
												+ "Remaining coins: %.2f\n", days, wage, trader.getCoins()));
				currentIslandNo = destination;
				trader.setCurrentIsland(island.get(currentIslandNo));
				System.out.println("Welcome to "+trader.getCurrentIsland().getName()+"\nThis " + trader.getCurrentIsland().getIslandDescription());
				scanTransactionOrTravel(island, trader);
				}
			
			else {
				System.out.println(gameOver(trader, "You've lost your ship"));
			}
			
		}else {
			System.out.println(gameOver(trader, "You don't have enough days/coins to set sail to your destination"));
		}
	}
	
		
	public String gameOver(Trader trader, String reason) {
		String bought;
		String sold;
		if (trader.allBoughtItems().size() == 0) {
			bought = "0";
		}else {
			bought = trader.getBoughtItems();
		}
		
		if (trader.allSoldItems().size() == 0) {
			sold = "0";
		}else {
			sold = trader.getSoldItems();
		}
		
		double outcome = trader.getProfit()-trader.getTotalTravelCost();
		
		String string = String.format("\n\nGame Over\n"
									+ "\n%s" 
									+ "\nItems bought:\n%s\nItems sold:\n%s"
									+ "\nRemaining coin: %.2f \t\t Remaining days to play: %.2f"
									+ "\nProfit from selling goods: %.2f - traveling cost: %.2f"
									+ "Total Profit: %.2f"
									+ "\n", reason, bought, sold, trader.getCoins(), trader.getDays(),
										trader.getProfit(), trader.getTotalTravelCost(), outcome);
		scanner.close();
		return string;
	}




	
	
}



