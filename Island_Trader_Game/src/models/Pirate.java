package models;

import dice_game.Game;

public class Pirate extends Event{
	
	public Pirate() {
		super("Pirates", 0);
	}
	
	public void attack(Trader trader) {
		System.out.println("Your ship is being attacked by \"Pirates\"\n"
						  + "To defete pirates you must win the Dice Roll game.");
		Game game = new Game (trader.getName(), super.getName());
		String winner = game.play();
		
		if (winner == trader.getName()) {
			System.out.println("\n" + trader.getName() + ", you have successfully defeted the pirates!!! Yay...\n");
		}else {
			System.out.println("\nYou lost the game, pirates have boarded your ship.\n");
			double cost = 0;
			for (Item item: trader.allBoughtItems()) {
				cost += item.getBuyPrice();
			}
			if (cost > 50) {
				trader.setitemsBoughtToLost(cost);
				trader.getCurretShip().removeCannons(trader.getCurretShip().getNumberOfCannons());
				System.out.println("You have lost all of your cargo");
			}else{
				System.out.println("You goods were not enough, so pirates tooK all your coins and ship, and "
						+"make you and your crew walk the plank.");
				trader.getCurretShip().setDurability(0.0);
				trader.setCoins(0);
			}
		}
	}
}
