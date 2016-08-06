package cluedo;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Player {
	
	private Character character;
	private boolean playing = true;
	private Room room = null;
	private int id;
	private Position position;
	Board board;
	
	private Set<Card> hand = new HashSet<Card>();
	
	public Player(Character character, Board board, int id) {
		this.character = character;
		this.id = id;
		this.board = board;
		position = this.character.getStart();
	}
	
	/**
	 * Returns the room that the player is in and null if they aren't in one
	 * @return
	 */
	public Room inRoom() {
		return room;
	}
	
	/**
	 * Returns the players position on the board
	 * @return
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Takes an input key from the user and attempts to move in that direction
	 * @return
	 */
	public void move(Scanner s) {
		int remainingSteps = rollDice();
		
		while(remainingSteps > 0){
			System.out.println(String.format("Moves remaining: %d. Input a direction: 'W' 'A' 'S' 'D'", remainingSteps));
			System.out.println(position.toString());
			String key = s.next().toUpperCase(); //Take the first string from the user
			s.nextLine();//End the line
			switch(key){
				case "W":
					if(goUp())
						remainingSteps--;
					break;
				case "A":
					if(goLeft())
						remainingSteps--;
					break;
				case "S":
					if(goDown())
						remainingSteps--;
					break;
				case "D":
					if(goRight())
						remainingSteps--;	
					break;
				default: {
					System.out.println("Invalid character, please try again");
				}
			}
		}
	}
	
	/**
	 * Returns a random(ish) number between 1 and 6 to represent the players dice roll
	 * @return
	 */
	private int rollDice() {
		Random rand = new Random();
		int roll = rand.nextInt(6) + 1;
		System.out.println(String.format("DEBUG: dice rolled and was: %d", roll));
		return roll;
	}
	
	/**
	 * Moves the player by using the stairs, this should end the players turn
	 */
	public void useStairs() {
		Room oldRoom = room;
		room = room.getStairRoom();
		
		System.out.println(String.format("DEBUG: player used stairs from %s and is now in %s", oldRoom, room));
	}
	
	/**
	 * Checks which placemat the player is on 
	 */
	public void enterRoom() {
		
	}

	/**
	 * Gets the door the player wishes to exit the room from and then calls move
	 */
	public void exitRoom() {
		System.out.println("DEBUG: exit room called");
	}
	
	/**
	 * Make a game ending accusation
	 */
	public void accuse() {
		System.out.println("DEBUG: accuse called");
		System.out.println("Something about asking for a room/weapon/character");
	}
	
	
	
	/**
	 * Sets the players hand
	 * @param hand
	 */
	public void addCard(Card card) {
		hand.add(card);
	}
	
	/**
	 * Returns the players hand as a string
	 * @return
	 */
	public String handToString() {
		String s = "";
		for(Card c : hand) {
			s += c.toString() + ", ";
		}
		return s;
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
		Position newP = new Position(position.x,position.y-1);
		board.movePlayer(position, newP, toChar());
		position = newP;
		return true;
	}
	/**
	 * Attempts to move the player left 1 space
	 * Returns true if was successful
	 * @return
	 */
	private boolean goLeft(){
		//Note - Move validity is determined from the board class
		Position newP = new Position(position.x-1, position.y);
		board.movePlayer(position, newP, toChar());
		position = newP;
		return true;
	}
	/**
	 * Attempts to move the player right 1 space
	 * Returns true if was successful
	 * @return
	 */
	private boolean goRight(){
		Position newP = new Position(position.x+1, position.y);
		board.movePlayer(position, newP, toChar());
		position = newP;
		return true;
	}
	/**
	 * Attempts to move the player down 1 space
	 * Returns true if was successful
	 * @return
	 */
	private boolean goDown(){
		Position newP = new Position(position.x, position.y+1);
		board.movePlayer(position, newP, toChar());
		position = newP;
		return true;
	}
	
	public char toChar() {
		return Integer.toString(id).charAt(0);
	}

	
	public String toString() {
		return character.toString();
	}
	
	//Some sort of move 
}
