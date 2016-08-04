package cluedo;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CluedoGame {

	//List of the players in the game
	List<Player> players = new ArrayList<Player>();
	//Number of players in the game
	int numPlayers;
	//Mapping character to their name
	Map<String, Character> availableChars = new HashMap<String, Character>();
	
	public CluedoGame() {
		Board b = new Board();
		Scanner s = new Scanner(System.in);
		startGame(s);
	}
	
	/**
	 * Start of the game method,
	 * finds the number of players
	 * who's playing what
	 * selects the solution
	 * deals the hands
	 * resets the board
	 */
	public void startGame(Scanner s) {
		resetGame();
		
		//Get number of players
		
		
		
		int i = 0;
		while(i < numPlayers) {
		
			System.out.println(String.format("Player %d, please enter the character you wish to play as:", i));
			//Switch find the player they entered and assign that from the set to their player object
			for(Character c : availableChars.values()) {
				System.out.println(c.toString());
			} 
			String plCharChoice = s.nextLine();
			if(isValidCharName(plCharChoice)) {
				switch(plCharChoice) {
				case "Miss Scarlet": 
				};
			} else {
				System.out.println("That was an invalid player, please enter again:");
			}
		}
	}
	
	private boolean isValidCharName(String name) {
		return availableChars.keySet().contains(name);
	}
	
	/**
	 * Gets the number of players in the game from the user and ensures that it is between 2 and 6
	 * @param s
	 * @return number of players in this game
	 */
	private int getNumPlayers(Scanner s) {
		System.out.println("Welcome to cluedo, please enter the number of people who will be playing (2-6):");
		// === Check here that the input from the user is actually an integer ===
		int numPlayers = s.nextInt();
		while(numPlayers > 6 || numPlayers < 2) {
			System.out.println("That was an invalid number of players, ensure the number of players is between 2 and 6");
			numPlayers = s.nextInt();
		}
		System.out.println(String.format("Awesome, there will be %d players this game.", numPlayers));
		return numPlayers;
	}
	/**
	 * Resets all the fields for a new game
	 */
	private void resetGame() {
		resetCharacters();
		numPlayers = 0;
	}
	
	/**
	 * Resets the availableCharacters HashSet for a new game
	 */
	private void resetCharacters() {
		//Reset the Map
		availableChars = new HashMap<String, Character>();
		//Populate the map with all the options
		availableChars.put("Miss Scarlet", new Character("Miss Scarlet"));
		availableChars.put("Colonel Mustard", new Character("Colonel Mustard"));
		availableChars.put("Mrs. White", new Character("Mrs. White"));
		availableChars.put("The Reverend Green", new Character("The Reverend Green"));
		availableChars.put("Mrs. Peacock", new Character("Mrs. Peacock"));
		availableChars.put("Professor Plum", new Character("Professor Plum"));
	}
	
	
	public static void main(String[] args) {
		CluedoGame game = new CluedoGame();
	}

}
