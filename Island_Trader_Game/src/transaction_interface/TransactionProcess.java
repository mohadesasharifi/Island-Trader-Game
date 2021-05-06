package transaction_interface;

public interface TransactionProcess {
	
	void purchaseSuccessfull(String name, double buyPrice,
							 int quantity, String type, String units);
	
	void purchaseUnsuccessfull(String error, double coins);
	
	void successfullSale(String name, double salePrice, int quantity, double coins, String units);
	
	void unsuccessfullSale(String error);
}
