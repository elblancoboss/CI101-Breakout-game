package breakout;

import static breakout.Global.*;

import java.awt.event.KeyEvent;
/**
 * BreakOut controller, handles user interactions
 * @author Mike Smith University of Brighton
 */
public class ControllerBreakout
{
    private ModelBreakout model;   // Model of game
    private ViewBreakout  view;    // View of game

    public ControllerBreakout( ModelBreakout aBreakOutModel,
    ViewBreakout aBreakOutView )
    {
        model  = aBreakOutModel;
        view   = aBreakOutView;
        view.setController( this );    // View could talk to controller
    }

    /**
     * Decide what to do for each interaction from the user
     * Called from the interaction code in the view
     * @param keyCode The key pressed
     */
    public void userKeyInteraction(int keyCode )
    {
        // Key typed includes specials, -ve
        // Char is ASCII value
        switch ( keyCode )               // Character is
        {
            case -KeyEvent.VK_LEFT:        // Left Arrow
            model.moveBat( -BAT_MOVE );
            break;
            case -KeyEvent.VK_RIGHT:       // Right arrow
            model.moveBat( +BAT_MOVE );
            break;
            case -KeyEvent.VK_UP:          // Up arrow
            //model.startGame();
            break;
            case -KeyEvent.VK_DOWN:        // Down arrow
            //model.stopGame();
            break;
        }
    }

}
