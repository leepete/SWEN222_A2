package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;

import cluedo.Board;
import cluedo.CluedoGame;

/**
 * The GameBoard is responsible for managing the GUI 
 * 
 *
 */

public class GuiFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//JFrame - organisation tool
	//JPanel - can draw on it easier/content
	
	/*Draws shapes for us, we need 2 copies is for the save changes*/
	
	private static Board myBoard;

	private GuiPanel myGamePanel;
	public JPanel masterPanel;
	
	private GuiPopups myPopups;

	JButton accuse, suggest, endTurn, roll, stairs, exitRoom;
	
	
	/**IMAGES*/
	private ImageIcon img;
	private JLabel imgLabel;
	

	public GuiFrame(){
		super("Cluedo GameBoard");
		myGamePanel = new GuiPanel();
		myPopups = new GuiPopups();
	}

	public void start(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes
		setVisible(true); //to see the window
		buildGUI(); //goes into constructor
	}

	private void buildGUI(){		// goes into start
		//Main panel to put other panels in it
//		masterPanel = new JPanel();
//		masterPanel.setLayout(new GridLayout(1,2,3,3));
//		masterPanel.add(grid);
//		masterPanel.add(leftPane());
//		add(masterPanel);
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1280,900)); // the frame size
		
		JPanel leftPane = new JPanel(new GridBagLayout());
		leftPane.setPreferredSize(new Dimension(290,620));
		
		JPanel checkList = new JPanel(new GridBagLayout()); 
		checkList.setPreferredSize(new Dimension(290,620));
		
		JPanel bBoard = new JPanel(); //cluedo Board - we want a grid bag lauout
		JPanel showHand = new JPanel(new GridBagLayout()); //might need to change to a scroll bar maybe
		//JScrollPane showHand = new JScrollPane();
		showHand.setPreferredSize(new Dimension(0,150));
		//showHand.setBounds(450, 700, 450, 450);
		showHand.setAlignmentX(LEFT_ALIGNMENT);
		
		/**Panels will now have access to GridBagConstraints*/
		GridBagConstraints gbc = new GridBagConstraints(); // helps organise and space in a grid system
		gbc.anchor = GridBagConstraints.WEST; //ALIGNS THE CHECKBOXES
		gbc.fill = GridBagConstraints.BOTH; //aligns buttons perfect - the lengths are even
		gbc.insets = new Insets(15,15,15,15);
		gbc.weightx = 1; ///button size
		gbc.weighty = 1;		
		
		/**Left SIDE*/
		accuse = new JButton("Accuse");
		//accuse.setPreferredSize(new Dimension(40, 40));
		accuse.setFont(new Font("Helvetica", Font.BOLD, 30));
		accuse.addActionListener(this);
		
		suggest = new JButton("Suggestion");
		suggest.setFont(new Font("Helvetica", Font.BOLD, 30));
		suggest.addActionListener(this);
		
		JLabel playerWho = new JLabel("Player 1 as");
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
		
		//ImageIcon die = new ImageIcon(getClass().getResource("/die.png"));
		roll = new JButton("Roll Dice");
		roll.setFont(new Font("Helvetica", Font.BOLD, 30));
		roll.addActionListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		leftPane.add(accuse, gbc);
		gbc.gridy = 1;
		leftPane.add(suggest, gbc);
		gbc.gridy = 2;
		leftPane.add(playerWho, gbc);
		gbc.gridy = 3;
		leftPane.add(exitRoom, gbc);
		gbc.gridy = 4;
		leftPane.add(stairs, gbc);
		gbc.gridy = 5;
		leftPane.add(endTurn, gbc);
		gbc.gridy = 6;
		leftPane.add(roll, gbc);
		
		/**Right SIDE*/
		gbc.insets = new Insets(1,1,1,1);
		
		JLabel check = new JLabel("CheckList");
		check.setFont(new Font("Helvetica", Font.BOLD, 20));
		gbc.insets = new Insets(1,1,1,1);
		
		JLabel rooms = new JLabel("Rooms:");
		rooms.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		JCheckBox billRoom = new JCheckBox("Billiard Room");
		JCheckBox dineRoom = new JCheckBox("Dining Room");
		//dineRoom.setHorizontalTextPosition(SwingConstants.RIGHT);
		JCheckBox hall = new JCheckBox("Hall");
		JCheckBox ballRoom = new JCheckBox("Ball Room");
		JCheckBox library = new JCheckBox("Library");
		JCheckBox study = new JCheckBox("Study");
		JCheckBox conservatory = new JCheckBox("Conservatory");
		JCheckBox lounge = new JCheckBox("Lounge");
		JCheckBox kitchen = new JCheckBox("Kitchen");
		
		JLabel chars = new JLabel("Characters:");
		chars.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		JCheckBox white = new JCheckBox("Mrs White");
		JCheckBox peacock = new JCheckBox("Mrs Peacock");
		JCheckBox mustard = new JCheckBox("Colonel Mustard");
		JCheckBox reverend = new JCheckBox("The Reverend");
		JCheckBox scarlett = new JCheckBox("Miss Scarlett");
		JCheckBox plum = new JCheckBox("Professor Plum");
		
		JLabel weapons = new JLabel("Weapons:");
		weapons.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		JCheckBox spanner = new JCheckBox("Spanner");
		JCheckBox rope = new JCheckBox("Rope");
		JCheckBox candle = new JCheckBox("Candlestick");
		JCheckBox revolver = new JCheckBox("Revolver");
		JCheckBox leadpipe = new JCheckBox("Lead Pipe");
		JCheckBox dagger = new JCheckBox("Dagger");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		checkList.add(check,gbc);
		gbc.gridy = 1;
		checkList.add(rooms,gbc);
		gbc.gridy = 2;
		checkList.add(billRoom, gbc);
		gbc.gridy = 3;
		checkList.add(dineRoom, gbc);
		gbc.gridy = 4;
		checkList.add(hall, gbc);
		gbc.gridy = 5;
		checkList.add(ballRoom, gbc);
		gbc.gridy = 6;
		checkList.add(library, gbc);
		gbc.gridy = 7;
		checkList.add(study, gbc);
		gbc.gridy = 8;
		checkList.add(conservatory, gbc);
		gbc.gridy = 9;
		checkList.add(lounge, gbc);
		gbc.gridy = 10;
		checkList.add(kitchen, gbc);
		
		gbc.gridy = 11;
		checkList.add(chars, gbc);
		gbc.gridy = 12;
		checkList.add(white, gbc);
		gbc.gridy = 13;
		checkList.add(peacock, gbc);
		gbc.gridy = 14;
		checkList.add(mustard, gbc);
		gbc.gridy = 15;
		checkList.add(reverend, gbc);
		gbc.gridy = 16;
		checkList.add(scarlett, gbc);
		gbc.gridy = 17;
		checkList.add(plum, gbc);
		
		gbc.gridy = 19;
		checkList.add(weapons,gbc);
		gbc.gridy = 20;
		checkList.add(spanner, gbc);
		gbc.gridy = 21;
		checkList.add(rope, gbc);
		gbc.gridy = 22;
		checkList.add(candle, gbc);
		gbc.gridy = 23;
		checkList.add(revolver, gbc);
		gbc.gridy =24;
		checkList.add(leadpipe, gbc);
		gbc.gridy = 25;
		checkList.add(dagger, gbc);
		
		
		/**CENTER*/
		/**Import Image*/
		img = new ImageIcon(getClass().getResource("/cluedo.JPG"));
		imgLabel = new JLabel(img);

		bBoard.add(imgLabel, gbc);
		
		/**SHOW HAND*/
		JTextArea s = new JTextArea("PLACEHOLDER");
		showHand.add(s,gbc);
		
		
		
		/**ADD to FRAME*/
		//leftPane.setBackground(Color.CYAN);
		add(leftPane, BorderLayout.WEST); //adds to JFrame Object
		
		bBoard.setBackground(Color.YELLOW);
		add(bBoard, BorderLayout.CENTER);
		
		//checkList.setBackground(Color.MAGENTA);
		add(checkList, BorderLayout.EAST);
		
		showHand.setBackground(Color.RED);
		//showHand.setLocation(900, 900);
		add(showHand, BorderLayout.SOUTH);
		
	
		setResizable(false); //resizable window - set false when we get the board working
	
		pack(); //resizes panel so things fit, no extra whitespace
		setLocationRelativeTo(null); //centres frame onscreen  when it runs
		setVisible(true); //shows JFrame
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object button = e.getSource();
		
		if(button.equals(accuse)){
			JOptionPane.showMessageDialog(null, "You have accused");
			setVisible(true);
		} else if(button.equals(suggest)) {
			JOptionPane.showMessageDialog(null, "You have Suggested");
			setVisible(true);
		} else if(button == exitRoom) {
			JOptionPane.showMessageDialog(null, "Exiting room");
			setVisible(true);
		} else if(button.equals(stairs)) {
			JOptionPane.showMessageDialog(null, "Using the stairs");
			setVisible(true);
		} else if(button.equals(endTurn)) {
			JOptionPane.showMessageDialog(null, "ending turn");
			setVisible(true);
		} else if(button.equals(roll)) {
			JOptionPane.showMessageDialog(null, "Rolling the dice");
			setVisible(true);
		}	
	}

	/**
	 * Returns the number of players in this game
	 * @return
	 */
	public int getNumPlayers() {
		return myPopups.numberOfPlayers();
	}
	
	/**
	 * Assigns a character to a player
	 * @return
	 */
	public void assignPlayerCharacters(int nPlayers) {
		myPopups.assignCharacters(nPlayers);
		
	}
	
}

