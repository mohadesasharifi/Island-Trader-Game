package elements;
import java.util.*;

public class Islands {
	private String islandName;
	private int islandNumber;
	private double distance;
	private Store islandShop = new Store();
	private String specialityItem;
	private static int counter = 0;
	
	
	public Islands() {
		islandNumber = counter;
		counter += 1;
	}
	
	public Islands (String name, Store store) {
		islandName = name;
		islandShop = store;
		islandNumber = counter;
		counter += 1;
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
	
	public Store getStore() {
		return islandShop;
	}
	
	public void addProductsToStore(Items item) {
		islandShop.addProducts(item);
	}


	public double calculateDistance(){
		return 0.0;// stub to apply
	}
	
	public void setSpecialityItem(String name) {
		specialityItem = name;
	}
	
	public ArrayList<Items> getItemsFromStore() {
		return islandShop.getProducts();
	}
	
	public String toString() {
		return ("Welcome to " + islandName + " this is number " + islandNumber 
				+ " island on this planet, we have " +islandShop.getName()
				+ " store if you'd like to buy or sell items.");
	}
	
	
	public static void main(String[] args) {
		Store shop = new Store("Yoyo Store");
		Items item1 = new Items("Strawberry", 4, 8, 100, "Vege");
		Items item2 = new Items("Banana", 4, 8, 100, "Fruit");
		Items item3 = new Items("Pear", 4, 8, 100, "Upgrade");
		Items item4 = new Items("Wheat", 4, 8, 100, "Vege");
		Items item5 = new Items("Apple", 4, 8, 100, "Fruit");
		Items item6 = new Items("Orange", 4, 8, 100, "Vege");
		Items item7 = new Items("katsu Chicken", 4, 8, 100, "Travel");
		
		shop.addProducts(item7);
		shop.addProducts(item6);
		shop.addProducts(item5);
		shop.addProducts(item4);
		
		Islands island = new Islands("Test Island", shop);
		System.out.println(island.getItemsFromStore());
		
	}
	
}
