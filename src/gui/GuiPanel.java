package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import cluedo.Board;



public class GuiPanel extends JPanel{
	
	private static final int WIDTH = 1980;
	private static final int HEIGHT = 1080;
	
	public GuiPanel(){
		//GUI construct
		super();
		//DIMENSION is basically an X & Y box - swing loves Dimensions than X & Y	
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setVisible(true);
		this.setBackground(Color.BLACK); ///black to test
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.drawRect(10, 10, 10, 10);
		//g.drawString("THIS IS THE PANEL!!!!!!",10,20); ///test
	}
	
	public void redraw(){
		this.repaint(); //calls paintCOmponent
		System.out.println("DEBUG: Redrawn");
	}
}
