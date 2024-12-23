package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import models.*;

class IslandTests {
	Trader trader = new Trader();
	Item item1 = new Item("Strawberry", 4, 8, 100, "Vege");
	Item item2 = new Item("Banana", 4, 8, 100, "Fruit");
	Item item3 = new Item("Pear", 4, 8, 100, "Upgrade");
	Item item4 = new Item("Wheat", 4, 8, 100, "Vege");
	Item item5 = new Item("Apple", 4, 8, 100, "Fruit");
	Item item6 = new Item("Orange", 4, 8, 100, "Vege");
	Item item7 = new Item("katsu Chicken", 4, 8, 100, "Travel");

	Store store1 = new Store("Kmart", trader);
	Store store2 = new Store("WareHouse", trader);
	
	
	@Test
	void setName() {
		Island island1 = new Island("HomeIsland", store1);
		Island island2 = new Island();
		island1.setName("FantasyIsland");
		assertEquals("FantasyIsland", island1.getName());
		island2.setName("Moonrise Kingdom");
		assertEquals("Moonrise Kingdom", island2.getName());
		island1.setIslandDescription("Yolo");
		assertEquals("Yolo", island1.getIslandDescription());
		assertEquals(89, island1.getIslandNo());
	}
	
	@Test
	void setStore() {
		Island island1 = new Island();
		island1.setStore(store1);
		assertEquals("Kmart", island1.getStore().getName());
		assertEquals(store1.getProducts(), island1.getStore().getProducts());
		Island island2 = new Island("MyIsland", store1);
		island2.setStore(store2);
		assertEquals(store2.getName(), island2.getStore().getName());
		assertEquals(store2.getProducts(), island2.getStore().getProducts());	
	}
	
	
	@Test
	void getName() {
		Island island1 = new Island("HomeIsland", store1);
		Island island2 = new Island();
		island2.setName("HomeIsland");
		assertEquals(island1.getName(), "HomeIsland");
		assertEquals(island2.getName(), "HomeIsland");
	}
	
	@Test
	void getStore(){
		Island island1 = new Island("HomeIsland", store1);
		assertEquals(island1.getStore(), store1);
		Island island2 = new Island();
		island2.setStore(store2);
		assertEquals(island2.getStore(), store2);
	}
	
	@Test
	void addProductsToStore(){
		Island island1 = new Island("HomeIsland", store1);
		island1.addProductsToStore(item1);
		island1.addProductsToStore(item2);
		assertEquals(island1.getStore().getProducts(), store1.getProducts());
		Island island2 = new Island();
		island2.setStore(store1);
		island2.addProductsToStore(item3);
		assertEquals(island2.getStore().getProducts(), store1.getProducts());
		island2.setStore(store2);
		island2.addProductsToStore(item4);
		assertEquals(island2.getStore().getProducts(), store2.getProducts());
	}
	
	@Test
	void setSpecialityItem() {
		Island island1 = new Island("HomeIsland", store1);
		island1.setSpecialityItem("banana");
		assertEquals("banana", island1.getSpecialityItem());
		Island island2 = new Island();
		island2.setSpecialityItem("apple");
		assertEquals(island2.getSpecialityItem(), "apple");
	}
	
	@Test
	void getSpecialityItem() {
		Island island1 = new Island("HomeIsland", store1);
		island1.setSpecialityItem("banana");
		assertEquals("banana", island1.getSpecialityItem());
		Island island2 = new Island();
		island2.setSpecialityItem("apple");
		assertEquals(island2.getSpecialityItem(), "apple");
	}
	
	
	@Test
	void getItemsFromStore() {
		Island island1 = new Island("HomeIsland", store1);
		island1.addProductsToStore(item1);
		island1.addProductsToStore(item2);
		assertEquals(island1.getItemsFromStore(), store1.getProducts());
		Island island2 = new Island();
		island2.setStore(store1);
		island2.addProductsToStore(item3);
		assertEquals(island2.getItemsFromStore(), store1.getProducts());
		assertEquals(132, island1.toString().length());
	}
	
	
	

}
