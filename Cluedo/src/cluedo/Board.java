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
		
	private static final int BOARD_WIDTH = 25;
	private static final int BOARD_HEIGHT = 25;
	
	private int[][] cBoard; // = new int[BOARD_WIDTH][BOARD_HEIGHT];
	List<Player> players = new ArrayList<Player>(); //real players ingame
	List<Weapon> weapons = new ArrayList<Weapon>(); 
	
	public Board(){
		String board = "---------------------------\n" +
					   "|#########S#####S#########|\n" +
					   "|     T#+++     +++#      |\n" +
					   "|      ++         ++  C   |\n" +
					   "|  K   ++    B    ++      |\n" +
					   "|      ++         ++d     |\n" +
					   "|      ++d       d+++   T#|\n" +
					   "|#   d ++         +++++++S|\n" +
					   "|++++++++ d     d +++++++#|\n" +
					   "|#++++++++++++++++++      |\n" +
					   "|        ++~~~~~~+++d  b  |\n" +
					   "|        ++~    ~+++      |\n" +
					   "|   D   d++~    ~+++    d |\n" +
					   "|        ++~  I ~++++++++#|\n" +
					   "|        ++~    ~+++  d  #|\n" +
					   "|      d ++~    ~++       |\n" +
					   "|#+++++++++~~~~~~++d  L   |\n" +
					   "|S+++++++++++++++++       |\n" +
					   "|#++++++++       +++     #|\n" +
					   "|T     d++       ++++++++S|\n" +
					   "|       ++      d++++++++#|\n" +
					   "|       ++   H   ++d     T|\n" +
					   "|   l   ++       ++       |\n" +
					   "|       ++       ++   s   |\n" +
					   "|      #S#       #+       |\n" +
					   "---------------------------\n";
		System.out.println(board);

		
		
		
	}
	
	

}
