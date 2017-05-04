package breakout;

import java.util.Date;

/**
 * Make getting the time of day easier
 * @author Mike Smith University of Brighton
 */

class Timer
{
    private static long startTime;
    private static long stopTime;

    /**
     * Remember the current time
     */
    public static void startTimer()
    {
        startTime = System.currentTimeMillis();
    }

    /**
     * How long has passed since call to startTimer
     * @return How long in milliseconds
     */
    public static long timeTaken()
    {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Return time in milliseconds
     * @return time in milliseconds
     */
    private static long getTimeInMills()
    {
        return System.currentTimeMillis();
    }
}
