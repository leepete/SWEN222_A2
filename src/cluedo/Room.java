package cluedo;
import java.util.List;
//import java.util.ArrayList;
import java.util.Set;
//import java.util.HashSet;

public class Room {
	
	private String name;
	private List<Position> vertices;
	private boolean hasPassage;
	private Room passageWay;
	private Set<Position> doors;
	
	public Room(String name, boolean hasPassage, Room passageWay, Set<Position> doors, List<Position> vertices) {
		this.name = name;
		this.hasPassage = hasPassage;
		this.passageWay = passageWay;
		this.vertices = vertices;
		this.doors = doors;
	}
	
	public Set<Position> getDoors() {
		return doors;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return "";
	}
}
