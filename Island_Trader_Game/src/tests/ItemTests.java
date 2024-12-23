package tests;
import models.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTests {
	private	Item item1 = new Item("Strawberry", 4, 8, 100, "Travel");
	private Item item2 = new Item("Banana", 4, 8, 100, "Vege");
	private Item item3 = new Item("Pear", 4, 4, 100, "Upgrade");
	private Item item4 = new Item("Wheat", 4, 8, 100, "Fruit");
	
	
	@Test
	void getSellPrice() {
		item1.setSellPrice(10);
		assertEquals(10, item1.getSellPrice());
		assertEquals(4, item3.getSellPrice());
		item3.setSoldTo("YourStore");
		assertEquals("YourStore", item3.getSoldTo());
		assertEquals(59, item1.storesSaleDispaly().length());
		assertEquals(82, item3.saleDetails().length());
		assertEquals(75, item2.toString().length());
	}
	
	@Test
	void getBuyPrice() {
		item2.setBoughtFrom("MyStore");
		assertEquals("MyStore", item2.getBoughtFrom());
		item2.setBuyPrice(5);
		assertEquals(5, item2.getBuyPrice());
		assertEquals(4, item4.getBuyPrice());
		
		assertEquals(82, item2.buyDetails().length());
		assertEquals(79, item2.buyDetailsUpgrades().length());
		
	}
	
	@Test
	void getQuantityAvailble() {
		item1.setQuantity(100);
		assertEquals(100, item1.getQuantityAvailble());
		assertEquals(100, item3.getQuantityAvailble());
		item1.setUnits("KG");
		assertEquals("KG", item1.getUnits());
	}
	
	@Test
	void getProductName() {
		assertEquals("Banana", item2.getProductName());
		assertEquals("Wheat", item4.getProductName());
	}
	
	@Test
	void getType() {
		item1.setType("Travel");
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
