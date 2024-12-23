package models;
/**
 * Bad Weather Event
 * 
 * @author Zahid khan
 * 25/04/21
 */

public class BadWeather extends Event{
	/**
	 * this is a constructor for Bad Weather Event which uses parent class Event to set Name and Damage Capability
	 */
	public BadWeather() {
		super("Bad Weather", 50);
	}
	
	/**
	 * this method returns a String with the name of the Event
	 * @return String Name of Event
	 */
	public String getName() {
		return "Encountered Bad Weather";
	}
	
	/**
	 * method attack that does the damage to the traders' ship and prints out the outcome
	 */
	@Override
	public String attack(Trader trader) {
		int damage = (int)Math.floor(Math.random()*(50-30+1)+30);
		Ship traderShip = trader.getCurretShip();
		traderShip.takeDamage(damage);
		double damageTaken = (damage * 100) / traderShip.getMaxDurability();
		double remainingDurability = traderShip.getDurabilityPercentage();
		return String.format("Your ship took %.2f percent damage due to Bad Weather."
				+ "Your remaining durability is: %.2f percent", damageTaken, remainingDurability);
	}
	
}
