package cluedo;

public class Character extends Card{
	private String name;
	
	public Character(String name) {
		this.name = name;	
	}
	
	public String toString() {
		return name;
	}
}
