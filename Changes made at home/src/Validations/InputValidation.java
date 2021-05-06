package Validations;


public class InputValidation {
	
	public InputValidation(){};
	
	public boolean validName(String name) {
		if (name.matches("[a-zA-Z]+")) {
			if (name.length() > 2 && name.length() < 15) {
				return true;
			}
		}
		return false;
	}
	
	
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
	
	public boolean inputMatch(String input, String string1, String string2) {
		
		if(input.equalsIgnoreCase(string1) || input.equalsIgnoreCase(string2)) {
			return true;
		}
		return false;
	}
	
	
	public boolean notHomeIsland(int homeIslandNo, String chosenIsland) {
		if (Integer.parseInt(chosenIsland) == homeIslandNo) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		InputValidation check = new InputValidation();
		System.out.println(check.notHomeIsland(1, "2"));
		System.out.println(check.validNumberInRange("13",0, 4));
	}
}
