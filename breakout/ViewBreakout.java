package breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import static breakout.Global.*;

/**
 * Displays a graphical view of the game of breakout
 *  Uses Garphics2D would need to be re-implemented for Android
 * @author Mike Smith University of Brighton
 */
public class ViewBreakout extends JFrame implements Observer
{ 
    private ControllerBreakout controller;
    private GameObject   ball;             // The ball
    private GameObject[] bricks;           // The bricks
    private GameObject   bat;              // The bat
    private int          score =  0;       // The score
    private long         timeTaken = 0;    // How long
    private int          frames = 0;       // Frames output
    private final static int  RESET_AFTER = 200;

    /**
     * Construct the view of the game
     */

    public ViewBreakout()
    {
        setSize( W, H );                        // Size of window
        addKeyListener( new Transaction() );    // Called when key press
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Timer.startTimer();
    }

    /**
     *  Code called to draw the current state of the game
     *   Uses draw:       Draw a shape
     *        fill:       Fill the shape
     *        setPaint:   Colour used
     *        drawString: Write string on display
     *  @param g Graphics context to use
     */
    public void drawActualPicture( Graphics2D g )
    {
        frames++;
        // White background

        g.setPaint( Color.white );
        g.fill( new Rectangle2D.Float( 0, 0, W, H ) );

        Font font = new Font("Monospaced",Font.BOLD,24); 
        g.setFont( font ); 

        // Blue playing border

        g.setPaint( Color.blue );              // Paint Colour
        g.draw( new Rectangle2D.Float( B, M, W-B*2, H-M-B ) );

        // Display the ball
        display( g, ball );

        // Display the bricks that make up the game
        // *[3]**********************************************************
        // * Fill in code to display bricks (A brick may not exist)     *
        // **************************************************************

        for (GameObject brick : bricks) 
        {
            if (null != brick)
            {
                display ( g, brick );
            }
        }

        // Display the bat
        display( g, bat );

        // Display state of game
        g.setPaint( Color.black );
        FontMetrics fm = getFontMetrics( font );
        String fmt = "BreakOut: Score = [%6d] fps=%5.1f";
        String text = String.format(fmt, score, 
                frames/(Timer.timeTaken()/1000.0)
            );
        if ( frames > RESET_AFTER ) 
        { frames = 0; Timer.startTimer(); }
        g.drawString( text, W/2-fm.stringWidth(text)/2, (int)M*2 );
    }

    private void display( Graphics2D g, GameObject go )
    {
        switch( go.getColour() )
        {
            case GRAY: g.setColor( Color.gray );
            break;
            case BLUE: g.setColor( Color.blue );
            break;
            case RED:  g.setColor( Color.red );
            break;
            case BLACK:  g.setColor( Color.black );
            break;
            case AMBER:  g.setColor( Color.orange );
            break;
        }
        g.fill( new Rectangle2D.Float( go.getX(),     go.getY(), 
                go.getWidth(), go.getHeight() ) );
    }

    /**
     * Called from the model when its state has changed
     * @param aModel Model to be displayed
     * @param arg    Any arguments
     */
    @Override
    public void update( Observable aModel, Object arg )
    {
        ModelBreakout model = (ModelBreakout) aModel;
        // Get from the model the ball, bat, bricks & score
        ball    = model.getBall();              // Ball
        bricks  = model.getBricks();            // Bricks
        bat     = model.getBat();               // Bat
        score   = model.getScore();             // Score
        //Debug.trace("Update");
        repaint();                              // Re draw game
    }

    /**
     * Called by repaint to redraw the Model
     * @param g    Graphics context
     */
    @Override
    public void update( Graphics g )          // Called by repaint
    {
        drawPicture( (Graphics2D) g );          // Draw Picture
    }

    /**
     * Called when window is first shown or damaged
     * @param g    Graphics context
     */
    @Override
    public void paint( Graphics g )           // When 'Window' is first
    {                                         //  shown or damaged
        drawPicture( (Graphics2D) g );          // Draw Picture
    }

    private BufferedImage theAI;              // Alternate Image
    private Graphics2D    theAG;              // Alternate Graphics

    public void drawPicture( Graphics2D g )   // Double buffer
    {                                         //  to avoid flicker
        if (  theAG == null )
        {
            Dimension d = getSize();              // Size of curr. image
            theAI = (BufferedImage) createImage( d.width, d.height );
            theAG = theAI.createGraphics();
        }
        drawActualPicture( theAG );             // Draw Actual Picture
        g.drawImage( theAI, 0, 0, this );       //  Display on screen
    }

    /**
     * Need to be told where the controller is
     * @param aPongController The controller used
     */
    public void setController(ControllerBreakout aPongController)
    {
        controller = aPongController;
    }

    /**
     * Methods Called on a key press 
     *  calls the controller to process
     */
    class Transaction implements KeyListener  // When character typed
    {
        @Override
        public void keyPressed(KeyEvent e)      // Obey this method
        {
            // Make -ve so not confused with normal characters
            controller.userKeyInteraction( -e.getKeyCode() );
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            // Called on key release including specials
        }

        @Override
        public void keyTyped(KeyEvent e)
        {
            // Send internal code for key
            controller.userKeyInteraction( e.getKeyChar() );
        }
    }
}
