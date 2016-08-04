package cluedo;

import java.util.Random;

public class Player {
	
	private Character token;
	private boolean playing = true;
	public Point position;
	
	public Player(Character token) {
		this.token = token;
	}
	
	/**
	 * Moves the player based on their dice roll to a position on the board and returns this
	 * @return
	 */
	public Point move() {
		Point p = new Point(1,2);
		return p;
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
	
	public String toString() {
		return token.toString();
	}
	
	//Some sort of move 
}
