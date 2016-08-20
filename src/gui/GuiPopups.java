package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class GuiPopups implements ActionListener {
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
					JOptionPane.PLAIN_MESSAGE, 0, null, options, "3");
		return rc+3; //3 is the minimum number of players
		
	}
	
	public void assignCharacters(int numPlayers) {
		
		String[] charStrings = {"Miss Scarlet", "Mrs. White", "Mrs. Peacock", "Colonel Mustard", "The Reverend Green", "Professor Plum"};
		JOptionPane.showInputDialog(null, "Assign a character to each player", "Assign Characters", 
				JOptionPane.PLAIN_MESSAGE, null, charStrings, "Miss Scarlet");
	}	
}
