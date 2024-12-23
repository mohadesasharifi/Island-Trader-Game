package models;

/**
 * This is an abstract class for Events, it's implemented by the sub-events.
 * @author Zahid Khan
 *
 */
public abstract class Event {
	//this is a variable to store the name of the event
	private String eventName;
	//this is a variable to store the damage capability of events
	private double damageCapability;
	
	/**
	 * a constructor to an abstract class which is extended by the events
	 * @param name
	 * @param damage
	 */
	public Event(String name, double damage) {
		eventName = name;
		damageCapability = damage; 
	}
	
	/**
	 * this method returns the name of the event
	 * @return type String
	 */
	public String getName() {
		return eventName;
	}
	
	/**
	 * this method returns the damage capability of an event
	 * @return damageCapability of type Double
	 */
	public double getDamageCapability() {
		return damageCapability;
	}
	
	/**
	 * abstract methods to be implemented by the classes that extend Event
	 * @param trader of type Trader
	 */
	public abstract String attack(Trader trader);
	
}
