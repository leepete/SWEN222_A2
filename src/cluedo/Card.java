package cluedo;

public abstract class Card {
	/**
	 * Returns the name of the card
	 * @return String
	 */
	public String getName() {
		if(this instanceof Room){
			Room room = (Room) this;
			return room.getName();
		}
		else if(this instanceof Weapon){
			Weapon weapon = (Weapon) this;
			return weapon.getName();
		}
		else if(this instanceof Character){
			Character character = (Character) this;
			return character.getName();
		}
		return null;
	}
	public boolean equals(Card card){
		if(card.getName().equals(this.getName())){
			return true;
		}
		return false;
	}
}
