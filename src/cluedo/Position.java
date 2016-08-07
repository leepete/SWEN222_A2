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
<<<<<<< HEAD
		return (row + " " + col);
=======
		return "X: " + col + " Y: " + row;
>>>>>>> 3758f1d6e895fec6fe8437e96776d7062322eedf
	}
	
	public int hashCode(){
		return row ^ col;
	}
	
}
