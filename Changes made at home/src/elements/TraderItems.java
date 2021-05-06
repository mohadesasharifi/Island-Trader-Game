package elements;

public class TraderItems extends Items{
	private String name;
	private double buyPrice;
	private double sellPrice;
	private int quantity = 0;
	private String type;

	void Super() {
	}
	public TraderItems(String tempProductName, double tempBuyPrice,
			 double tempSellPrice, int quantity, String tempType) {
		name = tempProductName;
		buyPrice = tempBuyPrice;
		sellPrice = tempSellPrice;
		quantity = quantity;
		type = tempType;
	
}
	public void setName(String tempName) {
		name = tempName;
	}
	public void setbuyPrice(double tempBuyPrice) {
		buyPrice = tempBuyPrice;
	}
	public void setSellPrice(double tempsellPrice) {
		sellPrice = tempsellPrice;
	}
	public void setQuantity(int tempQuantity) {
		quantity = tempQuantity;
	}
	public void setype(String tempType) {
		type = tempType;
	}
}
