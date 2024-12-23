package models;
import java.util.*;
import transaction_interface.*;

public class Store {
	private String storeName;
	private double cashRegister = 10000.0;
	private TransactionProcess process;
	private String[] allcategories = new String[] {"Vege", "Fruit", "Travel", "Upgrade"};
	private ArrayList<Item> travelItems = new ArrayList<Item>();
	private ArrayList<Item> fruits = new ArrayList<Item>();
	private ArrayList<Item> vegetables = new ArrayList<Item>();
	private ArrayList<Item> upgrades = new ArrayList<Item>();
	private ArrayList<ArrayList<Item>> products = new ArrayList<ArrayList<Item>>();
	
	/**
	 * this is an generic constructor for store
	 */
	public Store() {
		products.add(travelItems);
		products.add(fruits);
		products.add(vegetables);
		products.add(upgrades);
	}
	
	/**
	 * this constructor sets the store name and establishes a link between store and trader by passing trader as a parameter.
	 * @param name String
	 * @param tempProcess type Trader
	 */
	public Store(String name, Trader tempProcess) {
		storeName = name;
		products.add(travelItems);
		products.add(fruits);
		products.add(vegetables);
		products.add(upgrades);
		process = tempProcess;
	}
	
	/**
	 * this method sets the name of the store
	 * @param tempName 
	 */
	public void setName(String tempName) {
		storeName = tempName;
	}
	
	/**
	 * this method returns the name of the stroe
	 * @return
	 */
	public String getName() {
		return storeName;
	}
	
	/**
	 * this method adds coins to the stores' bank / cash register
	 * @param coins type double
	 */
	public void addToStoreRegister(double coins) {
		cashRegister += coins;
	}
	
	/**
	 * this method subtracts coins from the stores' bank / cash register
	 * @param coins type double
	 */
	public void subtractStoreRegister(double coins) {
		cashRegister -= coins;
	}
	
	/**
	 * this method returns the current amount of coins store has
	 * @return type double
	 */
	public double getCashRegister() {
		return cashRegister;
	}
	
	/**
	 * this method adds items to the stores' invetory, it the item is already in the invetory it updates it's quantity 
	 * @param item
	 * @param index
	 */
	public void addItem(Item item, int index) {
		boolean found = false;
		for(Item product: products.get(index)) {
			if (product.getProductName().equalsIgnoreCase(item.getProductName())){
				product.updateQuantity(product.getQuantityAvailble()+item.getQuantityAvailble());
			}
		}
		if (!found) {
			products.get(index).add(item);
		}
	}
	
	/**
	 * this method add the products to the store based on the category
	 * @param item type Item
	 */
	public void addProducts(Item item) {
		//System.out.println(item);
		if (item.getType().equalsIgnoreCase("Travel")){
				//add to travel
			addItem(item, 2);
		}else if (item.getType().equalsIgnoreCase("FRUIT")) {
			//add to Fruit
			addItem(item, 1);
		}else if (item.getType().equalsIgnoreCase("VEGE")) {
			//add to vegetables
			addItem(item, 0);
		}else if (item.getType().equalsIgnoreCase("UPGRADE")) {
			// add to upgrades
			addItem(item, 3);
		}
		
	}
	
	/**
	 * this method returns a list containing all the possible category types
	 * @return
	 */
	public String[] getAllCategories() {
		return allcategories;
	}
	
	/**
	 * this method returns an Array of type Items containing all the products a store has.
	 * @return
	 */
	public ArrayList<Item> getProducts(){
		//returns an array of all the products
		ArrayList<Item> result = new ArrayList<Item>();
		
		for (ArrayList<Item> subList: products) {
			//System.out.println(subList);
			for (Item item: subList) {
				result.add(item);
			}
		}
		
		return result;
	}
	
	/**
	 * this method returns an Array of type Items containing all the products of a specific category type
	 * @param category - type String name of category
	 * @return type Array containing type Item
	 */
	public ArrayList<Item> getProducts(String category){
		ArrayList<Item> result = new ArrayList<Item>();
		
		for (ArrayList<Item> subList: products) {
			for (Item item: subList) {
				if (item.getType().equalsIgnoreCase(category)) {
					result.add(item);
				}
			}
		}
		return result;
	}
	
	
	/**
	 * this method gets type Item as a parameter and search for that item in the store and returns it when it finds the item else
	 * returns null for item not found.
	 * @param product
	 * @return
	 */
	public Item findProduct(Item product) {
		ArrayList<Item> subList = getProducts(product.getType());
		Item result = null;
		
		for (Item item: subList) {
			if (product.getProductName().equalsIgnoreCase(item.getProductName())) {
				result = item;
			}
		}
		return result;
	}
	
	
	/**
	 * this method processes a purchase of an item for trader, so store is selling the item. it requires the item trader wants
	 * to purchase and the quantity he/she wants to purchase. This method doesn't return anything but invokes one of the 
	 * methods in the interface based on the outcome on the transaction. if the transaction fails it invokes purrchaseUnsuccessfull
	 * from interface otherwise it invokes purchaseSuccessfull from interface.
	 * @param tempProduct
	 * @param coins
	 * @param orderedQuantity
	 */
	public String makePurchase(Item tempProduct, double coins, int orderedQuantity) {
		Item product = findProduct(tempProduct);
		
		if (product != null) {
			double cost = (product.getSellPrice() * orderedQuantity);
			
			if(product.getQuantityAvailble() < orderedQuantity &&
			   product.getQuantityAvailble() > 0) {
				process.purchaseUnsuccessfull("Low Stock Levels, we only have "+product.getQuantityAvailble()+" available.", coins);
			}
			else if(coins < cost) {
				process.purchaseUnsuccessfull("You don't have enough Coins", coins);
			}
			else if (product != null &&
				product.getQuantityAvailble() >= orderedQuantity &&
				coins == cost) {
				addToStoreRegister(coins);
				product.updateQuantity(product.getQuantityAvailble() - orderedQuantity);
				process.purchaseSuccessfull(product.getProductName(), product.getSellPrice(),
											orderedQuantity, product.getType(), product.getUnits());
				 
			}else {
				process.purchaseUnsuccessfull("Out Of Stock!", coins);
				
			}
		}else {
			return ("Product not Found");
		}
		return "Successful";
	}
	
	
	/**
	 * this method process the sale on an item of trader, so store is purchasing this item. it requires the item trader
	 * wants to sell and the quantity he/she wants to sell. This method doesn't return anything but invokes one of the 
	 * methods in the interface based on the outcome on the transaction. if the transaction fails it invokes unsuccessfullSale
	 * from interface otherwise it invokes successfullSale from interface.
	 * @param tempProduct
	 * @param sellQuantity
	 */
	public void makeSale(Item tempProduct, int sellQuantity) {
		Item product = findProduct(tempProduct);
		
		if (sellQuantity > 0 && product != null){
			double cost = (product.getBuyPrice() * sellQuantity);
			product.updateQuantity(product.getQuantityAvailble() + sellQuantity);
			this.subtractStoreRegister(cost);
			process.successfullSale(product.getProductName(), product.getBuyPrice(), sellQuantity, cost, product.getUnits());
		}else {
			String error = "Sale Unsuccessfull...";
			process.unsuccessfullSale(error);
		}
	}
	
	/**
	 * String representation of the store and all of it's products line by line
	 * @return type String
	 */
	public String toString() {
		String result = "Item " + storeName + " Can Buy and Sell:\n";
		for (ArrayList<Item> subList: products) {
			for (Item item: subList) {
				result += item + "\n";
			}
		}
		return result;
	}	

}
