package gamegui;

import models.GameManager;
import transaction_interface.ManagerInterface;

public class Gui implements ManagerInterface{
	
	private GameManager gameManager;

    // The currently active screen in this gui
    private Screen screen;
	
   
	public void setup(GameManager gameManager) {
	
		this.gameManager = gameManager;
		screen = new WelcomeScreenGUI(gameManager);
		screen.show();
		
	}


	public void start() {
		 screen.quit();
	     screen = new MainGameGUI(gameManager);
	     screen.show();
		
	}

	@Override
	public boolean confirmQuit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void quit() {
        screen.quit();
		
	}

	@Override
	public void showError(String error) {
		 screen.showError(error);		
	}

}
