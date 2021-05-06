package elements;
import java.util.*;
import Interfaces.*;

public class Store {
	private String storeName;
	private double cashRegister = 10000.0;
	private TransactionProcess process;
	private String[] allcategories = new String[] {"Vege", "Fruit", "Travel", "Upgrade"};
	private ArrayList<Items> travelItems = new ArrayList<Items>();
	private ArrayList<Items> fruits = new ArrayList<Items>();
	private ArrayList<Items> vegetables = new ArrayList<Items>();
	private ArrayList<Items> upgrades = new ArrayList<Items>();
	private ArrayList<ArrayList<Items>> products = new ArrayList<ArrayList<Items>>();
	
	
	public Store() {
		products.add(travelItems);
		products.add(fruits);
		products.add(vegetables);
		products.add(upgrades);
		process = new Trader();
	}
	
	
	public Store(String name) {
		storeName = name;
		products.add(travelItems);
		products.add(fruits);
		products.add(vegetables);
		products.add(upgrades);
		process = new Trader();
		
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
	
	public void addItem(Items item, int index) {
		boolean found = false;
		for(Items product: products.get(index)) {
			if (product.getProductName().equalsIgnoreCase(item.getProductName())){
				product.updateQuantity(product.getQuantityAvailble()+item.getQuantityAvailble());
			}
		}
		if (!found) {
			products.get(index).add(item);
		}
	}
	
	
	public void addProducts(Items item) {
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
	
	
	public ArrayList<Items> getProducts(){
		//returns an array of all the products
		ArrayList<Items> result = new ArrayList<Items>();
		
		for (ArrayList<Items> subList: products) {
			//System.out.println(subList);
			for (Items item: subList) {
				result.add(item);
			}
		}
		
		return result;
	}
	
	
	public ArrayList<Items> getProducts(String category){
		//returns an array of all the products of 1 category
		ArrayList<Items> result = new ArrayList<Items>();
		
		for (ArrayList<Items> subList: products) {
			for (Items item: subList) {
				if (item.getType().equalsIgnoreCase(category)) {
					result.add(item);
				}
			}
		}
		return result;
	}
	
	
	public Items findProduct(Items product) {
		ArrayList<Items> subList = getProducts(product.getType());
		Items result = null;
		
		for (Items item: subList) {
			if (product.getProductName() == item.getProductName()) {
				result = item;
			}
		}
		return result;
	}
	
	
	
	public void makePurchase(Items tempProduct, double coins, int orderedQuantity) {
		Items product = findProduct(tempProduct);
		
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
											orderedQuantity, product.getType());
				//System.out.println("Successful....!!!");
				 
			}else {
				process.purchaseUnsuccessfull("Out Of Stock!", coins);
				
			}
		}else {
			System.out.println("Product not Found");
		}
	}
	
	
	
	public void makeSale(Items tempProduct, int sellQuantity) {
		Items product = findProduct(tempProduct);
		
		if (sellQuantity > 0 && product != null){
			double cost = (product.getBuyPrice() * sellQuantity);
			product.updateQuantity(product.getQuantityAvailble() + sellQuantity);
			this.subtractStoreRegister(cost);
			process.successfullSale(product.getProductName(), product.getBuyPrice(), sellQuantity, cost);
		}else {
			String error = "Sale Unsuccessfull...";
			process.unsuccessfullSale(error);
		}
	}
	
	
	public String toString() {
		String result = "Items " + storeName + " Can Buy and Sell:\n";
		for (ArrayList<Items> subList: products) {
			for (Items item: subList) {
				result += item + "\n";
			}
		}
		return result;
	}
	
	
//	public static void main(String[] args) {
//		Store test1 = new Store("newIslandStore");
//		Items item1 = new Items("Strawberry", 4, 8, 100, "Travel");
//		Items item2 = new Items("Banana", 4, 8, 100, "Vege");
//		Items item3 = new Items("Pear", 4, 8, 100, "Upgrade");
//		Items item4 = new Items("Wheat", 4, 8, 100, "Vege");
//		Items item5 = new Items("Apple", 4, 8, 100, "Travel");
//		Items item6 = new Items("Orange", 4, 8, 100, "Fruit");
//		Items item7 = new Items("katsu Chicken", 4, 8, 100, "Upgrade");
//		
//		//System.out.println(item1.getType());
//		test1.addProducts(item4);
//		test1.addProducts(item5);
//		test1.addProducts(item6);
//		test1.addProducts(item7);
//		
//		System.out.println(test1.getProducts());
//		//System.out.println("111111111111111111111111");
//		
//		test1.makePurchase(item5, 40, 5);
//		
//		//System.out.println("111222222222");
////		System.out.println(test1.getProducts());
//		//System.out.println("1111133333331");
//		
//	}
		
	

}
