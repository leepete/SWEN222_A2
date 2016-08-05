package cluedo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board {
		
	private static final int BOARD_WIDTH = 26;
	private static final int BOARD_HEIGHT = 27;
	
	//Fix me please
	private char[][] board = { "##########################".toCharArray(),
							   "##########+####+##########".toCharArray(),
							   "#=====T#+++====+++#======#".toCharArray(),
							   "#=KITC=++===  ===++=CONS=#".toCharArray(),
							   "#=    =++=      =++=    =#".toCharArray(),
							   "#=    =++= BALL =++v=   =#".toCharArray(),
							   "#==   =++< ROOM >+++===T##".toCharArray(),
							   "##===v=++=      =++++++++#".toCharArray(),
							   "#++++++++=v====v=+++++++##".toCharArray(),
							   "##+++++++++++++++++======#".toCharArray(),
							   "#=====+++++++++++++<    =#".toCharArray(),
							   "#=   ====++=====+++=BILL=#".toCharArray(),
							   "#= DINE =++= C =+++=    =#".toCharArray(),
							   "#= ROOM >++= E =+++====v=#".toCharArray(),
							   "#=      =++= L =++++++++##".toCharArray(),
							   "#=      =++= L =+++==^==##".toCharArray(),
							   "#=====v==++= R =++==   ==#".toCharArray(),
							   "##+++++++++=====++< LIBR=#".toCharArray(),
							   "#+++++++++++++++++==   ==#".toCharArray(),
							   "##++++++++==^^==+++=====##".toCharArray(),
							   "#T=====^++=    =+++++++++#".toCharArray(),
							   "#=     =++=    >++++++++##".toCharArray(),
							   "#=LOUNG=++=HALL=++^=====T#".toCharArray(),
							   "#=     =++=    =++= STY =#".toCharArray(),
							   "#=    ==++=    =++==    =#".toCharArray(),
							   "#======#+#======#+#======#".toCharArray(),
							   "##########################".toCharArray()};
	
	public Board(){
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}	
	}
	
	
}
