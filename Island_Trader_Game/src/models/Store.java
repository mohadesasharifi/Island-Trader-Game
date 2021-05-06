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
	
	
	public Store() {
		products.add(travelItems);
		products.add(fruits);
		products.add(vegetables);
		products.add(upgrades);
	}
	
	
	public Store(String name, Trader tempProcess) {
		storeName = name;
		products.add(travelItems);
		products.add(fruits);
		products.add(vegetables);
		products.add(upgrades);
		process = tempProcess;
	}
	
	
	public void setName(String tempName) {
		storeName = tempName;
	}
	
	
	public String getName() {
		return storeName;
	}
	
	
	public void addToStoreRegister(double coins) {
		cashRegister += coins;
	}
	
	
	public void subtractStoreRegister(double coins) {
		cashRegister -= coins;
	}
	
	public double getCashRegister() {
		return cashRegister;
	}
	
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
	
	
	public String[] getAllCategories() {
		return allcategories;
	}
	
	
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
	
	
	
	public void makePurchase(Item tempProduct, double coins, int orderedQuantity) {
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
			System.out.println("Product not Found");
		}
	}
	
	
	
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
