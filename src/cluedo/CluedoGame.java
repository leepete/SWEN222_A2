package cluedo;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import jdk.nashorn.internal.runtime.options.Options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class CluedoGame {

	
	//Array of all the possible inputs from the player during their turn for easy error checking
	private List<String> turnOptions = new ArrayList<String>();
	private final int minPlayers = 3;
	private final int maxPlayers = 6;

	private static final int numRooms = 9;
	
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
	public static Suggestion solution;
	public boolean playing = true;
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
	private final Position[] kitchenSpaces = {
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
	
	
	public CluedoGame() {
		populateRooms();
		this.b = new Board();
		Scanner s = new Scanner(System.in);
		startGame(s);
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
	public void startGame(Scanner s) {
		resetGame();
		System.out.println("Welcome to Cluedo, in this game you must solve the murder by correctly deducing the CHARACTER the WEAPON and the ROOM");
		System.out.println("Your player number will be displayed on the board so don't forget it!");
		System.out.println("Any inputs you give are case insensitive but very strict on spelling!");
		System.out.println("The '+' tiles on the board represent walkable corridoors while the 'x' tiles are placemats which are located outside each door indicated by an arrow");
		System.out.println("To enter a room you must walk into the arrow while standing on its placemat. '#' and '=' and other players cannot be walked over so don't try!");
		System.out.println("Finially, in the corner rooms you will find a letter 'Z' or 'Y', these are secret(ish) stairways leading to the room in the opposite corner");
		//Get number of players
		numPlayers = getNumPlayers(s);
		int i = 1;
		while(i <= numPlayers) {
			Player p;
			System.out.println(String.format("Player %d, please enter the character you wish to play as:", i));
			for(Character c : availableChars.values()) {
				System.out.println(c.toString());
			}
			String plCharChoice = s.nextLine().toUpperCase();
			if(isValidCharName(plCharChoice)) {
				//Add a player with this character to the array
				p = new Player(availableChars.get(plCharChoice), b, this, i);
				players.add(p);
				System.out.println(String.format("Player %d will be playing as: %s", p.getID(), plCharChoice));
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
		shuffleNDeal();
		
		//Print the board with all the players in their starting positions here
		for(Player p : players) {
			b.activeBoard[p.getPosition().y][p.getPosition().x] = p.toChar();
		}
		
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
		
		int iP = 0; //index of the current player from the players array list
		//'playing' is a boolean toggled when someone wins the game or when only 1 player remains
		while(playing) {
			turnOptions.clear();
			Player player = players.get(iP);
			 //If the current player is not playing then move to the next player
			if(!player.isPlaying()) {
				iP = (iP+1)%numPlayers;
				continue;
			}
			//Beginning of turn
			player.sameRoom = player.inRoom(); //For ensuring they dont reenter the same room for another guess on the same turn
			turnInput(s, player);
			//If the player is in a different room to what they started their turn in they can make a guess
			if(player.inRoom() != player.sameRoom && player.isPlaying()) {
				askGuess(s, player);
				
			}
			iP = (iP+1)%numPlayers; //Increment the current player, always keeping it within the bounds of the array
		}
	}
	
	public void askGuess(Scanner s, Player p) {
		boolean validInput = false;
		
		while(!validInput) {
			
			System.out.println(String.format("You are in the %s, would you like to make a suggestion? 'YES' 'NO'", p.inRoom()));
			String input = s.next().toUpperCase();
			s.nextLine();
			switch(input) {
			case "YES":
				p.guess(s);
				validInput = true;
				break;
			case "NO":
				validInput = true;
				break;
			default:
				System.out.println("Invalid input, please try again");
			}
		}
	}
	
	/**
	 * Returns true if the suggestion presented was refuted by a player
	 * Iterates through all the players even if they are out of the game,
	 * if a player has a card for one of the pieces from the suggestion they
	 * must show one (their choice) to the player guessing, thus refuting the guess.
	 * @param suggest
	 * @return
	 */
	public String[] refuteGuess(Scanner s, Suggestion suggest, Player guesser) {
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
					input = s.nextLine().toUpperCase();
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
	public void turnInput(Scanner s, Player p) {
		turnOptions.clear();
		boolean validInput = false;
		Room playerRoom = p.inRoom();
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
			b.printBoard();
			
			//Prompts
			System.out.print(String.format("Player %d, it is your turn! Hand: ",p.getID()));
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
					p.move(s);
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
					p.accuse(s);
					validInput = true;
					break;
				}
			}
			else { //Else ask again
				System.out.println(String.format("\'%s\' is an invalid input, please use an option provided.", input));
			}
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
			playing = false;
			for(Player p : players) {
				if(p.isPlaying()) {
					System.out.println(String.format("CONGRATULATIONS! Player %d as %s, has won the game by default...", p.getID(), p.toString()));
					break;
				}
			}
			
		}

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
		System.out.println("Please enter the number of people who will be playing (3-6):");
		// === Check here that the input from the user is actually an integer ===
		int num = s.nextInt();
		s.nextLine();// consume the end of the line

		while(num > maxPlayers || num < minPlayers) {
			System.out.println("That was an invalid number of players, ensure the number of players is between 3 and 6");
			num = s.nextInt();
		}
		System.out.println(String.format("Awesome, there will be %d players this game.", num));
		return num;
	}
	
	/**
	 * Resets all the fields for a new game
	 */
	private void resetGame() {
		resetCharacters();
		b.resetBoard();
		numPlayers = 0;
		playing = true;
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
		for(int i = 0; i < roomsArray.length; i++) {
			roomSet.add(roomsArray[i]);
		}
	}
	
	/**
	 * Resets the availableCharacters HashSet for a new game
	 */
	private void resetCharacters() {
		//Reset the Map
		availableChars = new HashMap<String, Character>();
		//Populate the map with all the options
		for(Character c : charactersArray) {
			availableChars.put(c.toString(), c);
		}
	}
	
	
	public static void main(String[] args) {
		CluedoGame game = new CluedoGame();
	}

}