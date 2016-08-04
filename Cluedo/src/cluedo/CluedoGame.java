package cluedo;
import java.util.List;
import java.util.ArrayList;

public class CluedoGame {

	public CluedoGame() {
		System.out.println("New Game!?");
	}
	List<Player> players = new ArrayList<Player>();
	
	public static void main(String[] args) {
		CluedoGame game = new CluedoGame();	
	}

}
