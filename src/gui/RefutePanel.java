package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import cluedo.CluedoGame;
import cluedo.Player;
import cluedo.Suggestion;

public class RefutePanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup handButtons;
	private JLabel title, room, weapon, character;
	
	private Player refuter;
	private GridBagConstraints gbc;
	private ArrayList<JRadioButton> handRadioList;
	private Suggestion suggest;

	public RefutePanel(Player refuter, Suggestion suggest){
		super();
		
		this.refuter = refuter;
		this.suggest = suggest;
		
		room = new JLabel(suggest.getRoom());
		weapon = new JLabel(suggest.getWeapon());
		character = new JLabel(suggest.getCharacter());
		
		gbc = new GridBagConstraints();
		
		handButtons = new ButtonGroup();
		handRadioList = new ArrayList<JRadioButton>();
		
		setLayout(new GridBagLayout());
		
		setContents();
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object button = e.getSource();
	}
	
	/**
	 * Returns the name of the card they refute the guess with
	 * @return
	 */
	public String getValues() {
		return getSelectedButtonText();
	}
	
	/**
	 * Gets the selected radio button and returns its string
	 * Code from: http://stackoverflow.com/a/13232816
	 * iterate over each button in the group and checks if it is selected,
	 * because its in a group only one button can be selected so once it has been found, return the text
	 * @param buttonGroup
	 * @return
	 */
	private String getSelectedButtonText() {
        for (Enumeration<AbstractButton> buttons = handButtons.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        
        return null;
    }

	private void setContents() {
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10,15,15,15);
		
		handButtons = new ButtonGroup();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.gridwidth = 7;
		title = new JLabel("Select which card you would like to refute the guess with");
		add(title, gbc);
		
		gbc.gridy++;
		gbc.gridwidth = 1;
		
		//gbc.anchor = GridBagConstraints.PAGE_START;
		add(room, gbc);
		gbc.gridx++;
		add(weapon, gbc);
		gbc.gridx++;
		add(character, gbc);
		gbc.gridx++;
		gbc.gridy++;
		//gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		
		//For each card in the refuter's hand, buttonize it!
		for(String card : refuter.getHand()) {
			stringToButton(card);
		}
		
		for(JRadioButton rb : handRadioList) {
			add(rb, gbc);
			gbc.gridx++;
		}
		
		gbc.gridy++;
		selectFirstAvailableChar();
	}
	
	/**
	 * Converts a string (from the players hand) to a button
	 */
	private void stringToButton(String card) {
		JRadioButton button = new JRadioButton(card);
		handButtons.add(button);
		handRadioList.add(button);
		//If the suggestion contains the current card then enable it
		if(suggest.contains(card)) {
			button.setEnabled(true);
		} else {
			button.setEnabled(false);
		}
	}
	
	
	/**
	 * Selects the first available character from the radio buttons as a default (ensures something is always selected)
	 */
	private void selectFirstAvailableChar() {
		for (Enumeration<AbstractButton> buttons = handButtons.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isEnabled()) {
                button.setSelected(true);
                return;
            }
        }
	}
}
