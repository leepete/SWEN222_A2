package cluedo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Board {
		
	private static final int BOARD_WIDTH = 25;
	private static final int BOARD_HEIGHT = 25;
	
	private int[][] cBoard = new int[BOARD_WIDTH][BOARD_HEIGHT];
	List<Players> players = new ArrayList<Players>(); //real players ingame
	List<Weapon> weapons = new ArrayList<Weapon>(); 
	
	public Board() throws IOException {
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(new File("cluemap.txt")));
			String line;
			
			while((line = br.readLine()) != null){
				System.out.println(line);
				//for(int i = 0; i < )
			}

		} catch(FileNotFoundException e){
			e.printStackTrace();
		} 
		finally{
			br.close();
		}	
		
		
	}
	
	

}
