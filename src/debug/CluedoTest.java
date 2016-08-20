package debug;

import cluedo.Board;
import gui.GuiFrame;

public class CluedoTest {

	/**Testing purposes*/
	private static Board myBoard;
	/** Create this*/
	private static GuiFrame myGameBoard;
	
	public static void main(String[] args) {
		//Represents Logic
		myBoard = new Board();
		System.out.println("DEBUG: this is the board");
		
		System.out.println("*************************");
		
		//Represents GUI
		myGameBoard = new GuiFrame();
		myGameBoard.start();
		System.out.println("DEBUG: this is the  GUI");
		
		
	

	}

}
