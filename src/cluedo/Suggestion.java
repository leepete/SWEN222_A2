package cluedo;

public class Suggestion {
	
	Weapon weapon;
	Character character;
	Room room;
	
	public Suggestion(Weapon weapon, Character character, Room room) {
		this.weapon = weapon;
		this.room = room;
		this.character = character;
	}
	
	public String toString() {
		return String.format("W: %s C: %s R: %s", weapon.toString(), character.toString(), room.toString() );
	}
}
