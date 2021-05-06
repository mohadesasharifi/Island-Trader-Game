package Main;

import Validations.InputValidation;

import java.text.NumberFormat;
import java.util.*;
import elements.*;

/**
 * Command-line operations of the Game Island Trader
 * 
 * @author Mohadesa Sharifi
 *
 */

public class CommandLines {
	// Instance of class InputValidation with variable check to check all the user inputs
	InputValidation check = new InputValidation();
	Route route = new Route();
	// String variable for User name
	private String name;
	
	// Integer variable for number of days user wants to play the game
	private int days;
	
	// Integer variable for Users' ship number
	private int shipNo = 0;
	
	// Integer variable for user's home-island
	private int homeIslandNo = 0;
	
	// String variable for ?
	private String categoryNumber;
	
	// Array List for items ?
	private ArrayList<Items> selectedCategoryItems;
	
	// Integer variable for number of item purchased
	private int itemNoToPurchase;
	
	// String variable to start or end the game
	private String startGame;
	
	
	// Integer variable for number of current Island
	private int currentIslandNo = 0;
	/**
	 * Constructor for class Command lines
	 */
	public CommandLines() {};
	
	
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
	 * this method returns Users home-island
	 * @return integer - islands number
	 */
	public int getHomeIslandNo() {
		return homeIslandNo;
	}
	
	
	public String getStartGame() {
		return startGame;
	}
	
	
	/**
	 * start up for the game by asking user to add name.
	 * @param island Array of all the islands
	 * @param ship Array of all the ships
	 */
	public void setGame(ArrayList<Islands> island, ArrayList<Ship> ship) {
		setName(island, ship);
	}
	
	/**
	 * this method prompts user to add name, and check if it's valid using the {@link InputValidation} class.
	 * @param island Array of all the islands
	 * @param ship Array of all the ships
	 */
	public void setName(ArrayList<Islands> island, ArrayList<Ship> ship) {
		System.out.println("Please choose a Trader name");
		Scanner scanner = new Scanner(System.in);
		name = scanner.next();
		
		while(!(check.validName(name))) {
			System.out.println("\nUsername must be between 3-12 alphabetical characters:");
			name = scanner.next();
		}    	
		daysToPlay(island, ship);
		//scanner.close();
	}
	
	
	/**
	 * Prompts user to add number of days he'd like to play the game
	 * @return string "You have {number of days} days today sail."
	 */
	public void daysToPlay(ArrayList<Islands> island, ArrayList<Ship> ship) {
		String tempInput;
		System.out.println("\nPlease choose how many days would you like to play the game.\n"
				+ "It should be between 20 to 50 days");
		Scanner scanner = new Scanner(System.in);
		tempInput = scanner.next();
		
		while(!(check.validNumberInRange(tempInput, 20, 50))) {
				System.out.println("\nYour input should a number be between 20 to 50 days.");
				tempInput = scanner.next();
		}
		days = Integer.parseInt(tempInput);
		chooseShip(island, ship);
		//scanner.close();
	}
	
	
	/**
	 * lists all the ships for User to choose from
	 * @param ships An Array containing all the ships
	 * @return a chosen ship
	 */
	public void chooseShip(ArrayList<Islands> island, ArrayList<Ship> ships){
		String tempShip;
		System.out.println("\nPlease choose a ship from the following:");
		for(int number=0; number< ships.size(); number++) {
			System.out.println(number+1 +": " +ships.get(number));	
		}
		System.out.println("\nPlease enter a ship number you'd like to choose:");
		Scanner scanner = new Scanner(System.in);
		tempShip = scanner.next();
		
		while(!(check.validNumberInRange(tempShip, 1, 4))) {
			System.out.println("\nPlease choose from the available options:");
			tempShip = scanner.next();
		}
		shipNo = Integer.parseInt(tempShip) - 1;
		start(island, ships);
		//scanner.close();
	}
	
	/**
	 * homeIsland is chosen randomly from an Array of 5 Islands
	 * @param an Array containing all the islands
	 * @return returns an Island
	 */
	public String homeIsland(ArrayList<Islands> allIslands) {
		int number = (int)Math.floor(Math.random()*(4-0+1)+0);
		homeIslandNo = number;
		currentIslandNo = homeIslandNo;
		return allIslands.get(homeIslandNo).getName();
	}

	
	/**
	 * this method set's up the trader and sets startGame to "Y" or "Q"
	 * @param island an Array of all the islands
	 * @param ship an Array of all the Ships
	 */
	public void start(ArrayList<Islands> island, ArrayList<Ship> ship) {
		System.out.println("\nPlease enter Y to begin the game and Q to exit the game:");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		while(input.equalsIgnoreCase("Y") == false) {
			if (input.equalsIgnoreCase("Q")) {
				break;
			}
			System.out.println("\nPlease enter Y to begin the game and Q to exit the game:");
			input = scanner.next();
		}
		if (input.equalsIgnoreCase("Y")) {
			System.out.println("\nDear " + name + "\n" + "Welcome to the " + homeIsland(island) + "\n"+ ship.get(shipNo) + "\nYou have " + days + " days to travel." );
			startGame = input;
		}else {
			startGame = input;
		}
		//scanner.close();
	}
	
	/**
	 * starts the game
	 * @param island an Array of all the islands
	 * @param trader 
	 */
	public void playTheMainGame(ArrayList<Islands> island, Trader trader) {
		visitStore(island, trader);
		
	}
	
	/**
	 * getItemsByCategory is a support method called by visit store.
	 * getItemsByCategory lists items in a store by category types
	 * there are 4 categories: 1.vege 2.fruit 3.travel 4.upgrades
	 * @param island
	 */
	public void getItemsByCategory(ArrayList<Islands> island) {
		
		String[] category = island.get(currentIslandNo).getStore().getAllCategories();
		for(int number =0;  number < category.length; number ++)  {
			System.out.println("Please choose a category from bellow to see available items.");
			System.out.println(number+1 +": " + category[number]);
		}
		Scanner scanner = new Scanner(System.in);
		categoryNumber = scanner.next();
		while(!(check.validNumberInRange(categoryNumber, 1, 4))) {
			System.out.println("Please choose from the available options:");
			categoryNumber = scanner.next();
		}
		selectedCategoryItems = island.get(currentIslandNo).getStore().getProducts(category[Integer.parseInt(categoryNumber) -1]);
		int count = 1;
		for(Items item: selectedCategoryItems) {
			System.out.println(count + ": " + item);
			count+=1;
		}
		//scanner.close();
	}
	
	/**
	 * Trader can visit the current island's store to buy or sell items. 
	 * trader can do as many transaction as he can without exiting the store.
	 * visitStore calls purchase if user want to purchase or desinationIsalnd if user wants to exit the store and travel. 
	 * @param island
	 * @param trader
	 */
	public void visitStore(ArrayList<Islands> island, Trader trader) {
		String input = "";
		trader.setHomeIsland(island.get(homeIslandNo));
		System.out.println(name + ", You can visit " +  island.get(currentIslandNo).getStore().getName() +
				" store to buy and sell goods or move to another island. "+
				"\nEnter Yes to go to store or Enter No to go to another Island");
		Scanner scanner = new Scanner(System.in);
		input = scanner.next();
		while(check.inputMatch(input, "yes", "no") == false) {
			System.out.println("Please enter Yes or No");
			input = scanner.next();
		}
		
		if (input.equalsIgnoreCase("YES")) {
			purchase1(island, trader);
		}
		else {
			if (days > 2) {
				destinationIsland(island, trader);
			}
		}
		//scanner.close();		
	}
	
	/**
	 * user can choose whether to make a transaction or travel 
	 * if user chooses to travel then destinationIsland method is called 
	 * if user chooses to make another transaction scanTransactionType is called.
	 * @param island
	 * @param trader
	 */
	public void scanTransactionOrTravel(ArrayList<Islands> island, Trader trader) {
		System.out.println(
				"\nDear trader you can sell your items or make another puprchase."+
				"\nYou can go to some other islands for a better deal."+
				"\nIf you want to travel enter 1. \nIf you want to make another transaction enter 2" 
				);

		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		while(!(check.validNumberInRange(input, 1, 2))) {
			System.out.println("Please enter 1 or 2");
			input = scanner.next();
		}
		if(input.equalsIgnoreCase("1") && days > 2) {
			destinationIsland(island, trader);
		}
		else {
			scanTransactionType(island, trader);
		}		
	}
	
	
	/**
	 * user can purchase or sell. 
	 * @param island
	 * @param trader
	 */
	public void scanTransactionType(ArrayList<Islands> island, Trader trader) {
		
		System.out.println("Do you want to make another purchase or sell your items"+
				"\nFor purchasing items enter 1 \nfor selling items enter 2"
				);

		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		while(!(check.validNumberInRange(input, 1, 2))) {
			System.out.println("Please enter 1 or 2");
			input = scanner.next();
		}
		if(input.equalsIgnoreCase("1")) {
			purchase1(island, trader);
		}
		else {
			sell(island, trader);
		}
	}
	
	
	/**
	 * trader make a purchase from a store where his ship is docked.
	 * @param island
	 * @param trader
	 */
	public void purchase1(ArrayList<Islands> island, Trader trader) {
		getItemsByCategory(island);
		System.out.println("Please choose an item to purchase. Enter a number");
		Scanner scanner = new Scanner(System.in);
		
		String input = scanner.next();
		while(!(check.validNumberInRange(input, 1, selectedCategoryItems.size()))) {
			System.out.println("Please eneter a valid number.");
			input = scanner.next();
		}
		itemNoToPurchase = Integer.parseInt(input);
		System.out.println("Please enter the quantity you want to purchase");
		Scanner scanner2 = new Scanner(System.in);
		String quantity = scanner2.next();
		while(!(check.validNumberInRange(quantity, 1, selectedCategoryItems.get(itemNoToPurchase -1).getQuantityAvailble()))) {
			System.out.println("Please eneter a valid number.");
			input = scanner.next();
		}
		Items item = selectedCategoryItems.get(itemNoToPurchase -1);
		trader.purchase(item , Integer.parseInt(quantity));
		System.out.println(trader.allBoughtItems());
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		System.out.println("You have "+ currency.format(trader.getCoins())+ " coins remained.");
		
		scanTransactionOrTravel(island, trader);	
	}
	/**
	 * Trader sell item to a store in current island.
	 * @param island
	 * @param trader
	 */
	public void sell(ArrayList<Islands> island, Trader trader) {
		System.out.println("Choose items from list below to sell");
		int count = 0; 
		for(Items item: trader.getBoughtItems1()) {
			count ++;
			System.out.println(count + ": " + item);
		}
		
		Scanner scanner = new Scanner(System.in);
		String itemNo = scanner.next();
		
		while(!(check.validNumberInRange(itemNo, 0, trader.getBoughtItems1().size()))) {
			System.out.println("Please enter a valid number");
			itemNo = scanner.next();
		}
		
		int itemNoToSell = Integer.parseInt(itemNo);
		System.out.println("Please enter the quantity you want to sell");
		Scanner scanner2 = new Scanner(System.in);
		String quantity = scanner2.next();
		
		while(!(check.validNumberInRange(quantity, 1, trader.getBoughtItems1().get(itemNoToSell -1).getQuantityAvailble()))) {
			System.out.println("Please enter a valid number");
			quantity = scanner2.next();
		}
		
		trader.sellItem(trader.getBoughtItems1().get(itemNoToSell -1), Integer.parseInt(quantity));
		System.out.println("Items sold: " + trader.getSoldItems());
		System.out.println("You have "+ trader.getCoins()+ " coins remained.");
		
		scanTransactionOrTravel(island, trader);
	}
	
	public void destinationIsland(ArrayList<Islands> islands, Trader trader) {
		System.out.println("There are 4 islands you can travel from your current Island "+ islands.get(currentIslandNo).getName()+ " to sell and buy items.\n"
				+ "Choose one of the following islands to travel.");
		for (int count=0; count < 5; count++) {
			if(islands.get(count) != islands.get(currentIslandNo)) {
				System.out.println(count + ": " + islands.get(count).getName());
			}	
		}
		Scanner scanner = new Scanner(System.in);
		String islandNo = scanner.next();
//		&& !(check.notHomeIsland(currentIslandNo, islandNo))
		while((Integer.parseInt(islandNo) == currentIslandNo) || !(check.validNumberInRange(islandNo, 0, 4))) {
			System.out.println("Please enter a number between 0 to 4. Do not enter your homeIsland number");
			islandNo = scanner.next();
		}
		
		chooseRoute(currentIslandNo, Integer.parseInt(islandNo), trader, islands);
		//scanner.close();
			
	}
	
	public void chooseRoute(int source, int destination, Trader trader, ArrayList<Islands> island) {
		
		System.out.println("Source: " + source + "Destination: " + destination);
		ArrayList<ArrayList<Integer>> routes = route.calculateAllPaths(source, destination);
		for (int count=0; count < routes.size();count++) {
			ArrayList<Integer> route = (ArrayList<Integer>)routes.get(count);
				System.out.println(count + ": " + routes.get(count));
		}
		System.out.println("Please choose a route to travel to your destination");
		Scanner scanner = new Scanner(System.in);
		String chosenRoute = scanner.next();
		while(!(check.validNumberInRange(chosenRoute, 0, routes.size()))) {
			System.out.println("Please enter a valid number.");
			chosenRoute = scanner.next();
		}
		ArrayList<Integer> myRoute = (ArrayList<Integer>) routes.get(Integer.parseInt(chosenRoute));
		LoadGameData data = new LoadGameData();
		if ( myRoute.size() <= days) {
			ArrayList<Event> randomeEvent = data.checkForEvents(myRoute.size());
			if (randomeEvent != null) {
				for (Event event: randomeEvent) {
					event.attack(trader);
				}
			}
			System.out.println("Remaining Durability: " + trader.getCurretShip().getDurability());
			if(trader.getCurretShip().getDurability() > 0) {
				System.out.println("Your route to the destination island is " + route);
				days -= myRoute.size();
				trader.setDaysToPlay(days);
				System.out.println("you have " + days + " remaining days to travel.");
				currentIslandNo = destination;
				trader.setCurrentIsland(island.get(currentIslandNo));
				scanTransactionOrTravel(island, trader);
			}
			
			else {
				System.out.println(gameOver(trader));
			}
			
		}
		else {
			System.out.println(gameOver(trader));
		}
		//scanner.close();
	}
	
		
	public String gameOver(Trader trader) {
		String string = "Game Over" + "\nItems bought: " + trader.getBoughtItems() + "\nItems sold: " + trader.getSoldItems()
		+ "\nRemaining coin: " + trader.getCoins();
		return string;
	}
	
	
}



