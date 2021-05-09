package tests;
import models.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemsTests {
	private	Item item1 = new Item("Strawberry", 4, 8, 100, "Travel");
	private Item item2 = new Item("Banana", 4, 8, 100, "Vege");
	private Item item3 = new Item("Pear", 4, 4, 100, "Upgrade");
	private Item item4 = new Item("Wheat", 4, 8, 100, "Fruit");
	
	
	@Test
	void getSellPrice() {
		assertEquals(8, item1.getSellPrice());
		assertEquals(4, item3.getSellPrice());
	}
	
	@Test
	void getBuyPrice() {
		assertEquals(4, item2.getBuyPrice());
		assertEquals(4, item4.getBuyPrice());
	}
	
	@Test
	void getQuantityAvailble() {
		assertEquals(100, item1.getQuantityAvailble());
		assertEquals(100, item3.getQuantityAvailble());
	}
	
	@Test
	void getProductName() {
		assertEquals("Banana", item2.getProductName());
		assertEquals("Wheat", item4.getProductName());
	}
	
	@Test
	void getType() {
		assertEquals("Travel", item1.getType());
		assertEquals("Upgrade", item3.getType());
		assertEquals("Fruit", item4.getType());
		assertEquals("Vege", item2.getType());
	}
	
	
	@Test
	void updateQuantity(){
		item2.updateQuantity(10);
		assertEquals(10, item2.getQuantityAvailble());
		
		item2.updateQuantity(10);
		assertEquals(10, item2.getQuantityAvailble());
		
		item2.updateQuantity(90);
		assertEquals(90, item2.getQuantityAvailble());
		
		item2.updateQuantity(90);
		assertEquals(90, item2.getQuantityAvailble());
	}
	
	
}
