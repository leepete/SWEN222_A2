package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CharacterAssignmentPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	private static final int WIDTH = 290;
	//	private static final int HEIGHT = 620;

	private JRadioButton scarlet, mustard, plum, white, peacock, green;
	private Map<String, JRadioButton> characterRadioMap;
	private ArrayList<String> unavailableChars;
	private ButtonGroup characterButtons;
	private JLabel playerWho; //this updates which players turn it is
	private JTextField playerNameTF;
	private int playerID;
	private GridBagConstraints gbc;


	public CharacterAssignmentPanel(int playerID, ArrayList<String> unavailableChars){
		super();
		
		this.unavailableChars = unavailableChars;
		this.playerID = playerID;
		gbc = new GridBagConstraints();
		characterRadioMap = new HashMap<String, JRadioButton>();
		playerNameTF = new JTextField(20);
		characterButtons = new ButtonGroup();
		playerWho = new JLabel(String.format("Player %d, Please enter a name and choose your character", playerID));
		

		//this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
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
	 * Returns the name and character that the player chose
	 * @return
	 */
	public String[] getValues() {
		String[] values = new String[2];
		
		String playerName = playerNameTF.getText();
		String characterName = getSelectedButtonText();
		
		//If the user doesn't enter a name, they will be given the name of their character
		if(playerName == "") {
			playerName = characterName;
		}
		
		values[0] = playerName;
		values[1] = characterName;
		
		return values;
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
        for (Enumeration<AbstractButton> buttons = characterButtons.getElements(); buttons.hasMoreElements();) {
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
		
		
		//Add the Radio buttons
		scarlet = new JRadioButton("Miss Scarlet");
		mustard = new JRadioButton("Colonel Mustard");
		plum = new JRadioButton("Professor Plum");
		white = new JRadioButton("Mrs White");
		peacock = new JRadioButton("Mrs Peacock");
		green = new JRadioButton("The Reverend Green");
		
		initialiseCharMap();
		
		characterButtons = new ButtonGroup();
		characterButtons.add(scarlet);
		characterButtons.add(mustard);
		characterButtons.add(plum);
		characterButtons.add(white);
		characterButtons.add(peacock);
		characterButtons.add(green);
		//Grey out the unavailable ones
		greyOutRadio();
		//Selecte a default character
		selectFirstAvailableChar();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		//Title
		add(playerWho, gbc);
		gbc.gridy++;
		//Text box for player name
		add(playerNameTF, gbc);
		gbc.gridy++;
		//Radio buttons for choosing which character you want to be
		add(scarlet, gbc);
		gbc.gridy++;
		add(mustard, gbc);
		gbc.gridy++;
		add(plum, gbc);
		gbc.gridy++;
		add(white, gbc);
		gbc.gridy++;
		add(peacock, gbc);
		gbc.gridy++;
		add(green, gbc);
		
		
	}
	
	/**
	 * Selects the first available character from the radio buttons as a default (ensures something is always selected)
	 */
	private void selectFirstAvailableChar() {
		for (Enumeration<AbstractButton> buttons = characterButtons.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isEnabled()) {
                button.setSelected(true);
                return;
            }
        }
	}

	/**
	 * Greys out the character radio buttons that other players have chosen
	 */
	private void greyOutRadio() {
		//For each unavailable character, set the radiobutton to disabled
				
		for(String s : unavailableChars) {
			try {
				characterRadioMap.get(s).setEnabled(false);
			} catch(NullPointerException npe) {
				System.out.println("Error disabling option, could not find " + s);
			}
		}
	}

	/**
	 * Initialises the map of strings to character radio buttons
	 */
	private void initialiseCharMap() {
		

		characterRadioMap.put("MISS SCARLET", scarlet);
		characterRadioMap.put("COLONEL MUSTARD", mustard);
		characterRadioMap.put("PROFESSOR PLUM", plum);
		characterRadioMap.put("MRS WHITE", white);
		characterRadioMap.put("MRS PEACOCK", peacock);
		characterRadioMap.put("THE REVEREND GREEN", green);
	}

	/*public void setContents(){
		//Panels will now have access to GridBagConstraints
		gbc.anchor = GridBagConstraints.WEST; //ALIGNS THE CHECKBOXES
		gbc.fill = GridBagConstraints.BOTH; //aligns buttons perfect - the lengths are even
		gbc.insets = new Insets(10,15,15,15);
		gbc.weightx = 1; ///button size
		gbc.weighty = 1;

		//Left SIDE
		accuse = new JButton("Accuse");
		//accuse.setPreferredSize(new Dimension(250, 90));
		accuse.setFont(new Font("Helvetica", Font.BOLD, 30));
		accuse.addActionListener(this);

		suggest = new JButton("Suggestion");
		//suggest.setPreferredSize(new Dimension(250, 90));
		suggest.setFont(new Font("Helvetica", Font.BOLD, 30));
		suggest.addActionListener(this);

		playerWho = new JLabel("          Player 1 as");
		//playerWho.setPreferredSize(new Dimension(250, 100));
		playerWho.setFont(new Font("Helvetica", Font.BOLD, 25));

		exitRoom = new JButton("Exit Room");
		//exitRoom.setPreferredSize(new Dimension(250, 90));
		exitRoom.setFont(new Font("Helvetica", Font.BOLD, 30));
		exitRoom.addActionListener(this);

		stairs = new JButton("Stairs");
		//.setPreferredSize(new Dimension(250, 90));
		stairs.setFont(new Font("Helvetica", Font.BOLD, 30));
		stairs.addActionListener(this);

		endTurn = new JButton("End Turn");
		//endTurn.setPreferredSize(new Dimension(250, 90));
		endTurn.setFont(new Font("Helvetica", Font.BOLD, 30));
		endTurn.addActionListener(this);

		//ImageIcon die = new ImageIcon(getClass().getResource("/die.png"));
		roll = new JButton("Roll Dice");
		//roll.setPreferredSize(new Dimension(250, 90));
		roll.setFont(new Font("Helvetica", Font.BOLD, 30));
		roll.addActionListener(this);

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(accuse, gbc);
		gbc.gridy = 1;
		add(suggest, gbc);
		gbc.gridy = 2;
		add(playerWho, gbc);
		gbc.gridy = 3;
		add(exitRoom, gbc);
		gbc.gridy = 4;
		add(stairs, gbc);
		gbc.gridy = 5;
		add(endTurn, gbc);
		gbc.gridy = 6;
		add(roll, gbc);


	}*/


}
