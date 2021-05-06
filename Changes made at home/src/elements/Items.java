package elements;

public class Items {
	private String productName;
	private double buyPrice;
	private double sellPrice;
	private int quantityAvailable;
	private String type;
	
	public Items() {};
	
	public Items(String tempProductName, double tempBuyPrice,
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
	
	public double getBuyPrice() {
		return buyPrice;
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
	
	public void updateQuantity(int tempQuantity) {
		quantityAvailable = tempQuantity;
	}
	
	
	public String toString() {
		return String.format("Item: %-14s Buy Price: %-4s Sell Price: %-4s Quantity Available: %s",
				getProductName(), getBuyPrice(), getSellPrice(), getQuantityAvailble());
	}
	
	
}
