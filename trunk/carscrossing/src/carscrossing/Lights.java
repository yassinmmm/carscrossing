/*
 *Lights oject class
 */

package carscrossing;

/**
 *
 * @author Pro
 */



public class Lights {
// Lights need MANY!! attributes and METHODS to RULE OVER cars!!!
    private boolean green=false;
    private int amberDelay=0;
    private int delayCount=10;
    private static int time=0;

    int getDelayCount(){
        return delayCount;
    }

    void resetDelayCount() {
        delayCount=0;
    }

    void plusDelayCount(){
        ++delayCount;
    }

    int getAmberDelay() {
        return amberDelay;
    }

    void setAmberDelay(int value) {
        amberDelay = value;
    }

    // Method to set objects state.
    void setGreen (boolean value) {
        green = value;

        // initialise delay
        if (value==false){

        }
    }

    //Method to get objects state.
    boolean getGreen (){
        return green;
    }

    void setTime(){
        ++time;
    }



}