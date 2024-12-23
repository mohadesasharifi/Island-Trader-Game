package models;

import java.text.NumberFormat;

/**
 * this is model class for products, it creates products of type items with certain attributes.
 * @author Zahid Khan
 *
 */

public class Item {
	//item name
	private String productName;
	// buy price of the item
	private double buyPrice;
	//sell price of the item
	private double sellPrice;
	//available quantity of the item
	private int quantityAvailable;
	//category which item belongs to 
	private String type;
	//quantity of items 
	private String units;
	//the store that the item is bought from
	private String boughtFrom;
	//the store that the item is sold to 
	private String soldTo;
	
	NumberFormat currency = NumberFormat.getCurrencyInstance();
	
	/**
	 * Initialize the item by a name
	 * @param tempName of type String
	 */
	public Item(String tempName) {
		productName = tempName;
	};
	
	/**
	 * 
	 * @param tempProductName is product name
	 * @param tempBuyPrice is sell price of the item
	 * @param tempSellPrice is sell price of the item
	 * @param quantity is the quantity of the item
	 * @param tempType is the category of item
	 * will set an item with params
	 */
	public Item(String tempProductName, double tempBuyPrice,
				 double tempSellPrice, int quantity, String tempType) {
		productName = tempProductName;
		buyPrice = tempBuyPrice;
		sellPrice = tempSellPrice;
		quantityAvailable = quantity;
		type = tempType;
	}
	
	/**
	 * @return sell price of item
	 */
	public double getSellPrice() {
		return sellPrice;
	}
	
	/**
	 * @param tempPrice is the sell price of item
	 * this method will set sell price of item to tempPrice
	 */
	public void setSellPrice(double tempPrice) {
		sellPrice =  tempPrice;
	}
	
	/**
	 * 
	 * @return item's buy price
	 */
	public double getBuyPrice() {
		return buyPrice;
	}
	
	/**
	 * set the buy price of item
	 * @param tempPrice - of type Double
	 */
	public void setBuyPrice(double tempPrice) {
		buyPrice = tempPrice;
	}
	
	/**
	 * set the quantity of item
	 * @param tempQuantity (integer value)
	 */
	public void setQuantity(int tempQuantity) {
		quantityAvailable = tempQuantity;
	}
	
	/**
	 * 
	 * @return the available quantity of item
	 */
	public int getQuantityAvailble() {
		return quantityAvailable;
	}
	
	/**
	 * 
	 * @return the name of product
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * return type/ category of item
	 * @return type - category of the item
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * set the category of item
	 * @param tempType of category type of type String 
	 */
	public void setType(String tempType) {
		type = tempType;
	}
	
	/**
	 * update the quantity of item
	 * @param tempQuantity number of items - type integer
	 */
	public void updateQuantity(int tempQuantity) {
		quantityAvailable = tempQuantity;
	}
	
	/**
	 * set the quantity of item
	 * @param tempUnits
	 */
	public void setUnits(String tempUnits) {
		units = tempUnits;
	}
	
	/**
	 * 
	 * @return category of item
	 */
	public String getUnits() {
		return units;
	}
	
	/**
	 * return a list of bought item
	 * @param store name of type String
	 */
	public void setBoughtFrom(String store) {
		boughtFrom = store;
	}
	
	/**
	 * @return where the item is bought form
	 */
	public String getBoughtFrom() {
		return boughtFrom;
	}
	
	/**
	 * set the name of the store the items is sold to.
	 * @param store name of type String
	 */
	public void setSoldTo(String store) {
		soldTo = store;
	}
	
	/**
	 * return the store where the item is sold to 
	 * @return the name of the store it's sold to.
	 */
	public String getSoldTo() {
		return soldTo;
	}
	
	/**
	 * @return details of purchase
	 */
	public String buyDetails() {
		String buyPrice = currency.format(getBuyPrice());
		return String.format("Item: %-16s\tBuy Price: %-4s\t Quantity: %s %s \t\tBought from: %s",
				getProductName(), buyPrice, getQuantityAvailble(), getUnits(), getBoughtFrom());
	}
	
	/**
	 * 
	 * @return the details of purchased upgrades
	 */
	public String buyDetailsUpgrades() {
		String buyPrice = currency.format(getBuyPrice());
		return String.format("Item: %-16s \tBuy Price: %-4s \tQuantity: %s \t\tBought from: %s\n",
				getProductName(), buyPrice, getQuantityAvailble(), getBoughtFrom());
	}
	
	/**
	 * 
	 * @return the details of traders sale
	 */
	public String saleDetails() {
		String sellPrice = currency.format(getSellPrice());
		return String.format("Item: %-16s\tSale Price: %-4s \t Quantity: %s%s \t\t Sold to: %s",
				getProductName(), sellPrice, getQuantityAvailble(), getUnits(), getSoldTo());
	}
	
	
	/**
	 * 
	 * @return details of store sale
	 */
	public String storesSaleDispaly() {
		String sellPrice = currency.format(getSellPrice());
		
		return String.format("Item: %-16s\tSale Price: %-4s\tQuantity: %s%s",
				getProductName(), sellPrice, getQuantityAvailble(), getUnits());
	}
	
	/**
	 * return string representation of item
	 */
	public String toString() {
		String buyPrice = currency.format(getBuyPrice());
		String sellPrice = currency.format(getSellPrice());
		
		return String.format("Item: %-16s\tBuy Price: %-4s\tSell Price: %-4s\tQuantity: %s%s",
				getProductName(), buyPrice, sellPrice, getQuantityAvailble(), getUnits());
	}

}
