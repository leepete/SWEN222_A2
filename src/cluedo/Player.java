package cluedo;

import java.util.Random;
import java.util.Scanner;


public class Player {
	
	private Character player;
	private boolean playing = true;
	public Position position;
	
	public Player(Character player) {
		this.player = player;
	}
	
	/**
	 * Moves the player based on their dice roll to a position on the board
	 */
	public void move(Scanner s) {
		String key = s.nextLine().toUpperCase(); 
		int move = rollDice(); //rolldice temp holder
		while(move != 0){ 
			switch(key){
				case "W":
					goUp();
					move--;
					break;
				case "A":
					goLeft();
					move--;
					break;
				case "S":
					goDown();
					move--;
					break;
				case "D":
					goRight();
					move--;
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
