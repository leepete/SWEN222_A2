package cluedo;

public class Suggestion {

	String weapon;
	String character;
	String room;

	//boolean isAccuse = false;

	public Suggestion(String weapon, String character, String room) {
		this.weapon = weapon;
		this.room = room;
		this.character = character;
	}


	public boolean isValid()
	{
		return (this.room != null && this.character != null && this.weapon != null);
	}

	public boolean equals(Suggestion s){
		if(s == null) return false;
				
		return (this.weapon.equals(s.weapon) && this.character.equals(s.character) && this.room.equals(s.room));
			
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((character == null) ? 0 : character.hashCode());
//		result = prime * result + ((room == null) ? 0 : room.hashCode());
//		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
//		return result;
//	}


//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Suggestion other = (Suggestion) obj;
//		if (character == null) {
//			if (other.character != null)
//				return false;
//		} else if (!character.equals(other.character))
//			return false;
//		if (room == null) {
//			if (other.room != null)
//				return false;
//		} else if (!room.equals(other.room))
//			return false;
//		if (weapon == null) {
//			if (other.weapon != null)
//				return false;
//		} else if (!weapon.equals(other.weapon))
//			return false;
//		return true;
//	}


	public String toString() {
		return String.format("W: %s C: %s R: %s", weapon.toString(), character.toString(), room.toString() );
	}
}
