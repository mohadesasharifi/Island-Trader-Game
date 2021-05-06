package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import elements.Pirate;
import elements.Islands;
import elements.Items;
import elements.Ship;
import elements.Store;
import elements.Trader;

class PirateTest {

	@Test
	void assertEquals() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		Pirate pirate = new Pirate();
		pirate.attack(newTrader);
		ArrayList<Items> cargo = newTrader.allBoughtItems();
		double durability = newTrader.getCurretShip().getDurability();
		Assertions.assertEquals(durability ==0.0 || cargo.size() ==0 || (durability > 0.0 && cargo.size() >0), true);	
		
		Ship newShip1 = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland1 = new Islands();
		newIsland.setName("Home");
		Trader newTrader1 = new Trader("msh", 1000, newShip, newIsland);
		
		Items item1 = new Items("Strawberry", 50, 25, 100, "Travel");
		Items item2 = new Items("Banana", 50, 26, 100, "Vege");
		
		Store test1 = new Store("newIslandStore", newTrader);
		newIsland.setStore(test1);
		test1.addProducts(item1);
		test1.addProducts(item2);
		
		newTrader.purchase(item1, 1);
		newTrader.purchase(item2, 1);
		
		pirate.attack(newTrader);
		ArrayList<Items> cargo1 = newTrader.allBoughtItems();
		double durability1 = newTrader.getCurretShip().getDurability();
		Assertions.assertEquals(durability ==0.0 || cargo.size() ==0 || (durability > 0.0 && cargo.size() >0), true);	
	}

}
