package models;

import java.text.NumberFormat;

public class Item {
	private String productName;
	private double buyPrice;
	private double sellPrice;
	private int quantityAvailable;
	private String type;
	private String units;
	private String boughtFrom;
	private String soldTo;
	NumberFormat currency = NumberFormat.getCurrencyInstance();
	
	public Item(String tempName) {
		productName = tempName;
	};
	
	public Item(String tempProductName, double tempBuyPrice,
				 double tempSellPrice, int quantity, String tempType) {
		productName = tempProductName;
		buyPrice = tempBuyPrice;
		sellPrice = tempSellPrice;
		quantityAvailable = quantity;
		type = tempType;
	}
	
	
	public double getSellPrice() {
		return sellPrice;
	}
	
	
	public void setSellPrice(double tempPrice) {
		sellPrice =  tempPrice;
	}
	
	
	public double getBuyPrice() {
		return buyPrice;
	}
	
	
	public void setBuyPrice(double tempPrice) {
		buyPrice = tempPrice;
	}
	
	
	public void setQuantity(int tempQuantity) {
		quantityAvailable = tempQuantity;
	}
	
	
	public int getQuantityAvailble() {
		return quantityAvailable;
	}
	
	
	public String getProductName() {
		return productName;
	}
	
	
	public String getType() {
		return type;
	}
	
	
	public void setType(String tempType) {
		type = tempType;
	}
	
	
	public void updateQuantity(int tempQuantity) {
		quantityAvailable = tempQuantity;
	}
	
	
	public void setUnits(String tempUnits) {
		units = tempUnits;
	}
	
	
	public String getUnits() {
		return units;
	}
	
	
	public void setBoughtFrom(String store) {
		boughtFrom = store;
	}
	
	
	public String getBoughtFrom() {
		return boughtFrom;
	}
	
	
	public void setSoldTo(String store) {
		soldTo = store;
	}
	
	
	public String getSoldTo() {
		return soldTo;
	}
	
	public String buyDetails() {
		String buyPrice = currency.format(getBuyPrice());
		return String.format("Item: %-16s\tBuy Price: %-4s\tQuantity: %s%s\t Bought from: %s",
				getProductName(), buyPrice, getQuantityAvailble(), getUnits(), getBoughtFrom());
	}
	
	
	public String saleDetails() {
		String sellPrice = currency.format(getBuyPrice());
		return String.format("Item: %-16s\tSale Price: %-4s\tQuantity: %s%s\t Sold to: %s",
				getProductName(), sellPrice, getQuantityAvailble(), getUnits(), getSoldTo());
	}
	
	
	
	public String storesSaleDispaly() {
		String sellPrice = currency.format(getSellPrice());
		
		return String.format("Item: %-16s\tSale Price: %-4s\tQuantity: %s%s",
				getProductName(), sellPrice, getQuantityAvailble(), getUnits());
	}
	
	
	public String toString() {
		String buyPrice = currency.format(getBuyPrice());
		String sellPrice = currency.format(getSellPrice());
		
		return String.format("Item: %-16s\tBuy Price: %-4s\tSell Price: %-4s\tQuantity: %s%s",
				getProductName(), buyPrice, sellPrice, getQuantityAvailble(), getUnits());
	}

}
