package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Main.LoadGameData;
import elements.*;

class IslandsTests {
	Trader trader = new Trader();
	Items item1 = new Items("Strawberry", 4, 8, 100, "Vege");
	Items item2 = new Items("Banana", 4, 8, 100, "Fruit");
	Items item3 = new Items("Pear", 4, 8, 100, "Upgrade");
	Items item4 = new Items("Wheat", 4, 8, 100, "Vege");
	Items item5 = new Items("Apple", 4, 8, 100, "Fruit");
	Items item6 = new Items("Orange", 4, 8, 100, "Vege");
	Items item7 = new Items("katsu Chicken", 4, 8, 100, "Travel");

	Store store1 = new Store("Kmart", trader);
	Store store2 = new Store("WareHouse", trader);
	
	
	@Test
	void setName() {
		Islands island1 = new Islands("HomeIsland", store1);
		Islands island2 = new Islands();
		island1.setName("FantasyIsland");
		assertEquals("FantasyIsland", island1.getName());
		island2.setName("Moonrise Kingdom");
		assertEquals("Moonrise Kingdom", island2.getName());	
	}
	
	@Test
	void setStore() {
		Islands island1 = new Islands();
		island1.setStore(store1);
		assertEquals("Kmart", island1.getStore().getName());
		assertEquals(store1.getProducts(), island1.getStore().getProducts());
		Islands island2 = new Islands("MyIsland", store1);
		island2.setStore(store2);
		assertEquals(store2.getName(), island2.getStore().getName());
		assertEquals(store2.getProducts(), island2.getStore().getProducts());	
	}
	
	
	@Test
	void getName() {
		Islands island1 = new Islands("HomeIsland", store1);
		Islands island2 = new Islands();
		island2.setName("HomeIsland");
		assertEquals(island1.getName(), "HomeIsland");
		assertEquals(island2.getName(), "HomeIsland");
	}
	
	@Test
	void getStore(){
		Islands island1 = new Islands("HomeIsland", store1);
		assertEquals(island1.getStore(), store1);
		Islands island2 = new Islands();
		island2.setStore(store2);
		assertEquals(island2.getStore(), store2);
	}
	
	@Test
	void addProductsToStore(){
		Islands island1 = new Islands("HomeIsland", store1);
		island1.addProductsToStore(item1);
		island1.addProductsToStore(item2);
		assertEquals(island1.getStore().getProducts(), store1.getProducts());
		Islands island2 = new Islands();
		island2.setStore(store1);
		island2.addProductsToStore(item3);
		assertEquals(island2.getStore().getProducts(), store1.getProducts());
		island2.setStore(store2);
		island2.addProductsToStore(item4);
		assertEquals(island2.getStore().getProducts(), store2.getProducts());
	}
	
	@Test
	void setSpecialityItem() {
		Islands island1 = new Islands("HomeIsland", store1);
		island1.setSpecialityItem("banana");
		assertEquals("banana", island1.getSpecialityItem());
		Islands island2 = new Islands();
		island2.setSpecialityItem("apple");
		assertEquals(island2.getSpecialityItem(), "apple");
	}
	
	@Test
	void getSpecialityItem() {
		Islands island1 = new Islands("HomeIsland", store1);
		island1.setSpecialityItem("banana");
		assertEquals("banana", island1.getSpecialityItem());
		Islands island2 = new Islands();
		island2.setSpecialityItem("apple");
		assertEquals(island2.getSpecialityItem(), "apple");
	}
	
	
	@Test
	void getItemsFromStore() {
		Islands island1 = new Islands("HomeIsland", store1);
		island1.addProductsToStore(item1);
		island1.addProductsToStore(item2);
		assertEquals(island1.getItemsFromStore(), store1.getProducts());
		Islands island2 = new Islands();
		island2.setStore(store1);
		island2.addProductsToStore(item3);
		assertEquals(island2.getItemsFromStore(), store1.getProducts());
	}
	
	@Test
	public
	String toString() {
		Islands island1 = new Islands("HomeIsland", store1);
		assertEquals(island1,"Welcome to " + island1.getName() + " this is number " + island1.getIslandNo() + " island on this planet, we have " +island1.getStore().getName()+ " store if you'd like to buy or sell items.");
		return island1.toString();
	}
	
	

}
