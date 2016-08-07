package cluedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	public Position movePlayer(Position oldP, Position newP, Player p) {
		char c = p.toChar();
		int oldX = oldP.x;
		int oldY = oldP.y;
		int newX = newP.x;
		int newY = newP.y;
		//Check if we are doing a valid move
		if(validCorridorMove(newP)) {
			teleport(p, oldP, newP);
			printBoard();
			return newP;
		} else if(validRoomEntry(oldP, newP)) { //Might be trying to enter a room
			Room r = CluedoGame.placemats.get(oldP);
			if(r != null) {
				p.enterRoom(r);
				activeBoard[oldY][oldX] = board[oldY][oldX]; //remove the players icon from the placemat
				printBoard();
				return newP;
			}
		}
		return null;
	}
	
	/**
	 * Teleports the player to the given position
	 * @param p
	 * @param oldP
	 * @param newP
	 */
	private void teleport(Player p, Position oldP, Position newP) {
		activeBoard[newP.y][newP.x] = p.toChar(); //move the player to the new position
		activeBoard[oldP.y][oldP.x] = board[oldP.y][oldP.x]; //return the old position back to its original state
	}
	
	/**
	 * Returns an array of the unblocked placemats that a player can exit their current room from
	 * @return
	 */
	public List<Position> getBlockedMats(Room r) {
		List<Position> blockedMats = new ArrayList<Position>();
		for(Position p : r.placemats) {
			if(!walkable(p)) {
				blockedMats.add(p);
			}
		}
		return blockedMats;
	}
	
	private boolean arrayContains(char[] array, char c) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == c) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the position on the active board is available for a player to move onto
	 * @param pos
	 * @return
	 */
	private boolean walkable(Position pos) {
		if(activeBoard[pos.y][pos.x] == placemat || activeBoard[pos.y][pos.x] == corridor) {
			return true;
		}
		return false;
	}
	
	public boolean validRoomEntry(Position oldP, Position newP) {
		int curX = oldP.x;
		int curY = oldP.y;
		int newX = newP.x;
		int newY = newP.y;
		if(board[curY][curX] == placemat && arrayContains(doors, activeBoard[newY][newX])) {
			//Else if we're on a placemat and moving into a door
			System.out.println("DEBUG: on a placemat and going into a room!");
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the move is valid, ie the new position is clear of debris
	 * @param oldPos
	 * @param newPos
	 * @return
	 */
	public boolean validCorridorMove(Position newP) {
		int newX = newP.x;
		int newY = newP.y;
		System.out.println(String.format("DEBUG: trying to move to %d, %d: \'%c\'",newX, newY, activeBoard[newY][newX]));
		//If attempting to move off the board, fail
		if(newX < 0 || newX > BOARD_HEIGHT || newY < 0 || newY > BOARD_WIDTH) {
			System.out.println("DEBUG: move failed due to attempted move off board");
			return false;
		}
		
		return walkable(newP);
	}
	
	public void resetBoard() {
		for(int x = 0; x < BOARD_HEIGHT; x++) {
			for(int y = 0; y < BOARD_WIDTH; y++) {
				activeBoard[x][y] = board[x][y];
			}
		}
	}
	
	
}