package elements;

public class Ship {
	private String name;
	private double speed;
	private int crew;
	private double maxCargoCapacity;
	private double cargoCapacity;
	private double damageStatus;
	private double durability;
	private double maxDurability;
	private double wagePerDay;
	private double INDIVIDUALWAGE = 1.5;
	private double REPAIRCOST = 1.0;
	
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
	
	public void upgradeCargoCapacity(double tempCargoCapacity) {
		cargoCapacity += (tempCargoCapacity * 20);
	}
	
	public double getAvailableStorage() {
		if (cargoCapacity < 0) {
			cargoCapacity = 0;
		}
		return cargoCapacity;
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
	
	public String repairShip(double coins) {
		
		return "";
	}
	
	public String toString() {
		return ("Ship name is "+name+". Speed is "
				+speed+". Number of crew is "+crew+". Capacity is "
				+cargoCapacity+". Durability is "+durability);
	}
	
}
