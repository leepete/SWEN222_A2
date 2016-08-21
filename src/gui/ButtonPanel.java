package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cluedo.CluedoGame;
import cluedo.Player;

public class ButtonPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 290;
	private static final int HEIGHT = 620;
	
	private JButton accuse, suggest, endTurn, roll, stairs, exitRoom;
	private JLabel playerWho; //this updates which players turn it is
	private GuiFrame frame;
	private GridBagConstraints gbc = new GridBagConstraints(); 
	
	public ButtonPanel(GuiFrame frame){
		super();
		this.frame = frame;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridBagLayout());
		this.setVisible(true);
		setButtons();
	}

	
	public void setButtons(){
		/**Panel will now have access to GridBagConstraints*/
		gbc.anchor = GridBagConstraints.WEST; //ALIGNS THE CHECKBOXES
		gbc.fill = GridBagConstraints.BOTH; //aligns buttons perfect - the lengths are even
		gbc.insets = new Insets(10,15,15,15);
		gbc.weightx = 1; ///button size
		gbc.weighty = 1;
		
		/**Left SIDE*/
		accuse = new JButton("Accuse");
		accuse.setFont(new Font("Helvetica", Font.BOLD, 30));
		accuse.addActionListener(this);
		
		suggest = new JButton("Suggestion");
		suggest.setFont(new Font("Helvetica", Font.BOLD, 30));
		suggest.addActionListener(this);
		
		playerWho = new JLabel("ITS SOMEONES TURN");
		playerWho.setFont(new Font("Helvetica", Font.BOLD, 25));
		
		exitRoom = new JButton("Exit Room");
		exitRoom.setFont(new Font("Helvetica", Font.BOLD, 30));
		exitRoom.addActionListener(this);
		
		stairs = new JButton("Stairs");
		stairs.setFont(new Font("Helvetica", Font.BOLD, 30));
		stairs.addActionListener(this);
		
		endTurn = new JButton("End Turn");
		endTurn.setFont(new Font("Helvetica", Font.BOLD, 30));
		endTurn.addActionListener(this);
		
		ImageIcon die = new ImageIcon(getClass().getResource("../die1.jpg"));
		roll = new JButton(die);
		roll.setFont(new Font("Helvetica", Font.BOLD, 30));
		roll.addActionListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(accuse, gbc);
		gbc.gridy++;
		add(suggest, gbc);
		gbc.gridy++;
		add(playerWho, gbc);
		gbc.gridy++;
		add(exitRoom, gbc);
		gbc.gridy++;
		add(stairs, gbc);
		gbc.gridy++;
		add(endTurn, gbc);
		gbc.gridy++;
		add(roll, gbc);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object button = e.getSource();
		if(button.equals(accuse)){
			String[] accusation = frame.accuse();
			if(accusation != null) {
				CluedoGame.currentPlayer.accuse(accusation);
			}
			

		}else if(button.equals(suggest)) {
			String[] suggestion = frame.suggest();
			if(suggestion != null) {
				CluedoGame.currentPlayer.suggest(suggestion);
			}
			
		} else if(button.equals(exitRoom)) {
			JOptionPane.showMessageDialog(null, "Exiting room");
			
		} else if(button.equals(stairs)) {
			JOptionPane.showMessageDialog(null, "Using the stairs");
			
		} else if(button.equals(endTurn)) {
			JOptionPane.showMessageDialog(null, "ending turn");
			
		} else if(button.equals(roll)) {
			
			JOptionPane.showMessageDialog(null, "Rolling the dice");
			CluedoGame.currentPlayer.move();
			
		}	
	
		
	}
}
