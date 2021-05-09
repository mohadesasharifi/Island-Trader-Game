package models;
import transaction_interface.*;
import java.util.*;

/**
 * This class creates an instance of Trader
 * @author Zahid Khan
 * 21/04/21
 */

public class Trader implements TransactionProcess{
	// String to contain name of trader
	private String name;
	// Double to contain trader's money/coins
	private double currentCoins = 400;
	// ArrayLIst of type Item to contain all the bought items
	private ArrayList<Item> itemsBought = new ArrayList<Item>();
	// ArrayList of type Item to contains all the sold items
	private ArrayList<Item> itemsSold = new ArrayList<Item>();
	// double for total traveling cost
	private double totalTravelCost = 0;
	// double for containing traders profit
	private double profit = 0; 
	// variable type Ship to contain traders' current ship
	private Ship currentShip;
	// variable type Island to contain traders' home island
	private Island homeIsland;
	// variable type Island to update trader's current island as he travels around
	private Island currentIsland = homeIsland;
	// integer to contain the game duration
	private double daysToPlay = 0;
	
	
	/**
	 * this is an empty constructor for Trader
	 */
	public Trader() {};
	
	
	/**
	 * This is also a constructor for Trader
	 * @param tName requires a name as String
	 * @param tCoins requires an initial coins as double
	 * @param tCurrentShip requires a {@link Ship} to sail in
	 * @param tHomeIsland requires a {@link Island} to set as home island
	 */
	public Trader(String tName, double tCoins, Ship tCurrentShip, Island tHomeIsland){
		name = tName;
		currentCoins = tCoins;
		currentShip = tCurrentShip;
		homeIsland =  tHomeIsland;
		currentIsland =  tHomeIsland;
	}
	
	
	/**
	 * this method sets the name of trader
	 * @param tempName accepts name is strings
	 */
	public void setName(String tempName) {
		name = tempName;
	}
	
	
	/**
	 * this method assigns ship to the trader
	 * @param ship of type {@link Ship}
	 */
	public void setCurrentShip(Ship ship) {
		currentShip = ship; 
	}
	
	
	/**
	 * this method returns traders ship
	 * @return type {@link Ship}
	 */
	public Ship getCurretShip() {
		return currentShip;
	}
	
	
	/**
	 * this method sets the duration of the game
	 * @param days of type integer
	 */
	public void setDaysToPlay(int days) {
		daysToPlay = days;
	}
	
	
	/**
	 * this method sets traders home island
	 * @param myHomeIsland of type {@link Island}
	 */
	public void setHomeIsland(Island myHomeIsland) {
		homeIsland = myHomeIsland;
		currentIsland = myHomeIsland;
	}
	
	
	/**
	 * this method updates current island as trader travels 
	 * @param island of type {@link Island}
	 */
	public void setCurrentIsland(Island island) {
		currentIsland = island;
	}
	
	
	/**
	 * this method returns current island
	 * @return type {@link Island}
	 */
	public Island getCurrentIsland() {
		return currentIsland;
	}
	
	
	/**
	 * this method sets current coins
	 * @param coins of type double 
	 */
	public void setCoins(double coins) {
		currentCoins = coins;
	}
	
	
	/**
	 * this method returns current/remaining coins
	 * @return type double
	 */
	public double getCoins() {
		return currentCoins;
	}
	
	
	/**
	 * this method adds the given amount to the current coins
	 * @param tempCoins of type double
	 */
	public void addCoins(double tempCoins) {
		currentCoins += tempCoins;
	}
	
	
	/**
	 * this method subtracts the given amount from current coins
	 * @param tempCoins of type double
	 */
	public void subtractCoins(double tempCoins) {
		currentCoins -= tempCoins;
	}
	
	
	/**
	 * this method returns Traders' name
	 * @return type String
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * this method returns remaining game duration
	 * @return type Integer
	 */
	public double getDays() {
		return daysToPlay;
	}
	
	
	/**
	 * this method returns Traders' home island
	 * @return type {@link Island}
	 */
	public Island gethomeIsland() {
		return homeIsland;
	}
	
	
	/**
	 * this method calculates the profit/loss made by selling goods 
	 * and calls {@link updateProfit} to add profit/loss
	 * @param buyPrice of type double as buy price
	 * @param sellPrice of type double as sell price
	 * @param quantity of type Integer as number of products sold
	 */
	public void calculateProfit(double buyPrice, double sellPrice, int quantity) {
		double result = ((sellPrice - buyPrice) * quantity);
		updateProfit(result);
	}
	
	
	/**
	 * this method updates the current profit / loss
	 * @param profitFromSoldItems of type double
	 */
	public void updateProfit(double profitFromSoldItems) {
		profit += profitFromSoldItems;
	}
	
	
	public double getTotalTravelCost() {
		return totalTravelCost;
	}
	
	
	public void addTotalTravelCost(double cost) {
		totalTravelCost += cost;
		subtractCoins(cost);
	}
	
	
	
	/**
	 * this method returns the current profit
	 * @return type double
	 */
	public double getProfit() {
		return profit;
	}
	
	
	/**
	 * this method returns an Array containing all the bought items
	 * @return type {@link ArrayList} of type {@link Item}
	 */
	public ArrayList<Item> allBoughtItems() {
		return itemsBought;
	}
	
	
	/**
	 * this method return an Array containing all the sold items
	 * @return of type {@link ArrayList} of type {@link Item}
	 */
	public ArrayList<Item> allSoldItems(){
		return itemsSold;
	}
	
	
	/**
	 * when Pirates steel you cargo, this method empties your {@link itemsBought} and subtract the cost from profit
	 * @param cost of type double
	 */
	public void setitemsBoughtToLost(double cost) {
		itemsBought = new ArrayList<Item>();
		updateProfit(-cost);
	}
	
	
	/**
	 * this method completes the purchase
	 * @param item of type {@link Item}, that you'd like to purchase
	 * @param quantity of type Integer, the number of items
	 */
	public void purchase(Item item, int quantity) {
		Store currentStore = currentIsland.getStore();
		double cost = (item.getSellPrice() * quantity);
		if (quantity > 0) {
			if (currentShip.getAvailableStorage() >= quantity) {
				if (cost <= currentCoins) {
					subtractCoins(cost);
					currentStore.makePurchase(item, cost, quantity);
				}else {
					System.out.println("you don't have enough coins.");
				}
			}else {
				System.out.println("You don't have enough space on you ship.");
			}
		}else {
			System.out.println("Quantity must be greater than 0 to proceed with purchase.");
		}
	}
	
	
	/**
	 * After the purchase is completed, bought items are added to an array, if the item bought is an upgrad
	 * which is used straight away hence not added to the {@link ItemBought} array.
	 * @param newitem of type {@link Item}, an item that we bought
	 * @param quantity of type Integer, the number of items bought
	 */
	public void addToItemsBought(Item newItem, int quantity){
		boolean found = false;
		if(!(newItem.getType().equalsIgnoreCase("Upgrade"))){
			for (Item item: itemsBought) {
				if (item.getProductName().equalsIgnoreCase(newItem.getProductName())&&
					item.getBoughtFrom().equalsIgnoreCase(newItem.getBoughtFrom())){
					item.updateQuantity(item.getQuantityAvailble() + quantity);
					found = true;
					getCurretShip().loadProducts(quantity);
					break;
				}
			}
			if (!found) {
				itemsBought.add(newItem);
				getCurretShip().loadProducts(quantity);
			}
		}else {
			useUpgrades(newItem, quantity);
		}
	}
	
	
	/**
	 * upgrades are used straight away and are not re-sell able
	 * @param newitem type item upgrades
	 * @param quantity type Integer number of upgrades
	 */
	public void useUpgrades(Item newItem, int quantity) {
		currentShip.addInstallUpgrades(newItem);
		if (newItem.getProductName().equalsIgnoreCase("Cargo Space")) {
			currentShip.upgradeCargoCapacity(quantity);
			System.out.println("You've upgraded your ships' Cargo Capacity");
			System.out.println("New Cargo Capacity: " + currentShip.getAvailableStorage());
		}else if(newItem.getProductName().equalsIgnoreCase("Durability")) {
			currentShip.upgradeDurability(quantity);
			System.out.println("You've upgraded your ships' durability");
			System.out.println("New Durability: " + currentShip.getDurability());
		}else if(newItem.getProductName().equalsIgnoreCase("Engine")) {
			currentShip.upgradeSpeed(quantity);
			System.out.println("You've upgraded your ships' speed");
			System.out.println("New Speed: " + currentShip.getSpeed());
		}else if (newItem.getProductName().equalsIgnoreCase("Cannon")) {
			currentShip.addCannons(quantity);
			System.out.println("You've added cannon/s to your ship");
			System.out.println("The total number of Cannons on your ship: " + currentShip.getNumberOfCannons());
		}
	}
	
	
	/**
	 * this method finds the item in {@link itemsBought} array comparing the names of products
	 * @param name of type String
	 * @return of type {@link Item}
	 */
	public Item findItem(String name) {
		for (Item item: itemsBought) {
			if (item.getProductName().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}
	
	
	/**
	 * this implementation of interface is used when a purchase is successful,
	 * and new item is added to the {@link itemsBought} Array
	 */
	@Override
	public void purchaseSuccessfull(String name, double buyPrice, int quantity, String type, String units) {
		Item newItem = new Item(name);
		newItem.setBuyPrice(buyPrice);
		newItem.setQuantity(quantity);
		newItem.setType(type);
		newItem.setBoughtFrom(getCurrentIsland().getStore().getName());
		newItem.setUnits(units);
		addToItemsBought(newItem, quantity);
	}
	
	
	@Override
	/**
	 * this implementation of interface is used when a purchase is not successful,
	 * an error message is printed
	 */
	public void purchaseUnsuccessfull(String error, double coins) {
		this.addCoins(coins);
		System.out.println(error);
		
	}
	
	
	/**
	 * this methods is used to sell an item
	 * @param item of type {@link Item} that you want to sell
	 * @param quantity of type Integer, the amount you want to sell
	 */
	public void sellItem(Item item, int quantity) {
		Store currentStore = currentIsland.getStore();
		Item boughtItem = findItem(item.getProductName());
		if (boughtItem != null && boughtItem.getQuantityAvailble() > 0) {
			currentStore.makeSale(item, quantity);
		}else {
			System.out.println("You don't have enough " + item.getProductName());
		}
	}
	
	
	/**
	 * after the sale of an item is completed, the item is added to the {@link itemSold} array
	 * @param product of type {@link Item}, that you want to sell
	 * @param quantity of type Integer, the number of items you want to sell
	 */
	public void addToItemsSold(Item product, int quantity) {
		itemsSold.add(product);
		currentShip.unloadProducts(quantity);
		calculateProfit(product.getBuyPrice(), product.getSellPrice(), quantity);
	}
	
	
	/**
	 * Interface Implementation of successful sale, the items sold is removed from array {@link itemsBought}
	 * and added to the array {@link itemsSold}
	 */
	@Override
	public void successfullSale(String name, double sellPrice, int tempQuantity, double coins, String units) {
		addCoins(coins);
		Item item = findItem(name);
		item.updateQuantity(item.getQuantityAvailble()-tempQuantity);
		
		Item newItem = new Item(name, item.getBuyPrice(), sellPrice, tempQuantity, item.getType());
		newItem.setSoldTo(getCurrentIsland().getStore().getName());
		newItem.setUnits(units);
		addToItemsSold(newItem, tempQuantity);
		
	}
	
	
	/**
	 * Interface Implementation of unsuccessful sale, and the error message is printed
	 */
	public void unsuccessfullSale(String error) {
		System.out.println(error);
	}
	
	
	/**
	 * this method returns all the items bought in String representation
	 * 1 item per line
	 * @return of type String
	 */
	public String getBoughtItems() {
		String result = "";
		for (Item item: itemsBought) {
			result += item.buyDetails() + "\n";
		}
		return result;
	}
	
	
	/**
	 * this method returns all the items sold in String representation
	 * 1 item per line
	 * @return
	 */
	public String getSoldItems() {
		String result = "";
		for (Item item: itemsSold) {
			result += item.saleDetails() + "\n";
		}
		return result;
	}
	
	
	public String getProfile() {
		String result = "%ss' Profile:\n";
		result += "Current Coins: %.2f \t Profit: %.2f \t Days remaining: %.2f\n";
		result += "Home Island: %s \t Current Island: %s\n";
		result += "Ship: %s\n";
		return String.format(result, this.name, this.currentCoins, this.profit,
							this.daysToPlay, this.homeIsland.getName(),
							this.currentIsland.getName(), this.currentShip.getName());
	}
	
	
	/**
	 * this method return an informative String about Trader
	 * @return of type String
	 */
	@Override
	public String toString(){
		return String.format("I am a trader. My name is %s.\nMy home island is %s, I have %s coins.\nMy current ships is %s", name, homeIsland.getName(), currentCoins, currentShip.getName()); 
	}
	
}
