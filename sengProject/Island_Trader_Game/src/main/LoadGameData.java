package main;

import java.util.*;
import java.io.*;
import models.*;
/**
 * this model helps the game load all the data, like ships, stores, islands, routes and events.
 * @author Zahid Khan
 *
 */


public class LoadGameData {
	// Constants for Island descriptions
	private String[] DESCRIPTIONS = new String[] 
			{"This island is famous for it's Vegetables",
			 "This island is famous for it's fruits",
			 "This island is famous for manufacturing",
			 "This island is famous for it's beaches",
			 "This island is famous for it's green meadows and high mountains"};
	
	// this constant has names for all the stores
	private String[] STORENAMES = new String[] {"Coin Save", "The Mega",
												"River Side", "The New Wave",
												"Save Mart"};
	// this constant has names for all the islands
	private String[] ISLANDNAMES = new String[] {"Fantasy Island", "Mysterious Island",
												 "Treasure Island", "Moonrise Kingdom",
												 "Green Island"};
	
	// Array which will contain all the ships
	private ArrayList<Ship> allShips = new ArrayList<Ship>();
	
	// Array which will contain all the islands
	private ArrayList<Island> allIslands = new ArrayList<Island>();
	
	// Array which will contain all the stores 
	private ArrayList<Store> allStores = new ArrayList<Store>();
	
	// Array which will contain all the Events - bad or good
	private ArrayList<Event> events = new ArrayList<Event>();
	
	private GenerateProductList products;
	
	/**
	 * constructor for Load Game Data
	 */
	public LoadGameData() {
		products = new GenerateProductList();
	};
	
	/**
	 * this method reads all the products form a file and allocates all the products to the all the stores
	 */
	public void allocateProducts() {
		ArrayList<String> productByStore = products.getProducts();
		
		for (int i=0; i < productByStore.size(); i++) {
			for (String line : productByStore.get(i).split("\n")) {
				String[] row = line.split(",");
				Item newItem = new Item(row[0].strip(),
										  Double.parseDouble(row[1].strip()),
										  Double.parseDouble(row[2].strip()),
										  Integer.parseInt(row[3].strip()),
										  row[4].strip());
				newItem.setUnits(row[5]);
				allStores.get(i).addProducts(newItem);
			}
		}
	}
	
	
	/**
	 * this method creates all the store by reading the names of stores form a Constant Array
	 * @param trader
	 * @return an Array containing all the stores
	 */
	public ArrayList<Store> createAllStores(Trader trader) {
		// this should create all 5 stores which and return an array;
		for (String storeName: STORENAMES) {
			Store newStroe = new Store(storeName, trader);
			allStores.add(newStroe);
		}
		return allStores;
	}
	
	
	/**
	 * this method created all the ships by ready the file containing all the details for ships
	 * @return an Array containing all the ships
	 */
	public ArrayList<Ship> createAllShips() {
		String[] ship1 = "Loyal Explorer, 400, 20, 630, 130".split(",");
		String[] ship2 = "Emperor of the Sea, 450, 25, 749, 160".split(",");
		String[] ship3 = "Ocean Warrior, 350, 15, 529, 100".split(",");
		String[] ship4 = "Gladiator of the Sea, 300, 10, 449, 100".split(",");
		
		ArrayList<String[]> readShip = new ArrayList<String[]>();
		readShip.add(ship1);
		readShip.add(ship2);
		readShip.add(ship3);
		readShip.add(ship4);
		
		for (String[] row : readShip) {
			Ship newShip = new Ship(row[0].strip(), Double.parseDouble(row[1].strip()),
									Integer.parseInt(row[2].strip()),
									Double.parseDouble(row[3].strip()),
									Double.parseDouble(row[4].strip()));
			allShips.add(newShip);
		}
		
		return allShips;	
	}
	
	
	/**
	 * This method creates all the islands and stores them in an Array.
	 * Before creating all the islands, it creates all the stores and allocated
	 * each store products it can buy of sell and 1 stores is allocated to each island 
	 * @return an Array containing all the Island
	 */
	public ArrayList<Island> createAllIslands(Trader trader) {
		createAllStores(trader);
		allocateProducts();
		
		int tempIndex = 0;
		for(String name: ISLANDNAMES) {
			Island newIsland = new Island(name, allStores.get(tempIndex));
			newIsland.setIslandDescription(DESCRIPTIONS[tempIndex]);
			allIslands.add(newIsland);
			tempIndex += 1;
		}
		return allIslands;
	}
	
	
	/**
	 * This method creates a User/Trader who plays the game
	 * @param name String of length 3-15 and only alphabets are accepted
	 * @param days Integer between 20 and 50
	 * @param shipNo User/Trader selects the Ship and number is passed to select that ship from Array
	 * @param homeIslandNo Integer is randomly selected to assign User home island
	 * @return an instance of User/Trader
	 */
	public Trader createTrader(String name, int days, int shipNo, int homeIslandNo) {
		Trader trader = new Trader();
		trader.setName(name);
		trader.setDaysToPlay(days);
		trader.setCurrentShip(allShips.get(shipNo));
		trader.setHomeIsland(allIslands.get(homeIslandNo));
		return trader;
	}
	
	
	/**
	 * this method creates an Array of all the possible events when traveling
	 */
	public void createEvents() {
		BadWeather badweather = new BadWeather();
		Shark sharkAttack = new Shark();
		Pirate pirateAttack = new Pirate();
		RescueSailors rescue = new RescueSailors();
		events.add(pirateAttack);
		events.add(badweather);
		events.add(sharkAttack);
		events.add(rescue);
		events.add(null);
		events.add(null);
	}
	
	
	/**
	 * This methods randomly chooses events that can occur when traveling
	 * @param routeLength length of the route is used to decide how many events can happen
	 * @return returns a list of events to occur
	 */
	public ArrayList<Event> checkForEvents(int routeLength) {
		createEvents();
		if (routeLength == 2) {
			ArrayList<Event> subList = new ArrayList<Event>();
			int number = (int)Math.floor(Math.random()*(-1-4+1)+4);
			subList.add(events.get(0));
			subList.add(events.get(number));
			return subList;
		}
		else if(routeLength == 3) {
			int number = (int)Math.floor(Math.random()*(-1-5+1)+5);
			ArrayList<Event> subList = new ArrayList<Event>();
			subList.add(events.get(number));
			return subList;
		}else if (routeLength >= 4) {
			int number = (int)Math.floor(Math.random()*(0-5+1)+5);
			ArrayList<Event> subList = new ArrayList<Event>();
			subList.add(events.get(number));
			return subList;
		}
		return null;
	}
	
}
