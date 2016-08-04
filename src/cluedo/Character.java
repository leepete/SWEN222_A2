package cluedo;

public class Character extends Card{
	private String name;
	private Position startPos;
	
	public Character(String name, Position startPos) {
		this.name = name;
		this.startPos = startPos;
	}
	
	/**
	 * Returns the starting location for this character
	 * @return
	 */
	public Position getStart() {
		return startPos;
	}
	
	public String toString() {
		return name;
	}
}
