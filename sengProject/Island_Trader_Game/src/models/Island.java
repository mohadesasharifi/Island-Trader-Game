package models;
import java.util.*;

/**
 * this is the model class for Islands, it creates islands.
 * @author Zahid Khan
 *
 */

public class Island {
	
	public static double DISTANCE = 400;
	public String islandDescription;
	private String islandName;
	private int islandNumber;
	private Store islandShop = new Store();
	private String specialityItem;
	private static int counter = 0;
	
	/**
	 * this is an empty constructor for island class, it sets the generic name
	 */
	public Island() {
		islandDescription =  "This is a new Island";
		islandNumber = counter;
		counter += 1;
	}
	
	/**
	 * this constructor take name of the island and the store you want to assign to that island
	 * @param name
	 * @param store
	 */
	public Island (String name, Store store) {
		islandName = name;
		islandShop = store;
		islandNumber = counter;
		counter += 1;
		islandDescription =  "This is a new Island";
	}
	
	/**
	 * this method set's / updates the description of each island
	 * @param description
	 */
	public void setIslandDescription(String description) {
		islandDescription =  description;
	}
	
	/**
	 * this method returns the description of island
	 * @return islandDescription of type String
	 */
	public String getIslandDescription() {
		return islandDescription;
	}
	
	/**
	 * this method sets/updates the name of the island
	 * @param tempName
	 */
	public void setName(String tempName) {
		islandName = tempName;
	}
	
	/**
	 * this method assigns a store to the island
	 * @param store of type Store
	 */
	public void setStore(Store store) {
		islandShop = store;
	}
	
	/**
	 * this method returns the name of the island
	 * @return islandName of type String
	 */
	public String getName() {
		return islandName;
	}
	
	/**
	 * this method returns the number of the island in the galaxy(:D)
	 * @return islandNumber of type Integer
	 */
	public int getIslandNo() {
		return islandNumber;
	}
	
	/**
	 * this method returns the store island has
	 * @return islandShop of type Store
	 */
	public Store getStore() {
		return islandShop;
	}
	
	public void addProductsToStore(Item item) {
		islandShop.addProducts(item);
	}
	
	/**
	 * every island has it's own specialty, this method sets the specialty of the island by taking the name of the product as a parameter 
	 * @param name
	 */
	public void setSpecialityItem(String name) {
		specialityItem = name;
	}
	
	/**
	 * this method returns the specialty item of the store
	 * @return specialty Item of type string
	 */
	public String getSpecialityItem() {
		return specialityItem;
	}
	
	/**
	 * this method returns the products the store has on this island
	 * @return Array List of type Items
	 */
	public ArrayList<Item> getItemsFromStore() {
		return islandShop.getProducts();
	}
	
	@Override
	/**
	 * this method over-rides the to-string to show the short description about the island.
	 * @return String type.
	 */
	public String toString() {
		return ("Welcome to " + islandName + " " +islandShop.getName()
				+ " store is the biggest store on this island."
				+ " You can visit this store if you'd like to make a transaction.");
	}
}
