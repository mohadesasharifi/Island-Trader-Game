package gamegui;

import models.GameManager;
import transaction_interface.ManagerInterface;
/**
 * @author Mohadesa, Sharifi
 * @author Zahid khan
 */
public class Gui implements ManagerInterface{
	
	private GameManager gameManager;

    // The currently active screen in this gui
    private Screen screen;
	
   
	public void setup(GameManager gameManager) {
	
		this.gameManager = gameManager;
		screen = new WelcomeScreenGUI(gameManager);
		screen.show();
		
	}

	/**
	 * It closes the welcome screen and opens the mainGame screen
	 */
	public void start() {
		 screen.quit();
	     screen = new MainGameGUI(gameManager);
	     screen.show();
		
	}
	
	/**
	 * before closing the window ask the user to confirm quit.
	 */
	@Override
	public boolean confirmQuit() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * close the active screen
	 */
	@Override
	public void quit() {
        screen.quit();
		
	}
	

	/**
	 * make the game over
	 */
	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		screen.quit();
	    screen = new GameOver(gameManager);
	    screen.show();
	}
	
	/**
	 * will close current screen and open store screen
	 */
	public void goStore() {
		screen.quit();
		screen = new StoreScreen(gameManager);
	    screen.show();
	}

	/**
	 * will close the current screen and open travel screen 
	 */
	@Override
	public void goTravel() {
		screen.quit();
		screen = new TravelScreen(gameManager);
	    screen.show();
		
	}
	
	/**
	 * close current screen and opens profile screen
	 */
	@Override
	public void profilePage() {
		screen.quit();
		screen = new ProfileScreen(gameManager);
	    screen.show();
		
	}

	/**
	 * Close current screen and opens the mainGame screen
	 */
	@Override
	public void backToMainMenu() {
		// TODO Auto-generated method stub
		screen.quit();
		screen = new MainGameGUI(gameManager);
		screen.show();
		
	}

	/**
	 * Closes the current screen and opens purchase screen
	 */
	@Override
	public void purchase() {
		// TODO Auto-generated method stub
		screen.quit();
		screen = new PurchaseScreen(gameManager);
		screen.show();
	}

	/**
	 * Closes the current screen and opens the sell screen
	 */
	@Override
	public void sell() {
		// TODO Auto-generated method stub
		screen.quit();
		screen = new SellScreen(gameManager);
		screen.show();
	}

	/**
	 * Opens a screen to show how were you attacked by events on the route
	 */
	@Override
	public void eventPopUp(int length) {
		// TODO Auto-generated method stub
		screen.quit();
		screen = new EventPopUp("Event Screen", gameManager, length);
		screen.show();
		
	}
}
