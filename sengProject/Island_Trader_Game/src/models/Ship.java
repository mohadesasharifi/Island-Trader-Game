package models;
import java.util.ArrayList;

import validations.InputException;
/**
 * 
 * @author Mohadesa sharifi, Zahid khan
 *
 */
public class Ship {
	//Ship name
	private String name;
	//ship initial speed
	private double baseSpeed;
	//ship speed after upgrades
	private double speed;
	//number of crews of the ship
	private int crew;
	//initial cargo capacity of the ship
	private double baseCargoCapacity;
	//ship cargo capacity after upgrade
	private double cargoCapacity;
	//ship damage after attacks
	private double damageStatus;
	//ship initial durability
	private double baseDurability;
	//ship durability after attacks or repair
	private double durability;
	//maximum durability of ship
	private double maxDurability;
	//wage of crew per day
	private double wagePerDay;
	//wage per crew
	private double INDIVIDUALWAGE = 0.5;
	//repair cost
	private double REPAIRCOST = 1.0;
	//number of cannons initiated to zero
	private int cannons = 0;
	//trader is the owner
	private Trader owner;
	// ArrayList of type Item to contains all the details of upgrades
	private ArrayList<Item> istalledUpgrades = new ArrayList<Item>();
	
	/**
	 * empty constructor
	 */
	public Ship() {};
	
	
	/**
	 * @param tempName is name of ship to be assigned
	 * @param tempSpeed is speed of ship to assigned 
	 * @param tempCrew is number of crew
	 * @param tempCargoCapacity is cargo capacity
	 * @param tempDurability is ship durability
	 * will initialize the ship by the given params
	 */
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
	
	/**
	 * set the name of the ship
	 * @param ship is the name of the ship
	 */
	public void setName(String ship) {
		name = ship;
	}
	
	/**
	 * set ship speed to the tempSpeed
	 * @param tempSpeed is the new speed
	 */
	public void setSpeed(double tempSpeed) {
		speed = tempSpeed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * 
	 * @return the initial speed of the ship
	 */
	public double getBaseSpeed() {
		return baseSpeed;
	}
	
	/**
	 * set the speed of the ship after consuming quantity
	 * @param quantity is the number of engines that will increase ship's speed
	 */
	public void upgradeSpeed(double quantity) {
		speed += (quantity * 10);
	}
	
	/**
	 * 
	 * @return the number of ship's crew
	 */
	public int getCrew() {
		return crew;
	}
	
	/**
	 * sets the number of crew to tempCrew
	 * @param tempCrew
	 */
	public void setCrew(int tempCrew) {
		crew = tempCrew;
		wagePerDay = INDIVIDUALWAGE * tempCrew;
	}
	
	/**
	 * sets current trader as ship owner
	 * @param trader
	 */
	public void setShipOwner(Trader trader) {
		owner = trader;
	}
	
	
	/**
	 * @return the owner of the ship
	 */
	public Trader getShipOwner() {
		return owner;
	}
	
	
	/**
	 * add upgrades to the list which keeps history of installed upgrades
	 * @param newItem is the new upgrade purchased
	 */
	public void addInstallUpgrades(Item newItem) {
		istalledUpgrades.add(newItem);
	}
	
	/**
	 * upgrade ship's cargo capacity 
	 * @param tempCargoCapacity is cargo capacity purchased for ship
	 */
	public void upgradeCargoCapacity(double tempCargoCapacity) {
		cargoCapacity += (tempCargoCapacity * 20);
	}
	
	/**
	 * @return the ship's remaining cargo capacity
	 */
	public double getAvailableStorage() {
		if (cargoCapacity < 0) {
			cargoCapacity = 0;
		}
		return cargoCapacity;
	}
	
	/**
	 * @return ship's initial cargo capacity
	 */
	public double getBaseCargoCapacity() {
		return baseCargoCapacity;
	}
	
	/**
	 * add new cannons to ship's cannons 
	 * @param nums is the number of new cannons purchased for ship
	 */
	public void addCannons(int nums) {
		cannons += nums;
	}
	
	/**
	 * Subtract number of cannons need to be used from the ship's cannons
	 * @param nums is the number of cannons to be used
	 */
	public void removeCannons(int nums) {
		if (nums > 0 && nums <= getNumberOfCannons()) {
			cannons -= nums;
		}else{
			throw new InputException("Number of Cannons must be between 0 and "+getNumberOfCannons());
		}
		
	}
	
	/**
	 * @return the number of ship's cannons
	 */
	public int getNumberOfCannons() {
		return cannons;
	}
	
	/**
	 * loads items to the ship so subtract the number of items from the cargo
	 * @param quantity is the quantity of new items added to cargo
	 */
	public void loadProducts(int quantity) {
		cargoCapacity -= (double)quantity;
	}
	
	/**
	 * unloads items from the ship so add the new free space to the cargo
	 * @param quantity is the quantity of items needs to be unloaded 
	 */
	public void unloadProducts(int quantity) {
		cargoCapacity += (double)quantity;
	}
	
	/**
	 * set the durabitliy of the ship 
	 * @param tempDurability is new duribility of the ship
	 */
	public void setDurability(double tempDurability) {
		durability = tempDurability;
		maxDurability = tempDurability;
		damageStatus = 0.0;
	}
	
	/**
	 * @param tempDurability is new durability bought for ship
	 */
	public void upgradeDurability(double tempDurability) {
		maxDurability += (tempDurability * 10);
		durability += (tempDurability * 10);
	}
	
	/**
	 * checks that new durability does not exceed the max ship durability
	 * @param newDurability
	 */
	public void addDurability(double newDurability) {
		if(maxDurability >= (durability + newDurability)){
			durability += newDurability;
			damageStatus -= newDurability;
		}else{
			durability = maxDurability;
			damageStatus = 0;
		}
	}
	
	/**
	 * @return the durability of the ship
	 */
	public double getDurability() {
		if (durability < 0) {
			durability = 0;
		}
		return durability;
	}
	
	/**
	 * @return durability of the ship in percentage 
	 */
	public double getDurabilityPercentage() {
		double current = getDurability();
		double result = (current * 100) / maxDurability;
		return result;
	}
	
	/**
	 * @return maximum durability alllowed to upgrade
	 */
	public double getMaxDurability() {
		return maxDurability;
	}
	
	/**
	 * @return initila durability of the ship
	 */
	public double getBaseDurability() {
		return baseDurability;
	}
	
	/**
	 * return the damage the ship toke
	 * @param tempDamage
	 * @return
	 */
	public double takeDamage(double tempDamage) {
		damageStatus += tempDamage;
		durability -= tempDamage;
		return durability;
	}
	
	/**
	 * @return damage status of the ship
	 */
	public double getDamageStatus() {
		return damageStatus;
	}
	
	/**
	 * 
	 * @return the name of the ship
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return daily wage of the crew
	 */
	public double getDailyWage() {
		return wagePerDay;
	}
	
	/**
	 * repair ship
	 * @param trader is the owner 
	 * @return
	 */
	public String repairShip(Trader trader) {
		double coinsNeeded = this.getMaxDurability() - this.getDurability();
		trader.subtractCoins(coinsNeeded);
		double newDurability = coinsNeeded * REPAIRCOST;
		addDurability(newDurability);
		return "Ship repaired, remaining durability is : " + getDurabilityPercentage() +" percent.\n"
				+ "Cost to repair was: "+ coinsNeeded + " Coins\n";
	}
	
	
	/**
	 * @return history of upgrades installed on this ship
	 */
	public String showUpgrades() {
		String result = "";
		if (istalledUpgrades.size() == 0) {
			return "You have not installed any upgrades";
		}
		for (Item item: istalledUpgrades) {
			result += item.buyDetails() + "\n";
		}
		return result;
		
	}
	
	/**
	 * 
	 * @return upgrades but in string does not print anything which is usefull for Gui
	 */
	public String showUpgradesGUI() {
		String result = "";
		if (istalledUpgrades.size() == 0) {
			result += "You have not installed any upgrades";
			return result;
		}
		for (Item item: istalledUpgrades) {
			result += item.buyDetailsUpgrades();
		}
		return result;
		
	}
	
	/**
	 * @return details of the ship
    */
	public String shipStatus() {
		return (String.format("Ship name: %-20s \t\tSpeed: %.2f\n"
				+ "Number of crew: %s \tDaily wage: %.2f \tCargo Capacity: %.2fkg\n"
				+ "Durability: %.2f%% \tRepair cost: %.2f\n",
				  getName(), getSpeed(), getCrew(), getDailyWage(), getAvailableStorage(),
				  this.getDurabilityPercentage(), getDamageStatus()*REPAIRCOST));
	}
	
	public String toString() {
		return (String.format("Ship name: %-20s \tSpeed: %.2f \tNumber of crew: %s \nDaily wage: %.2f \tCargo Capacity: %.2fkg \tDurability: %.2f\n",
							  getName(), getSpeed(), getCrew(), getDailyWage(), getAvailableStorage(), getDurability()));
	}
	
}
