package transaction_interface;
import java.util.*;

import models.*;

public interface GameManagerInterface {
	
	int setHomeIslandRandomly();
	
	String setName();
	
	int daysToPlay();
	
	int chooseShip(ArrayList<Ship> ships);
	
	void setTrader();
	
	void viewProfile(ArrayList<Island> Island, Trader trader);
	
	void start(ArrayList<Island> island, ArrayList<Ship> ship);
	
	void playTheMainGame(ArrayList<Island> island, Trader trader);
	
	void scanTransactionOrTravel(ArrayList<Island> island, Trader trader);
	
	void getItemsByCategory(ArrayList<Island> island, Trader trader);
	
	void scanTransactionType(ArrayList<Island> island, Trader trader);
	
	void purchase(ArrayList<Island> island, Trader trader);
	
	void transactionProcess(ArrayList<Island> island, Trader trader);
	
	void sell(ArrayList<Island> island, Trader trader);
	
	void destinationIsland(ArrayList<Island> Island, Trader trader);
	
	void printallpossilberoutes(int source, int destination, ArrayList<Island> island, ArrayList<ArrayList<Integer>> routes);
	
	void randomEvents(int size);
	
	void chooseRoute(int source, int destination, Trader trader, ArrayList<Island> island);
	
	String gameOver(Trader trader, String reason);
	
}
