package cluedo;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class CluedoGame {

	
	//Array of all the possible inputs from the player during their turn for easy error checking
	private List<String> turnOptions = new ArrayList<String>();
	private int minPlayers = 1; //DEBUGGING +++++++++++++++++++++++++++++++++++++++++++
	private int maxPlayers = 6;
	//List of the players in the game
	public List<Player> players = new ArrayList<Player>();
	//Number of players in the game
	public int numPlayers;
	//Mapping character to their name
	public Map<String, Character> availableChars = new HashMap<String, Character>();
	
	//Sets to emulate the deck of cards
	public Set<Card> cardSet = new HashSet<Card>();
	public Set<Weapon> weaponSet = new HashSet<Weapon>();
	public Set<Room> roomSet = new HashSet<Room>();
	public Set<Character> characterSet = new HashSet<Character>();
	
	//The answer to ~~life the universe and everything~~ the game of cluedo
	public Suggestion solution;
	public boolean playing = true;
	public Board b;
	
	//Array holding all the players 
	private final Character[] charactersArray = {
			new Character("MISS SCARLET", new Position(8, 25)),
			new Character("COLONEL MUSTARD", new Position(1, 18)),
			new Character("MRS WHITE", new Position(10, 1)),
			new Character("THE REVEREND GREEN", new Position(15, 1)),
			new Character("MRS PEACOCK", new Position(24, 7)),
			new Character("PROFESSOR PLUM", new Position(24, 20)) };
	
	private final Weapon[] weaponsArray = {
			new Weapon("CANDLESTICK"),
			new Weapon("DAGGER"),
			new Weapon("LEAD PIPE"),
			new Weapon("REVOLVER"),
			new Weapon("ROPE"),
			new Weapon("SPANNER")};
	
	public CluedoGame() {
		this.b = new Board();
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
			String plCharChoice = s.nextLine().toUpperCase();
			System.out.println("DEBUG: plcharchoice: " + plCharChoice);
			if(isValidCharName(plCharChoice)) {
				//Add a player with this character to the array
				players.add(new Player(availableChars.get(plCharChoice), b, i));
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
		
		//Print the board with all the players in their starting positions here
		for(Player p : players) {
			b.activeBoard[p.getPosition().y][p.getPosition().x] = p.toChar();
		}
		b.printBoard();
		
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
			turnOptions.clear();
			Player player = players.get(iP); //Sets the current player so we only have to .get once
			 //If the current player is not playing then move to the next player
			if(!player.isPlaying()) {
				iP = (iP%numPlayers)+1;
				continue;
			}
			//Add the options to the turnOptions set that are available to the player..
			//Check if the player is in a room
			Room playerRoom = player.inRoom();
			if(playerRoom != null) {
				if(playerRoom.getStairRoom() != null) {
					//Make using the stairs an option
					turnOptions.add("STAIRS");
				}
				//Make exiting the room and option
				turnOptions.add("EXIT"); //NOTE will ask the player what door they wish to exit out of if there is more than one door
			}
			else { //Moving is only an option if the player is not in a room
				turnOptions.add("MOVE");
			}
			//Make making an accusation an option
			turnOptions.add("ACCUSE");
			
			//Loop until the player gives a valid input
			while(!validInput) {
				
				System.out.println(String.format("DEBUG: PC: %s player# %d", player.toString(), iP));
				
				System.out.println(String.format("Player %d, it is your turn! What would you like to do?", iP+1));
				for(int i = 0; i < turnOptions.size()-1; i++) {
					System.out.print(turnOptions.get(i) + ", ");
				}
				//Print the last option separately so it doesnt have a comma after it and prints the newline
				System.out.println(turnOptions.get(turnOptions.size()-1));
				
				String input = s.next().toUpperCase(); //Get the input from the user and make it uppercase
				s.nextLine(); //Consume the end of the line
				System.out.println(String.format("DEBUG: \'%s\' provided", input));
				//If the input was valid, find what the input was
				if(turnOptions.contains(input)) {
					switch(input) {
					case "MOVE":
						player.move(s);
						validInput = true;
						break;
					case "STAIRS":
						player.useStairs();
						validInput = true;
						break;
					case "EXIT":
						player.exitRoom();
						validInput = true;
						break;
					case "ACCUSE":
						player.accuse();
						validInput = true;
						break;
					}
				}
				else { //Else ask again
					System.out.println(String.format("\'%s\' is an invalid input, please use an option provided.", input));
				}
			}
			validInput = false;
			
			iP = (iP%numPlayers)+1; //Increment the current player, always keeping it within the bounds of the array
		}
		System.out.println("DEBUG: No longer playing, doing end of game roundup");
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
		weaponSet.remove(w);
		roomSet.remove(r);
		characterSet.remove(c);
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
		System.out.println("Welcome to cluedo, please enter the number of people who will be playing (3-6):");
		// === Check here that the input from the user is actually an integer ===
		numPlayers = s.nextInt();
		s.nextLine();// consume the end of the line
		while(numPlayers > maxPlayers || numPlayers < minPlayers) {
			System.out.println("That was an invalid number of players, ensure the number of players is between 3 and 6");
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
		b.resetBoard();
		numPlayers = 0;
		
		resetDeck();
	}
	
	/**
	 * Resets the sets for weapons, rooms and characters
	 */
	private void resetDeck() {
		//Reset the sets for weapons, rooms and characters
		weaponSet = new HashSet<Weapon>();
		for(Weapon w : weaponsArray) {
			weaponSet.add(w);
		}
		
		characterSet = new HashSet<Character>();
		for(Character c : charactersArray) {
			characterSet.add(c);
		}
			
		roomSet = new HashSet<Room>();
		//Make the rooms that have stairs
		Room kitchen = new Room("KITCHEN");
		Room conservatory = new Room("CONSERVATORY");
		Room study = new Room("STUDY");
		Room lounge = new Room("LOUNGE");
		
		//Set their stairs
		kitchen.setStairRoom(study);
		study.setStairRoom(kitchen);
		conservatory.setStairRoom(lounge);
		lounge.setStairRoom(conservatory);
		
		//Add the rooms to the set containing all the rooms
		roomSet.add(kitchen);
		roomSet.add(conservatory);
		roomSet.add(study);
		roomSet.add(lounge);
		
		roomSet.add(new Room("BALL ROOM"));
		roomSet.add(new Room("BILLIARD ROOM"));
		roomSet.add(new Room("LIBRARY"));
		roomSet.add(new Room("HALL"));
		roomSet.add(new Room("DINING ROOM"));
	}
	
	/**
	 * Resets the availableCharacters HashSet for a new game
	 */
	private void resetCharacters() {
		//Reset the Map
		availableChars = new HashMap<String, Character>();
		//Populate the map with all the options
		availableChars.put("MISS SCARLET", charactersArray[0]);
		availableChars.put("COLONEL MUSTARD", charactersArray[1]);
		availableChars.put("MRS WHITE", charactersArray[2]);
		availableChars.put("THE REVEREND GREEN", charactersArray[3]);
		availableChars.put("MRS PEACOCK", charactersArray[4]);
		availableChars.put("PROFESSOR PLUM", charactersArray[5]);
	}
	
	
	public static void main(String[] args) {
		CluedoGame game = new CluedoGame();
	}

}
