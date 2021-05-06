package Main;
import java.util.*;
import elements.*;


public class Main {
	
	
	public static void main(String[] args) {
		LoadGameData data = new LoadGameData();
		
		final ArrayList<Ship> ships = data.createAllShips();
		final ArrayList<Islands> islands = data.createAllIslands();
		
		//if (args.length > 0 && (args[0].equals("cmd"))) {
		CommandLines command = new CommandLines();
		command.setGame(islands, ships);
		Trader trader = data.createTrader(command.geTraderName(), command.getDaystoPlayGame(), command.getShipNo(), command.getHomeIslandNo());
		//Trader trader = data.createTrader("Zahid", 30, 1, 0);
		//command.playTheMainGame(islands, trader);
		if (command.getStartGame().equalsIgnoreCase("Y")) {
			command.playTheMainGame(islands, trader);
		}else {
			System.out.println("Thank you for Playing");
		}
		
        //}
		
		
		
		
	}

}
