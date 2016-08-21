package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HandPanel extends JPanel {

	//
	private static final int WIDTH = 0;
	private static final int HEIGHT = 150;

	BufferedImage image;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18;
	GridBagConstraints gbc = new GridBagConstraints();
	


	public HandPanel(){
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setAlignmentX(LEFT_ALIGNMENT);
		setVisible(true);
		setLabels();
		setOpaque(false);
		setLayout(new GridBagLayout());
//		l.setIcon(new ImageIcon("die1.jpg"));
//		add(l);
 
	
		 validate();
	}

	@Override
	public void paintComponent(Graphics g){
		//setBackground(Color.RED);
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	
		//repaint();
	}
	
	public void setLabels(){
		ImageIcon g = new ImageIcon(getClass().getClassLoader().getResource("billiardroom.png"));
		l1 = new JLabel(g);
		gbc.gridx = 0;
		gbc.gridy= 0;
		add(l1, gbc);
		
		ImageIcon g1 = new ImageIcon(getClass().getClassLoader().getResource("Candlestick.png"));
		l2 = new JLabel(g1);
		gbc.gridx++;
		gbc.gridy=0;
		add(l2, gbc);
		
		ImageIcon g2 = new ImageIcon(getClass().getClassLoader().getResource("Colonel Mustard.png"));
		l3 = new JLabel(g2);
		gbc.gridx++;
		gbc.gridy=0;
		add(l3, gbc);
		
		ImageIcon g3 = new ImageIcon(getClass().getClassLoader().getResource("dagger.png"));
		l4 = new JLabel(g3);
		gbc.gridx++;
		gbc.gridy=0;
		add(l4, gbc);
		
		ImageIcon g4 = new ImageIcon(getClass().getClassLoader().getResource("MissScarlet.png"));
		l5 = new JLabel(g4);
		gbc.gridx++;
		gbc.gridy=0;
		add(l5, gbc);
		
		ImageIcon g5 = new ImageIcon(getClass().getClassLoader().getResource("diningroom.png"));
		l6 = new JLabel(g5);
		gbc.gridx++;
		gbc.gridy=0;
		add(l6, gbc);
		
		ImageIcon g6 = new ImageIcon(getClass().getClassLoader().getResource("library.png"));
		l7 = new JLabel(g6);
		gbc.gridx++;
		gbc.gridy=0;
		add(l7, gbc);
		
		ImageIcon g7 = new ImageIcon(getClass().getClassLoader().getResource("lounge.png"));
		l8 = new JLabel(g7);
		gbc.gridx++;
		gbc.gridy=0;
		add(l8, gbc);
		
		ImageIcon g8 = new ImageIcon(getClass().getClassLoader().getResource("Rope.png"));
		l9 = new JLabel(g8);
		gbc.gridx++;
		gbc.gridy=0;
		add(l9, gbc);
		
//		ImageIcon g9 = new ImageIcon(getClass().getClassLoader().getResource("Spanner.png"));
//		l10 = new JLabel(g9);
//		gbc.gridx++;
//		gbc.gridy=0;
//		add(l10, gbc);
		
//		ImageIcon g10 = new ImageIcon(getClass().getClassLoader().getResource("study.png"));
//		l11 = new JLabel(g10);
//		gbc.gridx++;
//		gbc.gridy=0;
//		add(l11, gbc);
////		
//		ImageIcon g11 = new ImageIcon(getClass().getClassLoader().getResource("Mrs_White.png"));
//		l12 = new JLabel(g11);
//		gbc.gridx++;
//		gbc.gridy=0;
//		add(l12, gbc);

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
