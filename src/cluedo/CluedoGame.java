package cluedo;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class CluedoGame {

	
	//Array of all the possible inputs from the player during their turn for easy error checking
	private static final String[] turnOptions = {"MOVE", "STAIRS","ACCUSE", "YES", "NO"};
	
	//List of the players in the game
	List<Player> players = new ArrayList<Player>();
	//Number of players in the game
	int numPlayers;
	//Mapping character to their name
	Map<String, Character> availableChars = new HashMap<String, Character>();
	
	//Sets to emulate the deck of cards
	Set<Card> cardSet = new HashSet<Card>();
	Set<Weapon> weaponSet = new HashSet<Weapon>();
	Set<Room> roomSet = new HashSet<Room>();
	Set<Character> characterSet = new HashSet<Character>();
	
	//The answer to ~~life the universe and everything~~ the game of cluedo
	Suggestion solution;
	boolean playing = true;
	
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
		getNumPlayers(s);
		int i = 1;
		while(i <= numPlayers) {
		
			System.out.println(String.format("Player %d, please enter the character you wish to play as:", i));
			for(Character c : availableChars.values()) {
				System.out.println(c.toString());
			}
			String plCharChoice = s.nextLine();
			System.out.println("DEBUG: plcharchoice: " + plCharChoice);
			if(isValidCharName(plCharChoice)) {
				//Add a player with this character to the array
				players.add(new Player(availableChars.get(plCharChoice)));
				//Remove this character from the available characters
				availableChars.remove(plCharChoice);
				//A player has been successfully added
				i++;
			} else {
				System.out.println("That was an invalid player, please enter again");
			}
		}
		
		System.out.println("All players successfully allocated");
		makeSolution();
		System.out.println(String.format("DEBUG: This is the answer to the game: %s", solution.toString()));
		shuffleNDeal();
		turnCycle(s);
	}
	
	/**
	 * Player turn cycle,
	 * While there are still players in the game and someone hasn't won,
	 * 	iterate over the players in the game
	 * 	 print: the player who's turn it is, their hand, and what room they're in (if applic)
	 *   print: Options available to the player; Roll dice/use stairs/make accusation
	 *    delegate to other methods for each of the options above
	 */
	public void turnCycle(Scanner s) {
		boolean validInput = false;
		int iP = 0; //index of the current player from the players array list
		//'playing' is a boolean toggled when someone wins the game or when only 1 player remains
		while(playing) {
			Player player = players.get(iP); //Sets the current player so we only have to .get once
			 //If the current player is not playing then move to the next player
			if(!player.isPlaying()) {
				iP = (iP+1) % players.size();
				continue;
			}
			while(!validInput) {
				System.out.println(String.format("Player %d, it is your turn! What would you like to do?", iP));
				System.out.print(String.format("MOVE, "));
				//If player is in a room, allow them the option of using the stairs
				if(player.inRoom() != null) {
					System.out.print("STAIRS, ");
					//if room player is in has stairs
						//ask if they want to use stairs
				}
				System.out.println("ACCUSE");
				
				String input = s.next().toUpperCase(); //Get the input from the user and make it uppercase
				s.nextLine(); //Consume the end of the line
				System.out.println(String.format("DEBUG: \'%s\' provided", input));
				if(Arrays.asList(turnOptions).contains(input)) {
					validInput = true;
				}
				else {
					System.out.println(String.format("\'%s\' is an invalid input, please use an option provided.", input));
				}
			}
			iP = (iP+1)%players.size(); //Increment the current player, always keeping it within the bounds of the array
		}
	}
	
	/**
	 * Merges the weapon room and character sets into the card set, 
	 * which then iterates over the players array adding a random card
	 * to their hands until there are no more cards (some players will have more cards)
	 */
	private void shuffleNDeal() {
		cardSet.addAll(weaponSet);
		cardSet.addAll(roomSet);
		cardSet.addAll(characterSet);
		
		//While there are cards in the set, keep dealing them to the players
		int i = 0;
		Iterator<Card> cardSetItr = cardSet.iterator();
		while(cardSetItr.hasNext()) {
			players.get(i % players.size()).addCard(cardSetItr.next());
			i++;
		}
		for(Player p : players) {
			System.out.println(p.handToString());
		}
	}
	
	/**
	 * Draws a Card from the weapon, character and room sets to make up the solution to the game
	 */
	private void makeSolution() {
		Weapon w = weaponSet.iterator().next();
		Room r = roomSet.iterator().next();
		Character c = characterSet.iterator().next();
		solution = new Suggestion(w, c , r);
		
	}
	
	
	/**
	 * Returns true if the passed string is a valid name of a character
	 * @param name
	 * @return
	 */
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
		numPlayers = s.nextInt();
		s.nextLine();// consume the end of the line
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
		
		resetDeck();
	}
	
	/**
	 * Resets the sets for weapons, rooms and characters
	 */
	private void resetDeck() {
		//Reset the sets for weapons, rooms and characters
		weaponSet = new HashSet<Weapon>();
		weaponSet.add(new Weapon("Candlestick"));
		weaponSet.add(new Weapon("Dagger"));
		weaponSet.add(new Weapon("Lead Pipe"));
		weaponSet.add(new Weapon("Revolver"));
		weaponSet.add(new Weapon("Rope"));
		weaponSet.add(new Weapon("Spanner"));
		
		characterSet = new HashSet<Character>();
		//Change the start locations for the players when peter fixes the board
		characterSet.add(new Character("Miss Scarlet", new Position(0,0)));
		characterSet.add(new Character("Colonel Mustard", new Position(0, 0)));
		characterSet.add(new Character("Mrs. White", new Position(0,0)));
		characterSet.add(new Character("The Reverend Green", new Position(0,0)));
		characterSet.add(new Character("Mrs. Peacock", new Position(0,0)));
		characterSet.add(new Character("Proffesor Plum", new Position(0,0)));
		
		roomSet = new HashSet<Room>();
		roomSet.add(new Room("Kitchen"));
		roomSet.add(new Room("Ball Room"));
		roomSet.add(new Room("Conservatory"));
		roomSet.add(new Room("Billiard Room"));
		roomSet.add(new Room("Library"));
		roomSet.add(new Room("Study"));
		roomSet.add(new Room("Hall"));
		roomSet.add(new Room("Lounge"));
		roomSet.add(new Room("Dining Room"));
	}
	
	/**
	 * Resets the availableCharacters HashSet for a new game
	 */
	private void resetCharacters() {
		//Reset the Map
		availableChars = new HashMap<String, Character>();
		//Populate the map with all the options
		availableChars.put("Miss Scarlet", new Character("Miss Scarlet", new Position(0, 0)));
		availableChars.put("Colonel Mustard", new Character("Colonel Mustard", new Position(0, 0)));
		availableChars.put("Mrs. White", new Character("Mrs. White", new Position(0, 0)));
		availableChars.put("The Reverend Green", new Character("The Reverend Green", new Position(0,0)));
		availableChars.put("Mrs. Peacock", new Character("Mrs. Peacock", new Position(0,0)));
		availableChars.put("Professor Plum", new Character("Professor Plum", new Position(0,0)));
	}
	
	
	public static void main(String[] args) {
		CluedoGame game = new CluedoGame();
	}

}
