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
							   "#==   =++<1    4>+++===T##".toCharArray(),
							   "##===v=++=2    3=++++++++#".toCharArray(),
							   "#++++++++=v====v=+++++++##".toCharArray(),
							   "##+++++++++++++++++======#".toCharArray(),
							   "#=====+++++++++++++<1   =#".toCharArray(),
							   "#=   ====++=====+++=BILL=#".toCharArray(),
							   "#= DINE =++= C =+++=   2=#".toCharArray(),
							   "#=     1>++= E =+++====v=#".toCharArray(),
							   "#=      =++= L =++++++++##".toCharArray(),
							   "#=    2 =++= L =+++==^==##".toCharArray(),
							   "#=====v==++= R =++== 1 ==#".toCharArray(),
							   "##+++++++++=====++<2LIBR=#".toCharArray(),
							   "#+++++++++++++++++==   ==#".toCharArray(),
							   "##++++++++==^^==+++=====##".toCharArray(),
							   "#T=====^++= 12 =+++++++++#".toCharArray(),
							   "#=     =++=   3>++++++++##".toCharArray(),
							   "#=LOUNG=++=HALL=++^=====T#".toCharArray(),
							   "#=     =++=    =++= STY =#".toCharArray(),
							   "#=    ==++=    =++==    =#".toCharArray(),
							   "#======#+#======#+#======#".toCharArray(),
							   "##########################".toCharArray()};
	
	public Board(){
		for(int i = 0; i < BOARD_HEIGHT; i++) {
			for(int j = 0; j < BOARD_WIDTH; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}	
	}
	
	
}
