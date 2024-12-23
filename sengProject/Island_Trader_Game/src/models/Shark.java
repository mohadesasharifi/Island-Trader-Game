package models;

/**
 * Shark Attack Event
 * 
 * @author Zahid khan
 * 25/04/21
 */

public class Shark extends Event{
	
	/**
	 * constructor for Event sharks, and it sets the name and damage capability of event Shark
	 */
	public Shark() {
		super("Sharks", 20);
	}
	
	/**
	 * this method returns the name of the string
	 */
	@Override
	public String getName() {
		return "Shark Attack";
	}
	
	/**
	 * this method invokes the attack of Shark event and does the damage to the ship randomly
	 * it doens't return anything but it prints out the outcome of the event
	 */
	@Override
	public String attack(Trader trader) {
		// TODO Auto-generated method stub
		int damage = (int)Math.floor(Math.random()*(30-20+1)+20);
		Ship traderShip = trader.getCurretShip();
		traderShip.takeDamage(damage);
		double damageTaken = (damage * 100) / traderShip.getMaxDurability();
		double remainingDurability = traderShip.getDurabilityPercentage();
		
		return String.format("Your ship took %.2f percent damage due to Shark attacks."
				+ "Your remaining durability is: %.2f percent", damageTaken, remainingDurability);
	}
	
}
