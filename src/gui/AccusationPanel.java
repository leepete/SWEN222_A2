package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AccusationPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	private static final int WIDTH = 290;
	//	private static final int HEIGHT = 620;

	private JRadioButton kitchenRB, ballRoomRB, conservatoryRB, billiardRoomRB, libraryRB, studyRB, hallRB, loungeRB, diningRoomRB;
	private JRadioButton spannerRB, revolverRB, pipeRB, ropeRB, candleRB, daggerRB;
	private JRadioButton scarletRB, mustardRB, plumRB, whiteRB, peacockRB, greenRB;
	
	private ButtonGroup roomButtons;
	private ButtonGroup weaponButtons;
	private ButtonGroup characterButtons;
	
	private Map<String, JRadioButton> roomRadioMap;
	private Map<String, JRadioButton> weaponRadioMap;
	private Map<String, JRadioButton> characterRadioMap;
	
	private List<JRadioButton> roomRadioList;
	private List<JRadioButton> weaponRadioList;
	private List<JRadioButton> characterRadioList;
	
	private JLabel title; 
	
	private int playerID;
	private GridBagConstraints gbc;


	public AccusationPanel(){
		super();
		
		gbc = new GridBagConstraints();
		
		roomButtons = new ButtonGroup();
		characterButtons = new ButtonGroup();
		weaponButtons = new ButtonGroup();
		
		title = new JLabel(String.format("Choose a ROOM WEAPON and CHARACTER you think will solve the case!"));
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		

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
		String[] values = new String[3];
	
		values[0] = getSelectedButtonText(roomButtons); //room
		values[1] = getSelectedButtonText(weaponButtons); //weapon
		values[3] = getSelectedButtonText(characterButtons); //character
		
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
	private String getSelectedButtonText(ButtonGroup bg) {
        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        
        return null;
    }

	private void setContents() {
		int i = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10,15,15,15);
		
		
		//Add the Radio buttons
		
		initialiseRoomButtons();
		initialiseWeaponButtons();
		initialiseCharButtons();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		add(title, gbc);
		gbc.gridy++;
		
		//add the rooms to the panel
		for(i = 0; i < 9; i++) {
			add(roomRadioList.get(i), gbc);
			gbc.gridx++;
		}
		gbc.gridy++;
		gbc.gridx = 0;
		
		//add the weapons to the panel
		for(i = 0; i < 6; i++) {
			add(weaponRadioList.get(i), gbc);
			gbc.gridx++;
		}
		gbc.gridy++;
		gbc.gridx = 0;
		
		//add the characters to the panel
		for(i = 0; i < 6; i++) {
			add(characterRadioList.get(i), gbc);
			gbc.gridx++;
		}
		gbc.gridy++;
		
	}
	
	/**
	 * Initialises the room radio buttons
	 */
	private void initialiseRoomButtons() {
		roomRadioList = new ArrayList<JRadioButton>();
		
		kitchenRB = new JRadioButton("KITCHEN", true);
		ballRoomRB = new JRadioButton("BALL ROOM", false);
		conservatoryRB = new JRadioButton("CONSERVATORY", false);
		billiardRoomRB = new JRadioButton("BILLIARD ROOM", false);
		libraryRB = new JRadioButton("LIBRARY", false);
		studyRB = new JRadioButton("STUDY", false);
		hallRB = new JRadioButton("HALL", false);
		loungeRB = new JRadioButton("LOUNGE", false);
		diningRoomRB = new JRadioButton("DINING ROOM", false);
		
		roomRadioList.add(kitchenRB);
		roomRadioList.add(ballRoomRB);
		roomRadioList.add(conservatoryRB);
		roomRadioList.add(billiardRoomRB);
		roomRadioList.add(libraryRB);
		roomRadioList.add(studyRB);
		roomRadioList.add(hallRB);
		roomRadioList.add(loungeRB);
		roomRadioList.add(diningRoomRB);
		
		groupRoomButtons();
		mapRoomStringButtons();
	}
	
	/**
	 * Initialises the weapon radio buttons
	 */
	private void initialiseWeaponButtons() {
		weaponRadioList = new ArrayList<JRadioButton>();
		
		spannerRB = new JRadioButton("Spanner", true);
		revolverRB = new JRadioButton("Revolver", false);
		pipeRB = new JRadioButton("Lead Pipe", false);
		ropeRB = new JRadioButton("Rope", false);
		candleRB = new JRadioButton("Candlestick", false);
		daggerRB = new JRadioButton("Dagger", false);
		
		weaponRadioList.add(spannerRB);
		weaponRadioList.add(revolverRB);
		weaponRadioList.add(pipeRB);
		weaponRadioList.add(ropeRB);
		weaponRadioList.add(candleRB);
		weaponRadioList.add(daggerRB);
		
		groupWeaponButtons();
		mapWeaponStringButtons();
	}
	
	/**
	 * Initialises the character radio buttons
	 */
	private void initialiseCharButtons() {
		characterRadioList = new ArrayList<JRadioButton>();
		
		scarletRB = new JRadioButton("Miss Scarlet");
		mustardRB = new JRadioButton("Colonel Mustard");
		plumRB = new JRadioButton("Professor Plum");
		whiteRB = new JRadioButton("Mrs White");
		peacockRB = new JRadioButton("Mrs Peacock");
		greenRB = new JRadioButton("The Reverend Green");
		
		characterRadioList.add(scarletRB);
		characterRadioList.add(mustardRB);
		characterRadioList.add(plumRB);
		characterRadioList.add(whiteRB);
		characterRadioList.add(peacockRB);
		characterRadioList.add(greenRB);
		
		groupCharButtons();
		mapCharStringButtons();
	}
	
	/**
	 * Adds all the rooms into the room button group
	 */
	private void groupRoomButtons() {
		roomButtons = new ButtonGroup();
		
		roomButtons.add(kitchenRB);
		roomButtons.add(ballRoomRB);
		roomButtons.add(conservatoryRB);
		roomButtons.add(billiardRoomRB);
		roomButtons.add(libraryRB);
		roomButtons.add(studyRB);
		roomButtons.add(hallRB);
		roomButtons.add(loungeRB);
		roomButtons.add(diningRoomRB);
	}
	
	/**
	 * Adds all the weapons into the weapon button group
	 */
	private void groupWeaponButtons() {
		weaponButtons = new ButtonGroup();
		
		weaponButtons.add(spannerRB);
		weaponButtons.add(revolverRB);
		weaponButtons.add(pipeRB);
		weaponButtons.add(ropeRB);
		weaponButtons.add(candleRB);
		weaponButtons.add(daggerRB);
	}
	
	/**
	 * Adds all the characters into the character button group
	 */
	private void groupCharButtons() {
		characterButtons = new ButtonGroup();
		
		characterButtons.add(scarletRB);
		characterButtons.add(mustardRB);
		characterButtons.add(plumRB);
		characterButtons.add(whiteRB);
		characterButtons.add(peacockRB);
		characterButtons.add(greenRB);
	}
	
	/**
	 * Maps the name of the room to the corresponding button
	 */
	private void mapRoomStringButtons() {
		roomRadioMap = new HashMap<String, JRadioButton>();
		
		roomRadioMap.put("KITCHEN", kitchenRB);
		roomRadioMap.put("BALL ROOM", ballRoomRB);
		roomRadioMap.put("CONSERVATORY", conservatoryRB);
		roomRadioMap.put("BILLIARD ROOM", billiardRoomRB);
		roomRadioMap.put("LIBRARY", libraryRB);
		roomRadioMap.put("STUDY", studyRB);
		roomRadioMap.put("HALL", hallRB);
		roomRadioMap.put("LOUNGE", loungeRB);
		roomRadioMap.put("DINING ROOM", diningRoomRB);
	}
	
	/**
	 * Maps the name of the weapon to the corresponding button
	 */
	private void mapWeaponStringButtons() {
		weaponRadioMap = new HashMap<String, JRadioButton>();
		
		weaponRadioMap.put("SPANNER", spannerRB);
		weaponRadioMap.put("REVOLVER", revolverRB);
		weaponRadioMap.put("LEAD PIPE", pipeRB);
		weaponRadioMap.put("ROPE", ropeRB);
		weaponRadioMap.put("CANDLESTICK", candleRB);
		weaponRadioMap.put("DAGGER", daggerRB);
		
	}

	/**
	 *  Maps the name of the character to the corresponding button
	 */
	private void mapCharStringButtons() {
		characterRadioMap = new HashMap<String, JRadioButton>();
		
		characterRadioMap.put("MISS SCARLET", scarletRB);
		characterRadioMap.put("COLONEL MUSTARD", mustardRB);
		characterRadioMap.put("PROFESSOR PLUM", plumRB);
		characterRadioMap.put("MRS WHITE", whiteRB);
		characterRadioMap.put("MRS PEACOCK", peacockRB);
		characterRadioMap.put("THE REVEREND GREEN", greenRB);
	}
}
