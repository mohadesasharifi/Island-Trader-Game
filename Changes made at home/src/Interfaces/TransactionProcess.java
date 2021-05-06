package Interfaces;

public interface TransactionProcess {
	
	void purchaseSuccessfull(String name, double buyPrice,
							 int quantity, String type);
	
	void purchaseUnsuccessfull(String error, double coins);
	
	void successfullSale(String name, double salePrice, int quantity, double coins);
	
	void unsuccessfullSale(String error);
}
