package elements;

public class BadWeather extends Event{
	
	public BadWeather() {
		super("Bad Weather", 50);
	}

	@Override
	public void attack(Trader trader) {
		int damage = (int)Math.floor(Math.random()*(50-30+1)+30);
		Ship traderShip = trader.getCurretShip();
		traderShip.takeDamage(damage);
		double damageTaken = (damage * 100) / traderShip.getMaxDurability();
		double remainingDurability = traderShip.getDurabilityPercentage();
		
		System.out.println(String.format("You ship took %.2f percent damage due to Bad Weather."
				+ "Your remaining durability is: %.2f percent", damageTaken, remainingDurability));
	}
}
