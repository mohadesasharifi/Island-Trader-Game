package elements;

public class Shark extends Event{
	
	public Shark() {
		super("Sharks", 20);
	}

	@Override
	public void attack(Trader trader) {
		int damage = (int)Math.floor(Math.random()*(30-20+1)+20);
		Ship traderShip = trader.getCurretShip();
		traderShip.takeDamage(damage);
		double damageTaken = (damage * 100) / traderShip.getMaxDurability();
		double remainingDurability = traderShip.getDurabilityPercentage();
		
		System.out.println(String.format("You ship took %.2f percent damage due to Shark attacks."
				+ "Your remaining durability is: %.2f percent", damageTaken, remainingDurability));
	}
}
