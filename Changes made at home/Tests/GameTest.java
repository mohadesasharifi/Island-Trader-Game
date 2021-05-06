package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dice_game.Dice;
import dice_game.Game;
import dice_game.Player;

class GameTest {

	@Test
	void winner() {
		Game game = new Game("John", "Andy");
		assertEquals(game.winner(), null);
		
	}
	
	@Test
	void asserEquals() {
		// TODO Auto-generated method stub
		Game game = new Game("John", "Andy");
		String name = game.play();
		Assertions.assertEquals(name.equalsIgnoreCase("John") || name.equalsIgnoreCase("Andy"), true);
	}

}
