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

    /**
     * Calls for the object Car[] from Lane
     * @return
     */
    public Car[] Cars(){
        return Cars;
    }

    /**
     * Calls for the return of object Lights from Lane
     * @return
     */
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

    /**
     * Moves car objects in Car[] simulating one point in time
     * @param Lanes - Passes in the reference to the Object array of Lanes
     * @param x - Passes in the position of the array
     *
     */

    void moveCars(Lane[] Lanes, int x) {

        if (Cars[length - 1] != null) {
            Cars[length - 1] = null;
        }

        for (int y = length - 2; y > 9; --y) {

            if (Cars[y] != null) {
                Cars[y + 1] = Cars[y];
                Cars[y] = null;
            }
        }

        if (stopOrGo.getGreen()) {

            for (int y = 9; y >= 0; y--) {

                if (Cars[y] != null) {
                    Cars[y + 1] = Cars[y];
                    Cars[y] = null;
                }
            }
        } else {

            for (int y = 8; y > -1; y--) {

                if (Cars[y] != null) {

                    if (Cars[y + 1] == null) {

                        Cars[y + 1] = Cars[y];
                        Cars[y] = null;
                    }

                    if ((x != (Lanes.length - 1)) && (getFlow() == Lanes[x + 1].getFlow())) {

                        if (((Lanes[x + 1].Cars[y]) == null) && (Lanes[x + 1].Cars[y + 1]) == null) {

                            Lanes[x + 1].Cars[y + 1] = Cars[y];
                            Cars[y] = null;
                        }
                    }

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

    /**
     * Generates an object Car with Car[] using probability set by user
     *
     */

    void bingCar() {

        if (Cars[0] == null) {

            if (Math.random() <= carProb) {

                Cars[0] = new Car();
            }
        }
    }
}
