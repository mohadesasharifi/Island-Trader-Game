package transaction_interface;

/**
 * This model helps the trader to transact with store
 * @author Zahid Khan
 *
 */
public interface TransactionProcess {
	
	String purchaseSuccessfull(String name, double buyPrice,
							 int quantity, String type, String units);
	
	String purchaseUnsuccessfull(String error, double coins);
	
	String successfullSale(String name, double salePrice, int quantity, double coins, String units);
	
	String unsuccessfullSale(String error);
}
