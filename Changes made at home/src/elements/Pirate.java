package elements;

import dice_game.Game;

public class Pirate extends Event{
	
	public Pirate() {
		super("Pirates", 0);
	}
	
	public void attack(Trader trader) {
		//Run the game
		Game game = new Game (trader.getName(), super.getName());
		String winner = game.play();
		
		if (winner == trader.getName()) {
			System.out.println(trader.getName() + " has won and defeted the pirates!!! Yay...");
		}else {
			double cost = 0;
			for (Items item: trader.allBoughtItems()) {
				cost += item.getBuyPrice();
			}
			if (cost > 50) {
				trader.setitemsBoughtToLost(cost);	
			}else{
				System.out.println("You goods were not enough, so pirates tool all your coins and ship, and "
						+"make you and your crew walk the plank.");
				trader.getCurretShip().setDurability(0.0);
				trader.setCoins(0);
			}
		}
	}
}
