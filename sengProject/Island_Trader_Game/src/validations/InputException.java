package validations;
/**
 * this method extends IllegalStateException, in case of invalid input it shows you the error message.
 * @author Zahid Khan
 *
 */
@SuppressWarnings("serial")
public class InputException extends IllegalStateException{
	
	/**
	 * constructor
	 */
	public InputException() {};
	
	/**
	 * returns the error message.
	 * @param message
	 */
	public InputException(String message) {
		super(message);
	}
}
