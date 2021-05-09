package models;
import java.util.ArrayList;

import validations.InputException;

public class Ship {
	private String name;
	private double baseSpeed;
	private double speed;
	private int crew;
	private double baseCargoCapacity;
	private double cargoCapacity;
	private double damageStatus;
	private double baseDurability;
	private double durability;
	private double maxDurability;
	private double wagePerDay;
	private double INDIVIDUALWAGE = 0.5;
	private double REPAIRCOST = 1.0;
	private int cannons = 0;
	private Trader owner;
	// ArrayList of type Item to contains all the details of upgrades
	private ArrayList<Item> istalledUpgrades = new ArrayList<Item>();
	
	
	public Ship() {};
	
	
	public Ship(String tempName, double tempSpeed, int tempCrew,
			double tempCargoCapacity, double tempDurability) {
		name = tempName;
		speed = tempSpeed;
		crew = tempCrew;
		cargoCapacity = tempCargoCapacity;
		durability = tempDurability;
		maxDurability = tempDurability;
		wagePerDay = INDIVIDUALWAGE * tempCrew;
		damageStatus = 0.0;
		baseSpeed = tempSpeed;
		baseCargoCapacity = tempCargoCapacity;
		baseDurability = tempDurability;
		
	}
	
	public void setName(String ship) {
		name = ship;
	}
	
	public void setSpeed(double tempSpeed) {
		speed = tempSpeed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	
	public double getBaseSpeed() {
		return baseSpeed;
	}
	
	public void upgradeSpeed(double quantity) {
		speed += (quantity * 10);
	}
	
	public int getCrew() {
		return crew;
	}
	
	public void setCrew(int tempCrew) {
		crew = tempCrew;
		wagePerDay = INDIVIDUALWAGE * tempCrew;
	}
	
	public void setShipOwner(Trader trader) {
		owner = trader;
	}
	
	public Trader getShipOwner() {
		return owner;
	}
	
	
	public void addInstallUpgrades(Item newItem) {
		istalledUpgrades.add(newItem);
	}
	
	
	public void upgradeCargoCapacity(double tempCargoCapacity) {
		cargoCapacity += (tempCargoCapacity * 20);
	}
	
	public double getAvailableStorage() {
		if (cargoCapacity < 0) {
			cargoCapacity = 0;
		}
		return cargoCapacity;
	}
	
	
	public double getBaseCargoCapacity() {
		return baseCargoCapacity;
	}
	
	
	public void addCannons(int nums) {
		cannons += nums;
	}
	
	
	public void removeCannons(int nums) {
		if (nums > 0 && nums <= getNumberOfCannons()) {
			cannons -= nums;
		}else{
			throw new InputException("Number of Cannons must be between 0 and "+getNumberOfCannons());
		}
		
	}
	
	
	public int getNumberOfCannons() {
		return cannons;
	}
	
	public void loadProducts(int quantity) {
		cargoCapacity -= (double)quantity;
	}
	
	public void unloadProducts(int quantity) {
		cargoCapacity += (double)quantity;
	}
	
	public void setDurability(double tempDurability) {
		durability = tempDurability;
		maxDurability = tempDurability;
		damageStatus = 0.0;
	}
	
	
	public void upgradeDurability(double tempDurability) {
		maxDurability += (tempDurability * 10);
		durability += (tempDurability * 10);
	}
	
	
	public void addDurability(double newDurability) {
		if(maxDurability >= (durability + newDurability)){
			durability += newDurability;
			damageStatus -= newDurability;
		}else{
			durability = maxDurability;
			damageStatus = 0;
		}
	}
	
	public double getDurability() {
		if (durability < 0) {
			durability = 0;
		}
		return durability;
	}
	
	public double getDurabilityPercentage() {
		double current = getDurability();
		double result = (current * 100) / maxDurability;
		return result;
	}
	
	public double getMaxDurability() {
		return maxDurability;
	}
	
	
	public double getBaseDurability() {
		return baseDurability;
	}
	
	
	public double takeDamage(double tempDamage) {
		damageStatus += tempDamage;
		durability -= tempDamage;
		return durability;
	}
	
	public double getDamageStatus() {
		return damageStatus;
	}
	
	
	public String getName() {
		return name;
	}
	
	public double getDailyWage() {
		return wagePerDay;
	}
	
	public String repairShip(Trader trader) {
		double coinsNeeded = this.getMaxDurability() - this.getDurability();
		trader.subtractCoins(coinsNeeded);
		double newDurability = coinsNeeded * REPAIRCOST;
		addDurability(newDurability);
		return "Ship repaired, remaining durability is : " + getDurabilityPercentage() +" percent.\n"
				+ "Cost to repair was: "+ coinsNeeded + " Coins\n";
	}
	
	
	
	public String showUpgrades() {
		String result = "";
		for (Item item: istalledUpgrades) {
			result += item.buyDetails() + "\n";
		}
		return result;
		
//		return (String.format("Factory Speed: %.2f \t\t\t Upgraded Speed: %.2f\n"
//				+ "Factory Carogo Capacity: %.2f \t Upgraded Carogo Capacity: %.2f\n"
//				+ "Factory Durability: %.2f \t\t Upgraded Durability: %.2f\n"
//				+ "Number of Cannons on the Ship: %d\n",
//				  getBaseSpeed(), getSpeed(),
//				  getBaseCargoCapacity(), getAvailableStorage(),
//				  getBaseDurability(), getDurability(),
//				  getNumberOfCannons()));
	}
	
	public String shipStatus() {
		return (String.format("Ship name: %-20s \t\tSpeed: %.2f\n"
				+ "Number of crew: %s \tDaily wage: %.2f \tCargo Capacity: %.2fkg\n"
				+ "Durability: %.2f%% \tRepair cost: %.2f\n",
				  getName(), getSpeed(), getCrew(), getDailyWage(), getAvailableStorage(),
				  this.getDurabilityPercentage(), getDamageStatus()*REPAIRCOST));
	}
	
	public String toString() {
		return (String.format("Ship name: %-20s \tSpeed: %.2f \tNumber of crew: %s \tDaily wage: %.2f \tCargo Capacity: %.2fkg \tDurability: %.2f\n",
							  getName(), getSpeed(), getCrew(), getDailyWage(), getAvailableStorage(), getDurability()));
	}
	
}
