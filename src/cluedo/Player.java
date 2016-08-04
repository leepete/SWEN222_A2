package cluedo;

import java.util.Random;
import java.util.Scanner;


public class Player {
	
	private Character player;
	private boolean playing = true;
	public Position position;
	
	public Player(Character token) {
		this.player= token;
	}
	
	/**
	 * Moves the player based on their dice roll to a position on the board and returns this
	 * @return
	 */
	public void move(Scanner s) {
		String key = s.nextLine().toUpperCase(); 
		while(true){ //fix loop later
		switch(key){
			case "w":
				goUp();
				break;
			case "a":
				goLeft();
				break;
			case "s":
				goDown();
				break;
			case "d":
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
		return player.toString();
	}
	
	//Some sort of move 
}
