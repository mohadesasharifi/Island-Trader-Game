package transaction_interface;

import models.GameManager;

/**
 * manager interface plays a role of middle man between GUI, CMD, and Game Manager
 * @author Mohadesa Sharifi
 *
 */

public interface ManagerInterface {
	  /**
     * Initialises this UI and sets up the given RocketManager with the rockets to be managed.
     * Note that setup need not be complete by the time this method returns.
     * Once setup is complete this UI must call gameManager.
     *
     * @param gameManager The game manager that this UI interacts with
     */
    void setup(GameManager gameManager);

    /**
     * Starts this UI allowing the user to manage their selected gameManager
     */
    void start();

    /**
     * Confirms that the user really wants to quit.
     *
     * @return true if the user wants to quit, false otherwise
     */
    boolean confirmQuit();

    /**
     * Quits the application.
     */
    void quit();
    
    /**
     * calls the game over method
     */
    void gameOver();
    
	void goStore();
	
	void goTravel();

	void profilePage();
	
	void backToMainMenu();

	void purchase();

	void sell();
	
	void eventPopUp(int length);

}
