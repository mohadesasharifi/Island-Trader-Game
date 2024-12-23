package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import models.Island;
import models.Shark;
import models.Ship;
import models.Trader;

class SharkTest {

	@Test
	void assertEquals() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		Shark shark = new Shark();
		shark.attack(newTrader);
		double damageTaken = newTrader.getCurretShip().getDamageStatus();
		double remainingDurability = newTrader.getCurretShip().getDurabilityPercentage();
		Assertions.assertEquals(damageTaken > 0.0 && remainingDurability < 80, true);
		Assertions.assertEquals("Shark Attack", shark.getName());
		
	}

}
