package gamegui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class WelcomeScreenGUI {

	private JFrame frmIslandTrader;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreenGUI window = new WelcomeScreenGUI();
					window.frmIslandTrader.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeScreenGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIslandTrader = new JFrame();
		frmIslandTrader.setForeground(Color.BLACK);
		frmIslandTrader.setFont(new Font("Dialog", Font.BOLD, 12));
		frmIslandTrader.setTitle("Island Trader");
		frmIslandTrader.setBounds(100, 100, 650, 480);
		frmIslandTrader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmIslandTrader.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(101, 6, 445, 40);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Welcome to the Island Trader Game");
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(101, 152, 445, 48);
		panel.add(panel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Days to play:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		panel_3.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setColumns(15);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(101, 108, 445, 40);
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Name:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(15);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignOnBaseline(true);
		panel_4.setBounds(101, 205, 445, 48);
		panel.add(panel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Choose your ship:");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_4.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		panel_4.add(comboBox);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(223, 338, 193, 59);
		panel.add(panel_5);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_5.add(btnStartGame);
	}
}
