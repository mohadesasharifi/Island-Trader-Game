package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import models.*;
import main.LoadGameData;


class TraderTests {
	Trader trader = new Trader();
	LoadGameData data = new LoadGameData();
	// initialization of islands contain all the islands
	private ArrayList<Island> islands = data.createAllIslands(trader);
	
	@Test
	void getName() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		assertEquals("Khan", newTrader.getName());
	}
	
	@Test
	void setName() {
		Trader testTrader = new Trader();
		testTrader.setName("Zahid");
		assertEquals("Zahid", testTrader.getName());
	}
	
	
	@Test
	void setHomeIsland(){
		trader.setHomeIsland(islands.get(0));
		assertEquals(islands.get(0), trader.getCurrentIsland());
	}
	
	@Test
	void setCurrentIsland() {
		trader.setCurrentIsland(islands.get(0));
		assertEquals(islands.get(0), trader.getCurrentIsland());
	}
	@Test
	void setCoins() {
		trader.setCoins(100);
		assertEquals(100, (int) trader.getCoins());
	}
	
	
	@Test
	void addTotalTravelCost(){
		trader.addTotalTravelCost(100);
		assertEquals(100, (int) trader.getTotalTravelCost());
	}

	@Test
	void getCurrentShip() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Ship newShip2 = new Ship("Heavey Duty", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		newTrader.setCurrentShip(newShip2);
		assertEquals(newShip2, newTrader.getCurretShip());
	}
	
	@Test
	void getDays() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		
		newTrader.setDaysToPlay(10);
		assertEquals(10, (int) newTrader.getDays());
	}
	
	@Test
	void getHomeIsland() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		
		assertEquals(newIsland, newTrader.gethomeIsland());
	}
	
	
	@Test
	void sellItem() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 150, newShip, newIsland);
		newTrader.setCurrentShip(newShip);
		Store test1 = new Store("newIslandStore", newTrader);
		
		assertEquals("You have not sold anything yet", newTrader.getSoldItems());
		
		
		Item item1 = new Item("Towel", 4, 8, 100, "Travel");
		Item item2 = new Item("Onion", 4, 8, 100, "Vege");
		Item item3 = new Item("Pear", 4, 8, 10, "Fruit");
		Item item4 = new Item("Cargo Space", 4, 8, 10, "Upgrade");
		
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
		
		assertEquals("You don't have enough "+item1.getProductName(), newTrader.sellItem(item1, 1));
		
		newTrader.sellItem(item3, 2);
		assertEquals(3, newTrader.allBoughtItems().size());
		assertEquals(1, newTrader.findItem(item3.getProductName()).getQuantityAvailble());
		assertEquals(2, newTrader.allSoldItems().get(1).getQuantityAvailble());
		
		newTrader.sellItem(item2, 5);
		assertEquals(66, newTrader.getCoins(), 0.1);
		assertEquals(198.9, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		assertEquals(-36, newTrader.getProfit(), 0.1);
		assertEquals(258, newTrader.getSoldItems().length());
	}
	
	
	@Test
	void purchase() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 150, newShip, newIsland);
		newTrader.setCurrentShip(newShip);
		
		
		String output = newTrader.getBoughtItems();
		assertEquals("You have not purchased anything yet.", output);
		
		Store test1 = new Store("newIslandStore", newTrader);
		
		Item item1 = new Item("Strawberry", 4, 8, 100, "Travel");
		Item item2 = new Item("Banana", 4, 8, 100, "Vege");
		Item item3 = new Item("Engine", 4, 8, 10, "Upgrade");
		Item item4 = new Item("Cargo Space", 4, 8, 10, "Upgrade");
		
		newIsland.setStore(test1);
		
		test1.addProducts(item1);
		test1.addProducts(item2);
		test1.addProducts(item3);
		test1.addProducts(item4);
		
		//System.out.println(newTrader.getCurretShip().getAvailableStorage());
		
		newTrader.purchase(item2, 5);
		newTrader.purchase(item1, 2);
		newTrader.purchase(item2, 5);
		item2.updateQuantity(5);
		
		
		assertEquals(54.0, newTrader.getCoins(), 0.1);
		assertEquals(item2.getProductName(), newTrader.findItem(item2.getProductName()).getProductName());
		assertEquals(item1.getProductName(), newTrader.findItem(item1.getProductName()).getProductName());
		assertEquals(87.99, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		newTrader.sellItem(item1, 2);
		assertEquals(item2.getProductName(), newTrader.allBoughtItems().get(0).getProductName());
		assertEquals(89.99, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		
		newTrader.purchase(item3, 2);
		assertEquals(2, newTrader.allBoughtItems().size());
		assertEquals(89.99, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		assertEquals(120, newTrader.getCurretShip().getSpeed(), 0.1);
		
		newTrader.purchase(item4, 5);
		assertEquals(189.99, newTrader.getCurretShip().getAvailableStorage(), 0.01);
		assertEquals(177,  newTrader.getBoughtItems().length());
		assertEquals(2, newTrader.getBoughtList().size());
	}
	
	@Test
	void setitemsBoughtToLost() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 150, newShip, newIsland);
		newTrader.setCurrentShip(newShip);
		Store test1 = new Store("newIslandStore", newTrader);
		
		Item item1 = new Item("Strawberry", 4, 8, 100, "Travel");
		Item item2 = new Item("Banana", 4, 8, 100, "Vege");
		Item item3 = new Item("Cannon", 4, 8, 10, "Upgrade");
		Item item4 = new Item("Durability", 4, 8, 10, "Upgrade");
		
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
		assertEquals(2, newTrader.allBoughtItems().size());
		assertEquals(95, newTrader.getCurretShip().getAvailableStorage(), 0.1);
		assertEquals(100, newTrader.getCurretShip().getSpeed(), 0.1);
		
		newTrader.purchase(item4, 5);
		assertEquals(94.99, newTrader.getCurretShip().getAvailableStorage(), 0.01);
		newTrader.setitemsBoughtToLost(100);
	}
	
	@Test
	void purchaseUnsuccessfull() {
		trader.addCoins(100);
		String output = trader.unsuccessfullSale("Error");
		assertEquals("Error", output);
	}
	
	
	
}
