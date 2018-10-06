/**
 * SJSU Spring 2018 CS 151
 * Team Project Mancala
 * @author Brandon Zhou, Pratyusha Pogaru
 * @version 1.0
 * @since 04/05/2018
 */



import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.Icon;

/**
 *  One of two concrete Strategies to create a different "style" for the game.
 */

public class StyleBIcon implements Icon {
	
	private int width;
	private int height;
	private int numStones;
	private int row;
	private int col;
	private Mancala pit;
	private Color color;
	private int x;
	private int y;
	
	
	/**
	 *  Constructor to create a StyleBIcon object
	 *  @param the Mancala to draw with StyleBIcon style
	 */
	public StyleBIcon(Mancala pit) {
		this.width = 100;
		this.height = 200;
		this.pit = pit;
		color = Color.BLUE;
	}
	
	
	/**
	 *  Draws the Mancala with corresponding number of stones
	 *  @param Component c the Component in which to draw the Icon
	 *  @param Graphics g the context for the Icon
	 *  @param x the x-coordinate to draw the Pit at
	 *  @param y the y-coordinate to draw the Pit at
	 *  Precondition: The Graphics and Component objects must be valid
	 *  Postcondition: The Icon (Mancala) and its stones must be drawn at the specified coordinates
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color); //should be blue
		
		Rectangle2D pitOutline = new Rectangle2D.Double(0, 0, width, height);
		g2.draw(pitOutline);
		
		int xGap = 0;
		int yGap = 0;
		int numRows = pit.getNumber()/4;
		//account for non-multiples of 4
		if (pit.getNumber()%4 != 0) {
			numRows = pit.getNumber()/4 + 1;
		}
		
		//scale to # of rows & minimize distortion of stones
		int stoneWidth = width/4;
		int stoneHeight = width/4;
		if (pit.getNumber() > 32) {
			stoneHeight = height/numRows;
		}
		
		int numDrawn = 0;
		
		for (int i = 0; i <= numRows; i++) {
			xGap = 0;
			for (int j = 0; j < 4; j++) {
				if (pit.getNumber() > numDrawn) {
					Ellipse2D temp = new Ellipse2D.Double(xGap, yGap, stoneWidth, stoneHeight);
					g2.fill(temp);
					numDrawn++;
					xGap += stoneWidth;
				}
			}
			yGap += stoneHeight;
		}
		
	}

	
	/**
	 *  Returns Icon's width
	 *  @return the width
	 */
	@Override
	public int getIconWidth() {
		return width;
	}

	
	/**
	 *  Returns Icon's height
	 *  @return the height
	 */
	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	
	
}