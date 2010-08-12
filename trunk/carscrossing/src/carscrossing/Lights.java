/*
 *Lights oject class
 */

package carscrossing;

/**
 *
 * @author Pro
 */



public class Lights {
// Lights only need one attribute for cars to act apon.
    boolean go;
    
    
    // Method to set objects state.
    void setGo (boolean value) {
        go = value;
    }

    //Method to get objects state.
    boolean getGo (){
        return go;
    }
}
