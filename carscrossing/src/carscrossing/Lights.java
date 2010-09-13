/*
 *Lights oject class
 */

package carscrossing;

/**
 *
 * @author Justin Jackson
 */

public class Lights {

    private boolean green=false;
    private int amberDelay=0;
    private int delayCount=10;

    int getDelayCount(){
        return delayCount;
    }

    void resetDelayCount() {
        delayCount=0;
    }

    /**
     * When called this method adds 1 to delay count
     *
     */

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
    }

    //Method to get objects state.
    boolean getGreen (){
        return green;
    }
}