package models;


/**
 * Rescue Sailors Event
 * 
 * @author Zahid khan
 * 25/04/21
 */

public class RescueSailors extends Event{
	/**
	 * constructor for Event rescue sailors
	 */
	public RescueSailors() {
		super("Rescue Sailors", 0);
	}
	
	/**
	 * returns the name of the Event
	 * @return type String
	 */
	public String getName() {
		return "Rescue Sailors";
	}
	
	/**
	 * this is implementation of an abstract method in parent class
	 * this method invokes the the attack of the Event and prompt user to decide if they want to save the stranded sailor (generated randomly)
	 * or not, this method does not do any damage to the ship, but if user decides to save the sailors they reward him 2 coins
	 * per sailor
	 */
	@Override
	public String attack(Trader trader) {
		
		int sailors = (int)Math.floor(Math.random()*(30-10+1)+10);
		trader.addCoins(sailors * 2);
		return (String.format("you've been rewared with %d coins for rescuing %s sailor/s.", sailors * 2, sailors));
	}
	
	
}
