package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import elements.*;

class StoresTests {
	Trader trader = new Trader();
	Items item1 = new Items("Strawberry", 4, 8, 100, "Vege");
	Items item2 = new Items("Banana", 4, 8, 100, "Fruit");
	Items item3 = new Items("Pear", 4, 8, 100, "Upgrade");
	Items item4 = new Items("Wheat", 4, 8, 100, "Vege");
	Items item5 = new Items("Apple", 4, 8, 100, "Fruit");
	Items item6 = new Items("Orange", 4, 8, 100, "Vege");
	Items item7 = new Items("katsu Chicken", 4, 8, 100, "Travel");
	
	
	@Test
	void setName() {
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		store1.setName("Wallmart");
		store2.setName("Kupan");
		assertEquals("Wallmart", store1.getName());
		assertEquals("Kupan", store2.getName());
	}
	@Test
	void getName() {
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		store1.setName("Wallmart");
		assertEquals("Wallmart", store1.getName());
		assertEquals("Kmart", store2.getName());
	}
	
	@Test
	void addToStoreRegister() {
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		store1.addToStoreRegister(100.0);
		store2.addToStoreRegister(100.0);
		assertEquals(10100.0, store1.getCashRegister());
		assertEquals(10100.0, store2.getCashRegister());
	}
	
	@Test
	void subtractStoreRegister() {
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		store1.subtractStoreRegister(100.0);
		store2.subtractStoreRegister(100.0);
		assertEquals(9900.0, store1.getCashRegister());
		assertEquals(9900.0, store2.getCashRegister());
	}
	
	@Test
	void getCashRegister() {
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		assertEquals(10000.0, store1.getCashRegister());
		assertEquals(10000.0, store2.getCashRegister());

	}
	
	
	@Test
	void addItem() {
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		store1.addItem(item1, 0);
		store2.addItem(item2, 0);
		assertEquals(store1.getProducts().get(0), item1);
		assertEquals(store2.getProducts().get(0), item2);
	}
	
	@Test
	void addProducts() {
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		store1.addProducts(item1);
		store2.addProducts(item2);
		ArrayList list1 = new ArrayList();
		list1.add(item1);
		ArrayList list2 = new ArrayList();
		list2.add(item2);
		assertEquals(store1.getProducts(), list1);
		assertEquals(store2.getProducts(), list2);
	}
	
	@Test
	void getAllCategories() {
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		String[] allcategories = new String[] {"Vege", "Fruit", "Travel", "Upgrade"};
		for(int count=0; count < store1.getAllCategories().length; count++) {
				assertEquals(store1.getAllCategories()[count], allcategories[count]);
				assertEquals(store2.getAllCategories()[count], allcategories[count]);

		}		
	}
	
	@Test
	void getProducts(){
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		store1.addProducts(item1);
		store2.addProducts(item2);
		ArrayList list1 = new ArrayList();
		list1.add(item1);
		ArrayList list2 = new ArrayList();
		list2.add(item2);
		assertEquals(store1.getProducts(), list1);
		assertEquals(store2.getProducts(), list2);
	}
	
	@Test
	void getProducts1(){
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		assertEquals(store1.getProducts("Fruit"), list1);
		assertEquals(store1.getProducts("Fruit"), list2);
	}
	
	@Test
	void findProduct() {
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		store1.addProducts(item1);
		store2.addProducts(item2);
		assertEquals(store1.findProduct(item1), item1);
		assertEquals(store2.findProduct(item2), item2);
	}
	
	@Test
	void makePurchase() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 150, newShip, newIsland);
		newTrader.setCurrentShip(newShip);
		Store store1 = new Store("newIslandStore", newTrader);
		
		newIsland.setStore(store1);
		
		store1.addProducts(item1);
		store1.addProducts(item2);
		store1.addProducts(item3);
		store1.addProducts(item4);
		
		newTrader.purchase(item1, 1);
		assertEquals(store1.findProduct(item1).getQuantityAvailble(), 99);
		newTrader.purchase(item2, 2);
		assertEquals(store1.findProduct(item2).getQuantityAvailble(), 98);
		newTrader.purchase(item3, 3);
		assertEquals(store1.findProduct(item3).getQuantityAvailble(), 97);
		newTrader.purchase(item4, 200);
		assertEquals(store1.findProduct(item4).getQuantityAvailble(), 100);
		newTrader.purchase(item4, 80);
		assertEquals(store1.findProduct(item4).getQuantityAvailble(), 100);
		
	}
	
	
	@Test
	void makeSale() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 150, newShip, newIsland);
		newTrader.setCurrentShip(newShip);
		Store store1 = new Store("newIslandStore", newTrader);
		
		newIsland.setStore(store1);
		
		store1.addProducts(item1);
		store1.addProducts(item2);
		store1.addProducts(item3);
		store1.addProducts(item4);
		newTrader.purchase(item1, 4);
		newTrader.purchase(item2, 5);
		newTrader.purchase(item4, 10);
		newTrader.sellItem(item1, 4);
		newTrader.sellItem(item2, 5);
		newTrader.sellItem(item4, 10);

		assertEquals(store1.findProduct(item1).getQuantityAvailble(), 100);
		assertEquals(store1.findProduct(item2).getQuantityAvailble(), 100);
		assertEquals(store1.findProduct(item4).getQuantityAvailble(), 100);
	}
}
