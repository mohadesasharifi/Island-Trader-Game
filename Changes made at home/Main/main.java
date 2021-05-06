package Main;
import java.util.*;
import elements.*;


public class main {
	
	
	public static void main(String[] args) {
		LoadGameData data = new LoadGameData();
		
		ArrayList<Ship> Ships = data.createAllShips();
		ArrayList<Islands> Islands = data.createAllIslands();
		
		
		
		if (args.length > 0 && (args[0].equals("cmd"))) {
			CommandLines command = new CommandLines();
			
        }
		
		
		
		
	}

}
