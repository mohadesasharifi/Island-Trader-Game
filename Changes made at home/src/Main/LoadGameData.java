package Main;

import java.util.*;
import java.io.*;
import elements.*;

public class LoadGameData {
	
	private String[] STORENAMES = new String[] {"Coin Save", "The Mega",
												"River Side", "The New Wave",
												"Save Mart"}; 
	private String READISLANDS = "bin/allIslands.csv";
	private String READSHIPS = "bin/allShipsDetails.csv";
	private String READPRODUCTS = "bin/Products.csv";
	
	private ArrayList<Ship> allShips = new ArrayList<Ship>();
	private ArrayList<Islands> allIslands = new ArrayList<Islands>();
	private ArrayList<Store> allStores = new ArrayList<Store>();
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public LoadGameData() {};
	
	/**
	 * 
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
					Items newItem = new Items(row[0],
											  Double.parseDouble(row[1]),
											  Double.parseDouble(row[2]),
											  Integer.parseInt(row[3]),
											  row[4]);
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
	
	public ArrayList<Store> createAllStores() {
		// this should create all 5 stores which and return an array;
		for (String storeName: STORENAMES) {
			Store newStroe = new Store(storeName);
			allStores.add(newStroe);
		}
		return allStores;
	}
	
	public ArrayList<Ship> createAllShips() {
		// this should build all 4 ships and assign features;
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(READSHIPS));
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
	
	public ArrayList<Islands> createAllIslands() {
		// this should read data from a file about 5 islands and create all 5 and return them in an array;
		// and use the array from store and assign 1 store to each island;
		createAllStores();
		allocateProducts();
		
		int tempIndex = 0;
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(READISLANDS));
			
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				Islands newIsland = new Islands(row[0], allStores.get(tempIndex));
				allIslands.add(newIsland);
				tempIndex += 1;
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
		return allIslands;
	}
	
	public Trader createTrader(String name, int days, int shipNo, int homeIslandNo) {
		Trader trader = new Trader();
		trader.setName(name);
		trader.setDaysToPlay(days);
		trader.setCurrentShip(allShips.get(shipNo));
		trader.setHomeIsland(allIslands.get(homeIslandNo));
		return trader;
	}
	
	public void createEvents() {
		BadWeather badweather = new BadWeather();
		Shark sharkAttack = new Shark();
		Pirate pirateAttack = new Pirate();
		events.add(pirateAttack);
		events.add(badweather);
		events.add(sharkAttack);
		events.add(null);
	}
	
	
	public ArrayList<Event> checkForEvents(int routeLength) {
		createEvents();
		if (routeLength == 2) {
			ArrayList<Event> subList = new ArrayList<Event>();
			int number = (int)Math.floor(Math.random()*(1-3+1)+3);
			System.out.println("Randome Event Number is " + number);
			subList.add(events.get(0));
			subList.add(events.get(number));
			return subList;
		}
		else if(routeLength == 3) {
			int number = (int)Math.floor(Math.random()*(-1-3+1)+3);
			System.out.println("Randome Event Number is " + number);
			ArrayList<Event> subList = new ArrayList<Event>();
			subList.add(events.get(number));
			return subList;
		}else if (routeLength >= 4) {
			int number = (int)Math.floor(Math.random()*(1-3+1)+3);
			System.out.println("Randome Event Number is " + number);
			ArrayList<Event> subList = new ArrayList<Event>();
			subList.add(events.get(number));
			return subList;
		}
		return null;
	}
	public static void main(String[] args){
		
		LoadGameData data = new LoadGameData();
		CommandLines command = new CommandLines();
		
//		for (Islands island: data.createAllIslands()) {
//			System.out.println(island);
//			Store test1 = island.getStore();
//			System.out.println(test1.getProducts());
//		}
		

		ArrayList<Islands> island = data.createAllIslands(); 
		ArrayList<Ship> ship = data.createAllShips();
//		
//		for (Islands land: island) {
//			System.out.println(land.getStore().getName());
//			System.out.println(land.getStore().getProducts());
//		}
		
		command.setGame(island, ship);
		Trader trader = data.createTrader(command.geTraderName(), command.getDaystoPlayGame(), command.getShipNo(), command.getHomeIslandNo());
		if (command.getStartGame().equalsIgnoreCase("Y")) {
			command.playTheMainGame(island, trader);
		}else {
			System.out.println("Thank you for Playing");
		}
	}	
}
