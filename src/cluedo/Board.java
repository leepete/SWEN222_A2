package cluedo;

public class Board {
		
	public static final int BOARD_WIDTH = 26;
	public static final int BOARD_HEIGHT = 27;
	
	private char corridor = '+';
	private char boundary = '#';
	private char wall = '=';
	private char placemat = 'x';
	private char[] doors = {'^', '<', '>', 'v'};
	private char[] players = {'1', '2', '3', '4', '5', '6'};
	
	
	
	private final char[][] board = {   
		   "##########################".toCharArray(),
		   "##########+####+##########".toCharArray(),
		   "#=====Y#+++====+++#======#".toCharArray(),
		   "#=    =++===  ===++=CONS=#".toCharArray(),
		   "#=KITC=++=      =++=    =#".toCharArray(),
		   "#=    =++= BALL =++v=   =#".toCharArray(),
		   "#==   =+x<a    d>x+x===Z##".toCharArray(),
		   "##===v=++=b    c=++++++++#".toCharArray(),
		   "#++++x+++=v====v=+++++++##".toCharArray(),
		   "##++++++++x++++x+++======#".toCharArray(),
		   "#=====+++++++++++++<a   =#".toCharArray(),
		   "#=   ====++=====+++=BILL=#".toCharArray(),
		   "#= DINE =++= C =+++=   b=#".toCharArray(),
		   "#=     a>x+= E =+++====v=#".toCharArray(),
		   "#=      =++= L =+++++x+x##".toCharArray(),
		   "#=    b =++= L =+++==^==##".toCharArray(),
		   "#=====v==++= R =++== a ==#".toCharArray(),
		   "##++++x++++=====+x<b LIB=#".toCharArray(),
		   "#+++++++++++xx++++==   ==#".toCharArray(),
		   "##+++++x++==^^==+++=====##".toCharArray(),
		   "#Z=====^++= ab =+++++++++#".toCharArray(),
		   "#=     =++=   c>x+x+++++##".toCharArray(),
		   "#=LOUNG=++=HALL=++^=====Y#".toCharArray(),
		   "#=     =++=    =++= STY =#".toCharArray(),
		   "#=    ==++=    =++==    =#".toCharArray(),
		   "#======#+#======#+#======#".toCharArray(),
		   "##########################".toCharArray()
		   };
	
	public char[][] activeBoard = new char[BOARD_HEIGHT][BOARD_WIDTH];
	
	public Board(){
		resetBoard();
		printBoard();	
	}
	
	public void printBoard() {
		for(int x = 0; x < BOARD_HEIGHT; x++) {
			for(int y = 0; y < BOARD_WIDTH; y++) {
				System.out.print(activeBoard[x][y]);
			}
			System.out.println();
		}
	}
	
	public void movePlayer(Position oldP, Position newP, char c) {
		int oldX = oldP.x;
		int oldY = oldP.y;
		int newX = newP.x;
		int newY = newP.y;
		
		activeBoard[newY][newX] = c; //draw the player at the new position
		activeBoard[oldY][oldX] = board[oldY][oldX]; //put the tile back to what it was without the player
		printBoard();
	}
	
	/**
	 * Returns true if the move is valid, ie the new position is clear of debris
	 * @param oldPos
	 * @param newPos
	 * @return
	 */
	public boolean validMove(Position currPos, Position newP) {
		int curX = currPos.x;
		int curY = currPos.y;
		int newX = newP.x;
		int newY = newP.y;
		//May move freely onto a CORRIDOR tile 
		if(activeBoard[newX][newY] == corridor || activeBoard[newX][newY] == placemat) {
			return true;
		}
		return false;
	}
	
	public void resetBoard() {
		for(int x = 0; x < BOARD_HEIGHT; x++) {
			for(int y = 0; y < BOARD_WIDTH; y++) {
				activeBoard[x][y] = board[x][y];
			}
			
		}
	}
	
	
}
