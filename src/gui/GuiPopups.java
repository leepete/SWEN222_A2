package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cluedo.Suggestion;

public class GuiPopups implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {}
	
	
	/**
	 * Makes an accusation and returns the names of the room, weapon and character they chose
	 * @return
	 */
	public String[] makeAccusation() {
		String[] options = {"Accuse", "Cancle"};
		AccusationPanel accusePanel = new AccusationPanel();
		JOptionPane.showOptionDialog(null, accusePanel, 
				"Make An Accusation", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
		return accusePanel.getValues();
	}
	
	/**
	 * Displays the dialog box asking the user how many players are in the game
	 * @return
	 */
	public int numberOfPlayers() {
		String[] options = {"3", "4", "5", "6"};
		int rc = JOptionPane.showOptionDialog(null, "How many players are in this game?", "How many players", 
					JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, "3");
		return rc+3; //3 is the minimum number of players
	}
	
	/**
	 * Loop through each player in the game,
	 * asks what their name is
	 * and what character they are playing as 
	 * @param numPlayers
	 */
	public String[] assignCharacters(int id, ArrayList<String> unavailableChars) {
		String[] options = {"Ok"};
		CharacterAssignmentPanel charAssignPanel = new CharacterAssignmentPanel(id, unavailableChars);
		JOptionPane.showOptionDialog(null, charAssignPanel, "Assign Character", 
				JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return charAssignPanel.getValues();
	}	
}