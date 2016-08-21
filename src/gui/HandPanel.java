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
	JLabel l1,l2,l3,l4,l5;
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
//		l6 = new JLabel("lab");
//		add(l6);
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
