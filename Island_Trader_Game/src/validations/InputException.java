package validations;

public class InputException extends IllegalStateException{
	
	public InputException() {};
	
	public InputException(String message) {
		super(message);
	}
}
