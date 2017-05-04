package breakout;

/**
 * Start the game
 *  The call to startGame() in the model starts 
 *    the actual play of the game
 *  Note: All issues of mutual exclusion on shared variables
 *        are ignored.
 * @author Mike Smith University of Brighton
 */
public class Main
{
    public static void main( String args[] )
    {
        Debug.trace("BreakOut");
        Debug.set( true );              // Set true to get debug info
        ModelBreakout model = new ModelBreakout();
        ViewBreakout  view  = new ViewBreakout();
        new ControllerBreakout( model, view );

        model.createGameObjects();       // Bricks/ bat/ ball
        model.addObserver( view );       // Add observer to the model

        view.setVisible(true);           // Make visible
        model.startGame();               // Start play
    }
}
