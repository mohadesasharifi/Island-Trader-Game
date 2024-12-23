package gamegui;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import models.GameManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

/**
 * This is the model class for profile screen. it builds the screen and loads all the necessary data.
 * @author Zahid Khan
 *
 */
public class ProfileScreen extends Screen{
	//label for days
	private JLabel lblDays;
	//label for coins
	private JLabel lblCoins;
	
	/**
	 * Constractor which makes passes string profile page to Screeen abstract class
	 * @param manager
	 */
	protected ProfileScreen(GameManager manager) {
		super("Profile Page", manager);
	}
	/**
	 * @param container is the container of Jframe in abstract class Screen. 
	 * Components will be added to this container.
	 * Profile page shows User's coins, profit, ship, current, 
	 * island, traderName, remaining days, bought items, sold items
	 */
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected void initialize(Container container) {
		JPanel panel = new JPanel();
		container.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(41, 6, 800, 30);
		panel.add(panel_1);
		
		JLabel lblGameOver = new JLabel("Traders' Profile");
		panel_1.add(lblGameOver);
		lblGameOver.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(41, 48, 800, 70);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNameLabel = new JLabel("Trader Name:");
		lblNameLabel.setBounds(0, 0, 100, 16);
		panel_2.add(lblNameLabel);
		
		lblDays = new JLabel("" + getManager().getDays());
		lblDays.setBounds(761, 0, 29, 16);
		panel_2.add(lblDays);
		
		JLabel lblDaysLabel = new JLabel("Days Remaining:");
		lblDaysLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDaysLabel.setBounds(631, 0, 118, 16);
		panel_2.add(lblDaysLabel);
		JLabel lblProfit = new JLabel("" + getManager().getProfit());
		lblProfit.setBounds(302, 0, 58, 16);
		panel_2.add(lblProfit);
		
		JLabel lblProfitrLabel = new JLabel("Profit:");
		lblProfitrLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProfitrLabel.setBounds(229, 0, 61, 16);
		panel_2.add(lblProfitrLabel);
		
		JLabel lblName = new JLabel(getManager().getName());
		lblName.setBounds(103, 0, 126, 16);
		panel_2.add(lblName);
		
		JLabel lblShipLabel = new JLabel("Ship : ");
		lblShipLabel.setBounds(0, 52, 48, 16);
		panel_2.add(lblShipLabel);
		
		JLabel lblShipName = new JLabel(getManager().getShip().getName());
		lblShipName.setBounds(49, 52, 148, 16);
		panel_2.add(lblShipName);
		
		JLabel lblCoinLabel = new JLabel("Coins:");
		lblCoinLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCoinLabel.setBounds(429, 0, 58, 16);
		panel_2.add(lblCoinLabel);
		
		lblCoins = new JLabel("" + getManager().getCoins());
		lblCoins.setBounds(497, 0, 74, 16);
		panel_2.add(lblCoins);
		
		JLabel lblHomeIslandLabel = new JLabel("Home Island:");
		lblHomeIslandLabel.setBounds(0, 26, 100, 16);
		panel_2.add(lblHomeIslandLabel);
		
		JLabel lblHomeIsland = new JLabel(getManager().getHomeIsland().getName());
		lblHomeIsland.setBounds(97, 26, 159, 16);
		panel_2.add(lblHomeIsland);
		
		JLabel lblCurrentIslandLabel = new JLabel("Current Island:");
		lblCurrentIslandLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCurrentIslandLabel.setBounds(374, 26, 113, 16);
		panel_2.add(lblCurrentIslandLabel);
		
		JLabel lblCurrentIsland = new JLabel(getManager().getCurrentIsland().getName());
		lblCurrentIsland.setBounds(494, 26, 159, 16);
		panel_2.add(lblCurrentIsland);
		
		JLabel lblDurabilityLabel = new JLabel("Durability:");
		lblDurabilityLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDurabilityLabel.setBounds(209, 52, 82, 16);
		panel_2.add(lblDurabilityLabel);
		
		JLabel lblDurability = new JLabel(("" + getManager().getShip().getDurabilityPercentage()));
		lblDurability.setBounds(302, 52, 58, 16);
		panel_2.add(lblDurability);
		
		JLabel lblCargoSpaceLabel = new JLabel("Cargo Space:");
		lblCargoSpaceLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCargoSpaceLabel.setBounds(384, 52, 103, 16);
		panel_2.add(lblCargoSpaceLabel);
		
		JLabel lblCargoSpace = new JLabel(""+ getManager().getShip().getAvailableStorage() +" Remaining");
		lblCargoSpace.setBounds(497, 52, 132, 16);
		panel_2.add(lblCargoSpace);
		
		JButton btnPurchaseCoins = new JButton("Purchase Coins");
		btnPurchaseCoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getManager().getDays() > 2) {
					Object[] possibilities = {"1 Day = 20", "2 Days = 50"};
					String selectedCoins = (String)JOptionPane.showInputDialog(
						                    null,
						                    "you'll loose 1 day but gain 20 coins or 2 days to gain 50 coins", "Purchase Coins",
						                    JOptionPane.PLAIN_MESSAGE,
						                    null,
						                    possibilities, "1 Day = 20");
					if (selectedCoins != null) {
						processCoinPurcase(selectedCoins);
					}
				}else {
					JOptionPane.showMessageDialog(null,
						    "You don't have enough days to trade for coins.",
						    "Purchase Coins",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPurchaseCoins.setBounds(647, 50, 143, 21);
		panel_2.add(btnPurchaseCoins);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(41, 120, 800, 135);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblBoughtItems = new JLabel("Items Bought :");
		lblBoughtItems.setBounds(0, 0, 146, 16);
		panel_3.add(lblBoughtItems);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 21, 800, 114);
		panel_3.add(scrollPane);
		
		JTextArea txtBoughtItems = new JTextArea();
		scrollPane.setViewportView(txtBoughtItems);
		txtBoughtItems.setEditable(false);
		txtBoughtItems.setText(getManager().getboughtItems());
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(41, 255, 800, 145);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblSoldItems = new JLabel("Items Sold : ");
		lblSoldItems.setBounds(0, 0, 112, 16);
		panel_4.add(lblSoldItems);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 21, 800, 124);
		panel_4.add(scrollPane_1);
		
		JTextArea txtSoldItems = new JTextArea();
		scrollPane_1.setViewportView(txtSoldItems);
		txtSoldItems.setEditable(false);
		txtSoldItems.setText(getManager().getSoldItems());
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMainMenu.addActionListener(e -> getManager().backToMainMenu());
		btnMainMenu.setBounds(41, 481, 117, 21);
		panel.add(btnMainMenu);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBounds(41, 401, 800, 70);
		panel.add(panel_4_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 21, 800, 49);
		panel_4_1.add(scrollPane_2);
		
		JTextArea txtSoldItems_1 = new JTextArea();
		scrollPane_2.setViewportView(txtSoldItems_1);
		txtSoldItems_1.setText((String) getManager().getShip().showUpgradesGUI());
		txtSoldItems_1.setEditable(false);
		
		JLabel lblUpgrades = new JLabel("Upgrades : ");
		lblUpgrades.setBounds(0, 0, 112, 16);
		panel_4_1.add(lblUpgrades);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(756, 481, 85, 21);
		panel.add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure, you want to end the game", "Save Sailors", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					getManager().gameOver();
				}
			}
		});
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	
	/**
	 * This method allows trader to purchase coins 
	 * Trader can buy 20 coins with the cost of one day
	 * @param selectedCoins of type Object
	 */
	public void processCoinPurcase(Object selectedCoins) {
		String numOfDays = (String) selectedCoins;
		String[] checkNumber = numOfDays.split(" ");
		if (checkNumber[0].equalsIgnoreCase("1")) {
			getManager().purchaseCoins(20, 1);
			lblCoins.setText("" + getManager().getCoins());
			lblDays.setText("" + getManager().getDays());
		}else {
			getManager().purchaseCoins(50, 2);
			lblCoins.setText("" + getManager().getCoins());
			lblDays.setText("" + getManager().getDays());
		}
	}
}
