package cluedo;

public class Suggestion {

	private String weapon;
	private String character;
	private String room;
	private String[] cards = new String[3];

	public Suggestion(String room, String weapon, String character) {
		this.room = room;
		this.weapon = weapon;
		this.character = character;
		cards[0] = room;
		cards[1] = weapon;
		cards[2] = character;
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
	
	//Returns the names of the cards in the suggestion
	public String getWeapon() {
		return weapon;
	}
	public String getRoom() {
		return room;
	}
	public String getCharacter() {
		return character;
	}
	
	public String toString() {
		return String.format("R: %s W: %s C: %s", room.toString(), weapon.toString(), character.toString() );
	}
}