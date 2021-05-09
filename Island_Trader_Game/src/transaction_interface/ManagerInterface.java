package transaction_interface;

import java.util.List;

import models.GameManager;



public interface ManagerInterface {
	  /**
     * Initialises this UI and sets up the given RocketManager with the rockets to be managed.
     * Note that setup need not be complete by the time this method returns.
     * Once setup is complete this UI must call {@link RocketManager#onSetupFinished(String, List)}.
     *
     * @param rocketManager The rocket manager that this UI interacts with
     */
    void setup(GameManager gameManager);

    /**
     * Starts this UI allowing the user to manage their selected {@link Rocket}s
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
     * Reports the given error to the user.
     *
     * @param error The error to display
     */
    void showError(String error);
}
