package carscrossing;

import java.util.Random;
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
    private Lights stopOrGo;

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

        // This small if is to remove the last car at the start of th new move
        if (Cars[length - 1] != null) {
            Cars[length - 1] = null;
        }

        // Loop is started at second last array now that last is clear and moves
        // all cars up to lights
        for (int y = length - 2; y > 10; --y) {

            if (Cars[y] != null) {
                Cars[y + 1] = Cars[y];
                Cars[y] = null;
            }
        }

        // All cars before lights will need follow different rules
        if (Lanes[x].stopOrGo.getGo()) {

            // The lights are green go as normal
            for (int y = 9; y == 0; --y) {

                if (Cars[y] != null) {
                    Cars[y + 1] = Cars[y];
                    Cars[y] = null;
                }
            }
        } else {
            // for the following for loop i start the count at 8 thus unsuring
            // that a cascade of stopping will occur at the lights
            for (int y = 8; y == 0; --y) {

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
                            Cars[y]=null;
                        }
                    }
                }
            }
        }
    }

    void bingCar() {

        if (Math.random() <= carProb) {

            Cars[0] = new Car();
        }
    }


    public static String screenOut(Lane[] Lanes, int value) {

        // this is going to be huge....challenge time :D
        String buildStringOut = "";
        String leftV = "          |";
        String rightV = "|          ";


        // Need to construct each line individually stating with top-half
        // of vertical lanes.
        for (int y = value + 19; y == value + 9; --y) {

            buildStringOut += leftV;

            for (int x = 0; x < Lanes.length; ++x) {

                if (Lanes[x].getFlow() == 1) {

                    if (Lanes[x].Cars[y] != null) {

                        buildStringOut += "C";
                    } else {
                        buildStringOut += " ";
                    }

                } // Because the construcion of Lanes[] consists of vertical lanes
                // then horizontal, i am able to do this.
                else {
                    buildStringOut += rightV;
                    break;
                }
            }
        }
        // begin building of horizontal lanes
        // need to add "--------|--|-----------"

        // horizontal lanes construction
        // loop cycle lanes[]
        for (int x = 0; x < Lanes.length; ++x) {

            // check for direction of lane
            if (Lanes[x].getFlow() == 3) {

                // loop through cars array of horizontal lane
                for (int y = 0; y < (Lanes[x].Cars.length); ++y) {

                    // check for car
                    if (Lanes[x].Cars[y] != null) {

                        buildStringOut += "C";

                    } else {
                        buildStringOut += " ";
                    }
                }
            }
        }

        // need to add "--------|--|-----------"

        //bottom half of vertical construction
        for (int y = 9; y > -1; --y) {

            // could be made into a function
            buildStringOut += leftV;

            for (int x = 0; x < Lanes.length; ++x) {

                if (Lanes[x].getFlow() == 1) {

                    if (Lanes[x].Cars[y] != null) {

                        buildStringOut += "C";
                    } else {
                        buildStringOut += " ";

                    }
                }
            }
        }
        return buildStringOut;
    }


    public static void main(String[] args) {

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
            menuSelect = keyboard.nextInt();

            if (menuSelect == 1) {
                // Get number of horizontal streets as H_street from user.
                System.out.println("Please enter number of horizontal lanes: ");
                H_street = keyboard.nextInt();
                pressFive = false;
            }

            if (menuSelect == 2) {
                // Get number of vertical streets as H_street from user.
                System.out.println("Please enter number of vertical lanes: ");
                V_street = keyboard.nextInt();
                pressFive = false;
            }

            if (menuSelect == 3) {
                // Get value for random calculation from user.
                System.out.println("Please enter a value for the"
                        + "Probability of a car entering "
                        + "a horizontal street [min 0, max 1]: ");
                chanceCarH_street = keyboard.nextFloat();
                pressFive = false;
            }

            if (menuSelect == 4) {
                // Get value for random calculation from user.
                System.out.println("Please enter a value for the"
                        + "Probability of a car entering "
                        + "a vertical street [min 0, max 1]: ");
                chanceCarV_street = keyboard.nextFloat();
                pressFive = false;
            }
            if (menuSelect == 5) {// or menuSelect==6~~if menuselect... {
                // I assume that one cycle refers to a small point in time
                // in wich a car can be generated by a lane or
                // move to the next available position
                if (pressFive == false) {
                    totalLanes = (H_street + V_street);
                    Lanes = new Lane[totalLanes];
                    int laneCount = 1;

                    for (int x = 0; x < V_street; ++x) {
                        String i = "V_street" + Integer.toString(laneCount);
                        Lanes[x] = new Lane();
                        Lanes[x].setName(i);
                        Lanes[x].setFlow(1);
                        Lanes[x].setCarProb(chanceCarV_street);
                        Lanes[x].setLength(H_street + 20);
                        laneCount += 1;
                    }

                    laneCount = 1;

                    for (int x = V_street; x < totalLanes; ++x) {
                        String i = "H_street" + Integer.toString(laneCount);
                        Lanes[x] = new Lane();
                        Lanes[x].setName(i);
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

                for (int x = 0; x < totalLanes; ++x) {

                    Lanes[x].moveCars(Lanes, x);
                    Lanes[x].bingCar();
                }

                // Screenout here

            }
        }
    }
}
// This code can be used later for the input of direction
// of a particualer lane.
/*
String[] inputArray = new String[4];
inputArray[0]="South to North: ";
inputArray[1]="North to South: ";
inputArray[2]="West to East: ";
inputArray[3]="East to West: ";
int LanesSN;
int LanesNS;
int LanesWE;
int LanesEW;

for (int k=0; k<4; ++k) {

Scanner keyboard = new Scanner(System.in);
// Ask user for input
System.out.println("Please enter the numer of lanes running"
+ inputArray[k]);
if (k==0) {
LanesSN = keyboard.nextInt();
}
if (k==1) {
LanesNS = keyboard.nextInt();
}
if (k==2) {
LanesWE = keyboard.nextInt();
}
if (k==3) {
LanesEW = keyboard.nextInt();
}
 *
 *
 */
// The reason for creating these variables is as individual
// lanes are created they will span accross other lanes,
// thus increasing their array length by the amount of lanes
// they cross.
/*
int horizLanes;

Scanner keyboard = new Scanner(System.in);
// Ask user for input
System.out.println("Please enter the numer of lanes running horizontal");

horizLanes = keyboard.nextInt();



int vertLanes;

Scanner keyboard = new Scanner(System.in);
// Ask user for input
System.out.println("Please enter the numer of lanes running vertical");

horizLanes = keyboard.nextInt();



int totalLanes  = (horizLanes + vertLanes);
int nL = 1;

for (int x=0; x<totalLanes ; ++x ){

if(LanesSN>0){
String i = String.
}




}







} */
