package Tests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import elements.*;


class TraderTests {
	
	@Test
	void getName() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		
		assertEquals("Khan", newTrader.getName());
	}
	
	@Test
	void getCurrentShip() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Ship newShip2 = new Ship("Heavey Duty", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		newTrader.setCurrentShip(newShip2);
		assertEquals(newShip2, newTrader.getCurretShip());
	}
	
	@Test
	void getDays() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		
		newTrader.setDaysToPlay(10);
		
		assertEquals(10, newTrader.getDays());
	}
	
	@Test
	void getHomeIsland() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		
		assertEquals(newIsland, newTrader.gethomeIsland());
	}
	
	
	@Test
	void sellItem() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 150, newShip, newIsland);
		newTrader.setCurrentShip(newShip);
		Store test1 = new Store("newIslandStore");
		
		Items item1 = new Items("Towel", 4, 8, 100, "Travel");
		Items item2 = new Items("Onion", 4, 8, 100, "Vege");
		Items item3 = new Items("Pear", 4, 8, 10, "Fruit");
		Items item4 = new Items("Cargo Space", 4, 8, 10, "Upgrade");
		
		newIsland.setStore(test1);
		
		test1.addProducts(item1);
		test1.addProducts(item2);
		test1.addProducts(item3);
		test1.addProducts(item4);
		
		newTrader.purchase(item2, 5);
		newTrader.purchase(item1, 2);
		newTrader.purchase(item3, 3);
		newTrader.purchase(item4, 5);
		
		assertEquals(30.0, newTrader.getCoins(), 0.1);
		assertEquals(item2.getProductName(), newTrader.findItem(item2.getProductName()).getProductName());
		assertEquals(item1.getProductName(), newTrader.findItem(item1.getProductName()).getProductName());
		assertEquals(189.9, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		
		newTrader.sellItem(item1, 2);
		assertEquals(item1.getProductName(), newTrader.allSoldItems().get(0).getProductName());
		assertEquals(191.9, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		assertEquals(0, newTrader.findItem(item1.getProductName()).getQuantityAvailble());
		
		newTrader.sellItem(item3, 2);
		assertEquals(4, newTrader.allBoughtItems().size());
		assertEquals(1, newTrader.findItem(item3.getProductName()).getQuantityAvailble());
		assertEquals(2, newTrader.allSoldItems().get(1).getQuantityAvailble());
		
		newTrader.sellItem(item2, 5);
		assertEquals(66, newTrader.getCoins(), 0.1);
		assertEquals(198.9, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		assertEquals(-36, newTrader.getProfit(), 0.1);
	}
	
	
	@Test
	void purchase() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 150, newShip, newIsland);
		newTrader.setCurrentShip(newShip);
		Store test1 = new Store("newIslandStore");
		
		Items item1 = new Items("Strawberry", 4, 8, 100, "Travel");
		Items item2 = new Items("Banana", 4, 8, 100, "Vege");
		Items item3 = new Items("Engine", 4, 8, 10, "Upgrade");
		Items item4 = new Items("Cargo Space", 4, 8, 10, "Upgrade");
		
		newIsland.setStore(test1);
		
		test1.addProducts(item1);
		test1.addProducts(item2);
		test1.addProducts(item3);
		test1.addProducts(item4);
		
		//System.out.println(newTrader.getCurretShip().getAvailableStorage());
		
		newTrader.purchase(item2, 5);
		newTrader.purchase(item1, 2);
		item2.updateQuantity(5);
		
		
		assertEquals(94.0, newTrader.getCoins(), 0.1);
		assertEquals(item2.getProductName(), newTrader.findItem(item2.getProductName()).getProductName());
		assertEquals(item1.getProductName(), newTrader.findItem(item1.getProductName()).getProductName());
		assertEquals(93, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		newTrader.sellItem(item1, 2);
		assertEquals(item2.getProductName(), newTrader.allBoughtItems().get(0).getProductName());
		assertEquals(95, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		
		newTrader.purchase(item3, 2);
		assertEquals(3, newTrader.allBoughtItems().size());
		assertEquals(95, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		assertEquals(120, newTrader.getCurretShip().getSpeed(), 0.1);
		
		newTrader.purchase(item4, 5);
		assertEquals(194.99, newTrader.getCurretShip().getAvailableStorage(), 0.01);
		
	}
	
	
	
	

}
