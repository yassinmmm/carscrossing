package carscrossing;

import java.util.Scanner;
/*
 *Lane object class
 */

/**
 *
 * @author Pro
 */
public class Lane {
    //Lane needs definative direction for future builds.

    private Car[] Cars;
    // The flow of traffic shal be represented as SN=1, NS=2, WE=3, EW=4
    private int flow;
    private String name;
    private int length;
    private double carProb;
    private Lights stopOrGo = new Lights();

    String getName() {
        return name;
    }

    void setName(String value) {
        name = value;
    }

    int getLength() {
        return length;
    }

    void setLength(int value) {
        length = value;
        Cars = new Car[value];
    }

    double getCarProb() {
        return carProb;
    }

    void setCarProb(double value) {
        carProb = value;
    }

    void setFlow(int value) {
        if (value == 3) {
            stopOrGo.setGo(true);
        }
        flow = value;
    }

    int getFlow() {
        return flow;
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
        if (Lanes[x].stopOrGo.getGo()) {

            // The lights are green go as normal
            for (int y = 9; y >= 0; --y) {

                if (Cars[y] != null) {
                    Cars[y + 1] = Cars[y];
                    Cars[y] = null;
                }
            }
        } else {
            // for the following for loop i start the count at 8 thus unsuring
            // that a cascade of stopping will occur at the lights
            for (int y = 8; y >= 0; --y) {

                if (Cars[y] != null) {

                    if (Cars[y + 1] != null) {

                        Cars[y + 1] = Cars[y];
                        Cars[y] = null;
                    }

                    // Overtaking exception only nessesary before lights as cars
                    // are assumed to have uniform speed before an after lights.

                    // check for next lane to move into
                    if (Lanes[x].getFlow() == Lanes[x + 1].getFlow()) {

                        // I decided from the check to both the same position
                        // and the next possition in the transition lane to
                        // simulate a realistic occurance.
                        if (((Lanes[x + 1].Cars[y]) != null) && (Lanes[x + 1].Cars[y + 1]) != null) {

                            Lanes[x + 1].Cars[y + 1] = Cars[y];
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

    public static String screenOut(Lane[] Lanes, int value) {


        // this is going to be huge....challenge time :D
        String buildStringOut = "";
        String leftV = "          |";
        String rightV = "|          \n";


        // Need to construct each line individually stating with top-half
        // of vertical lanes.
        for (int y = value + 19; y > value + 9; y--) {

            buildStringOut += leftV;

            for (int x = 0; x < Lanes.length; x++) {

                if (Lanes[x].getFlow() == 1) {

                    if (Lanes[x].Cars[y] != null) {

                        buildStringOut += "C";
                    } else {
                        buildStringOut += " ";
                    }
                }
            }
            buildStringOut += rightV;
        }

        // top of horizontal lanes has no lights
        buildStringOut += "---------|  |----------\n";
        // horizontal lanes construction
        // loop cycle lanes[]
        for (int x = 0; x < Lanes.length; x++) {

            // check for direction of lane
            if (Lanes[x].getFlow() == 3) {

                // loop through cars array of horizontal lane up to lights
                for (int y = 0; y < 9; y++) {

                    // check for car
                    if (Lanes[x].Cars[y] != null) {

                        buildStringOut += "c";

                    } else {
                        buildStringOut += " ";
                    }
                }

                // Lights output for horizontal
                for (int z = 0; z < Lanes.length; z++) {

                    if (Lanes[z].getFlow() == 3) {

                        if (Lanes[z].stopOrGo.getGo() == false) {

                            buildStringOut += "-";

                        } else {
                            buildStringOut += " ";
                        }
                    }
                }

                // Horizontal lane after lights
                for (int y = 10; y < Lanes[x].Cars.length; y++) {

                    // check for car
                    if (Lanes[x].Cars[y] != null) {

                        buildStringOut += "c";

                    } else {
                        buildStringOut += " ";
                    }
                }
            }
        }
        // build concatanation of bottom of horizontal lanes
        // with lights included
        buildStringOut += "----------|\n";
        for (int x = 0; x < Lanes.length; x++) {

            if (Lanes[x].getFlow() == 1) {

                if (Lanes[x].stopOrGo.getGo() == false) {

                    buildStringOut += "-";

                } else {
                    buildStringOut += " ";
                }
            }
        }
        buildStringOut += "|----------\n";
        //bottom half of vertical construction
        for (int y = 9; y > -1; y--) {

            // could be made into a function
            buildStringOut += leftV;

            for (int x = 0; x < Lanes.length; x++) {

                if (Lanes[x].getFlow() == 1) {

                    if (Lanes[x].Cars[y] != null) {

                        buildStringOut += "C";
                    } else {
                        buildStringOut += " ";

                    }
                }
            }
            buildStringOut += rightV;

        }
        return buildStringOut;
    }

    public static void main(String[] args) {

        String Catch = "";
        int menuSelect = 0;
        int H_street = 1;
        int V_street = 1;
        double chanceCarH_street = 0.5;
        double chanceCarV_street = 0.5;
        int totalLanes = 0;
        boolean pressFive = false;
        Lane[] Lanes = null;

        while (menuSelect != 7) {

            // I assumed option 7 was nessesary to add to the menu.

            System.out.println("=== MENU ===\n1. Set Number of horizontal lanes"
                    + " (H-street) [min 1, max 3]\n2. Set Number of vertical lanes"
                    + " (V-street) [min 1, max 4]\n3. Set Probability of a car"
                    + " entering H-street [min 0, max 1]\n4. Set Probability of a"
                    + " car entering V-street [min 0, max 1]\n5. Run one simulation"
                    + " cycle\n6. Set and run number of simulation cycles"
                    + " [min 1, max 10]\n7. Exit\nEnter your choice>");

            Scanner keyboard = new Scanner(System.in);
            Catch = keyboard.next();

            try {
                menuSelect = Integer.parseInt(Catch);

            } catch (Exception e) {
                menuSelect = 0;
            }

            if ((menuSelect <= 0) || (menuSelect >= 8)) {
                System.out.println("Please enter a valid menu input value");
            }

            if (menuSelect == 1) {

                while (menuSelect == 1) {

                    // Get number of horizontal streets as H_street from user.
                    System.out.println("Please enter number of horizontal lanes[min 1, max 3]: ");
                    Catch = keyboard.next();

                    try {
                        H_street = Integer.parseInt(Catch);
                    } catch (Exception e) {
                        H_street = 4;
                    }
                    if ((H_street > 0) && (H_street < 4)) {
                        pressFive = false;
                        break;
                    }
                    System.out.println("Please enter a valid input value");
                }
            }

            if (menuSelect == 2) {

                while (menuSelect == 2) {

                    // Get number of vertical streets as H_street from user.
                    System.out.println("Please enter number of vertical lanes[min 1, max 4]: ");
                    Catch = keyboard.next();
                    try {
                        V_street = Integer.parseInt(Catch);
                    } catch (Exception e) {
                        V_street = 5;
                    }
                    if ((V_street > 0) && (V_street < 5)) {
                        pressFive = false;
                        break;
                    }
                    System.out.println("Please enter a valid input value");
                }
            }

            if (menuSelect == 3) {

                while (menuSelect == 3) {

                    // Get value for random calculation from user.
                    System.out.println("Please enter a value for the"
                            + "Probability of a car entering "
                            + "a horizontal street [min 0, max 1]: ");
                    Catch = keyboard.next();
                    try {
                        chanceCarH_street = Double.parseDouble(Catch);
                    } catch (Exception e) {
                        chanceCarH_street = 2;
                    }
                    if ((chanceCarH_street > 0) && (chanceCarH_street < 1)) {
                        pressFive = false;
                        break;
                    }
                    System.out.println("Please enter a valid input value");
                }
            }

            if (menuSelect == 4) {

                while (menuSelect == 4) {
                    // Get value for random calculation from user.
                    System.out.println("Please enter a value for the"
                            + "Probability of a car entering "
                            + "a vertical street [min 0, max 1]: ");
                    Catch = keyboard.next();
                    try {
                        chanceCarV_street = Double.parseDouble(Catch);
                    } catch (Exception e) {
                        chanceCarV_street = 2;
                    }
                    if ((chanceCarV_street > 0) && (chanceCarV_street < 1)) {
                        pressFive = false;
                        break;
                    }
                    System.out.println("Please enter a valid input value");
                }
            }
            if (menuSelect == 5) {// or menuSelect==6~~if menuselect... {
                // I assume that one cycle refers to a small point in time
                // in wich a car can be generated by a lane or
                // move to the next available position
                if (pressFive == false) {
                    totalLanes = (H_street + V_street);
                    Lanes = new Lane[totalLanes];
                    int laneCount = 1;

                    for (int x = 0; x < V_street; x++) {

                        String vName = "V_street" + Integer.toString(laneCount);
                        Lanes[x] = new Lane();
                        Lanes[x].setName(vName);
                        Lanes[x].setFlow(1);
                        Lanes[x].setCarProb(chanceCarV_street);
                        Lanes[x].setLength(H_street + 20);
                        laneCount += 1;
                    }
                    laneCount = 1;

                    for (int x = V_street; x < totalLanes; x++) {

                        String hName = "H_street" + Integer.toString(laneCount);
                        Lanes[x] = new Lane();
                        Lanes[x].setName(hName);
                        Lanes[x].setFlow(3);
                        Lanes[x].setCarProb(chanceCarH_street);
                        Lanes[x].setLength(V_street + 20);
                        laneCount += 1;
                    }
                    pressFive = true;
                }
                // now it kows if it needs to build and does it, next -> moving
                // First stage is to move all existing cars second is to create
                // new cars in lanes.

                for (int x = 0; x < totalLanes; x++) {


                    System.out.println(Lanes[x].getName());
                    System.out.println(Lanes[x].Cars.length);

                    for (int y = 0; y < Lanes[x].Cars.length; y++) {

                        if (Lanes[x].Cars[y] != null) {

                            System.out.println(Lanes[x].Cars[y].isExists());

                        }



                    }







                    Lanes[x].moveCars(Lanes, x);
                    Lanes[x].bingCar();


                }
            } // Screenout here
            System.out.println(screenOut(Lanes, H_street));
        }
    }
}
