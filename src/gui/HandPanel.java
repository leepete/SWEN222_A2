package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cluedo.Board;
import cluedo.Card;
import cluedo.Player;

public class HandPanel extends JPanel implements MouseListener {
	
	//
	private static final int WIDTH = 0;
	private static final int HEIGHT = 150;
	
	private Board gameBoard;
	private int width;
	private int height;
	private List<String> hand = new ArrayList<String>();
	private Player p;
	private Card card;
	private static final String IMG_PATH = "../Graphics";
	BufferedImage image;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	
	public HandPanel(){
		super();
	
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setAlignmentX(LEFT_ALIGNMENT);
		addMouseListener(this);
		
		//this.addMouseListener(this);
//		THIS.GAMEBOARD = B;
//		THIS.HEIGHT = HEIGHT;
//		THIS.WIDTH = WIDTH;
//		THIS.P = PLAYER;
		//hand = p.getHand();
//		repaint();
//		setVisible(true);
//		 try {
//			image = ImageIO.read(new File("../die1.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		BufferedImage image;
		try {
			image = ImageIO.read(new File("die1.png"));
			JLabel picLabel = new JLabel(new ImageIcon(image));
			add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			setBackground(Color.RED);
//			JLabel picLabel = new JLabel(new ImageIcon(image));
//			add(picLabel);
			setVisible(true);
	}
	
	public void addCards(Player p){
		this.hand = p.getHand();
	}

	public void paint(Graphics g) {
//		for(int i = 0; i < hand.size(); i++){
//			try {
//				BufferedImage myPicture = ImageIO.read(new File(IMG_PATH + hand.get(i) + ".psd"));
//				int cWidth = 0;
//				if(hand.size() > 0){
//					cWidth = width / hand.size();
//				}
//				else{
//					return;
//				}
//				BufferedImage scaled = getScaledImage(myPicture, cWidth, height);
//				g.drawImage(scaled, cWidth * i,0, getParent());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		 Graphics2D g2d = (Graphics2D) g;
//	     if (image != null) {
//	        g2d.drawImage(image, WIDTH, HEIGHT, null);
//	     }
	}
	
	//Scale the image
	private BufferedImage getScaledImage(Image img, int w, int h){
	    BufferedImage resized = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resized.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(img, 0, 0, w, h, null);
	    g2.dispose();
	    return resized;
	}
	
	/**
	 * Sets the width of the canvas
	 * @param width
	 */
	public void setWidth(int width){
		this.width = width;
		setSize(this.width, this.height);
	}
	
	/**
	 * Sets the height of the canvas
	 * @param height
	 */
	public void setHeight(int height){
		this.height = height;
	}

	/**
	 * Calculates the clicked card
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Get the clicked location
		int x = e.getX();
		int y = e.getY();
		
		//Get the starting x and y location of the hand area
		int handX = 0;
		int handY = 0;
		int maxY = 80;
		
		//Get the width of each card
		List<String> hand = this.hand;
		int numCards = hand.size();
		int cardSize = this.getWidth() / numCards;
		//Figure out which card was clicked
			//for each card in the hand
		for(int i = 0; i < hand.size(); i++)
		{
			int currentX = (cardSize * i) + handX;
				if(x >= currentX && x <= currentX + cardSize)
				{
					if(y >= handY && y <= maxY)
					{
						//card = hand.get(i);
						}
				}
		}
		
	}

	public Card getSelectedCard()
	{
		return card;
	}
	
	/**
	 * Sets the hand for this instance of hand canvas
	 * @param hand
	 */
	public void setHandCards(ArrayList<String> hand)
	{
		this.hand = hand;
	}
	
//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
