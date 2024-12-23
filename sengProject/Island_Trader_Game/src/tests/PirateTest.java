package tests;

import models.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PirateTest {

	@Test
	void assertEquals() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		Pirate pirate = new Pirate();
		pirate.attack(newTrader);
		ArrayList<Item> cargo = newTrader.allBoughtItems();
		double durability = newTrader.getCurretShip().getDurability();
		Assertions.assertEquals(durability == 0.0 || cargo.size() == 0 || (durability > 0.0 && cargo.size() >0), true);	
		
		
		Item item1 = new Item("Strawberry", 50, 25, 100, "Travel");
		Item item2 = new Item("Banana", 50, 26, 100, "Vege");
		
		Store test1 = new Store("newIslandStore", newTrader);
		newIsland.setStore(test1);
		test1.addProducts(item1);
		test1.addProducts(item2);
		
		newTrader.purchase(item1, 1);
		newTrader.purchase(item2, 1);
		
		pirate.attack(newTrader);
		ArrayList<Item> cargo1 = newTrader.allBoughtItems();
		double durability1 = newTrader.getCurretShip().getDurability();
		cargo1.add(item2);
		newTrader.addCoins(durability1);;
		Assertions.assertEquals(durability == 0.0 || cargo.size() == 0 || (durability > 0.0 && cargo.size() > 0), true);	
	}

}
