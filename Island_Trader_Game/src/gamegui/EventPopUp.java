package gamegui;

import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import models.GameManager;
import models.Event;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JScrollPane;
/**
 * this is a model which shows the result of in case of an event.
 * @author Zahid Khan
 *
 */
public class EventPopUp extends Screen {
	private ArrayList<Event> messages = new ArrayList<Event>();
	private JTextPane textPane;
	private JButton btnOK;
	private boolean winner = true;
	
	/**
	 * this is a constructor for event popup class
	 * @param title
	 * @param manager
	 * @param TravelLength
	 */
	protected EventPopUp(String title, GameManager manager, int TravelLength) {
		super(title, manager);
		this.messages = getManager().randomEvents(TravelLength);
		updateLabel();
		
	}

	/**
	 * This screen displays the information about the events that took place during the sail
	 * if the user is lost everything due to attacks like pirates attack or bad weather 
	 * then it goes to Game-over screen 
	 * if Trader survived then mainGame screen opens.
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected void initialize(Container container) {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.control);
		container.add(mainPanel);
		mainPanel.setLayout(null);
		
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (winner == false ||
					getManager().getShip().getDurabilityPercentage() <= 0) {
					getManager().gameOver();
				}else {
					getManager().backToMainMenu();
				}
			}
		});
		btnOK.setBounds(408, 475, 85, 21);
		mainPanel.add(btnOK);
		
		JLabel lblTravelOutCome = new JLabel("Travel Out-Come");
		lblTravelOutCome.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTravelOutCome.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravelOutCome.setBounds(10, 10, 880, 53);
		mainPanel.add(lblTravelOutCome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 866, 401);
		mainPanel.add(scrollPane);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		
	}
	
	
	/**
	 * the label that shows the outcome of after an event is updated by this method, depending on the event type.
	 */
	private void updateLabel() {
		String result = "";
		if (messages.size() > 0 && messages.get(0) != null) {
			for (int i=0; i<this.messages.size(); i++) {
				result += "Event # " + (i+1) + "\n";
				Event event = this.messages.get(i);
				if (event != null) {
					if (event.getName().contains("Sailors")) {
						
						int reply = JOptionPane.showConfirmDialog(null, "You have come accross some sailors "
								+ "they will pay you 2 coins per sailor if you choose to save them.\n"
								+ "Would you like to save them:", "Save Sailors", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							result += getManager().attack(event);
						}else {
							result += ("All the stranded Sailors have drowned\n");
						}
					}else if(event.getName().contains("Pirates")){
						String[] temp = getManager().attack(event).split("\n");
						String newText = "";
						for (String str: temp) {
							newText += (str + "\n");
						}
						result += newText + "\n";
						if (result.contains("plank")) {
							winner = false;
							textPane.setText(result);
							break;
						}
						
					}else {
						result += getManager().attack(event) + "\n";
					}
				}else {
					result += ("You have not encountered any Event during your journey.\n");
				}
			}
		}else {
			result += ("You have not encountered any Event during your journey.\n");
		}
		if (winner == true) {
			textPane.setText(result);
		}
	}
}
