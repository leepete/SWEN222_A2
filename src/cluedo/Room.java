package cluedo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room extends Card {
	
	public Position[] placemats;
	public Map<String, Position> placeMap;
	
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
	 * Generates a map of labels to positions of the placemats for the doors in this room,
	 * used to decide which door to exit from.
	 */
	public void generateDoorLabels() {
		placeMap = new HashMap<String, Position>();
		for(int i = 0; i < placemats.length; i++) {
			placeMap.put(String.valueOf((char)('A'+i)), placemats[i]);
		}
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
