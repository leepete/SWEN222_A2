package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cluedo.Board;
import cluedo.CluedoGame;
import cluedo.Player;

public class BoardPanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	private final int sqSize = 26;
	private final int BOARD_WIDTH = 26; 
	private final int BOARD_HEIGHT = 27; 
	Board board;

	public BoardPanel(Board board){
		super();
		this.board = board;
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setVisible(true);

	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		drawSquares(g);

	}

	/**
	 * Board Layout that gets drawn
	 * @Graphics g
	 */
	public void drawSquares(Graphics g){
		

		char[][] guiBoard = board.getBoard();
		char corridor = '+';
		char boundary = '#';
		char wall = '=';
		char placemat = 'x';
		char stairZ = 'Z';
		char stairY = 'Y';
		char p1 = '1';
		char p2 = '2';
		char p3 = '3';
		char p4 = '4';
		char p5 = '5';
		char p6 = '6';
		char[] doors = {'^', '<', '>', 'v'};
		


		for(int x = 0; x < BOARD_HEIGHT; x++) {
			for	(int y = 0; y < BOARD_WIDTH; y++) {
				if(guiBoard[x][y] == (boundary)) {
					g.setColor(Color.BLUE);
					g.drawRect(y*sqSize,x*sqSize ,sqSize, sqSize);
					g.fillRect(y*sqSize,x*sqSize ,sqSize, sqSize);
				} if(guiBoard[x][y] == corridor) {
					g.setColor(Color.YELLOW);
					g.drawRect(y*sqSize,x*sqSize ,sqSize, sqSize);
					g.fillRect(y*sqSize,x*sqSize ,sqSize, sqSize);
				}if(guiBoard[x][y]  == wall) {
					g.setColor(Color.RED);
					g.drawRect(y*sqSize,x*sqSize ,sqSize, sqSize);
					g.fillRect(y*sqSize,x*sqSize ,sqSize, sqSize);
				}if(guiBoard[x][y] == placemat) {
					g.setColor(Color.GRAY);
					g.drawRect(y*sqSize,x*sqSize ,sqSize, sqSize);
					g.fillRect(y*sqSize,x*sqSize ,sqSize, sqSize);
				}if(guiBoard[x][y] == p1 || guiBoard[x][y] == p2 || guiBoard[x][y] == p3 || guiBoard[x][y] == p4 || guiBoard[x][y] == p5 || guiBoard[x][y] == p6) {
					g.setColor(Color.pink);
					g.fillOval(y*sqSize, x*sqSize, sqSize, sqSize);

				}if(guiBoard[x][y] == stairY) {
					g.setColor(Color.CYAN);
					g.drawRect(y*sqSize,x*sqSize ,sqSize, sqSize);
					g.fillRect(y*sqSize,x*sqSize ,sqSize, sqSize);
				}
				if(guiBoard[x][y] == stairZ) {
					g.setColor(Color.ORANGE);
					g.drawRect(y*sqSize,x*sqSize ,sqSize, sqSize);
					g.fillRect(y*sqSize,x*sqSize ,sqSize, sqSize);
				}if(arrayContains(doors, guiBoard[x][y])) {
					g.setColor(new Color(40,26,13));
					g.fillRect(y*sqSize,x*sqSize ,sqSize, sqSize);
					g.fillRect(y*sqSize,x*sqSize ,sqSize-10,sqSize/2-10);
					g.fillRect(y*sqSize,x*sqSize,sqSize-10,sqSize/2-10);
					g.setColor(Color.YELLOW);
					g.fillOval(y*sqSize+5,x*sqSize+5,10,10);
				}
				g.setColor(Color.BLACK);
				g.drawRect(y*sqSize,x*sqSize ,sqSize, sqSize);
				
			}
		}
	}

	private boolean arrayContains(char[] array, char c) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == c) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
