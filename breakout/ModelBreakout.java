package breakout;

import java.util.Observable;
import static breakout.Global.*;
/**
 * Model of the game of breakout
 *  The active object ActiveModel does the work of moving the ball
 * @author Mike Smith University of Brighton
 */

public class ModelBreakout extends Observable
{
    private GameObject ball;      // The ball
    private GameObject bricks[];  // The bricks
    private GameObject bat;       // The bat

    private ModelActivePart am  = new ModelActivePart( this );
    private Thread activeModel  = new Thread( am );

    private int score = 0;

    public void createGameObjects()
    {
        ball   = new GameObject(W/2, H/2, BALL_SIZE, BALL_SIZE, Colour.RED );
        bat    = new GameObject(W/2, H - BRICK_HEIGHT*4, BRICK_WIDTH*3, 
            BRICK_HEIGHT, Colour.GRAY);
        bricks = new GameObject[BRICKS];

        // *[1]**********************************************************
        // * Fill in code to place the bricks on the board              *
        // ********************************** ****************************

        for (int i =0; i < BRICKS; i++) {
            if( i <= 9) {
                bricks[i] = new GameObject(40 + (i *52), 100, BRICK_WIDTH, BRICK_HEIGHT, Colour.BLUE);
            }
            else if ( i <= 19) {
                bricks[i] = new GameObject(40 + ((i - 10)*52), 140, BRICK_WIDTH, BRICK_HEIGHT, Colour.BLACK);
            }
            else if ( i <= 29) {
                bricks[i] = new GameObject(40 + ((i - 20)*52), 180, BRICK_WIDTH, BRICK_HEIGHT, Colour.AMBER);
            }
        }
    }

    public void startGame()             { activeModel.start(); }

    public GameObject getBall()         { return ball; }

    public GameObject[] getBricks()     { return bricks; }

    public GameObject getBat()          { return bat; }

    public void addToScore( int n )     { score += n; }

    public int getScore()               { return score; }

    public void stopGame()              { }

    /**
     * Move the bat dist pixels. (-dist) is left or (+dist) is right
     * @param dist - Distance to move
     */
    public void moveBat( float dist )
    {
        // *[2]**********************************************************
        // * Fill in code to prevent the bat being moved off the screen *
        // *************************************************************

        Debug.trace( "Model: Move bat = %6.2f", dist );

        int batX = (int)bat.getX();
        int batY = (int)bat.getY();
        int batSize = BRICK_WIDTH*3;

        if (batX >= W - B - batSize)
            dist = -1;
        if (batX <= 0 + B)
            dist = 1;

        bat.moveX(dist);

    }

    /**
     * Model has changed so notify observers so that they
     *  can redraw the current state of the game
     */
    public void modelChanged()
    {
        setChanged(); notifyObservers();
    }

}
