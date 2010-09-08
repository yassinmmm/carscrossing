package carscrossing;


/*
 *Lane object class
 */

/**
 *
 * @author Justin Jackson
 */
public class Lane {
    
    private int flow;
    private String name;
    private int length;
    private double carProb;
    private Lights stopOrGo = new Lights();
    private Car[] Cars;

    String getName() {
        return name;
    }

    void setName(String value) {
        name = value;
    }

    int getLength() {
        return length;
    }

    void setLength(int value, int delay) {
        length = value;
        Cars = new Car[value];
        stopOrGo.setAmberDelay(delay);
    }

    double getCarProb() {
        return carProb;
    }

    void setCarProb(double value) {
        carProb = value;
    }

    void setFlow(int value) {
        if (value == 1) {
            stopOrGo.setGreen(true);
        }
        flow = value;
    }

    int getFlow() {
        return flow;
    }

    public Car[] Cars(){
        return Cars;
    }

    public Lights stopOrGo(){
        return stopOrGo;
    }

    void changeLights() {

        if (stopOrGo.getGreen() == true) {
            stopOrGo.setGreen(false);
        } else {
            stopOrGo.resetDelayCount();
        }
    }

    void moveCars(Lane[] Lanes, int x) {

        // This small if is to remove the last car at the start of the new move
        if (Cars[length - 1] != null) {
            Cars[length - 1] = null;
        }

        // Loop is started at second last array now that last is clear and moves
        // all cars after the lights
        for (int y = length - 2; y > 9; --y) {

            if (Cars[y] != null) {
                Cars[y + 1] = Cars[y];
                Cars[y] = null;
            }
        }

        // All cars before lights will need follow different rules
        if (stopOrGo.getGreen()) {

            // The lights are green go as normal
            for (int y = 9; y >= 0; y--) {

                if (Cars[y] != null) {
                    Cars[y + 1] = Cars[y];
                    Cars[y] = null;
                }
            }
        } else {
            // for the following for loop i start the count at 8 thus unsuring
            // that a cascade of stopping will occur at the lights
            for (int y = 8; y > -1; y--) {

                if (Cars[y] != null) {

                    if (Cars[y + 1] == null) {

                        Cars[y + 1] = Cars[y];
                        Cars[y] = null;
                    }

                    // Overtaking exception only nessesary before lights as cars
                    // are assumed to have uniform speed before an after lights.

                    // check for next lane to move into
                    if ((x != (Lanes.length - 1)) && (getFlow() == Lanes[x + 1].getFlow())) {

                        // I decided from the check to both the same position
                        // and the next possition in the transition lane to
                        // simulate a realistic occurance.
                        if (((Lanes[x + 1].Cars[y]) == null) && (Lanes[x + 1].Cars[y + 1]) == null) {

                            Lanes[x + 1].Cars[y + 1] = Cars[y];
                            Cars[y] = null;
                        }
                    }

                    // While a real driver would would opt to be in the
                    // lane closer to a street they are turning into this
                    // simulation just opts for a closer spot to the lights


                    if ((x != 0) && (Lanes[x].getFlow() == Lanes[x - 1].getFlow())) {
                        if (((Lanes[x - 1].Cars[y]) == null) && (Lanes[x - 1].Cars[y + 1]) == null) {

                            Lanes[x - 1].Cars[y + 1] = Cars[y];
                            Cars[y] = null;
                        }
                    }
                }
            }
        }
    }

    void bingCar() {

        if (Cars[0] == null) {

            if (Math.random() <= carProb) {

                Cars[0] = new Car();
            }
        }
    }

    /**
     * This function is a very large String concatenation
     * @param Lanes - Passes in the reference to the Object array of Lanes
     * @param vValue - Passes in the number of vertical lanes
     * @param hValue - Passes in the number of horizontal lanes
     * @return the finished concatenation String value
     */
    

    /**
     * This is the main which has no initial arguments passed into it
     * @param args
     */
    
}
