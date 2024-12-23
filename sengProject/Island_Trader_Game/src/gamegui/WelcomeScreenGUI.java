package gamegui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import main.LoadGameData;
import models.GameManager;
import models.Ship;
import transaction_interface.ManagerInterface;
import validations.InputValidation;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Welcome screen sets up the game.
 * @author Mohadesa Sharifi, Zahid khan
 */
public class WelcomeScreenGUI extends Screen{
	/**
	 * @wbp.parser.entryPoint
	 */
	private JTextField txtName;
	private JTextField txtDays;
	private JComboBox<String> cmbChooseShip;
	private String isValid;
	private JLabel lblError;
	private JLabel shipDetailLabel;
	private JButton btnStartGame;
	private int shipNo;
	private InputValidation check = new InputValidation();
	
	
	protected WelcomeScreenGUI(GameManager manager) {
		super("Welcome screen", manager);
	}
	
	/**
	 * @param container is the container of Jframe in abstract class Screen. Components will be added to this container.
	 * User can enter name and days to the Jtextfield.
	 * User can choose a ship from the comboBox.
	 * After the input is validated the start button will be enabled and
	 * gameManager finishes the game setup and and mainGame page will open.
	 */
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected void initialize(Container container) {
		
		JPanel panel = new JPanel();
		container.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 205, 878, 48);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblChooseShip = new JLabel("Choose your ship: ");
		lblChooseShip.setBounds(243, 8, 156, 17);
		lblChooseShip.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblChooseShip.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_4.add(lblChooseShip);
		
		cmbChooseShip = new JComboBox<String>();
		cmbChooseShip.setBounds(400, 3, 180, 27);
		cmbChooseShip.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Loyal Explorer", "Emperor of the Sea", "Ocean Warrior", "Gladiator of the Sea"}));
		cmbChooseShip.isEditable();
		panel_4.add(cmbChooseShip);
		cmbChooseShip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cmbChooseShip.getSelectedIndex() == 0) {
					shipDetailLabel.setText("");
					btnStartGame.setEnabled(false);
				}else {
					shipNo = cmbChooseShip.getSelectedIndex()-1;
					Ship chosenShip = getManager().getShips().get(shipNo);
					String chosenShipLabel = createShipLabel(chosenShip);
					shipDetailLabel.setText(String.valueOf(("<html><pre>" + chosenShipLabel + "</pre></html>")));
					validateButton(txtName, txtDays, shipDetailLabel);
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 6, 878, 40);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to the Island Trader Game");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(10, 5, 858, 26);
		panel_1.add(lblWelcome);
		lblWelcome.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 108, 878, 40);
		panel.add(panel_2);
		
		JLabel lblName = new JLabel("Enter Name:");
		lblName.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_2.add(lblName);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				isValid = txtName.getText();
				if(!check.validName(isValid)) {
					lblError.setText("<HTML>Please choose 3-15 alphabetical characters<HTML>");
					btnStartGame.setEnabled(false);
				}else{
					lblError.setText("");
					validateButton(txtName, txtDays, shipDetailLabel);
				}
			}
		});
		panel_2.add(txtName);
		txtName.setColumns(15);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 152, 878, 48);
		panel.add(panel_3);
		
		JLabel lblDaysToPlay = new JLabel("Days to play:");
		lblDaysToPlay.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDaysToPlay.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		panel_3.add(lblDaysToPlay);
		
		txtDays = new JTextField();
		txtDays.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!check.validNumberInRange(txtDays.getText(), 20, 50)) {
					lblError.setText("Please choose days between 20-50");
					btnStartGame.setEnabled(false);
				}else {
					lblError.setText("");
					validateButton(txtName, txtDays, shipDetailLabel);
				}
			}
		});
		panel_3.add(txtDays);
		txtDays.setColumns(15);
		
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(528, 345, 193, 59);
		panel.add(panel_5);
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.setForeground(Color.BLACK);
		btnStartGame.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		btnStartGame.setEnabled(false);
		btnStartGame.addActionListener(e -> setupComplete());
		panel_5.add(btnStartGame);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 68, 878, 40);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		lblError = new JLabel();
		lblError.setForeground(Color.RED);
		lblError.setBounds(6, 6, 866, 28);
		lblError.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblError);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(235, 258, 260, 205);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		shipDetailLabel = new JLabel();
		shipDetailLabel.setAlignmentX(0.5f);
		shipDetailLabel.setBounds(6, 6, 241, 179);
		
		panel_7.add(shipDetailLabel);
	}
	
	/**
	 * @param nameField is the name user entered
	 * @param daysField is days user entered
	 * @param shipDetail is the ship user chose
	 * This method checks if all the params are valid and then enables the start button
	 */
	private void validateButton(JTextField nameField, JTextField daysField, JLabel shipDetail) {
		if(check.validName(nameField.getText()) && 
		check.validNumberInRange(daysField.getText(), 20, 50) &&
		!shipDetail.getText().isEmpty()){
			btnStartGame.setEnabled(true);
			show();
		}
	}
	
	/**
	 * This method pass the valid name, days and ship to the game manager so that gamemanager 
	 * set the trader. 
	 */
	private void setupComplete() {
		getManager().onSetupFinished(txtName.getText(), txtDays.getText(), shipNo);
	}
	
	/**
	 * this method generates and return the label to print when user selects a ship.
	 * @param chosenShip
	 * @return
	 *  Ship name: Emperor of the Sea   	Speed: 450.00 	Number of crew: 25 
		Daily wage: 12.50 	Cargo Capacity: 749.00kg 	Durability: 160.00
	 */
	private String createShipLabel(Ship chosenShip) {
		
		String output = "";
		output += "Ship name: " + chosenShip.getName() + "\n";
		output += "Speed: " + chosenShip.getSpeed() + "\n";
		output += "Number of crew: " + chosenShip.getCrew() + "\n";
		output += "Daily wage: " + chosenShip.getDailyWage() + "\n";
		output += "Cargo Capacity: " + chosenShip.getAvailableStorage() + "\n";
		output += "Durability: " + chosenShip.getDurability();

		return output;
	}
	
	/**
	 * this method starts GUI or Command line depending on the arguments it's called with
	 * @param args
	 */
	public static void main(String[] args) {
		LoadGameData data = new LoadGameData();
		ManagerInterface ui;
		ui = new Gui();
		GameManager manager = new GameManager(ui, data);
        SwingUtilities.invokeLater(() -> manager.start());
	}
}
	

