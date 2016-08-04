package cluedo;

public class Position {
	
	private int row;
	private int col;
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return col;
	}

	public void setRow(int row){
		this.row = row;
	}
	
	public void setColumn(int col){
		this.col = col;
	}
	public boolean equals(Object o){
		if(o instanceof Position){
			Position p = (Position) o;
			return row == p.row && col == p.col;
		}
		return false;
	}
	
	public String toString(){
		return ((char)('a'+(col-1))) + Integer.toString(row);
	}
	
	public int hashCode(){
		return row ^ col;
	}
	
}
