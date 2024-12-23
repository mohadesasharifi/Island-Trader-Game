package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import models.RescueSailors;
import models.Island;
import models.Ship;
import models.Trader;

class RescueSailorsTest {

	@Test
	void assertEquals() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		RescueSailors rescue = new RescueSailors();
		rescue.attack(newTrader);
		Assertions.assertEquals(newTrader.getCoins() > 1000, true);
		Assertions.assertEquals("Rescue Sailors", rescue.getName());
	}

}
