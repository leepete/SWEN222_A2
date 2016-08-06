package cluedo;

import java.util.List;
import java.util.Map;

public class Room extends Card {
	
	public Position[] placemats;
	private String name;
	private Room stairs;
	
	public Room(String name, Position[] placemats) {
		this.name = name;
		this.placemats = placemats;
	}
	
	/**
	 * Returns a map of the positions of the doors in the room to a letter option
	 * @return
	 */
	public void getDoors() {
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
