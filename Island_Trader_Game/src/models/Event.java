package models;

public abstract class Event {
	private String eventName;
	private double damageCapability;
	
	
	public Event(String name, double damage) {
		eventName = name;
		//damageCapability = damage;
	}
	
	
	public String getName() {
		return eventName;
	}
	
	
	public double getDamageCapability() {
		return damageCapability;
	}
	
	
	public abstract void attack(Trader trader);
	
}
