package models;

import dice_game.Game;

/**
 * Pirates Attack Event
 * 
 * @author Zahid khan
 * 25/04/21
 */

public class Pirate extends Event{
	
	/**
	 * this is a constructor for Event Pirates, it sets the name of the Event and damage capability
	 */
	public Pirate() {
		super("Pirates", 0);
	}
	
	
	/**
	 * This method invokes pay dice game method and prints the outcome based on the winner of the game and 
	 * prints out the outcome of the game.
	 */
	@Override
	public String attack(Trader trader) {
		// TODO Auto-generated method stub
		String result = "Your ship is being attacked by \"Pirates\"\n";
		Game game = new Game (trader, super.getName());
		String winner = game.play();
		String[] checkWinner = winner.split("\n");
		
		result += winner;
		result += " is the Winner\n";
		
		if (checkWinner[checkWinner.length-1].equalsIgnoreCase(trader.getName())) {
			return result + "\nYou have defeted the Pirates...!!!";
		}else {
			double cost = 0;
			for (Item item: trader.allBoughtItems()) {
				cost += item.getBuyPrice();
			}
			if (cost > 50) {
				trader.setitemsBoughtToLost(cost);
				trader.getCurretShip().removeCannons(trader.getCurretShip().getNumberOfCannons());
//						return "Lost Cargo";
				return result + "\nYou have lost all of your cargo";
			}else{
				trader.getCurretShip().setDurability(0.0);
				trader.setCoins(0);
				trader.setProfit(0.0);
//						return "Walk Plank";
				return result + "\nYou goods were not enough, so pirates took all your coins and ship, and "
						+ "make you and your crew walk the plank.";
			}
		}
	}
}
