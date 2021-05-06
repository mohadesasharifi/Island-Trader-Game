package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import elements.RescueSailors;
import elements.Islands;
import elements.Ship;
import elements.Trader;

class RescueSailorsTest {

	@Test
	void assertEquals() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Islands newIsland = new Islands();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		RescueSailors rescue = new RescueSailors();
		rescue.attack(newTrader);
		Assertions.assertEquals(newTrader.getCoins() > 1000, true);	
	}

}
