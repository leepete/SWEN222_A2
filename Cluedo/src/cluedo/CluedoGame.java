package cluedo;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CluedoGame {
	
	public CluedoGame(){}

	
	/*Just Start title when you run game*/
	public static void title(){
		String draw = "Game starting...\n";
		System.out.println(draw);
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String draw2 = "WELCOME TO CLUEDO! \n";
		System.out.println(draw2);
	}
	
	public static void game() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("How many players (max 6)? ");
		//String s = br.readLine();
		int i = Integer.parseInt(br.readLine());
		if(i < 1 || i >= 7){
			System.out.print("TOO MANY PLAYERS! "+ "Please re-enter players: ");
		}
		
	}
					
	
	public static void main(String[] args) throws IOException {
		title();
		Board t = new Board();
		game();
	}

}
