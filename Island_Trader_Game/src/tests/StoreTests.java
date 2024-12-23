package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import models.*;

class StoreTests {
	Trader trader = new Trader();
	Item item1 = new Item("Strawberry", 4, 8, 100, "Vege");
	Item item2 = new Item("Banana", 4, 8, 100, "Fruit");
	Item item3 = new Item("Pear", 4, 8, 100, "Upgrade");
	Item item4 = new Item("Wheat", 4, 8, 100, "Vege");
	Item item5 = new Item("Apple", 4, 8, 100, "Fruit");
	Item item6 = new Item("Orange", 4, 8, 100, "Vege");
	Item item7 = new Item("katsu Chicken", 4, 8, 100, "Travel");
	
	
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
		store1.addProducts(item1);
		store2.addProducts(item2);
		store1.addProducts(item3);
		store1.addProducts(item7);
		ArrayList<Item> list1 = new ArrayList<Item>();
		list1.add(item1);
		list1.add(item2);
		list1.add(item3);
		list1.add(item7);
		ArrayList<Item> list2 = new ArrayList<Item>();
		list2.add(item2);
		assertEquals(store1.getProducts().size(), list1.size());
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
		ArrayList<Item> list1 = new ArrayList<Item>();
		list1.add(item1);
		ArrayList<Item> list2 = new ArrayList<Item>();
		list2.add(item2);
		assertEquals(store1.getProducts(), list1);
		assertEquals(store2.getProducts(), list2);
	}
	
	@Test
	void getProducts1(){
		Store store1 = new Store();
		ArrayList<Item> list1 = new ArrayList<Item>();
		ArrayList<Item> list2 = new ArrayList<Item>();
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
		Store store1 = new Store();
		Store store2 = new Store("Kmart", trader);
		store1.addProducts(item1);
		store2.addProducts(item2);
		assertEquals(104, store1.toString().length());
	}
	
	
	

}
