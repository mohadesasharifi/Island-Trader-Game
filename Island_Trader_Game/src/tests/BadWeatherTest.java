package tests;
import models.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class BadWeatherTest {

	@Test
	void assertEquals() {
		Ship newShip = new Ship("Falcon", 100, 10, 99.99, 80);
		Island newIsland = new Island();
		newIsland.setName("ISLAND");
		Trader newTrader = new Trader("Khan", 1000, newShip, newIsland);
		BadWeather badWeather = new BadWeather();
		badWeather.attack(newTrader);
		double damageTaken = newTrader.getCurretShip().getDamageStatus();
		double remainingDurability = newTrader.getCurretShip().getDurabilityPercentage();
		Assertions.assertEquals(damageTaken > 0.0 && remainingDurability < 80, true);	
		Assertions.assertEquals("Encountered Bad Weather", badWeather.getName());
	}
	

}
