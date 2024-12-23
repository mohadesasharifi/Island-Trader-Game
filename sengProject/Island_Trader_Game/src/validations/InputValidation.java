package validations;


/**
 * This class is created to check all the inputs for the game.
 * @author Zahid Khan
 *
 */
public class InputValidation {
	/**
	 * empty constructor
	 */
	public InputValidation(){};
	
	
	/**
	 * this method checks if the User's name is between 3 - 15 alphabetical characters.
	 * @param name
	 * @return
	 */
	public boolean validName(String name) {
		if (name.matches("[a-zA-Z]+")) {
			if (name.length() > 2 && name.length() <= 15) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * this method checks if the number is in the given range inclusive of start and end of the range.
	 * @param tempDays
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean validNumberInRange(String tempDays, int start, int end) {
		try {
			int days = Integer.parseInt(tempDays);
			if (days >= start && days <= end) {
				return true;
			}
		}catch(NumberFormatException e) {
			
		}
		return false;
	}
	
	/**
	 * this input matches the 2 inputs and see if they are equal.
	 * @param input
	 * @param string1
	 * @param string2
	 * @return
	 */
	public boolean inputMatch(String input, String string1, String string2) {
		
		if(input.equalsIgnoreCase(string1) || input.equalsIgnoreCase(string2)) {
			return true;
		}
		return false;
	}
	
	/**
	 * this method checks if the current island is the home island of the trader or not.
	 * @param homeIslandNo
	 * @param chosenIsland
	 * @return
	 */
	public boolean notHomeIsland(int homeIslandNo, String chosenIsland) {
		if (Integer.parseInt(chosenIsland) == homeIslandNo) {
			return false;
		}
		return true;
	}
	
}
