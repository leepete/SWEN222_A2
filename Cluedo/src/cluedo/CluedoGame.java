package cluedo;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class CluedoGame {

	
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
	
					
	
	public static void main(String[] args) throws IOException {
		title();
		Board t = new Board();
		
	}

}
