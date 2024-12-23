package gamegui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import models.*;
import validations.InputValidation;


import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Color;
/**
 * This the model class for sale screen, it builds the screen and loads all the necessary data.
 * @author Mohadesa Sharifi
 *
 */
public class SellScreen extends Screen{
	
	//textfield for user to enter quantitiy
	private JTextField txtQ;
	//Jlist where trader bought items are added
	private JList<StringBuilder> list;
	//scrollpane for Jlist to be added
	private Container scrollPane;
	//check is an object from InputValidation class
	private InputValidation check = new InputValidation();
	//sell button
	private JButton btnSell;
	// errors are dispalyed inside lblError
	private JLabel lblError;
	private JLabel lblupdateCC;
	//itemNo is the selected index from Jlist
	private int itemNo;
	//quantity is the integer user enters in textfield
	private int quantity;
	//lblcoins displays trader's coin and is dynamic
	private JLabel lblCoins;
	//lblProfit displays rader's profit
	private JLabel lblProfit;
	//container is object coming from Screen
	private Container container;
	//formatter is used for formatting coins and profit to currency 
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	protected SellScreen(GameManager manager) {
		super("Sell Screen", manager);
		}
	/**
	 * @param container is the container of Jframe in abstract class Screen. Components will be added to this container.
	 * This method will initialize the Jframe in Screen abstract class. 
	 * Back button opens storeScreen, Quit button make the game over, profile button opens profile page.
	 * user needs to select an item from Jlist, and enter quantity from available stock to sell.
	 * If all of the above criteria are met then the sell button will be enabled.
	 * clicking the sell button will call the methods for reseting coins and profit labels since the values changes.
	 * sell button listener calls addlist() method to change the list since the quantity of items decreases.
	 */
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected void initialize(Container container) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		this.container = container;
		JPanel storePanel = new JPanel();
		container.add(storePanel);
		storePanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(6, 6, 869, 50);
		storePanel.add(panel_1);
		
		JLabel lblTraderName = new JLabel("<HTML>Name : " + getManager().getName() + "<HTML>");
		lblTraderName.setHorizontalAlignment(SwingConstants.LEFT);
		lblTraderName.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_1.add(lblTraderName);
		lblTraderName.setBounds(0, 5, 235, 39);
		
		JLabel lblShowDays = new JLabel("Days Remaining : " + getManager().getDays());
		lblShowDays.setBounds(217, 5, 150, 39);
		panel_1.add(lblShowDays);
		
		double amt = getManager().getCoins();
		lblCoins = new JLabel("Coins: " + formatter.format(amt));
		lblCoins.setBounds(419, 5, 138, 39);
		panel_1.add(lblCoins);
		
		JLabel lblShowShip = new JLabel("Ship : " + getManager().getShip().getName());
		lblShowShip.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowShip.setBounds(651, 5, 187, 39);
		panel_1.add(lblShowShip);
		double pro = getManager().getProfit();
		lblProfit = new JLabel("Profit:" + formatter.format(pro));
		lblProfit.setBounds(549, 8, 114, 32);
		panel_1.add(lblProfit);
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(null);
		welcomePanel.setBounds(53, 68, 822, 93);
		storePanel.add(welcomePanel);
		
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 55, 794, 24);
		welcomePanel.add(lblError);
		
		JLabel lblNewLabel_3 = new JLabel("Welcome to "+getManager().getCurrentIsland().getStore().getName());
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(89, 21, 550, 23);
		welcomePanel.add(lblNewLabel_3);
		
		JLabel lblCCRemaining = new JLabel("Cargo Capacity : ");
		lblCCRemaining.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCCRemaining.setBounds(630, 0, 108, 24);
		welcomePanel.add(lblCCRemaining);
		
		lblupdateCC = new JLabel("" + getManager().getShip().getAvailableStorage());
		lblupdateCC.setBounds(740, 0, 45, 24);
		welcomePanel.add(lblupdateCC);
		
		txtQ = new JTextField();
		txtQ.setBounds(592, 457, 96, 28);
		storePanel.add(txtQ);
		txtQ.setColumns(10);
		txtQ.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkCanContinue();
			}
		});
	
		
		JLabel lblNewLabel = new JLabel("Quantity");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(502, 456, 80, 28);
		storePanel.add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(53, 457, 102, 28);
		btnBack.addActionListener(e -> getManager().store());
		storePanel.add(btnBack);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(177, 457, 102, 28);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure, you want to end the game", "Quit", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					getManager().gameOver();
				}
			}
		});
		storePanel.add(btnQuit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 195, 822, 251);
		storePanel.add(scrollPane);	
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(517, 455, 193, 15);
		storePanel.add(lblNewLabel_1);
		
		btnSell = new JButton("Sell");
		btnSell.setBounds(720, 459, 117, 25);
		storePanel.add(btnSell);
		btnSell.setEnabled(false);
		btnSell.addActionListener(e -> makeSell());
		
		
		JButton btnprofile = new JButton("Profile");
		btnprofile.setBounds(300, 457, 102, 29);
		storePanel.add(btnprofile);
		
		JLabel lblNewLabel_2 = new JLabel("Item");
		lblNewLabel_2.setBounds(63, 172, 136, 25);
		storePanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Bought price");
		lblNewLabel_2_1.setBounds(268, 172, 136, 25);
		storePanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Sell Price");
		lblNewLabel_2_2.setBounds(491, 172, 136, 25);
		storePanel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Avalaible Quantity");
		lblNewLabel_2_3.setBounds(729, 172, 136, 25);
		storePanel.add(lblNewLabel_2_3);
		btnprofile.addActionListener(e -> getManager().viewProfile());
		addList(container, scrollPane);
	}

	/**
	 * sell button listener will call makeSell() method. 
	 * this method will sell the selected items by the quantity that user provided.
	 * user coins will increase. 
	 * sold item will be added to the profile page.
	 * It will reset the values of of coin label, profit label, Jlist item's quantity.
	 * 
	 */
	private void makeSell() {
		// TODO Auto-generated method stub
		String q = txtQ.getText();
		quantity = Integer.parseInt(q);
		if (getManager().isSellSuccessful(itemNo, quantity)){
			getManager().sell(itemNo, quantity);;
			lblError.setText("Sell Successfull. Go to profile page to see Sold items");
			double amt = getManager().getCoins();
			lblCoins.setText("coins: " + formatter.format(amt));
			double coin = getManager().getProfit();
			lblProfit.setText("Profit: "+ formatter.format(coin));
			addList(container, scrollPane);
			lblupdateCC.setText("" + getManager().getShip().getAvailableStorage());
		}
		else {
			lblError.setText("Sell unsuccessful. Check if you have enough stock.");
		}
	}

	/**
	 * @param container is the container of Jframe in abstract class Screen. components will be added to container.
	 * @param scrollPane is the container inside the main container which will hold Jlist.
	 * Users bought items are returned by game manager to be added to the Jlist
	 * Jlist listeners call checkCanContinue() and setItemNo()
	 */
	protected void addList(Container container, Container scrollPane) {
		
		Item[] products = getManager().getBoughtList();
		
		
		list = new JList<StringBuilder>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultListModel<StringBuilder> items = new DefaultListModel<StringBuilder>();
		for(int count=0; count <products.length; count++) {
			Item storeItem = getManager().getCurrentIsland().getStore().findProduct(products[count]);
			StringBuilder builder = new StringBuilder();
			builder.append("<html><pre>");
			builder.append(String.format("%-25s \t%s \t%25s \t %25s", products[count].getProductName(), products[count].getBuyPrice(), storeItem.getBuyPrice(), products[count].getQuantityAvailble()));
			builder.append("</pre></html>");
			items.add(count, builder); 
			
		}

		list.setModel(items);	
		
		ListSelectionModel selectionModel = list.getSelectionModel();
		((JScrollPane) scrollPane).setViewportView(list);
		selectionModel.addListSelectionListener(event ->setItemNo(list.getSelectedIndex()));
		selectionModel.addListSelectionListener(event ->checkCanContinue());
	}


	/**
	 * This method validates the JtextFiled text. 
	 * checkCanContinue() is called by JtextField listener and Jlist listener
	 * checkCanContinue() checks if JtextFiled text is valid and a row is selected in Jlist 
	 * then it enables the sell button.
	 */

	private void checkCanContinue() {
		String q = txtQ.getText();
		boolean validQ = check.validNumberInRange(q, 1, getManager().maxQBoughtItems(itemNo));

		// Hide the name requirements text if the input is valid
		lblError.setText(validQ ? null : "Please choose from available Quantity");
		btnSell.setEnabled(!list.isSelectionEmpty() && validQ);
		
		}
	


	/**
	 * @param i is the item index user chose from the Jlist 
	 * itemNo is set to selected Jlist index.
	 */
	private void setItemNo(int i) {
		itemNo = i;

	}
}
	
	
	