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
//		/**Panels will now have access to GridBagConstraints*/
//		GridBagConstraints gbc = new GridBagConstraints(); // helps organise and space in a grid system
//		gbc.anchor = GridBagConstraints.WEST; //ALIGNS THE CHECKBOXES
//		gbc.fill = GridBagConstraints.BOTH; //aligns buttons perfect - the lengths are even
//		gbc.insets = new Insets(15,15,15,15);
//		gbc.weightx = 1; ///button size
//		gbc.weighty = 1;
		
		img = new ImageIcon(getClass().getResource("/cluedo.JPG"));
		imgLabel = new JLabel(img);
		//add(imgLabel,gbc);
		add(imgLabel);
		setBackground(Color.YELLOW);
	}
}
