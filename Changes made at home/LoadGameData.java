package Main;

import java.util.*;
import java.io.*;
import elements.*;

public class LoadGameData {
	
	private String READSTORES = "bin/allStores.csv";
	private String READISLANDS = "bin/allIslands.csv";
	private String READSHIPS = "bin/allShipsDetails.csv";
	private String READPRODUCTS = "bin/Products.csv";
	
	private ArrayList<Ship> allShips = new ArrayList<Ship>();
	private ArrayList<Islands> allIslands = new ArrayList<Islands>();
	private ArrayList<Store> allStores = new ArrayList<Store>();
	
	public LoadGameData() {};
	
	
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
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(READSTORES));
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				Store newStore = new Store(row[0]);
				allStores.add(newStore);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	
	
	public static void main(String[] args){
		
		LoadGameData data = new LoadGameData();
		CommandLines command = new CommandLines();
		
//		for (Islands island: data.createAllIslands()) {
//			System.out.println(island);
//			Store test1 = island.getStore();
//			System.out.println(test1.getProducts());
//		}
		
//		for (Ship ships: data.createAllShips()) {
//			System.out.println(ships);
//		}
//		
		System.out.println(command.chooseName());
		System.out.println(command.daysToPlay());
		System.out.println(command.chooseShip(data.createAllShips()));
		System.out.println(command.homeIsland(data.createAllIslands()));
		command.start(data.createAllIslands(), data.allShips);
		command.visitStore(data.createAllIslands(), command.getHomeIslandNo());
		Trader trader = data.createTrader(command.geTraderName(), command.getDaystoPlayGame(), command.getShipNo(), command.getHomeIslandNo());
		command.purchase1(trader);
		ArrayList<Islands> islands = data.createAllIslands(); 
		command.viewIsland1(islands);
		
		
	}
	
}
