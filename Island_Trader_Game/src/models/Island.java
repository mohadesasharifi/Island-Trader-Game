package models;
import java.util.*;

public class Island {
	public static double DISTANCE = 400;
	public String islandDescription;
	private String islandName;
	private int islandNumber;
	private Store islandShop = new Store();
	private String specialityItem;
	private static int counter = 0;
	
	
	public Island() {
		islandDescription =  "This is a new Island";
		islandNumber = counter;
		counter += 1;
	}
	
	public Island (String name, Store store) {
		islandName = name;
		islandShop = store;
		islandNumber = counter;
		counter += 1;
		islandDescription =  "This is a new Island";
	}
	
	
	public void setIslandDescription(String description) {
		islandDescription =  description;
	}
	
	
	public String getIslandDescription() {
		return islandDescription;
	}
	
	public void setName(String tempName) {
		islandName = tempName;
	}
	
	public void setStore(Store store) {
		islandShop = store;
	}
	
	public String getName() {
		return islandName;
	}
	
	public int getIslandNo() {
		return islandNumber;
	}
	public Store getStore() {
		return islandShop;
	}
	
	public void addProductsToStore(Item item) {
		islandShop.addProducts(item);
	}
	
	
	public void setSpecialityItem(String name) {
		specialityItem = name;
	}
	
	public String getSpecialityItem() {
		return specialityItem;
	}
	
	public ArrayList<Item> getItemsFromStore() {
		return islandShop.getProducts();
	}
	
	public String toString() {
		return ("Welcome to " + islandName + " this is number " + islandNumber 
				+ " island on this planet, we have " +islandShop.getName()
				+ " store if you'd like to buy or sell items.");
	}
}
