package breakout;

import static breakout.Global.Colour;

/**
 * An Object in the game, represented as a rectangle
 *  Holds details of shape, plus possible direction of travel
 * Would be better to use inheritance 
 * @author Mike Smith University of Brighton
 */
public class GameObject
{
    public enum Collision { HIT, NO_HIT };
    // All the variables below are vital to the state of the object
    private float topX   = 0.0f; // Top left corner X
    private float topY   = 0.0f; // Top left corner Y
    private float width  = 0.0f; // Width of object
    private float height = 0.0f; // Height of object
    private Colour colour;       // Colour of object
    private int   dirX   = 1;    // Direction X (1 or -1)
    private int   dirY   = 1;    // Direction Y (1 or -1)

    /** 
     * Constructor game object
     * @param x co-ordinate of game object
     * @param y co-ordinate of game object
     * @param widthIs  width of game object
     * @param heightIs height of game object
     * @param c Colour of the game object
     */
    public GameObject( float x,       float y, 
    float widthIs, float heightIs, Colour c )
    {
        topX   = x;       topY = y;
        width  = widthIs; height = heightIs; 
        colour = c;
    }

    /** @return x co-ordinate of Object */  
    public float getX()       { return topX; }

    /** @return y co-ordinate of Object */ 
    public float getY()       { return topY; }

    /** @return Width of Object */ 
    public float getWidth()   { return width; }

    /**@return Height of Object */ 
    public float getHeight()  { return height; }

    /**@return Colour of object */
    public Colour getColour() { return colour; }

    /**
     * Move object by X units
     *  The actual direction moved is flipped by changeDirectionX()
     *  @param units units to move
     */
    public void moveX( float units )
    {
        topX += units * dirX;
    }

    /**
     * Move object by Y units
     *   The actual direction moved is flipped by changeDirectionY()
     *   @param units units to move
     */
    public void moveY( float units )
    {
        topY += units * dirY;
    }

    /**
     * Change direction of future moves in the X direction 
     */
    public void changeDirectionX()
    {
        dirX = -dirX;
    }

    /**
     * Change direction of future moves in the Y direction 
     */
    public void changeDirectionY()
    {
        dirY = -dirY;
    }

    /**
     * Detect a collision between two GameObjects 
     *  Would be good to know where the object is hit
     *  @param obj Game object to see if 'hit' by 
     *  @return Typo of collision
     */

    public Collision hitBy( GameObject obj )
    {
        if ( topX >= obj.topX+obj.width     ||
        topX+width <= obj.topX         ||
        topY >= obj.topY+obj.height    ||
        topY+height <= obj.topY )
            return Collision.NO_HIT;
        else {
            return Collision.HIT;
        }
    }

}
