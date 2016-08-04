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
	
	private char[][] board = { "---------------------------".toCharArray(),
							   "|---------S----S----------|".toCharArray(),
							   "|     T#+++     +++#      |".toCharArray(),
							   "|      ++         ++  Con |".toCharArray(),
							   "|  Kit ++    Ball ++      |".toCharArray(),
							   "|      ++         ++◀     |".toCharArray(),
							   "|      ++◀       ▶+++   T#|".toCharArray(),
							   "|#   ▼ ++         +++++++S|".toCharArray(),
							   "|++++++++ ▼     ▼ +++++++#|".toCharArray(),
							   "|#++++++++++++++++++      |".toCharArray(),
							   "|        ++~~~~~~+++d bil |".toCharArray(),
							   "|        ++~    ~+++      |".toCharArray(),
							   "|  din  ▶++~    ~+++    ▼ |".toCharArray(),
							   "|        ++~  I ~++++++++#|".toCharArray(),
							   "|        ++~    ~+++  ▲  #|".toCharArray(),
							   "|      ▼ ++~    ~++       |".toCharArray(),
							   "|#+++++++++~~~~~~++◀  Lib |".toCharArray(),
							   "|S+++++++++++++++++       |".toCharArray(),
							   "|#++++++++  ▲▲   +++     #|".toCharArray(),
							   "|T     ▲++       ++++++++S|".toCharArray(),
							   "|       ++      ▶++++++++#|".toCharArray(),
							   "|       ++  hall ++▲     T|".toCharArray(),
							   "|  lou  ++       ++       |".toCharArray(),
							   "|       ++       ++  sdy  |".toCharArray(),
							   "|      #S#       #+       |".toCharArray(),
							   "---------------------------".toCharArray()};
	
	public Board(){
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}	
	}
	
	
}
