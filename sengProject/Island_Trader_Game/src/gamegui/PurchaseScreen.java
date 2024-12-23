package gamegui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import models.*;
import validations.InputValidation;


import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Color;



/**
 * This is model for purchase screen.
 * @author Zahid Khan
 *
 */
public class PurchaseScreen extends Screen{
	
	//textfield for user to enter quantity
	private JTextField txtQ;
	//Jlist where items are added
	private JList<StringBuilder> list;
	//The category number user choose to buy
	private int categoryNo;
	//Jlist is added to this scrollPane
	private Container scrollPane;
	//check is object inputValidation Class
	private InputValidation check = new InputValidation();
	//purchase button
	private JButton btnPurchase;
	//error label
	private JLabel lblError;
	private JLabel lblupdateCC;
	//itemNo that user selects to buy
	private int itemNo;
	//quantity that user enters in Jtextfield
	private int quantity;
	//label coins is dynamic
	private JLabel lblCoins;
	//label profit is dynamic
	private JLabel lblProfit;
	//container is coming from Screen abstract class
	private Container container;
	//formatter is used to format coins and profit
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	protected PurchaseScreen(GameManager manager) {
		super("Purchase Screen", manager);
		}
	/**
	 * @param container is the container of Jframe in abstract class Screen. Components will be added to this container.
	 * Purchase screen allows user to purchase items from the store in the current Island.
	 * Back button opens storeScreen, Quit button make the game over, profile button opens profile page.
	 * user needs to select a category from JcomboBox, select item from Jlist, and enter quantity to buy.
	 * If all of the above criteria are met then the purchase button will be enabled.
	 * clicking the purchase button will call the methods for reseting coins and profit labels since the values changes.
	 * purchase button listener calls addlist() method to change the list since the quantity of items decreases.
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
		panel_1.setBounds(28, 27, 869, 36);
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
		welcomePanel.setBounds(53, 67, 822, 97);
		storePanel.add(welcomePanel);
		
		JLabel lblWelcome = new JLabel("<HTML>Select from the given categories<HTML>");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(24, 6, 345, 34);
		welcomePanel.add(lblWelcome);
		
		String[] categories = getManager().getItemsByCategory();
	
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(386, 11, 106, 24);
		welcomePanel.add(comboBox);
		
		comboBox.setModel(new DefaultComboBoxModel<String>(categories));
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 62, 794, 24);
		welcomePanel.add(lblError);
		
		JLabel lblCCRemaining = new JLabel("Cargo Capacity : ");
		lblCCRemaining.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCCRemaining.setBounds(644, 0, 113, 24);
		welcomePanel.add(lblCCRemaining);
		
		lblupdateCC = new JLabel("");
		lblupdateCC.setText("" + getManager().getShip().getAvailableStorage());
		lblupdateCC.setBounds(759, 0, 45, 24);
		welcomePanel.add(lblupdateCC);
		comboBox.isEditable();		
		comboBox.addActionListener( e -> addTable( container,  scrollPane, comboBox.getSelectedIndex()));
		comboBox.addActionListener( e -> btnPurchase.setEnabled(false));
		comboBox.addActionListener(e -> txtQ.setEditable(true));
		comboBox.addActionListener(e -> setItemNo(0));
		
		txtQ = new JTextField();
		txtQ.setEditable(false);
		txtQ.setBounds(601, 455, 96, 28);
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
		lblNewLabel.setBounds(501, 454, 80, 28);
		storePanel.add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(53, 455, 102, 28);
		btnBack.addActionListener(e -> getManager().store());
		storePanel.add(btnBack);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(175, 455, 102, 28);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure, you want to end the game", "Save Sailors", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					getManager().gameOver();
				}
			}
		});
		storePanel.add(btnQuit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 182, 822, 251);
		storePanel.add(scrollPane);	
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(517, 455, 193, 15);
		storePanel.add(lblNewLabel_1);
		
		btnPurchase = new JButton("Purchase");
		btnPurchase.setBounds(730, 457, 117, 25);
		storePanel.add(btnPurchase);
		btnPurchase.setEnabled(false);
		btnPurchase.addActionListener(e -> makePurchase());
		
		JButton btnprofile = new JButton("Profile");
		btnprofile.setBounds(302, 455, 102, 29);
		storePanel.add(btnprofile);
		
		JLabel lblNewLabel_2 = new JLabel("Item");
		lblNewLabel_2.setBounds(53, 162, 193, 25);
		storePanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Price");
		lblNewLabel_2_1.setBounds(273, 162, 193, 25);
		storePanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Available Quantity");
		lblNewLabel_2_2.setBounds(476, 162, 193, 25);
		storePanel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_3 = new JLabel("Welcome to "+getManager().getCurrentIsland().getStore().getName());
		lblNewLabel_3.setBounds(10, 0, 873, 24);
		storePanel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(762, 67, 96, 25);
		storePanel.add(lblNewLabel_5);
		btnprofile.addActionListener(e -> getManager().viewProfile());
	}

	/**
	 * purchase button listener will call makePurchase() method. 
	 * this method will buy the selected items by the quantity that user provided
	 * user coins will be spent. 
	 * bought item will be added to the profile page.
	 * It will reset the values of of coin label, profit label, Jlist item's quantity.
	 * 
	 */
	private void makePurchase() {
		String q = txtQ.getText();
		quantity = Integer.parseInt(q);
		if (getManager().isPurchaseSuccessful(quantity, itemNo, categoryNo)){
			getManager().purchase(quantity, itemNo, categoryNo);
			lblError.setText("Purchase Successfull. Go to profile page to see bought items");
			double amt = getManager().getCoins();
			lblCoins.setText("coins: " + this.formatter.format(amt));
			double coin = getManager().getProfit();
			lblProfit.setText("Profit: "+ this.formatter.format(coin));
			addTable(container, scrollPane, categoryNo);
			lblupdateCC.setText("" + getManager().getShip().getAvailableStorage());

		}
		else {
			lblError.setText("Purchase unsuccessful. Check if you have enough coins or enough cargo space.");
		}
	}
	
	/**
	 * 
	 * @param container is the container of Jframe in abstract class Screen. components will be added to container.
	 * @param scrollPane is the container inside the main container which will hold Jlist.
	 * @param i is the category user chose, so categoryNo is set to i.
	 * The products from the given category is returned by game manager to be added to the Jlist
	 * Jlist listeners call checkCanContinue() and setItemNo()
	 */
	protected void addTable(Container container, Container scrollPane, int i) {
		categoryNo = i;
		Item[] products = getManager().showProducts(i);
		
		list = new JList<StringBuilder>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultListModel<StringBuilder> items = new DefaultListModel<StringBuilder>();
		for(int count=0; count <products.length; count++) {
			StringBuilder builder = new StringBuilder();
			builder.append("<html><pre>");
			builder.append(String.format("%-25s\t%s \t %25s", products[count].getProductName(), products[count].getSellPrice(), products[count].getQuantityAvailble()));
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
	 * then it enables the purchase button.
	 */

	private void checkCanContinue() {
		String q = txtQ.getText();
		boolean validQ = check.validNumberInRange(q, 1, getManager().getMaxQuantityAvailableAtStore(itemNo, categoryNo));

		// Hide the name requirements text if the input is valid
		lblError.setText(validQ ? null : "Please choose from available Quantity");
		// && 
		btnPurchase.setEnabled(!list.isSelectionEmpty() && validQ);
		}
	


	/**
	 * @param i is the item index user chose from the Jlist 
	 * itemNo is set to selected Jlist index.
	 */
	private void setItemNo(int i) {
		itemNo = i;
	}
}
