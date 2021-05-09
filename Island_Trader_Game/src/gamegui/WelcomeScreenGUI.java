package gamegui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.Color;
import java.awt.Container;

import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.GameManager;
import models.Ship;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class WelcomeScreenGUI extends Screen{
	/**
	 * @wbp.parser.entryPoint
	 */
	private JTextField txtName;
	private JTextField txtDays;
	private JComboBox cmbChooseShip;
	private String isValid;
	private JLabel lblError;
	private JButton btnStartGame;
	
	
	protected WelcomeScreenGUI(GameManager manager) {
		super("Welcome screen", manager);
	}
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected void initialize(Container container) {
		
		JPanel panel = new JPanel();
		container.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(101, 6, 445, 40);
		panel.add(panel_1);
		
		JLabel lblWelcome = new JLabel("Welcome to the Island Trader Game");
		panel_1.add(lblWelcome);
		lblWelcome.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(101, 108, 445, 40);
		panel.add(panel_2);
		
		JLabel lblName = new JLabel("Enter Name:");
		lblName.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_2.add(lblName);
		
		txtName = new JTextField();
		panel_2.add(txtName);
		txtName.setColumns(15);
		txtName.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				txtFieldContinue(txtName);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				txtFieldContinue(txtName);

			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				txtFieldContinue(txtName);

			}
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(101, 152, 445, 48);
		panel.add(panel_3);
		
		JLabel lblDaysToPlay = new JLabel("Days to play:");
		lblDaysToPlay.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDaysToPlay.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		panel_3.add(lblDaysToPlay);
		
		txtDays = new JTextField();
		panel_3.add(txtDays);
		txtDays.setColumns(15);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignOnBaseline(true);
		panel_4.setBounds(101, 205, 445, 48);
		panel.add(panel_4);
		
		JLabel lblChooseShip = new JLabel("Choose your ship:");
		lblChooseShip.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblChooseShip.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_4.add(lblChooseShip);
		
//		cmbChooseShip = new JComboBox();
//		for(Ship ship: getManager().getShips()){
//			cmbChooseShip.addItem(ship.getName());
//		
//		}
//		cmbChooseShip.setEditable(true);
//		panel_4.add(cmbChooseShip);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(223, 338, 193, 59);
		panel.add(panel_5);
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.setForeground(Color.BLACK);
		btnStartGame.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		btnStartGame.setEnabled(false);
		btnStartGame.addActionListener(e -> setupComplete());
		panel_5.add(btnStartGame);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(101, 68, 445, 40);
		panel.add(panel_6);
		
		lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblError);
	}
	
	private void txtFieldContinue(JTextField txtField) {
		boolean validtxtFeild = getManager().ValidName(txtField.getText());

		// Hide the name requirements text if the input is valid
		lblError.setText(validtxtFeild ? null : getManager().getName());
		btnStartGame.setEnabled(validtxtFeild);
		show();
	}
	
	private void setupComplete() {
		getManager().onSetupFinished(txtName.getText(), txtDays.getText());
	}
}
	

