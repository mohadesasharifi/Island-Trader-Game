package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dice_game.Player;

class PlayerTest {

	@Test
	void updateScore() {
		 Player player = new Player(1, "John");
		 player.updateScore(10);
		 assertEquals(player.getScore(), 10);
		 player.updateScore(29);
		 assertEquals(player.getScore(), 39);
		 player.updateScore(20);
		 assertEquals(player.getScore(), 59);
	}
	
	@Test
	void getScore() {
		Player player = new Player(1, "John");
		assertEquals(player.getScore(), 0);
		Player player2 = new Player(2, "John");
		assertEquals(player.getScore(), 0);
	}
	
	@Test
	public
	void string() {
		Player player = new Player(1, "John");
		assertEquals("John [0]", player.toString());
	}
	
	@Test
	void name() {
		Player player = new Player(1, "John");
		assertEquals("John", player.getName());
	}
	
}
