/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package carscrossing;

import java.util.Scanner;

/**
 *
 * @author Pro
 */
public class Crossing {


    public static String screenOut(Lane[] Lanes, int vValue, int hValue) {

        // this is going to be huge....challenge time :D
        String buildStringOut = "";
        String leftV = "          |";
        String rightV = "|          \n";
        int vCrossCount = hValue;

        // Need to construct each line individually stating with top-half
        // of vertical lanes.
        for (int y = hValue + 19; y > hValue + 9; y--) {

            buildStringOut += leftV;

            for (int x = 0; x < Lanes.length; x++) {

                if (Lanes[x].getFlow() == 1) {

                    if (Lanes[x].Cars()[y] != null) {

                        buildStringOut += "C";
                    } else {
                        buildStringOut += " ";
                    }
                }
            }
            buildStringOut += rightV;
        }

        // top of horizontal lanes has no lights as is only one way
        buildStringOut += "----------|";

        // i couldn't find a more efficient way for this...
        for (int k = 0; k < vValue; k++) {

            buildStringOut += " ";
        }

        buildStringOut += "|----------\n";
        // horizontal lanes construction
        // loop cycle lanes[]
        for (int x = 0; x < Lanes.length; x++) {

            // check for direction of lane
            if (Lanes[x].getFlow() == 3) {

                // loop through cars array of horizontal lane up to lights
                for (int y = 0; y < 10; y++) {

                    // check for car
                    if (Lanes[x].Cars()[y] != null) {

                        buildStringOut += "c";
                    } else {
                        buildStringOut += " ";
                    }
                }

                // Lights output for horizontal after lights if green

                if (Lanes[x].stopOrGo().getGreen()) {

                    buildStringOut += " ";

                    for (int y = 10; y < 10 + vValue; y++) {

                        // check for car
                        if (Lanes[x].Cars()[y] != null) {

                            buildStringOut += "c";
                        } else {
                            buildStringOut += " ";
                        }
                    }
                }

                // lights output for horizontal after lights if red

                if (!Lanes[x].stopOrGo().getGreen()) {
                    buildStringOut += "|";

                    for (int v = 0; v < Lanes.length; v++) {

                        if (Lanes[v].getFlow() == 1) {

                            if (Lanes[v].Cars()[9 + vCrossCount] != null) {

                                buildStringOut += "C";
                            }

                            if (Lanes[x].Cars()[10 + v] != null) {

                                buildStringOut += "c";
                            }

                            if ((Lanes[v].Cars()[9 + vCrossCount] == null) && (Lanes[x].Cars()[10 + v] == null)) {

                                buildStringOut += " ";
                            }
                        }
                    }
                }
                // next line is need to represent possible input of lights
                // for opposite direction
                buildStringOut += " ";
                // Horizontal lane after lights
                for (int y = (10 + vValue); y < Lanes[x].Cars().length; y++) {

                    // check for car
                    if (Lanes[x].Cars()[y] != null) {

                        buildStringOut += "c";
                    } else {
                        buildStringOut += " ";
                    }
                }
                buildStringOut += "\n";
                vCrossCount -= 1;
            }
        }
        // build concatanation of bottom of horizontal lanes
        // with lights included
        buildStringOut += "----------|";
        for (int x = 0; x < Lanes.length; x++) {

            if (Lanes[x].getFlow() == 1) {

                if (Lanes[x].stopOrGo().getGreen() == false) {

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

                    if (Lanes[x].Cars()[y] != null) {

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
        int cycle = 0;

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
            if ((menuSelect == 5) || (menuSelect == 6)) {
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
                        Lanes[x].setLength(H_street + 20, V_street);
                        laneCount += 1;
                    }
                    laneCount = 1;

                    for (int x = V_street; x < totalLanes; x++) {

                        String hName = "H_street" + Integer.toString(laneCount);
                        Lanes[x] = new Lane();
                        Lanes[x].setName(hName);
                        Lanes[x].setFlow(3);
                        Lanes[x].setCarProb(chanceCarH_street);
                        Lanes[x].setLength(V_street + 20, H_street);
                        laneCount += 1;
                    }
                    pressFive = true;
                }

                int cycleCount = 1;

                if (menuSelect == 6) {

                    while (menuSelect == 6) {

                        System.out.println("Please enter number of cycles for simulation [min 1, max 10]: ");
                        Catch = keyboard.next();

                        try {
                            cycleCount = Integer.parseInt(Catch);
                        } catch (Exception e) {
                            cycleCount = 11;
                        }
                        if ((cycleCount > 0) && (cycleCount < 11)) {
                            break;
                        }
                        System.out.println("Please enter a valid input value");
                    }
                }

                for (int cyc = 0; cyc < cycleCount; cyc++) {

                    // Separate loop must be constructed for change of lights
                    // for serious errors could occur if a car changes to
                    // a lane that is "in another time zone"

                    for (int x = 0; x < totalLanes; x++) {

                        Lanes[x].stopOrGo().plusDelayCount();

                        if (cycle % 8 == 0) {

                            Lanes[x].changeLights();
                        }

                        if ((Lanes[x].stopOrGo().getGreen() == false) && (Lanes[x].stopOrGo().getAmberDelay() == Lanes[x].stopOrGo().getDelayCount())) {

                            Lanes[x].stopOrGo().setGreen(true);
                        }
                    }

                    for (int x = 0; x < totalLanes; x++) {

                        Lanes[x].moveCars(Lanes, x);
                        Lanes[x].bingCar();
                    }
                    cycle += 1;
                    System.out.println(screenOut(Lanes, V_street, H_street) + "Cycle:" + cycle);
                }
            }
        }
    }

}
