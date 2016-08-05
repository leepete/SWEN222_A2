package cluedo;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;


public class Player {
	
	private Character character;
	private boolean playing = true;
	public Position position;
	private Room room = null;
	
	private Set<Card> hand = new HashSet<Card>();
	
	public Player(Character character) {
		this.character = character;
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
		String key = s.next().toUpperCase(); //Take the first string from the user
		// check that the key is actually a character
		s.nextLine();//End the line
		while(true){ //fix loop later
			switch(key){
				case "W":
					goUp();
					break;
				case "A":
					goLeft();
					break;
				case "S":
					goDown();
					break;
				case "D":
					goRight();
					break;
			default: {
				System.out.println("Incorrect move, please try again");
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
		return rand.nextInt(6) + 1;
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
	
	private void goUp(){
		position.setRow(position.getRow()-1);		
	}
	
	private void goLeft(){
		position.setColumn(position.getColumn()-1);
	}
	
	private void goRight(){
		position.setColumn(position.getColumn()+1);
	}
	
	private void goDown(){
		position.setRow(position.getRow()+1);
	}

	
	public String toString() {
		return character.toString();
	}
	
	//Some sort of move 
}
