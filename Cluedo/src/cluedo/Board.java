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
	List<Players> players = new ArrayList<Players>(); //real players ingame
	List<Weapon> weapons = new ArrayList<Weapon>(); 
	
	public Board() throws IOException {
		cBoard  = new int[BOARD_WIDTH][BOARD_HEIGHT];
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(new File("cluemap.txt")));
			String line;
			
			//int row = Integer.parseInt(br.readLine());
			//int col = Integer.parseInt(br.readLine());
			
			
			while((line = br.readLine()) != null){
				//System.out.println(line);
				
				String[] c = line.split("");
				//int[] c = line.toCharArray();
				
				for(int j = 0; j < cBoard.length; j++){
					for(int i = 0; i < cBoard[i].length-1; i++){
						//for(int j = 0; j < cBoard[i].length; j++){
						cBoard[j][i] = Integer.parseInt(c[i]);
						//cBoard[j++] = line.toCharArray();
						System.out.println(Arrays.toString(c));
					}
					System.out.println();
				}
			}

		} catch(FileNotFoundException e){
			e.printStackTrace();
		} 
		finally{
			br.close();
		}	
		
		
	}
	
	

}
