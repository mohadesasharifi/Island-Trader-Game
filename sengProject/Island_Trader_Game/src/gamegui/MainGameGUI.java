package gamegui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import models.GameManager;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.text.NumberFormat;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
/**
 * this screen is the main screen 
 * User plays the main game from mainGame screen.
 * user can go to other islands from this screen
 * User can go to store to buy or sell and make profit.
 * @author Mohadesa Sharifi and Zahid Khan 
 *
 */
public class MainGameGUI extends Screen{
	
	private Dimension btnDimensions = new Dimension(120, 30);
	//welcome Screen page
	private JPanel welcomePanel;
	
	protected MainGameGUI(GameManager manager) {
		super("MainGame", manager);
	}

	/**
	 * Game rules are displayed inside a text area.
	 * Name, profit, coins, remaining days, ship values are shown inside labels on the top.
	 * there are four buttons inside the container
	 * Profile button: will open the profile page.
	 * travel button: will open the harbor page.
	 * Go to Store button: will take you to the store on the current island where your ship is docked.
	 * quit button: will open gameover Screen.
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected void initialize(Container container) {
		// TODO Auto-generated method stub
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		container.add(mainPanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 869, 50);
		mainPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTraderName = new JLabel("<HTML>Name : " + getManager().getName() + "<HTML>");
		lblTraderName.setHorizontalAlignment(SwingConstants.LEFT);
		lblTraderName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblTraderName);
		lblTraderName.setBounds(0, 5, 235, 39);
		
		JLabel lblShowDays = new JLabel("Days Remaining : " + Math.ceil(getManager().getDays()));
		lblShowDays.setBounds(247, 5, 165, 39);
		panel_1.add(lblShowDays);
		
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		
		JLabel lblShowProfit = new JLabel("Profit : " + currency.format(getManager().getProfit()));
		lblShowProfit.setBounds(527, 6, 107, 39);
		panel_1.add(lblShowProfit);
		
		JLabel lblShowShip = new JLabel("Ship : " + getManager().getShip().getName());
		lblShowShip.setHorizontalAlignment(SwingConstants.TRAILING);
		lblShowShip.setBounds(636, 5, 227, 39);
		panel_1.add(lblShowShip);
		
		JLabel lblShowCoins = new JLabel("Coins : " + currency.format(getManager().getCoins()));
		lblShowCoins.setBounds(418, 5, 107, 39);
		panel_1.add(lblShowCoins);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(164, 469, 112, 35);
		mainPanel.add(panel_2);
		
		JButton btnTravel = new JButton("Travel");
		btnTravel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTravel.setMaximumSize(btnDimensions);
		btnTravel.setMinimumSize(btnDimensions);
		panel_2.add(btnTravel);
		btnTravel.addActionListener(e -> travel());
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(332, 469, 112, 35);
		mainPanel.add(panel_4);
		
		JButton btnGoToStore = new JButton("Go To Store");
		btnGoToStore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnGoToStore.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGoToStore.setMinimumSize(btnDimensions);
		btnGoToStore.setMaximumSize(btnDimensions);
		panel_4.add(btnGoToStore);
		btnGoToStore.addActionListener(e -> Store());
		
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		panel_3.setBounds(811, 469, 68, 35);
		mainPanel.add(panel_3);
		
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure, you want to end the game", "Save Sailors", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					getManager().gameOver();
				}
			}
		});
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQuit.setMinimumSize(btnDimensions);
		btnQuit.setMaximumSize(btnDimensions);
		panel_3.add(btnQuit);
		
		DefaultTableModel model = new DefaultTableModel();
		Object[] columns = {"Item Name", "Sale Price", "Quantity Available"};
		model.setColumnIdentifiers(columns);
		Object[] test = {"This is an Item", "$309", "1000"};
		model.addRow(test);
		
		
		welcomePanel = new JPanel();
		welcomePanel.setBounds(10, 72, 869, 387);
		mainPanel.add(welcomePanel);
		welcomePanel.setLayout(null);
		
		
		JLabel lblWelcome = new JLabel("<HTML>" + getManager().getCurrentIsland() + "<HTML>");
		lblWelcome.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWelcome.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcome.setBounds(6, 5, 847, 42);
		welcomePanel.add(lblWelcome);
		
		
		JLabel lblIslandDetails = new JLabel("<HTML>" + getManager().getCurrentIsland().getIslandDescription() + "<HTML>");
		lblIslandDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblIslandDetails.setVerticalAlignment(SwingConstants.TOP);
		lblIslandDetails.setBounds(6, 59, 847, 24);
		welcomePanel.add(lblIslandDetails);
		
		
		TextArea txtGameRules = new TextArea();
		txtGameRules.setEditable(false);
		txtGameRules.setText("Game Rules:\n"
							+ "You can sail to other islands"
							+ "You can go the stores to make a purchase or sell items from your invenotry\n"
							+ "You can buy goods on one island and sell them on another island\n"
							+ "You can be attached by pirates or sharks, or come across unfortunate weather that might damage your ship\n"
							+ "if you ship is damaged you must repair before setting sail to another island\n"
							+ "You can keep traveling between islands with a goal to maximise your profit\n"
							+ "You encounter pirates. They will try to board your ship and steal your goods\n"
							+ "\tTo fend off the pirates you must play and win a number game based on rolling a die\n"
							+ "\tIf the value of the goods do not satisfy the pirates then they take your ship and your money and make you\n"
							+ "\tand your crew walk the plank. The game is over.\n"
							+ "\tIf the pirates are satisfied with the goods they let you go and you continue your journey minus your goods\n"
							+ "When at sea, you may come across stranded sailor, if you rescue them the saved sailors gives you a monetary reward"
							);
		txtGameRules.setRows(5);
		txtGameRules.setBounds(6, 95, 853, 292);
		welcomePanel.add(txtGameRules);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 470, 112, 35);
		mainPanel.add(panel_6);
		
		JButton btnViewProfile = new JButton("View Profile");
		btnViewProfile.addActionListener(e -> showProfile());
		btnViewProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewProfile.setMinimumSize(btnDimensions);
		btnViewProfile.setMaximumSize(btnDimensions);
		panel_6.add(btnViewProfile);
		btnViewProfile.addActionListener(e -> showProfile());
		
		
//		JLabel lblNewLabel = new JLabel("New label");
//		mainPanel.add(lblNewLabel);
		
	}
	
	
	/**
	 * close this screen and open profile page
	 */
	private void showProfile() {
		// TODO Auto-generated method stub
		getManager().viewProfile();
		
	}
	
	
	/**
	 * close mainGame page and opens store screen
	 */
	private void Store() {
		// TODO Auto-generated method stub
		getManager().store();
	}
	
	/**
	 * close mainGame page and opens travel screeen
	 */
	public void travel() {
		getManager().travel();
	}
}
