package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	
	private ImageIcon img;
	private JLabel imgLabel;

	public BoardPanel(){
		super();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setVisible(true);
		importImage();
	}
	
	/**
	 * Imports Image of Cluedo
	 */
	public void importImage(){		
		img = new ImageIcon(getClass().getResource("/cluedo.JPG"));
		imgLabel = new JLabel(img);
		//add(imgLabel,gbc);
		add(imgLabel);
		setBackground(Color.YELLOW);
	}
}
