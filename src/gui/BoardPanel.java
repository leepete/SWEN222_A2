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
import javax.swing.Timer;

import cluedo.Board;

public class BoardPanel extends JPanel implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;

	private BufferedImage backdrop;
	private ImageIcon img;
	private Image BOARD_IMAGE;
	private JLabel imgLabel;

	private final int sqSize = 26;
	private final int BOARD_WIDTH = 26; 
	private final int BOARD_HEIGHT = 27; 
	Board board;
	
	private Rectangle rect;
	
	Timer time = new Timer(5, this);
	int x, y, velX, velY = 0;
	

	public BoardPanel(){
		super();
		time.start();
		img = new ImageIcon("../door.png");
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setVisible(true);
		//importImage();
		
		/**Key Implementation*/
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		requestFocusInWindow();
		revalidate();

	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawSquares(g);
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 30, 30);
		//img.paintIcon(this, g, sqSize, sqSize);
		
	}


	/**
	 * Board Layout that gets drawn
	 * @Graphics g
	 */
	public void drawSquares(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		Board b = new Board();
		
		char[][] board = b.getBoard();
		char corridor = '+';
		char boundary = '#';
		char wall = '=';
		char placemat = 'x';
		char[] doors = {'^', '<', '>', 'v'};
		char[] players = {'1', '2', '3', '4', '5', '6'};
				
		for(int x = 0; x < BOARD_HEIGHT; x++) {
			for	(int y = 0; y < BOARD_WIDTH; y++) {
				if(board[x][y] == (boundary)){
					g2.setColor(Color.BLUE);
					g2.drawRect(x*sqSize,y*sqSize ,sqSize, sqSize);
					g2.fillRect(x*sqSize,y*sqSize ,sqSize, sqSize);
				}else if(board[x][y] == corridor){
					g2.setColor(Color.YELLOW);
					g2.drawRect(x*sqSize,y*sqSize ,sqSize, sqSize);
					g2.fillRect(x*sqSize,y*sqSize ,sqSize, sqSize);
				}else if(board[x][y]  == wall){
					g2.setColor(Color.RED);
					g2.drawRect(x*sqSize,y*sqSize ,sqSize, sqSize);
					g2.fillRect(x*sqSize,y*sqSize ,sqSize, sqSize);
				}else if(board[x][y] == placemat){
					g2.setColor(Color.GRAY);
					g2.drawRect(x*sqSize,y*sqSize ,sqSize, sqSize);
					g2.fillRect(x*sqSize,y*sqSize ,sqSize, sqSize);
				}if(arrayContains(doors, board[x][y])){
//					g2.setColor(Color.MAGENTA);
//					g2.drawRect(x*sqSize,y*sqSize ,sqSize, sqSize);
//					g2.fillRect(x*sqSize,y*sqSize ,sqSize, sqSize);
					g2.setColor(new Color(165,42,42));
					//g2.setStroke(Rect);
					g2.fillRect(x*sqSize,y*sqSize ,sqSize, sqSize);
					g2.fillRect(x*sqSize,y*sqSize ,sqSize-10,sqSize/2-10);
					g2.fillRect(x*sqSize,y*sqSize,sqSize-10,sqSize/2-10);
					g2.setColor(new Color(255,255,0));
					g2.fillOval((x*sqSize),y*sqSize,10,10);
				}if(arrayContains(players, board[x][y])){
					g2.setColor(Color.CYAN);
					g2.drawRect(x*sqSize,y*sqSize ,sqSize, sqSize);
					g2.fillRect(x*sqSize,y*sqSize ,sqSize, sqSize);
				}
				g2.setColor(Color.BLACK);
				g2.drawRect(x*sqSize,y*sqSize ,sqSize, sqSize);
				//squares[i][j].draw(g2);
				repaint();
			}}
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
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT){
			velX = 1;
			velY = 0;
			//rect.setLocation(rect.x + 2, rect.y);
		}if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT){
			velX = -1;
			velY = 0;
			//rect.setLocation(rect.x - 2, rect.y);
		}if(code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP){
			velX = 0;
			velY = -1;
			//rect.setLocation(rect.x, rect.y-2);
		}if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_KP_DOWN){
			velX = 0;
			velY = 1;
			//rect.setLocation(rect.x , rect.y+2);
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/**Set Position Restrictions*/
		if(x < 0){
			velX = 0;
			x = 0;
		}
		if(x > 530){
			velX = 0;
			x = 530;
		}
		if(y < 0){
			velY = 0;
			y = 0;
		}
		if(y > 330){
			velY = 0;
			y = 330;
		}
		x = x + velX;
		y = y + velY;
		repaint();
	}
	




		
}
