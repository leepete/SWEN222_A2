package cluedo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Player {

	private Character character;
	private String name;
	private boolean playing = true;
	private Room room = null;
	private int id;
	private int remainingSteps;
	private Position position;
	private Board board;
	public Room sameRoom;

	private CluedoGame game;
	private List<String> hand = new ArrayList<String>();

	public static enum Direction {UP, DOWN, LEFT, RIGHT};
	
	public Player(String name, Character character, Board board, CluedoGame game, int id) {
		this.name = name;
		this.character = character;
		this.id = id;
		this.board = board;
		this.game = game;
		position = this.character.getStart();
	}
	/**
	 * Returns the room that the player is in and null if they aren't in one
	 * @return
	 */
	public Room inRoom() {
		return room;
	}
	
	public int remainingMoves() {
		return remainingSteps;
	}
	
	/**
	 * Returns the players hand
	 * @return
	 */
	public List<String> getHand() {
		return hand;
	}
	
	/**
	 * Gets the id of this player (their turn order)
	 * @return
	 */
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the players position on the board
	 * @return
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Ends the players turn
	 */
	public void endTurn() {
		remainingSteps = 0;	
	}
	
	/**
	 * Sets the position of the player
	 * @param newP
	 */
	public void setPosition(Position newP) {
		if(newP != null) {
			position = newP;			
		}
	}
	
	/**
	 * Takes an input key from the user and attempts to move in that direction
	 * @return
	 */
	public boolean move(Direction dir) {

			switch(dir){
				case UP:
					if(goUp())
						remainingSteps--;
					break;
				case LEFT:
					if(goLeft())
						remainingSteps--;
					break;
				case DOWN:
					if(goDown())
						remainingSteps--;
					break;
				case RIGHT:
					if(goRight())
						remainingSteps--;	
					break;
				default: {
					System.out.println("Invalid move input please try again");
				}
			}
		
		return false;		
	}
	
		
	/**
	 * generates a random(ish) number between 1 and 6 to represent the players dice roll
	 * @return
	 */
	public void rollDice() {
		Random rand = new Random();
		remainingSteps = rand.nextInt(6) + 1;
	}

	/**
	 * Moves the player by using the stairs, this should end the players turn
	 */
	public void useStairs() {
		Room oldRoom = room;
		room = room.getStairRoom();

		
		Position space = room.getSpaces()[id-1];
		board.teleport(this, position, space);
		
		System.out.println(String.format("Used stairs from %s, and is now in %s", oldRoom, room));
		//Make suggestion
	}

	/**
	 * Moves the player into the room
	 * @param room
	 */
	public void enterRoom(Room entRoom) {
		if(entRoom == null) {
			return;
		}
		System.out.println("Entering room: " + entRoom.toString());
		this.room = entRoom;
		endTurn();//Stop moving when we enter a room
		game.enterRoom();
		
	}

	/**
	 * Gets the door the player wishes to exit the room from and then calls move
	 */
	public void exitRoom(Scanner s) {
		Position exitMat = room.placemats[0]; //Initialise to make compiler happy 
		Map<String, Position> pM = room.placeMap;

		//Get all the possible doors from the room and map these to the letter shown on the map (determined from index)
		/*placeMap = new HashMap<String, Position>();
		for(int i = 0; i < room.placemats.length; i++) {
			placeMap.put(String.valueOf(('A'+i)), room.placemats[i]);
		}*/

		List<Position> blockMats = board.getBlockedMats(room);
		//Remove all the blocked placemats from the map of the available mats 
		for(Position p : blockMats) {
			if(pM.containsValue(p)) {
				pM.values().remove(p);
			}
		}

		//if there is no unblocked mats then END TURN
		if(pM.isEmpty()) {
			//END TURN
			System.out.println("Other players are blocking your exits, you will have to spend the turn here");
			return;
		} //else if there is more than one free mat then ASK WHERE TO LEAVE FROM
		else if (pM.size() > 1) {
			exitMat = whichExit(s);
		} //else there is only one free mat so poop the player onto that one
		else {
			//board move me to the placemat please
			exitMat = pM.values().iterator().next();
		}
		System.out.println(String.format("Exiting from %s", room.toString(), exitMat.x, exitMat.y));
		room = null; //No longer in a room

		//Leave the room
		board.teleport(this, position, exitMat);
		position = exitMat;
		
		game.startTurn();
	}
	
	/**
	 * Asks the player which exit they would like to leave the room from, from the available options
	 * and returns the position of the placemat
	 * @param s
	 * @return
	 */
	public Position whichExit(Scanner s) {
		Map<String, Position> pM = room.placeMap;
		Position mat = pM.values().iterator().next(); //Initialise to a random valid option
		boolean valid = false;
		while(!valid) {
			System.out.println("Which door would you like to go out? ");
			for(String label : pM.keySet())
				System.out.print(label + " ");
			String input = s.next().toUpperCase(); //Get the input from the user
			s.nextLine(); //Consume the line
			if(pM.containsKey(input)) {
				mat = pM.get(input);
				valid = true;
			}
			System.out.println("Invalid exit input please try again");
		}
		return mat;
	}

	/**
	 * Takes a String array, containing a room weapon and character.
	 * Converts this into a Suggestion object, and compares this with the solution
	 * @param choice
	 */
	public void accuse(String[] choice) {
		Suggestion suggest = new Suggestion(choice[0],choice[1], choice[2]);
		System.out.println("suggest: " + suggest.toString());
		if(!CluedoGame.solution.equals(suggest)) {
			playing = false;
			room = CluedoGame.cellar;
			Position space = room.getSpaces()[id-1];
			board.teleport(this, position, space);
		}
		game.gameOver(this);
	}
	
	/**
	 * Takes a String array, containing a room weapon and character.
	 * Converts this into a Suggestion object and puts it up for refutal
	 * @param choice
	 */
	public void suggest(String[] choice) {
		Suggestion suggest = new Suggestion(choice[0],choice[1], choice[2]);
		System.out.println("suggest: " + suggest.toString());
		String[] refuter = game.refuteGuess(suggest, this);
		if(refuter[0] == null) {
			System.out.println("No one was able to refute your guess! Could be time to solve the case?");
			return;
		}
		System.out.println(String.format("Player %d refutes your guess by reveiling %s from their hand.", Integer.parseInt(refuter[0]), refuter[1]));
	}

	/**
	 * Sets the players hand
	 * @param hand
	 */
	public void addCard(String card) {
		hand.add(card);
	}

	/**
	 * Returns true if the player is still in the game
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * Attempts to move the player up 1 space
	 * Returns true if was successful
	 * @return
	 */
	private boolean goUp(){
		 return board.movePlayer(position, new Position(position.x,position.y-1), this);
	}
	
	/**
	 * Attempts to move the player left 1 space
	 * Returns true if was successful
	 * @return
	 */
	private boolean goLeft(){
		return board.movePlayer(position, new Position(position.x-1, position.y), this);
	}
	/**
	 * Attempts to move the player right 1 space
	 * Returns true if was successful
	 * @return
	 */
	private boolean goRight(){
		return board.movePlayer(position, new Position(position.x+1, position.y), this);
	}
	
	/**
	 * Attempts to move the player down 1 space
	 * Returns true if was successful
	 * @return
	 */
	private boolean goDown(){
		return board.movePlayer(position, new Position(position.x, position.y+1), this);
	}
	
	/**
	 * Returns the player number of this player as a character
	 * @return
	 */
	public char toChar() {
		return Integer.toString(id).charAt(0);
	}

	
	/**
	 * Returns the character that this player is playing as
	 */
	public String toString() {
		return character.toString();
	}
}