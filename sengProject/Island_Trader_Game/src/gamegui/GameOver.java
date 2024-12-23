package gamegui;

import java.awt.Container;

import javax.swing.JPanel;

import models.GameManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Game Over will end the game.
 * You can't go to anyother screen.
 * @author Mohadesa Sharifi, Zahid Khan
 *
 */
public class GameOver extends Screen {

	
	protected GameOver(GameManager manager) {
		super("Game Over", manager);
	}

	/**
	 * GameOver screen shows the name, profit, coins, days remaining, ship inside labels
	 * bought items and sold items will will be displayed if there are any.
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
		panel_1.setBounds(41, 25, 811, 30);
		panel.add(panel_1);
		
		JLabel lblGameOver = new JLabel("Game Over");
		panel_1.add(lblGameOver);
		lblGameOver.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(41, 54, 811, 111);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNameLabel = new JLabel("Trader Name:");
		lblNameLabel.setBounds(0, 0, 100, 16);
		panel_2.add(lblNameLabel);
		
		JLabel lblDays = new JLabel("" + getManager().getDays());
		lblDays.setBounds(759, 0, 29, 16);
		panel_2.add(lblDays);
		
		JLabel lblDaysLabel = new JLabel("Days Remaining:");
		lblDaysLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDaysLabel.setBounds(613, 0, 136, 16);
		panel_2.add(lblDaysLabel);
		JLabel lblProfit = new JLabel("" + getManager().getProfit());
		lblProfit.setBounds(302, 0, 58, 16);
		panel_2.add(lblProfit);
		
		JLabel lblProfitrLabel = new JLabel("Profit : ");
		lblProfitrLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProfitrLabel.setBounds(229, 0, 61, 16);
		panel_2.add(lblProfitrLabel);
		
		JLabel lblName = new JLabel(getManager().getName());
		lblName.setBounds(99, 0, 126, 16);
		panel_2.add(lblName);
		
		JLabel lblShipLabel = new JLabel("Ship:");
		lblShipLabel.setBounds(0, 28, 39, 16);
		panel_2.add(lblShipLabel);
		
		JLabel lblShipName = new JLabel(getManager().getShip().getName());
		lblShipName.setBounds(40, 28, 159, 16);
		panel_2.add(lblShipName);
		
		JLabel lblCoinLabel = new JLabel("Profit : ");
		lblCoinLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCoinLabel.setBounds(435, 0, 61, 16);
		panel_2.add(lblCoinLabel);
		
		JLabel lblCoins = new JLabel("0.0");
		lblCoins.setBounds(508, 0, 58, 16);
		panel_2.add(lblCoins);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(41, 175, 811, 144);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblBoughtItems = new JLabel("Items Bought :");
		lblBoughtItems.setBounds(0, 0, 146, 16);
		panel_3.add(lblBoughtItems);
		
		JTextArea txtBoughtItems = new JTextArea();
		txtBoughtItems.setBounds(10, 21, 801, 123);
		panel_3.add(txtBoughtItems);
		txtBoughtItems.setEditable(false);
		txtBoughtItems.setText(getManager().getboughtItems());
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(41, 329, 811, 144);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblSoldItems = new JLabel("Items Sold : ");
		lblSoldItems.setBounds(0, 0, 112, 16);
		panel_4.add(lblSoldItems);
		
		JTextArea txtSoldItems = new JTextArea();
		txtSoldItems.setBounds(10, 21, 801, 123);
		panel_4.add(txtSoldItems);
		txtSoldItems.setEditable(false);
		txtSoldItems.setText(getManager().getSoldItems());
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setBounds(41, 6, 543, 16);
		panel.add(lblNewLabel_11);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().onFinish();
			}
		});
		btnQuit.setBounds(767, 483, 85, 21);
		panel.add(btnQuit);
	}
}
