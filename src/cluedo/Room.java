package cluedo;

public class Room extends Card {
	private String name;
	private Room stairs;
	
	public Room(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the room that the stairs from this room will take you to
	 * @param stairs
	 */
	public void setStairRoom(Room stairs) {
		this.stairs = stairs;
	}
	
	/**
	 * Returns the room that using the stairs from this room will take you to
	 * @return
	 */
	public Room getStairRoom() {
		return stairs;
	}
	
	public String toString() {
		return name;
	}
}
