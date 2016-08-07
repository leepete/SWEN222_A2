package cluedo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room extends Card {
	
	public Position[] placemats;
	public Map<String, Position> placeMap;
	
	private String name;
	private Room stairs;
	private Position[] spaces;
	
	public Room(String name, Position[] placemats) {
		this.name = name;
		this.placemats = placemats;
	}
	
	public Room(String name) {
		this.name = name;
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
	 * Sets the waiting spaces for players while they are in the room
	 * @param spaces
	 */
	public void setSpaces(Position[] spaces) {
		this.spaces = spaces;
	}
	
	public Position[] getSpaces() {
		return spaces;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}