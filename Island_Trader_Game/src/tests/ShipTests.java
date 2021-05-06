package tests;
import models.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ShipTests {
	
	@Test
	void setName() {
		Ship newShip = new Ship("Falcon", 150, 10, 800, 100);
		newShip.setName("YOLO");
		assertEquals("YOLO", newShip.getName());
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
		newShip.upgradeCargoCapacity(555.50);
		assertEquals(555.50, newShip.getAvailableStorage());
	}
	
	
	@Test
	void setDurability() {
		Ship newShip = new Ship("Falcon", 150, 10, 800, 100);
		newShip.setDurability(150);
		assertEquals(150, newShip.getDurability());
	}
	
	

}
