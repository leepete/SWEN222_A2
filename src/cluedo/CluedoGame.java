package cluedo;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import gui.GuiFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class CluedoGame {

	
	//Array of all the possible inputs from the player during their turn for easy error checking
	private List<String> turnOptions = new ArrayList<String>();
	private final int minPlayers = 3;
	private final int maxPlayers = 6;
	private int iP = 0;

	private static final int numRooms = 9;
	
	//List of the players in the game
	public List<Player> players = new ArrayList<Player>();
	//Number of players in the game
	public int numPlayers = 0;
	//Mapping character to their name
	public Map<String, Character> characterNameMap = new HashMap<String, Character>();
	
	//Sets to emulate the deck of cards
	public Set<Card> cardSet = new HashSet<Card>();
	public Set<Weapon> weaponSet = new HashSet<Weapon>();
	public Set<Room> roomSet = new HashSet<Room>();
	public Set<Character> characterSet = new HashSet<Character>();
	
	//The answer to ~~life the universe and everything~~ the game of cluedo
	public static Suggestion solution;
	public Board b;
	
	//Array holding all the players 
	public static final Character[] charactersArray = {
			new Character("MISS SCARLET", new Position(8, 25)),
			new Character("COLONEL MUSTARD", new Position(1, 18)),
			new Character("MRS WHITE", new Position(10, 1)),
			new Character("THE REVEREND GREEN", new Position(15, 1)),
			new Character("MRS PEACOCK", new Position(24, 7)),
			new Character("PROFESSOR PLUM", new Position(24, 20)) };
	
	public static final Weapon[] weaponsArray = {
			new Weapon("CANDLESTICK"),
			new Weapon("DAGGER"),
			new Weapon("LEAD PIPE"),
			new Weapon("REVOLVER"),
			new Weapon("ROPE"),
			new Weapon("SPANNER")};
	
	public static Room[] roomsArray = new Room[numRooms];
	public static Room cellar = new Room("CELLAR", new Position[0]);

	public static Map<Position, Room> placemats = new HashMap<Position, Room>();
	
	private final Position[] kitchenPlacemats = {
			new Position(5,8)};
	private final Position[] ballroomPlacemats = {
			new Position(8, 6),
			new Position(10, 9),
			new Position(15, 9),
			new Position(17, 6)};
	private final Position[] conservatoryPlacemats = {
			new Position(19,6)};
	private final Position[] billiardroomPlacemats = {
			new Position(18, 10),
			new Position(23, 14)};
	private final Position[] diningroomPlacemats = {
			new Position(9, 13),
			new Position(6, 17)};
	private final Position[] libraryPlacemats = {
			new Position(21, 14),
			new Position(17, 17)};
	private final Position[] hallPlacemats = {
			new Position(12, 18),
			new Position(13, 18),
			new Position(16, 21)};
	private final Position[] loungePlacemats = {
			new Position(7, 19)};
	private final Position[] studyPlacemats = {
			new Position(18, 21)};
	
	//Arrays holding the positions of where the player characters will be shown while they are in the rooms
	public final static Position[] kitchenSpaces = {
			new Position(2,3),
			new Position(3,3),
			new Position(4,3),
			new Position(2,5),
			new Position(3,5),
			new Position(4,5)};
	private final Position[] ballSpaces = {
			new Position(10,4),
			new Position(11,4),
			new Position(12,4),
			new Position(13,4),
			new Position(12,6),
			new Position(13,6)};
	private final Position[] conservatorySpaces = {
			new Position(21,4),
			new Position(22,4),
			new Position(23,4),
			new Position(21,5),
			new Position(22,5),
			new Position(23,5)};
	private final Position[] diningSpaces = {
			new Position(3,13),
			new Position(4,13),
			new Position(5,13),
			new Position(3,14),
			new Position(4,14),
			new Position(5,14)};
	private final Position[] billiardSpaces = {
			new Position(21,10),
			new Position(22,10),
			new Position(23,10),
			new Position(20,12),
			new Position(21,12),
			new Position(22,12)};
	private final Position[] librarySpaces = {
			new Position(20,16),
			new Position(22,16),
			new Position(20,17),
			new Position(20,18),
			new Position(21,18),
			new Position(22,18)};
	private final Position[] loungeSpaces = {
			new Position(3,21),
			new Position(4,21),
			new Position(5,21),
			new Position(3,23),
			new Position(4,23),
			new Position(5,23)};
	private final Position[] hallSpaces = {
			new Position(12,23),
			new Position(13,23),
			new Position(14,23),
			new Position(12,24),
			new Position(13,24),
			new Position(14,24)};
	private final Position[] studySpaces = {
			new Position(19,23),
			new Position(23,23),
			new Position(20,24),
			new Position(21,24),
			new Position(22,24),
			new Position(23,24)};
	private final Position[] cellarSpaces = {
			new Position(12,13),
			new Position(12,14),
			new Position(12,15),
			new Position(14,13),
			new Position(14,14),
			new Position(14,15)};
	
	public static GuiFrame guiFrame;
	
	//The player whose turn it currently is
	public static Player currentPlayer;
	
	public CluedoGame() {
		populateRooms();
		this.b = new Board();
		startGame();
	}
	
	/**
	 * Initialises the rooms so that the stairs and placemats are linked to the correct rooms
	 */
	private void populateRooms() {
		Room kitchen = new Room("KITCHEN", kitchenPlacemats);
		Room conservatory = new Room("CONSERVATORY", conservatoryPlacemats);
		Room study = new Room("STUDY", studyPlacemats);
		Room lounge = new Room("LOUNGE", loungePlacemats);
		Room ballroom = new Room("BALL ROOM", ballroomPlacemats);
		Room billiardroom = new Room("BILLIARD ROOM", billiardroomPlacemats);
		Room library = new Room("LIBRARY", libraryPlacemats);
		Room hall = new Room("HALL", hallPlacemats);
		Room diningroom = new Room("DINING ROOM", diningroomPlacemats);
		
		//Set their stairs
		kitchen.setStairRoom(study);
		study.setStairRoom(kitchen);
		conservatory.setStairRoom(lounge);
		lounge.setStairRoom(conservatory);
		

		kitchen.setSpaces(kitchenSpaces);
		study.setSpaces(studySpaces);
		conservatory.setSpaces(conservatorySpaces);
		lounge.setSpaces(loungeSpaces);
		ballroom.setSpaces(ballSpaces);
		billiardroom.setSpaces(billiardSpaces);
		library.setSpaces(librarySpaces);
		hall.setSpaces(hallSpaces);
		diningroom.setSpaces(diningSpaces);
		
		roomsArray[0] = kitchen;
		roomsArray[1] = conservatory;
		roomsArray[2] = study;
		roomsArray[3] = lounge;
		roomsArray[4] = ballroom;
		roomsArray[5] = billiardroom;
		roomsArray[6] = library;
		roomsArray[7] = hall;
		roomsArray[8] = diningroom;
		for(int i = 0; i < roomsArray.length; i++) {
			mapPlacemats(roomsArray[i]);
			roomsArray[i].generateDoorLabels();
		}
		
		
		//Cellar is a special room where the dead people go
		
		cellar.setSpaces(cellarSpaces);
	}
	
	/**
	 * Maps the position of the placemats to the room
	 * @param r
	 */
	private void mapPlacemats(Room r) {
		for(Position p : r.placemats) {
			placemats.put(p, r);
		}
	}
	
	/**
	 * Start of the game method,
	 * finds the number of players
	 * who's playing what
	 * selects the solution
	 * deals the hands
	 * resets the board
	 */
	public void startGame() {
		resetGame();

		//Find out how many players there are
		numPlayers = guiFrame.getNumPlayers();
		
		//Assign each player a name and character
		guiFrame.assignPlayerCharacters(numPlayers);
		
		System.out.println("All players successfully allocated");
		//Make the solution
		makeSolution();
		//Deal the hands to the players
		shuffleNDeal();
		
		//Print the board with all the players in their starting positions here
		for(Player p : players) {
			b.activeBoard[p.getPosition().y][p.getPosition().x] = p.toChar();
		}
		
		iP = 0;
		for(Player play : players) {
			System.out.println(play.getID() + " " + play.getName());
		}
		currentPlayer = players.get(iP);
		turnOptions.add("ACCUSE");
		currentPlayer.sameRoom = currentPlayer.inRoom();
		startTurn();
	}
	
	/**
	 * Creates a player and adds it to the players in the game
	 * called from the GUI classes
	 */
	public void createPlayer(String name, String playingAs, int playerID) {
		Character c = characterNameMap.get(playingAs);
		
		System.out.println("Playing as: " + c.toString());
		Player p = new Player(name, characterNameMap.get(playingAs), b, this, playerID);
		players.add(p);
	}
	
	
	public void startTurn() {
		System.out.println(currentPlayer.getName());
		findOptions();
		b.printBoard();
	}
	
	public void endTurn() {
		turnOptions.clear();
		iP = (iP+1)%numPlayers;
		currentPlayer = players.get(iP);
		turnOptions.add("ACCUSE");
		//Sameroom is the room you start the turn in, for ensuring you cant  
		currentPlayer.sameRoom = currentPlayer.inRoom();
		startTurn();
	}
	
	/**
	 * Finds out what options are available to the player
	 */
	private void findOptions() {
		if(!currentPlayer.isPlaying()) {
			turnOptions.clear();
			turnOptions.add("END TURN");
			return;
		}
		
		Room playerRoom = currentPlayer.inRoom();
		//If player is in a room
		if(playerRoom != null) {
			//And there are stairs
			if(playerRoom.getStairRoom() != null) {
				//Make using the stairs an option
				turnOptions.add("STAIRS");
			}
			//Make exiting the room and option
			turnOptions.add("EXIT ROOM"); //NOTE will ask the player what door they wish to exit out of if there is more than one door
		}
		else { //Moving is only an option if the player is not in a room
			turnOptions.add("ROLL");

		}
		guiFrame.updateOptions(turnOptions);
	}
	
	public void enterRoom() {
		if(currentPlayer.inRoom() != currentPlayer.sameRoom) {
			//Now in a valid suggestion room
			turnOptions.add("SUGGESTION");
		} else {
			turnOptions.clear();
			turnOptions.add("END TURN");
		}
		guiFrame.updateOptions(turnOptions);
	}

	
	/**
	 * Returns true if the suggestion presented was refuted by a player
	 * Iterates through all the players even if they are out of the game,
	 * if a player has a card for one of the pieces from the suggestion they
	 * must show one (their choice) to the player guessing, thus refuting the guess.
	 * @param suggest
	 * @return
	 */
	public String[] refuteGuess(Suggestion suggest, Player guesser) {
		boolean valid = false;
		String input, weapon, room, character;
		String[] refuter = new String[2];
		List<String> options = new ArrayList<String>(); //Array holding strings of cards that the player had in their hand
		int i = 0;
		//For each player in the game (playing or not)
		for(Player p : players) {
			//Find if they have a card from the suggestion
			room = suggest.getRoom();
			weapon = suggest.getWeapon();
			character = suggest.getCharacter();
			if(p.getHand().contains(room)) {
				
				options.add(room);
			}
			if(p.getHand().contains(weapon)) {
				options.add(weapon);
			}
			if(p.getHand().contains(character)) {
				options.add(character);
			}
			//If options is not empty ask which card they want to refute
			if(!options.isEmpty()) {
				while(!valid ) {
					System.out.println(String.format("Player %d, which card would you like to refute player %d's guess with?", p.getID(), guesser.getID()));
					//Print all options
					for(String o : options) {
						System.out.print(String.format("\'%s\' ", o));
					}
					input = "";
					//If options contains input its valid
					if(options.contains(input)) {
						valid = true;
						refuter[0] = Integer.toString(p.getID());
						refuter[1] = input;
						return refuter;
					}
				}
			}
			
		}
		return refuter;
	}
	
	/**
	 * Asks the player what they would like to do on their turn
	 * @param s
	 */
	/*public void turnInput(Scanner s, Player p) {
		turnOptions.clear();
		
		
		
			
		
		//Print hand
		for(String str : p.getHand()) {
			System.out.print(str + ", ");
		}System.out.println();
		
		if(playerRoom != null)System.out.print(String.format("You are currently in the %s ", playerRoom.toString()));
		System.out.println("What would you like to do?");
		
		//Print options
		for(int i = 0; i < turnOptions.size()-1; i++) {
			System.out.print(turnOptions.get(i) + ", ");
		}
		//Print the last option separately so it doesnt have a comma after it and prints the newline
		System.out.println(turnOptions.get(turnOptions.size()-1));
		

		
		String input = s.next().toUpperCase(); //Get the input from the user and make it uppercase
		s.nextLine(); //Consume the end of the line
		//If the input was valid, find what the input was
		if(turnOptions.contains(input)) {
			switch(input) {
			case "MOVE":
				p.move();
				validInput = true;
				break;
			case "STAIRS":
				p.useStairs();
				validInput = true;
				break;
			case "EXIT":
				p.exitRoom(s);
				validInput = true;
				break;
			case "ACCUSE":
				//p.accuse(s);
				validInput = true;
				break;
			}
			}
			else { //Else ask again
				System.out.println(String.format("\'%s\' is an invalid input, please use an option provided.", input));
			}
		}
	}*/
	
	
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
			players.get(i % players.size()).addCard(cardSetItr.next().toString());
			i++;
		}
	}
	
	/**
	 * Draws a Card from the weapon, character and room sets to make up the solution to the game
	 */
	private void makeSolution() {
		Random rand = new Random();
		
		//Randomly pulls a card of each type to make the solution
		String w = weaponSet.toArray()[rand.nextInt(weaponSet.size())].toString();
		String r = roomSet.toArray()[rand.nextInt(roomSet.size())].toString();
		String c = characterSet.toArray()[rand.nextInt(characterSet.size())].toString();

		weaponSet.remove(new Weapon(w));
		roomSet.remove(new Room(r));	
		characterSet.remove(new Character(c));
		
		solution = new Suggestion(r,w,c);
	}
	
	/**
	 * Takes the player being updated, their playing field will be true if they won or false if not.
	 * @param p
	 */
	public void gameOver(Player player) {
		int i = 0;
		//If the player is playing they won
		if(player.isPlaying()) {
			System.out.println(String.format("CONGRATULATIONS! Player %d as %s has won the game!", player.getID(), player.toString()));
			System.out.println("The Solution was: " + solution.toString());
			return;
		}
		System.out.println(String.format("Sorry player %d, your accusation was incorrect", player.getID()));
		for(Player p : players){
			if(p.isPlaying()){
				i++;
			}
		}
		if(i <= 1){
			for(Player p : players) {
				if(p.isPlaying()) {
					System.out.println(String.format("CONGRATULATIONS! Player %d as %s, has won the game by default...", p.getID(), p.toString()));
					break;
				}
			}	
		}
	}
	
	/**
	 * Resets all the fields for a new game
	 */
	public void resetGame() {
		
		resetCharacters();
		b.resetBoard();
		numPlayers = 0;
		resetDeck();
		guiFrame = new GuiFrame(this);
		guiFrame.start();
		
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
		for(int i = 0; i < roomsArray.length; i++) {
			roomSet.add(roomsArray[i]);
		}
	}
	
	/**
	 * Resets the availableCharacters HashSet for a new game
	 */
	private void resetCharacters() {
		//Reset the Map
		characterNameMap = new HashMap<String, Character>();
		//Populate the map with all the options
		for(Character c : charactersArray) {
			characterNameMap.put(c.toString(), c);
		}
	}
	
	
	public static void main(String[] args) {
		CluedoGame game = new CluedoGame();
	}

}