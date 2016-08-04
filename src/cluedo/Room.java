package cluedo;
import java.util.List;
//import java.util.ArrayList;
import java.util.Set;
//import java.util.HashSet;

public class Room {
	
	private String name;
	private List<Point> vertices;
	private boolean hasPassage;
	private Room passageWay;
	private Set<Point> doors;
	
	public Room(String name, boolean hasPassage, Room passageWay, Set<Point> doors, List<Point> vertices) {
		this.name = name;
		this.hasPassage = hasPassage;
		this.passageWay = passageWay;
		this.vertices = vertices;
		this.doors = doors;
	}
	
	public Set<Point> getDoors() {
		return doors;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return "";
	}
}
