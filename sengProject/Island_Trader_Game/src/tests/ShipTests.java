package tests;
import models.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ShipTests {
	Trader trader = new Trader();
	Item upgarde = new Item("new Upgrades");
	
	@Test
	void setName() {
		Ship testShip = new Ship();
		testShip.getAvailableStorage();
		Ship newShip = new Ship("Falcon", 150, 10, 800, 100);
		newShip.setName("YOLO");
		assertEquals("YOLO", newShip.getName());
		assertEquals(150, (int) newShip.getBaseSpeed());
		assertEquals(150, (int) newShip.getSpeed());
		newShip.setShipOwner(trader);
		assertEquals(trader, newShip.getShipOwner());
		newShip.loadProducts(801);
		assertEquals(800, newShip.getBaseCargoCapacity());
		assertEquals(0, newShip.getAvailableStorage());
		newShip.addCannons(5);
		assertEquals(5, newShip.getNumberOfCannons());
		newShip.removeCannons(3);
		assertEquals(2, newShip.getNumberOfCannons());
		newShip.unloadProducts(400);
		assertEquals(400, newShip.getAvailableStorage());
		newShip.upgradeDurability(2);
		assertEquals(120, newShip.getDurability());
		assertEquals(100, newShip.getDurabilityPercentage());
		assertEquals(120, newShip.getMaxDurability());
		assertEquals(100, newShip.getBaseDurability());
		newShip.takeDamage(121);
		assertEquals(0, newShip.getDurability());
		assertEquals(121, newShip.getDamageStatus());
		assertEquals(5, newShip.getDailyWage());
		newShip.repairShip(trader);
		assertEquals(100, newShip.getDurabilityPercentage());
		newShip.upgradeSpeed(2);
		assertEquals(170, newShip.getSpeed());
		
		assertEquals(35, newShip.showUpgrades().length());
		assertEquals(35, newShip.showUpgradesGUI().length());
		newShip.addInstallUpgrades(upgarde);
		assertEquals(78, newShip.showUpgrades().length());
		assertEquals(74, newShip.showUpgradesGUI().length());
		assertEquals(150, newShip.shipStatus().length());
		assertEquals(131, newShip.toString().length());
		
	}
	
	
	@Test
	void setSpeed() {
		Ship newShip = new Ship("Falcon", 150, 10, 800, 100);
		newShip.setSpeed(100);
		assertEquals(100, newShip.getSpeed());
	}
	
	@Test
	void setCrew() {
		Ship newShip = new Ship("Falcon", 150, 10, 800, 100);
		newShip.setCrew(5);
		assertEquals(5, newShip.getCrew());
	}
	
	
	@Test
	void setCargoCapacity() {
		Ship newShip = new Ship("Falcon", 150, 10, 800, 100);
		newShip.upgradeCargoCapacity(10.);
		assertEquals(1000, newShip.getAvailableStorage());
	}
	
	
	@Test
	void setDurability() {
		Ship newShip = new Ship("Falcon", 150, 10, 800, 100);
		newShip.setDurability(150);
		assertEquals(150, newShip.getDurability());
	}
	
	

}
