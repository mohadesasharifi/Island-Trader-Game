package gamegui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import models.GameManager;
import models.Island;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * User can travel to four other islands from the current island where his ship is docked.
 * @author Mohadesa Sharifi, Zahid khan 
 */
public class TravelScreen extends Screen{

	private JList<String> listSelectRoute;
	private JLabel lblTravelDuration;
	private JLabel lblTravelCost;
	private JLabel lblEvents;
	private JLabel lblRepairError;
	private JButton btnSetSail;
	private JButton btnRepairShip;
	private int travelLength = 0;
	private int destinationIndex;
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	
	protected TravelScreen(GameManager manager) {
		super("Travel", manager);
	}
	
	
	/**
	 * Name, days, profit, ship are displayed inside labels
	 * Islands are added to a Jlist where user can select and island to travel to
	 * when an island is selected the routes from the current island to the destination will be displayed 
	 * in the next Jlist 
	 * User selects a route from the Jlist that is when the set Sail button enables
	 * Jlist which has the routes has a listener that set the label's values(travel duration, 
	 * cost of travel, and ship durability) 
	 * there are buttons at the bottom of the screen to dispaly other screens. 
	 * there is a button repair ship which will repair your ship
	 * User can't travel unless the durability of ship is 100%
	 * the error will be shown in the error label.
	 */
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected void initialize(Container container) {
		JPanel storePanel = new JPanel();
		container.add(storePanel);
		storePanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 0, 869, 50);
		storePanel.add(panel_1);
		
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		
		JLabel lblTraderName = new JLabel("Name : "+ getManager().getName());
		lblTraderName.setHorizontalAlignment(SwingConstants.LEFT);
		lblTraderName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTraderName.setBounds(0, 5, 235, 39);
		panel_1.add(lblTraderName);
		
		JLabel lblShowDays = new JLabel("Days Remaining : " + Math.ceil(getManager().getDays()));
		lblShowDays.setBounds(247, 5, 168, 39);
		panel_1.add(lblShowDays);
		
		JLabel lblShowProfit = new JLabel("Profit : " + currency.format(getManager().getProfit()));
		lblShowProfit.setBounds(538, 5, 107, 39);
		panel_1.add(lblShowProfit);
		
		JLabel lblShowShip = new JLabel("Ship : "+getManager().getShip().getName()+"");
		lblShowShip.setHorizontalAlignment(SwingConstants.TRAILING);
		lblShowShip.setBounds(657, 5, 206, 39);
		panel_1.add(lblShowShip);
		
		JLabel lblShowCoins = new JLabel("Coins : " + currency.format(getManager().getCoins()));
		lblShowCoins.setBounds(427, 5, 107, 39);
		panel_1.add(lblShowCoins);
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(null);
		welcomePanel.setBounds(10, 63, 869, 391);
		storePanel.add(welcomePanel);
		
		JLabel lblWelcomeTravel = new JLabel("<HTML>Welcome to the Harbour, choose an island to travel to<HTML>");
		lblWelcomeTravel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWelcomeTravel.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeTravel.setBounds(6, 5, 847, 46);
		welcomePanel.add(lblWelcomeTravel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 61, 244, 253);
		welcomePanel.add(scrollPane);
		
		JList<String> listSelectAnIsland = new JList<String>();
		listSelectAnIsland.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listSelectAnIsland.getSelectedValue();
				destinationIndex = listSelectAnIsland.getSelectedIndex();
				showAllroutes(destinationIndex);
			}
		});
		listSelectAnIsland.setBackground(SystemColor.control);
		scrollPane.setViewportView(listSelectAnIsland);
		listSelectAnIsland.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultListModel<String> modelIslands = new DefaultListModel<String>();
		int index = 0;
		for (Island island : getManager().getIsalnds()) {
			if(island.getName().equalsIgnoreCase(getManager().getCurrentIsland().getName())) {
				String newString = island.getName() + " (Current Island)";
				modelIslands.add(index, newString);
			}else {
				modelIslands.add(index, island.getName());
			}
			index ++;
		}
		
		listSelectAnIsland.setModel(modelIslands);
		
		JLabel lblNewLabel = new JLabel("Select an Island");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		
		btnSetSail = new JButton("Set Sail");
		btnSetSail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getManager().setCurrentIsland(destinationIndex);
				gotodestination();
			}
		});
		btnSetSail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getManager().travel();
			}
		});
		btnSetSail.setEnabled(false);
		btnSetSail.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSetSail.setBounds(733, 324, 130, 57);
		welcomePanel.add(btnSetSail);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(260, 61, 599, 253);
		welcomePanel.add(scrollPane_1);
		
		listSelectRoute = new JList<String>();
		listSelectRoute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				routeSelected(listSelectRoute.getSelectedValue());
			}
		});
		listSelectRoute.setBackground(SystemColor.control);
		scrollPane_1.setViewportView(listSelectRoute);
		listSelectRoute.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblNewLabel_1 = new JLabel("Select one of the following routes:");
		lblNewLabel_1.setEnabled(true);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane_1.setColumnHeaderView(lblNewLabel_1);
		
		lblEvents = new JLabel("Errors are shown here");
		lblEvents.setBounds(6, 347, 464, 34);
		welcomePanel.add(lblEvents);
		
		lblTravelDuration = new JLabel("Travel Duration : ");
		lblTravelDuration.setBounds(6, 324, 244, 26);
		welcomePanel.add(lblTravelDuration);
		
		lblTravelCost = new JLabel("Cost of Travel : ");
		lblTravelCost.setBounds(260, 324, 179, 26);
		welcomePanel.add(lblTravelCost);
		
		JLabel lblNewLabel_2 = new JLabel("Ship Durability : "+getManager().getShip().getDurabilityPercentage());
		lblNewLabel_2.setBounds(479, 324, 244, 26);
		welcomePanel.add(lblNewLabel_2);
		
		lblRepairError = new JLabel("");
		lblRepairError.setForeground(Color.RED);
		lblRepairError.setBounds(480, 348, 250, 33);
		welcomePanel.add(lblRepairError);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 464, 112, 35);
		storePanel.add(panel_6);
		
		JButton btnViewProfile = new JButton("View Profile");
		btnViewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().viewProfile();
			}
		});
		btnViewProfile.setMinimumSize(new Dimension(120, 30));
		btnViewProfile.setMaximumSize(new Dimension(120, 30));
		btnViewProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_6.add(btnViewProfile);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(192, 464, 112, 35);
		storePanel.add(panel_2);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().backToMainMenu();
			}
		});
		btnMainMenu.setMinimumSize(new Dimension(120, 30));
		btnMainMenu.setMaximumSize(new Dimension(120, 30));
		btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(btnMainMenu);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(374, 464, 112, 35);
		storePanel.add(panel_4);
		
		JButton btnGoToStore = new JButton("Go To Store");
		btnGoToStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().store();
			}
		});
		btnGoToStore.setMinimumSize(new Dimension(120, 30));
		btnGoToStore.setMaximumSize(new Dimension(120, 30));
		btnGoToStore.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGoToStore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(btnGoToStore);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(811, 464, 68, 35);
		storePanel.add(panel_3);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure, you want to end the game", "Save Sailors", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					getManager().gameOver();
				}
			}
		});
		btnQuit.setMinimumSize(new Dimension(120, 30));
		btnQuit.setMaximumSize(new Dimension(120, 30));
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(btnQuit);
		
		JPanel panel_Repair = new JPanel();
		panel_Repair.setBounds(543, 464, 112, 35);
		storePanel.add(panel_Repair);
		panel_Repair.setLayout(null);
		
		btnRepairShip = new JButton("Repair Ship");
		btnRepairShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().shipRepair();
				lblRepairError.setText("Ship Repaired");
				lblShowCoins.setText("Coins : " + currency.format(getManager().getCoins()));
				lblNewLabel_2.setText("Ship Durability : "+getManager().getShip().getDurabilityPercentage());
				btnSetSail.setEnabled(true);
				btnRepairShip.setEnabled(false);
			}
		});
		btnRepairShip.setBounds(0, 5, 112, 25);
		btnRepairShip.setMinimumSize(new Dimension(120, 30));
		btnRepairShip.setMaximumSize(new Dimension(120, 30));
		btnRepairShip.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRepairShip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRepairShip.setEnabled(false);
		panel_Repair.add(btnRepairShip);
	}
	/**
	 * @param destination is the Jlist selected index. 
	 * this method will set the routes for the destination island to the second Jlist
	 */
	public void showAllroutes(int destination) {
		ArrayList<String> possibleRoutes = getManager().getRoutesByIslandName(destination);
		DefaultListModel<String> modelRoutes = new DefaultListModel<String>();
		int routeIndex = 0;
		for (String routeStr: possibleRoutes) {
			modelRoutes.add(routeIndex, routeStr);
			routeIndex ++;
		}
		listSelectRoute.setModel(modelRoutes);
	}
	
	/**
	 * After user selects a rout from Jlist then this method will reset the values in the labels.
	 * @param object of type Object
	 */
	public void routeSelected(Object object) {
		String[] selected = ((String) object).split("->");
		ArrayList<Double> travelCost = getManager().getTravelCost(selected.length);
		if (selected.length == 2) {
			lblEvents.setText("Morelikely to get attack by Priates and bad-weather or sharks");
		}else if(selected.length == 3) {
			lblEvents.setText("likely to get attack by Priates or bad-weather or sharks");
		}else {
			lblEvents.setText("likely to encounter no event or maximum of 1 event");
		}
		
		lblTravelCost.setText("Cost of Travel : " + currency.format(travelCost.get(0)));
		lblTravelDuration.setText("Travel Duration : " + Math.ceil(travelCost.get(1)));
		this.travelLength = selected.length;
		
		if (getManager().getDays() < travelCost.get(1)) {
			btnSetSail.setEnabled(false);
			lblRepairError.setText("You don't have enough days to travel");
		}else if (getManager().getCoins() < travelCost.get(0)) {
			btnSetSail.setEnabled(false);
			lblRepairError.setText("You dont' have enough Coins to travel");
		}else if (getManager().getShip().getDurabilityPercentage() == 100) {
			btnSetSail.setEnabled(true);
			lblRepairError.setText("");
			btnRepairShip.setEnabled(false);
		}else {
			btnSetSail.setEnabled(false);
			lblRepairError.setText("Repair your ship before setting sail");
			btnRepairShip.setEnabled(true);
		}
	}
	
	/**
	 * Shows a screen that tells you about the events occurred during the sailing
	 */
	public void gotodestination() {
		getManager().travelCostYou(travelLength);
		getManager().EventPopUp(travelLength);
	}
}
