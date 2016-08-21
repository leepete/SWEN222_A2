package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cluedo.CluedoGame;
import cluedo.Player;

public class HandPanel extends JPanel {

	//
	private static final int WIDTH = 0;
	private static final int HEIGHT = 150;

	BufferedImage image;
	JLabel l1,l2,l3,l4,l5;
	ArrayList<JLabel> cardList;
	GridBagConstraints gbc = new GridBagConstraints();
	


	public HandPanel(){
		super();
		cardList = new ArrayList<JLabel>();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setAlignmentX(LEFT_ALIGNMENT);
		setVisible(true);
		
		setOpaque(false);
		setLayout(new GridBagLayout());

 
	
		 validate();
	}

	@Override
	public void paintComponent(Graphics g){
		//setBackground(Color.RED);
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);

	}
	
	public void setLabels(){
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		for(String s : CluedoGame.currentPlayer.getHand()) {
			try {
				cardList.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource((String)(s + ".png")) ) ) );
			} catch(NullPointerException npe) {
				System.out.println("Caught me a null pointer :O?!");
			}
		}
		for(JLabel label : cardList) {
			add(label, gbc);
			gbc.gridx++;
		}
		cardList.clear();
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

}
