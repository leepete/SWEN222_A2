package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
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

	public CheckListPanel(LayoutManager layout){
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setLayout(layout);
		setVisible(true);
		check = new JLabel("CheckList");
		check.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		/**Initialise GridBagConstraints*/
		gbc.anchor = GridBagConstraints.WEST; //ALIGNS THE CHECKBOXES
		gbc.fill = GridBagConstraints.BOTH; //aligns buttons perfect - the lengths are even
		gbc.insets = new Insets(1 ,1, 1,1);
		gbc.weightx = 0; ///button size
		gbc.weighty = 0.1;	
		
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
		
//		gbc.anchor = GridBagConstraints.WEST; //ALIGNS THE CHECKBOXES
//		gbc.fill = GridBagConstraints.BOTH; //aligns buttons perfect - the lengths are even
//		gbc.insets = new Insets(15,15,15,15);
//		gbc.weightx = 1; ///button size
//		gbc.weighty = 1;	
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(check,gbc);
		gbc.gridy = 1;
		add(rooms,gbc);
		gbc.gridy = 2;
		add(billRoom, gbc);
		gbc.gridy = 3;
		add(dineRoom, gbc);
		gbc.gridy = 4;
		add(hall, gbc);
		gbc.gridy = 5;
		add(ballRoom, gbc);
		gbc.gridy = 6;
		add(library, gbc);
		gbc.gridy = 7;
		add(study, gbc);
		gbc.gridy = 8;
		add(conservatory, gbc);
		gbc.gridy = 9;
		add(lounge, gbc);
		gbc.gridy = 10;
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
		
//		gbc.anchor = GridBagConstraints.WEST; //ALIGNS THE CHECKBOXES
//		gbc.fill = GridBagConstraints.BOTH; //aligns buttons perfect - the lengths are even
//		gbc.insets = new Insets(15,15,15,15);
//		gbc.weightx = 1; ///button size
//		gbc.weighty = 1;	
		
		gbc.gridy = 11;
		add(chars, gbc);
		gbc.gridy = 12;
		add(white, gbc);
		gbc.gridy = 13;
		add(peacock, gbc);
		gbc.gridy = 14;
		add(mustard, gbc);
		gbc.gridy = 15;
		add(reverend, gbc);
		gbc.gridy = 16;
		add(scarlett, gbc);
		gbc.gridy = 17;
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
		
//		gbc.anchor = GridBagConstraints.WEST; //ALIGNS THE CHECKBOXES
//		gbc.fill = GridBagConstraints.BOTH; //aligns buttons perfect - the lengths are even
//		gbc.insets = new Insets(15,15,15,15);
//		gbc.weightx = 1; ///button size
//		gbc.weighty = 1;	
		
		gbc.gridy = 19;
		add(weapons,gbc);
		gbc.gridy = 20;
		add(spanner, gbc);
		gbc.gridy = 21;
		add(rope, gbc);
		gbc.gridy = 22;
		add(candle, gbc);
		gbc.gridy = 23;
		add(revolver, gbc);
		gbc.gridy =24;
		add(leadpipe, gbc);
		gbc.gridy = 25;
		add(dagger, gbc);
	}
}
