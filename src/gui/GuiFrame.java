package gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cluedo.CluedoGame;
import cluedo.Player;

/**
 * The GameBoard is responsible for managing the GUI 
 */

public class GuiFrame extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	//Master Panel hold all the other panels
	public JPanel masterPanel;
	
	//Sub Panels
	private ButtonPanel buttonPanel;
	private BoardPanel boardPanel;
	private HandPanel handPanel;
	private CheckListPanel checklistPanel;

	private GuiPopups myPopups;
	
	//Menu Bar
	private JMenuBar menuBar;
	private JMenu file;
	private JMenu options;
	
	//SubMenu tabs
	private JMenuItem newGame;
	private JMenuItem exitGame;
	private JMenuItem help;
	private JMenuItem about;
	
	//Game Object
	CluedoGame game;
	
	/**
	 * Constructor for the GUI
	 */
	public GuiFrame(CluedoGame game){
		super("Cluedo GameBoard");

		myPopups = new GuiPopups();
		
		this.game = game;
		
	}

	public void start(){
		/**Closing the Window Prompt*/
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		addWindowListener(new WindowAdapter() {
		   
			@Override
		    public void windowClosing(WindowEvent we){ 
		        String buttons[] = {"Yes","No"};
		        int prompt = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Cluedo",
		        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, buttons, buttons[1]);
		        if(prompt == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		setFocusable(true);
		addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
		setVisible(true); //to see the window
		buildGUI();
		
		
		///might need to call deal cards and print players methods here??
		//Na G
	}

	private void buildGUI(){
		//Main panel to put other panels in it
		masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());
		
		/** Setting Frame Size + Layout */
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1280,900)); // the frame size
		

		/** Initialise Panels */
		buttonPanel = new ButtonPanel(this);
		checklistPanel = new CheckListPanel(); 
		boardPanel = new BoardPanel(game.b);
		handPanel = new HandPanel(new GridBagLayout());
		
		boardPanel.addKeyListener(this);
		/**Adding Menu Bar to frame*/
		setMenu();
		
		/**ADD to MasterPanel*/
		masterPanel.add(buttonPanel, BorderLayout.WEST);
		masterPanel.add(boardPanel, BorderLayout.CENTER);
		masterPanel.add(checklistPanel, BorderLayout.EAST);
		masterPanel.add(handPanel, BorderLayout.SOUTH);
		add(masterPanel); //add to Frame
		

		setResizable(false); //unresizable window
		pack(); //resizes frame so things fit, no extra whitespace
		setLocationRelativeTo(null); //centres frame onscreen  when it runs
		setVisible(true); //shows JFrame
		repaint();
	}
	
	
	/**
	 * Setting the menu bar
	 */
	public void setMenu(){
		//Creating the menuBar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Adding the tabs in the menu*
		file = new JMenu("File");
		menuBar.add(file);
		
		options = new JMenu("Options");
		menuBar.add(options);
		
		//Adding sub boxes in file tab
		newGame = new JMenuItem("New Game");
		file.add(newGame);
		file.addSeparator(); //creates a line between stuff
		exitGame = new JMenuItem("Exit Game");
		file.add(exitGame);
		
		//Adding sub boxes in option tab
		help = new JMenuItem("Help");
		options.add(help);
		about = new JMenuItem("About");
		options.add(about);
		
		newGame.addActionListener(this);
		exitGame.addActionListener(this);
		help.addActionListener(this);
		about.addActionListener(this);			
	}

	/**
	 * Updates the button options available to the player
	 * @param options
	 */
	public void updateOptions(List<String> options) {
		buttonPanel.enableOptions(options);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object push = e.getSource();
		if(push.equals(newGame)){
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Create a New Game",
					 JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				this.dispose(); //closes the current app
				game.resetGame(); //creates a new APP
				repaint();
			}
		} else if(push.equals(exitGame)){
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Exiting Game",
					 JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		} else if(push.equals(help)){
			String s = "In this game, you must solve the murder by correctly deducing\n the CHARACTER the WEAPON and the ROOM.";

			JOptionPane.showMessageDialog(null, s, "About",
					JOptionPane.INFORMATION_MESSAGE);
		} else if(push.equals(about)){
			String s = "Cluedo re-created by Darren Hobern & Peter Lee\n for SWEN222 Assignment 2, 2016";
			JOptionPane.showMessageDialog(null, s, "About",
					JOptionPane.INFORMATION_MESSAGE);
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
		int i = 1;
		ArrayList<String> unavailableCharacters = new ArrayList<String>();
		while(i <= nPlayers) {
			String[] values = myPopups.assignCharacters(unavailableCharacters);
			game.createPlayer(values[0], values[1], i);
			unavailableCharacters.add(values[1]);
			i++;
		}
	}
	
	public String[] accuse() {
		return myPopups.makeAccusation();
	}
	
	public String[] suggest() {
		return myPopups.makeSuggestion();
	}
	
	/**
	 * Sends the end of turn back to the board
	 */
	public void endTurn() {
		game.endTurn();
	}
	
	/**
	 * Show the popup to say someone has won the game
	 * @param winDefault
	 */
	public void winner(boolean winDefault) {
		myPopups.winGame(winDefault);
	}
	
	public void loser() {
		myPopups.loseGame();
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Can't move without moves available
		if(CluedoGame.currentPlayer.remainingMoves() <= 0) {
			return;
		}
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){
			CluedoGame.currentPlayer.move(Player.Direction.RIGHT);
			
		}
		else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
			CluedoGame.currentPlayer.move(Player.Direction.LEFT);
			
		}
		else if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
			CluedoGame.currentPlayer.move(Player.Direction.UP);
			
		}
		else if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
			CluedoGame.currentPlayer.move(Player.Direction.DOWN);
			
		}
		boardPanel.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

