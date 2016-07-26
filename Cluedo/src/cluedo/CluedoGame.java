package cluedo;
import java.util.List;
import java.util.ArrayList;

public class CluedoGame {

	List<Players> players = new ArrayList<Players>();
	private int[][] cBoard = new int[24][24]; // 25x25 board
	
	/*Just Start title when you run game*/
	public static void asciiStart(){
		String draw = "";
		draw += " ▄▄▄▄▄▄▄▄▄▄▄  ▄            ▄         ▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄ \n";
		draw += "▐░░░░░░░░░░░▌▐░▌          ▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌" + "\n";
		draw += "▐░█▀▀▀▀▀▀▀▀▀ ▐░▌          ▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌" + "\n";
		draw += "▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌" + "\n";	
		draw += "▐░▌          ▐░▌          ▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌       ▐░▌▐░▌       ▐░▌" + "\n";
		draw += "▐░▌          ▐░▌          ▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌       ▐░▌" + "\n";
		draw += "▐░▌          ▐░▌          ▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌       ▐░▌▐░▌       ▐░▌" + "\n";
		draw += "▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌" + "\n";
		draw += "▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌" + "\n";
		draw += "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌" + "\n";
		draw += " ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀   ▀▀▀▀▀▀▀▀▀▀▀ " + "\n";                               
		System.out.println(draw);
	}
	
	public void board(){
		String board = "|xxxxxxxxxSxxxxxSxxxxxxxxx|\n" //x out of bounds - S for Start 
					 + "|KKKKKTx░░░BBBBB░░░xCCCCCC|\n" // T for Stairs - f for floors
					 + "|KKKKKK░░BBBBBBBBB░░CCCCCC|\n" //B for ballRoom
					 + "|KKKKKK░░BBBBBBBBB░░CCCCCC|\n"
					 + "|KKKKKK░░BBBBBBBBB░░dCCCCC|\n" //d for door
				   	 + "|KKKKKK░░dBBBBBBBd░░░CCCTx|\n" 
					 + "|xKKKdK░░BBBBBBBBB░░░░░░░S|\n"
					 + "|░░░░░░░░BdBBBBBdB░░░░░░░x|\n"
					 + "|x░░░░░░░░░░░░░░░░░░bbbbbb|\n" //b for billiard Room
					 + "|DDDDDDDD░░██████░░░bbbbbb|\n"
					 + "|DDDDDDDD░░█cccc█░░░bbbbbb|\n" //c = cluedo solution
					 + "|DDDDDDDd░░█cccc█░░░bbbbdb|\n"
					 + "|DDDDDDDD░░█cccc█░░░░░░░░x|\n"
					 + "|DDDDDDDD░░█cccc█░░░LLdLLx|\n"
					 + "|DDDDDDdD░░█cccc█░░LLLLLLL|\n"
					 + "|x░░░░░░░░░██████░░dLLLLLL|\n"
					 + "|S░░░░░░░░░░░░░░░░░LLLLLLL|\n"
					 + "|x░░░░░░░░HHHHHHH░░░LLLLLx|\n"
				     + "|Tllllld░░HHHHHHH░░░░░░░░S|\n"
				     + "|lllllll░░HHHHHHd░░░░░░░░x|\n"
				     + "|lllllll░░HHHHHHH░░dsssssT|\n"
				     + "|lllllll░░HHHHHHH░░sssssss|\n"
				     + "|lllllll░░HHHHHHH░░sssssss|\n"
				     + "|llllllxSxHHHHHHHx░sssssss|\n";
		
		for(int[] i : cBoard){
			//if(charAt)
		}
		System.out.println(board);
		
					 
		
	}
	
	public static void main(String[] args) {
		asciiStart();
		
	}

}
