/**
 * SJSU Spring 2018 CS 151
 * Team Project Mancala
 * @author  Brandon Zhou, Pratyusha Pogaru
 * @version 1.0
 * @since 04/05/2018
 */




import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**  Concrete Icon for use with the Strategy Pattern in the View
 */
public class StyleRIcon implements Icon
{
    private int width;
    private int height;
    private Color iconColor;
    private Mancala mancala;
    
    /**  Constructor for a StyleAIcon
     * @param m Reference to the Mancala object this Icon will represent
     */
    public StyleRIcon(Mancala m)
    {
        iconColor = Color.RED;
        width = 100;
        height = 200;
        mancala = m;
    }
    
    /**  Access Icon width
     * @return Returns Icon width
     */
    public int getIconWidth()
    {
        return this.width;
    }
    
    /**  Access Icon height
     * @return Returns Icon height
     */
    public int getIconHeight()
    {
        return this.height;
    }
    
    /**  Paints the Icon and the elements(stones) inside of it
     * @param c Component reference for the Icon
     * @param g Graphics context for the Icon
     * @param x x-coordinate for Icon to be painted
     * @param y y-coordinate for Icon to be painted
     * Precondition:  c and g must be valid to be painted on by an Icon
     * Postcondition:  An Icon will be painted with boundaries and ellipses representing the stones in the Mancala
     */
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(iconColor);
        
        Rectangle2D pitOutline = new Rectangle2D.Double(0, 0, width, height);
        g2.draw(pitOutline);
        
        int rows = mancala.getNumber()/4;
        if(mancala.getNumber()%4 != 0)
        {
            rows++;
        }
        int stoneWidth = width/4;
        int stoneHeight = width/4;
        if(mancala.getNumber() > 32)
        {
            stoneHeight = height/rows;
        }
        int drawnStones = 0;
        int currentX;
        int currentY = 0;
        for(int i = 0; i <= rows; i++)
        {
            currentX = 0;
            for(int j = 0; j < 4; j++)
            {
                if(drawnStones < mancala.getNumber())
                {
                    Ellipse2D stone = new Ellipse2D.Double(currentX, currentY, stoneWidth, stoneHeight);
                    g2.fill(stone);
                    drawnStones++;
                    currentX += stoneWidth;
                }
            }
            currentY += stoneHeight;
        }
    }
}