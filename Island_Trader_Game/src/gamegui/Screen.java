package gamegui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.GameManager;


public abstract class Screen {

	private JFrame frame;
	private GameManager manager;
    protected Screen(final String title, final GameManager manager) {
        this.manager = manager;
        initialize(title);
    }
    
    private void initialize(final String title) {
    	frame = new JFrame();
    	frame.setResizable(false);
 		frame.setForeground(Color.BLACK);
 		frame.setFont(new Font("Dialog", Font.BOLD, 12));
 		frame.setBounds(100, 100, 230, 24);
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		manager.onFinish();
             }
         });

         initialize(frame);

         // Size our frame
        frame.pack();

         // We set the location of our frame relative to null. This causes the frame to be placed
         // in the center of the screen.
        frame.setLocationRelativeTo(null);
    }
   
    protected abstract void initialize(Container container);

    /**
     * Gets the top level component of this screen.
     *
     * @return The top level component
     */
    protected Component getParentComponent() {
        return frame;
    }

    
    protected GameManager getManager() {
        return manager;
    }

    /**
     * Shows this screen by making it visible.
     */
    protected void show() {
        frame.setVisible(true);
    }

    protected boolean confirmQuit() {
    	int selection = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?",
                "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        return selection == JOptionPane.YES_OPTION;
    }

    /**
     * Quits this screen. This should dispose of the screen as necessary.
     */
    void quit() {
        frame.dispose();
    }

    
    void showError(String error) {
        JOptionPane.showMessageDialog(frame, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    
	
}
