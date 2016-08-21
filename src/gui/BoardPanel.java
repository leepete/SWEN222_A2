package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BoardPanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage backdrop;
	private ImageIcon img;
	private Image BOARD_IMAGE;
	private JLabel imgLabel;
	/**DEBUG: Draw a rect*/
	private Rectangle rect;

	public BoardPanel(){
		super();
		img = new ImageIcon(getClass().getResource("/cluedo.JPG"));
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setVisible(true);
		importImage();
		//addKeyListener(this);
		//movement();
		//BOARD_IMAGE = new ImageIcon("/cluedo.JPG").getImage();
	}

	/**
	 * Imports Image of Cluedo
	 */
	public void importImage(){		
		imgLabel = new JLabel(img);
		//add(imgLabel,gbc);
		add(imgLabel);
	}

	public void movement(){
		rect = new Rectangle(0,0,50,50);
		addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT){
					rect.setLocation(rect.x + 2, rect.y);
				}else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT){
					rect.setLocation(rect.x - 2, rect.y);
				}else if(code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP){
					rect.setLocation(rect.x, rect.y-2);
				}else if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_KP_DOWN){
					rect.setLocation(rect.x , rect.y+2);
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
		});
		}

		
		public void paintComponent(Graphics g){
			//g.drawImage(this.img, 0, 0, null);
//			Graphics2D g2 = (Graphics2D)g;
//			g2.fill(rect);   
			super.paintComponent(g);
			g.drawImage(BOARD_IMAGE, WIDTH, HEIGHT, null);
			g.setColor(Color.BLACK);
			
			//for(Node n)
        }
		
		
    

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
