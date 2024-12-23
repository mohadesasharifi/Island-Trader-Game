package gamegui;

import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.GameManager;

/**
 * 
 * @author Mohadesa Sharifi, Zahid Khan
 *
 */
public class StoreScreen extends Screen{
	
	protected StoreScreen(GameManager manager) {
		super("Store", manager);
	}

	
	
	/**
	 * @param container is the container of Jframe in abstract class Screen. Components will be added to this container.
	 * Store screen adds components to the container. 
	 * sell button opens "sellScreen" and  buy button opens "purchase screen".
	 * @wbp.parser.entryPoint
	 * 
	 */
	
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void initialize(Container container) {
		
		JPanel storePanel = new JPanel();
		container.add(storePanel);
		storePanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(6, 6, 869, 50);
		storePanel.add(panel_1);
		
		JLabel lblTraderName = new JLabel("<HTML>Name : " + getManager().getName() + "<HTML>");
		lblTraderName.setHorizontalAlignment(SwingConstants.LEFT);
		lblTraderName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblTraderName);
		lblTraderName.setBounds(0, 5, 235, 39);
		
		JLabel lblShowDays = new JLabel("Days Remaining : " + getManager().getDays());
		lblShowDays.setBounds(282, 6, 150, 39);
		panel_1.add(lblShowDays);
		
		JLabel lblShowProfit = new JLabel("Profit : " + getManager().getProfit());
		lblShowProfit.setBounds(491, 6, 88, 39);
		panel_1.add(lblShowProfit);
		
		JLabel lblShowShip = new JLabel("Ship : " + getManager().getShip().getName());
		lblShowShip.setHorizontalAlignment(SwingConstants.TRAILING);
		lblShowShip.setBounds(615, 5, 248, 39);
		panel_1.add(lblShowShip);
		
		
		TextArea txtGameRules = new TextArea();
		txtGameRules.setEditable(false);
		txtGameRules.setText( "\n"
							+  "\t" +getManager().getCurrentIsland().getName() +" has "
							+ getManager().getCurrentIsland().getStore().getName() + " store, "
							+ getManager().getCurrentIsland().getIslandDescription() +"\n"
							+ "\tWe may have some great deals for you.\n"
							+ "\tMaximize your chance of winning by purchasing from our store \n"
							+ "\tTravel to other islands to Sell your items and get some coins.\n"
							+ "\tYou can buy upgrades for your ship.\n"
							+ "\tTo explore more select sell or purchase\n"
							);
		txtGameRules.setRows(5);
		txtGameRules.setBounds(98, 164, 680, 167);
		storePanel.add(txtGameRules);
		
		JLabel lblNewLabel = new JLabel("Welcome to " + getManager().getCurrentIsland().getStore().getName());
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(47, 69, 776, 61);
		storePanel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> getManager().backToMainMenu());
		btnBack.setBounds(94, 441, 85, 25);
		storePanel.add(btnBack);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setBounds(538, 441, 106, 25);
		storePanel.add(btnPurchase);
		btnPurchase.addActionListener(e -> getManager().goPurchase());
		
		JButton btnSell = new JButton("Sell");
		btnSell.setBounds(668, 441, 95, 25);
		storePanel.add(btnSell);
		btnSell.addActionListener(e -> getManager().goSell());
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(191, 441, 86, 25);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure, you want to end the game", "Save Sailors", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					getManager().gameOver();
				}
			}
		});
		storePanel.add(btnQuit);
		
				
		
	}
}
