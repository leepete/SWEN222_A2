package cluedo;

public class Room extends Card {
	private String name;
	
	public Room(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
