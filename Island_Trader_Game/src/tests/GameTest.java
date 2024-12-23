package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dice_game.Game;
import models.Ship;
import models.Trader;

class GameTest {
	private Trader trader = new Trader();

	@Test
	void winner() {
		Game game = new Game(trader, "Andy");
		game.toString();
		assertEquals("Andy", "Andy");
		
	}
	
	@Test
	void asserEquals() {
		// TODO Auto-generated method stub
		Ship ship = new Ship();
		trader.setCurrentShip(ship);
		trader.getCurretShip().addCannons(1);
		
		Game game = new Game(trader, "Andy");
		String name = game.play();
		Assertions.assertEquals(name.equalsIgnoreCase("John") || name.equalsIgnoreCase("Andy"), false);
	}

}
