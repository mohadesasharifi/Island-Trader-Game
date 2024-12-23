package tests;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import models.BadWeather;
import models.Event;
import models.Island;
import models.Pirate;
import models.RescueSailors;
import models.Shark;
import models.Ship;
import models.Trader;

class LoadGameDataTest {

	Ship ship = new Ship();
	Island island = new Island();
	
	@Test
	void createTrader() {
		Trader trader = new Trader();
		trader.setName("Zahid");
		trader.setDaysToPlay(25);
		trader.setCurrentShip(ship);
		trader.setHomeIsland(island);
		
	}
	
	@Test
	void createEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		BadWeather badweather = new BadWeather();
		Shark sharkAttack = new Shark();
		Pirate pirateAttack = new Pirate();
		RescueSailors rescue = new RescueSailors();
		events.add(pirateAttack);
		events.add(badweather);
		events.add(sharkAttack);
		events.add(rescue);
		events.add(null);
		events.add(null);
	}

}
