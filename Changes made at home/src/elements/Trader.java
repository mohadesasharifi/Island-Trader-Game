package elements;
import Interfaces.*;
import java.util.*;

public class Trader implements TransactionProcess {
	private String name;
	private static double currentCoins = 100;
	private static ArrayList<Items> itemsBought = new ArrayList<Items>();
	private static ArrayList<Items> itemsSold = new ArrayList<Items>();
	private static double profit = 0; 
	private static Ship currentShip;
	private Islands homeIsland;
	private Islands currentIsland = homeIsland;
	private int daysToPlay = 0;
	
	
	public Trader() {
	};
	
	public Trader(String tName, double tCoins, Ship tCurrentShip, Islands tHomeIsland){
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
	
	
	public void setCurrentShip(Ship ship) {
		currentShip = ship; 
	}
	
	public Ship getCurretShip() {
		return currentShip;
	}
	
	public void setDaysToPlay(int days) {
		daysToPlay = days;
	}
	
	public void setHomeIsland(Islands myHomeIsland) {
		homeIsland = myHomeIsland;
		currentIsland = myHomeIsland;
	}
	
	public void setCurrentIsland(Islands island) {
		currentIsland = island;
	}
	
	
	public void setCoins(double coins) {
		currentCoins = coins;
	}
	
	public double getCoins() {
		return currentCoins;
	}
	
	public void addCoins(double tempCoins) {
		currentCoins += tempCoins;
	}
	
	public void subtractCoins(double tempCoins) {
		currentCoins -= tempCoins;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDays() {
		return daysToPlay;
	}
	
	public Islands gethomeIsland() {
		return homeIsland;
	}
	
	public void calculateProfit(double buyPrice, double sellPrice, int quantity) {
		double result = ((sellPrice - buyPrice) * quantity);
		updateProfit(result);
	}
	
	public void updateProfit(double profitFromSoldItems) {
		profit += profitFromSoldItems;
	}
	
	public double getProfit() {
		return profit;
	}
	
	public ArrayList<Items> allBoughtItems() {
		return itemsBought;
	}
	
	public ArrayList<Items> getBoughtItems1(){
		return itemsBought;
	}
	
	public ArrayList<Items> allSoldItems(){
		return itemsSold;
	}
	
	public void setitemsBoughtToLost(double cost) {
		itemsBought = new ArrayList<Items>();
		updateProfit(-cost);
	}
	
	
	public void purchase(Items item, int quantity) {
		Store currentStore = currentIsland.getStore();
		double cost = (item.getSellPrice() * quantity);
		if (quantity > 0) {
			if (cost <= currentCoins) {
				subtractCoins(cost);
				currentStore.makePurchase(item, cost, quantity);
//				addToItemsBought(item, quantity);
			}else {
				System.out.println("you don't have enought coins");
			}
		}else {
			System.out.println("Quantity must be greater than 0 to proceed with purchase");
		}
	}
	
	
	public void addToItemsBought(Items newitem, int quantity){
		boolean found = false;
		for (Items item: itemsBought) {
			if (item.getProductName().equalsIgnoreCase(newitem.getProductName())){
				item.updateQuantity(item.getQuantityAvailble() + quantity);
				found = true;
				break;
			}
		}
		if (!found) {
		
		itemsBought.add(newitem);
//		}
		if(!(newitem.getType().equalsIgnoreCase("Upgrade"))) {
			currentShip.loadProducts(quantity);
		}else {
			if (newitem.getProductName().equalsIgnoreCase("Cargo Space")) {
				currentShip.upgradeCargoCapacity(quantity);
			}else if(newitem.getProductName().equalsIgnoreCase("Durability")) {
				currentShip.upgradeDurability(quantity);
			}else if(newitem.getProductName().equalsIgnoreCase("Engine")) {
				currentShip.upgradeSpeed(quantity);
			}
		}
		}
	}
	
	
	public Items findItem(String name) {
		for (Items item: itemsBought) {
			if (item.getProductName().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}
	
	
	
	public void purchaseSuccessfull(String name, double buyPrice, int quantity, String type) {
		Items newItem = new Items(name, buyPrice, 0, quantity, type);
		addToItemsBought(newItem, quantity);
	}
	
	
	
	@Override
	public void purchaseUnsuccessfull(String error, double coins) {
		this.addCoins(coins);
		System.out.println(error);
		
	}
	
	
	
	public void sellItem(Items item, int quantity) {
		Store currentStore = currentIsland.getStore();
		if (item.getQuantityAvailble() > 0 && findItem(item.getProductName()) != null) {
			currentStore.makeSale(item, quantity);
		}else {
			System.out.println("You don't have enough " + item.getProductName());
		}
	}
	
	
	
	public void addToItemsSold(Items product, int quantity) {
		itemsSold.add(product);
		currentShip.unloadProducts(quantity);
		calculateProfit(product.getBuyPrice(), product.getSellPrice(), quantity);
	}
	
	
	
	public void successfullSale(String name, double sellPrice, int tempQuantity, double coins) {
		addCoins(coins);
		Items item = findItem(name);
		item.updateQuantity(item.getQuantityAvailble()-tempQuantity);
		Items newItem = new Items(name, item.getBuyPrice(), sellPrice, tempQuantity, item.getType());
		addToItemsSold(newItem, tempQuantity);
		
	}
	
	
	
	public void unsuccessfullSale(String error) {
		System.out.println(error);
	}
	
	
	public String getBoughtItems() {
		String result = "";
		for (Items item: itemsBought) {
			result += item + "\n";
		}
		return result;
	}
	
	
	public String getSoldItems() {
		String result = "";
		for (Items item: itemsSold) {
			result += item + "\n";
		}
		return result;
	}
	
	
	
	public String toString(){
		return String.format("I am a trader. My name is %s. My home island is %s I have %s coins. My current ships is %s", name, homeIsland.getName(), currentCoins, currentShip.getName()); 
	}

	
	
	
	
	public static void main(String[] arg) {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("MyISLAND");
		
		Trader newTrader = new Trader("Khan", 100, newShip, newIsland);
		//newTrader.setCurrentShip(newShip);
		
		Store test1 = new Store("newIslandStore");
		Items item1 = new Items("Strawberry", 4, 8, 100, "Travel");
		Items item2 = new Items("Banana", 4, 8, 100, "Vege");
		Items item3 = new Items("Pear", 4, 8, 100, "Upgrade");
		Items item4 = new Items("Wheat", 4, 8, 100, "Vege");
		Items item5 = new Items("Apple", 10, 8, 100, "Travel");
		Items item6 = new Items("Orange", 4, 8, 100, "Fruit");
		Items item7 = new Items("katsu Chicken", 4, 8, 100, "Upgrade");
		
		newIsland.setStore(test1);
		
//		//System.out.println(item1.getType());
		test1.addProducts(item4);
		test1.addProducts(item5);
//		test1.addProducts(item6);
//		test1.addProducts(item7);
		
		//System.out.println(test1.getProducts());
		//System.out.println(newTrader.getCurretShip().getAvailableStorage());
		System.out.println(newTrader);
		System.out.println(newTrader.getCurretShip().getAvailableStorage());
		newTrader.purchase(item5, 5);
//		newTrader.purchase(item6, 5);
//		newTrader.purchase(item4, 4);
		newTrader.purchase(item5, 2);
		System.out.println("COINS before sale");
		System.out.println(newTrader.getCoins());
		System.out.println("Make a Sale");
		newTrader.sellItem(item5, 2);
		System.out.println("COINS after sale");
		System.out.println(newTrader.getCoins());
		System.out.println("PROFIT");
		System.out.println(newTrader.getProfit());
		System.out.println("STORAGE");
		System.out.println(newTrader.getCurretShip().getAvailableStorage());
		System.out.println("Bought Items");
		System.out.println(newTrader.getBoughtItems());
		System.out.println("SOLD Items");
		System.out.println(newTrader.getSoldItems());
		
	}
}
