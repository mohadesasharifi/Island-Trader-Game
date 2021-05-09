package main;

import java.util.*;
import java.io.*;
import models.*;

public class LoadGameData {
	// Constants for Island descriptions
	private String[] DESCRIPTIONS = new String[] 
			{"Island is famous for it's Vegetables",
			 "Island is famous for it's fruits",
			 "Island is famous for manufacturing",
			 "Island is famous for it's beaches",
			 "Island is famous for it's green meadows and high mountains"};
	
	// this constant has names for all the stores
	private String[] STORENAMES = new String[] {"Coin Save", "The Mega",
												"River Side", "The New Wave",
												"Save Mart"};
	// this constant has names for all the islands
	private String[] ISLANDNAMES = new String[] {"Fantasy Island", "Mysterious Island",
												 "Treasure Island", "Moonrise Kingdom",
												 "Green Island"};
	// this constant is reading a file containing all the details about the ships
	private String READSHIPS = "bin/allShipsDetails.csv";
	// this constant is reading a file containing all the details about the products
	private String READPRODUCTS = "bin/Products.csv";
	
	// Array which will contain all the ships
	private ArrayList<Ship> allShips = new ArrayList<Ship>();
	// Array which will contain all the islands
	private ArrayList<Island> allIslands = new ArrayList<Island>();
	// Array which will contain all the stores 
	private ArrayList<Store> allStores = new ArrayList<Store>();
	// Array which will contain all the Events - bad or good
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public LoadGameData() {};
	
	/**
	 * this method reads all the products form a file and allocates all the products to the all the stores
	 */
	public void allocateProducts() {
		int tempIndex = -1;
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(READPRODUCTS));
			
			while ((line = reader.readLine()) != null){
				String[] row = line.split(",");
				
				if (row.length > 1) {
					Item newItem = new Item(row[0],
											  Double.parseDouble(row[1]),
											  Double.parseDouble(row[2]),
											  Integer.parseInt(row[3]),
											  row[4]);
					newItem.setUnits(row[5]);
					allStores.get(tempIndex).addProducts(newItem);
				}else{
					tempIndex += 1;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * this method creates all the store by reading the names of stores form a Constant Array 
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
		// this should build all 4 ships and assign features;
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(READSHIPS), "UTF-8"));
			//System.out.println(reader.readLine());
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				Ship newShip = new Ship(row[0], Double.parseDouble(row[1]),
										Integer.parseInt(row[2]),
										Double.parseDouble(row[3]),
										Double.parseDouble(row[4]));
				allShips.add(newShip);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
