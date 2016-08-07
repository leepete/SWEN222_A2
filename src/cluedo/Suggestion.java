package cluedo;

public class Suggestion {

	String weapon;
	String character;
	String room;

	public Suggestion(String room, String weapon, String character) {
		this.weapon = weapon;
		this.room = room;
		this.character = character;
	}

	public boolean isValid() {
		return (this.room != null && this.character != null && this.weapon != null);
	}

	public boolean equals(Suggestion s) {
		if(s == null){
			return false;
		}	
		return (this.weapon.equals(s.weapon) && this.character.equals(s.character) && this.room.equals(s.room));
			
	}

	public String toString() {
		return String.format("W: %s C: %s R: %s", weapon.toString(), character.toString(), room.toString() );
	}
}
