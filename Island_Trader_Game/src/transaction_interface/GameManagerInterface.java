package transaction_interface;
import java.util.*;

import models.*;

public interface GameManagerInterface {
	
	void setUp(GameManager manager);
	
	void start();
	
	boolean getUserName();
	
	boolean getDaysToPlay();
	
	boolean chooseShip(ArrayList<Ship> ships);
	
	boolean readyToPlay();
	
	
	
}
