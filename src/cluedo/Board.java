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
					   "|■■■■■■■■■S■■■■S■■■■■■■■■|\n" +
					   "|     T#⛶⛶⛶     +++#      |\n" +
					   "|      ++         ++  Con |\n" +
					   "|  Kit ++    Ball ++      |\n" +
					   "|      ++         ++◀     |\n" +
					   "|      ++◀       ▶+++   T#|\n" +
					   "|#   ▼ ++         +++++++S|\n" +
					   "|++++++++ ▼     ▼ +++++++#|\n" +
					   "|#++++++++++++++++++      |\n" +
					   "|        ++~~~~~~+++d bil |\n" +
					   "|        ++~    ~+++      |\n" +
					   "|  din  ▶++~    ~+++    ▼ |\n" +
					   "|        ++~  I ~++++++++#|\n" +
					   "|        ++~    ~+++  ▲  #|\n" +
					   "|      ▼ ++~    ~++       |\n" +
					   "|#+++++++++~~~~~~++◀  Lib |\n" +
					   "|S+++++++++++++++++       |\n" +
					   "|#++++++++  ▲▲   +++     #|\n" +
					   "|T     ▲++       ++++++++S|\n" +
					   "|       ++      ▶++++++++#|\n" +
					   "|       ++  hall ++▲     T|\n" +
					   "|  lou  ++       ++       |\n" +
					   "|       ++       ++  sdy  |\n" +
					   "|      #S#       #+       |\n" +
					   "---------------------------\n";
		System.out.println(board);
	}
	
	

}
