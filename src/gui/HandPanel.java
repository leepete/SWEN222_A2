package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HandPanel extends JPanel {
	
	private JTextArea s;
	private static final int WIDTH = 0;
	private static final int HEIGHT = 150;
	
	public HandPanel(LayoutManager layout){
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setAlignmentX(LEFT_ALIGNMENT);
		setVisible(true);
		addCards();
	}
	
	public void addCards(){
		s = new JTextArea("PLACEHOLDER");
		add(s);
		setBackground(Color.RED);
	}
}
