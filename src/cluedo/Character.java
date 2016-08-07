package cluedo;

public class Character extends Card{
	private String name;
	private Position startPos;
	
	public Character(String name, Position startPos) {
		this.name = name;
		this.startPos = startPos;
	}
	
	public Character(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the starting location for this character
	 * @return
	 */
	public Position getStart() {
		return startPos;
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
		Character other = (Character) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}