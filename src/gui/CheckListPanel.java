package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckListPanel extends JPanel {

	private JLabel check, rooms, chars, weapons;

	/**Room Checkboxes*/
	private JCheckBox billRoom, dineRoom, hall, ballRoom, library, study,
	conservatory, lounge, kitchen;

	/**Character Checkboxes*/
	private JCheckBox white, peacock, mustard, reverend, scarlett, plum;

	/**Weapon Checkboxes*/
	private JCheckBox spanner, rope, candle, revolver, leadpipe, dagger;
	
	private GridBagConstraints gbc = new GridBagConstraints(); // helps organise and space in a grid system

	private static final int WIDTH = 290;
	private static final int HEIGHT = 620;

	public CheckListPanel(){
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridBagLayout());
		setVisible(true);
		check = new JLabel("CheckList");
		check.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		/**Initialise GridBagConstraints*/
		gbc.anchor = GridBagConstraints.WEST; //ALIGNS THE CHECKBOXES
		gbc.fill = GridBagConstraints.BOTH; //aligns buttons perfect - the lengths are even
		gbc.insets = new Insets(1 ,1, 1,1);
		gbc.weightx = 0; ///button size
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		addRooms();
		addCharacters();
		addWeapons();
	}


	public void addRooms(){
		rooms = new JLabel("Rooms:");
		rooms.setFont(new Font("Helvetica", Font.BOLD, 16));

		billRoom = new JCheckBox("Billiard Room");
		dineRoom = new JCheckBox("Dining Room");
		hall = new JCheckBox("Hall");
		ballRoom = new JCheckBox("Ball Room");
		library = new JCheckBox("Library");
		study = new JCheckBox("Study");
		conservatory = new JCheckBox("Conservatory");
		lounge = new JCheckBox("Lounge");
		kitchen = new JCheckBox("Kitchen");

		add(check,gbc);
		gbc.gridy++;
		add(rooms,gbc);
		gbc.gridy++;
		add(billRoom, gbc);
		gbc.gridy++;
		add(dineRoom, gbc);
		gbc.gridy++;
		add(hall, gbc);
		gbc.gridy++;
		add(ballRoom, gbc);
		gbc.gridy++;
		add(library, gbc);
		gbc.gridy++;
		add(study, gbc);
		gbc.gridy++;
		add(conservatory, gbc);
		gbc.gridy++;
		add(lounge, gbc);
		gbc.gridy++;
		add(kitchen, gbc);
	}

	public void addCharacters(){
		chars = new JLabel("Characters:");
		chars.setFont(new Font("Helvetica", Font.BOLD, 16));

		white = new JCheckBox("Mrs White");
		peacock = new JCheckBox("Mrs Peacock");
		mustard = new JCheckBox("Colonel Mustard");
		reverend = new JCheckBox("The Reverend");
		scarlett = new JCheckBox("Miss Scarlett");
		plum = new JCheckBox("Professor Plum");
		
		gbc.gridy++;
		add(chars, gbc);
		gbc.gridy++;
		add(white, gbc);
		gbc.gridy++;
		add(peacock, gbc);
		gbc.gridy++;
		add(mustard, gbc);
		gbc.gridy++;
		add(reverend, gbc);
		gbc.gridy++;
		add(scarlett, gbc);
		gbc.gridy++;
		add(plum, gbc);
	}

	public void addWeapons(){
		weapons = new JLabel("Weapons:");
		weapons.setFont(new Font("Helvetica", Font.BOLD, 16));

		spanner = new JCheckBox("Spanner");
		rope = new JCheckBox("Rope");
		candle = new JCheckBox("Candlestick");
		revolver = new JCheckBox("Revolver");
		leadpipe = new JCheckBox("Lead Pipe");
		dagger = new JCheckBox("Dagger");
		
		gbc.gridy++;
		add(weapons,gbc);
		gbc.gridy++;
		add(spanner, gbc);
		gbc.gridy++;
		add(rope, gbc);
		gbc.gridy++;
		add(candle, gbc);
		gbc.gridy++;
		add(revolver, gbc);
		gbc.gridy++;
		add(leadpipe, gbc);
		gbc.gridy++;
		add(dagger, gbc);
	}
}
