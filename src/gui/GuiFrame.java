package gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cluedo.Board;

/**
 * The GameBoard is responsible for managing the GUI 
 */

public class GuiFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	private static Board myBoard;
	
	//Master Panel hold all the other panels
	public JPanel masterPanel;
	
	//Sub Panels
	private ButtonPanel buttonPanel;
	private BoardPanel boardPanel;
	private HandPanel handPanel;
	private CheckListPanel checklistPanel;
	
	/**
	 * Constructer for the GUI
	 */
	public GuiFrame(){
		super("Cluedo GameBoard");
	}

	public void start(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminates when closed
		setVisible(true); //to see the window
		buildGUI();
		
		
		///might need to call deal cards and print players methods here??
	}

	private void buildGUI(){
		//Main panel to put other panels in it
		masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout()); //Borderlayout is the magic!!! wahooo
		
		/** Setting Frame Size + Layout */
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1280,900)); // the frame size
		
		/** Initialise Panels */
		buttonPanel = new ButtonPanel(new GridBagLayout());
		checklistPanel = new CheckListPanel(new GridBagLayout()); 
		boardPanel = new BoardPanel();
		handPanel = new HandPanel(new GridBagLayout()); //might need to change to a different layout

		/**ADD to FRAME*/
		masterPanel.add(buttonPanel, BorderLayout.WEST);
		masterPanel.add(boardPanel, BorderLayout.CENTER);
		masterPanel.add(checklistPanel, BorderLayout.EAST);
		masterPanel.add(handPanel, BorderLayout.SOUTH);
		add(masterPanel);
		
		setResizable(false); //unresizable window
		pack(); //resizes frame so things fit, no extra whitespace
		setLocationRelativeTo(null); //centres frame onscreen  when it runs
		setVisible(true); //shows JFrame
		repaint();
	}


	/**
	 * Returns the number of players in this game
	 * @return
	 */
	public int getNumPlayers() {
		String[] options = {"3", "4", "5", "6"};
		int rc = JOptionPane.showOptionDialog(null, "How Many Players Are In This Game?", "How many players", 
					JOptionPane.DEFAULT_OPTION, 0, null, options, "3");
		int numPlayers = rc+3;
		
		return numPlayers;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Assigns a character to a player
	 * @return
	 */
	/*public Character assignPlayerCharacters() {
		Character assignedCharacter;
		String[] availableCharacters = {"Miss Scarlet", "Mrs. White", "Mrs. Peacock", "Colonel Mustard", "The Reverend Green", "Professor Plum"};
		JComboBox charBox = new JComboBox(availableCharacters);
		String selectedChar = (String)charBox.getSelectedItem();
		
	}*/
	
}

