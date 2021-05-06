package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import elements.Islands;
import elements.Shark;
import elements.Ship;
import elements.Trader;

class SharkTest {

	@Test
	void assertEquals() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		Shark shark = new Shark();
		shark.attack(newTrader);
		double damageTaken = newTrader.getCurretShip().getDamageStatus();
		double remainingDurability = newTrader.getCurretShip().getDurabilityPercentage();
		Assertions.assertEquals(damageTaken > 0.0 && remainingDurability < 80, true);	
	}

}
