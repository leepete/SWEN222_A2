package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GuiPopups implements ActionListener {
	
	CharacterAssignmentPanel charAssignPanel;
	public GuiPopups() {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
	
	public void assignCharacters(int numPlayers) {
		ArrayList<String> unavailableChars = new ArrayList<String>();
		String[] options = {"Ok"};
		unavailableChars.add("COLONEL MUSTARD");
		unavailableChars.add("MISS SCARLET");
		charAssignPanel = new CharacterAssignmentPanel(1, unavailableChars);
		JOptionPane.showOptionDialog(null, charAssignPanel, "Assign Character", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		
	}	
}
