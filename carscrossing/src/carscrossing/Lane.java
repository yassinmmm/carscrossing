
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

    String Direction;
    int length;
    boolean carMax;

    public static void main(String[] args) {
        // create user input variables

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
        }
        // The reason for creating these variables is as individual
        // lanes are created they will span accross other lanes,
        // thus increasing their array length by the amount of lanes
        // they cross.

        int horizLanes  = (LanesWE + LanesEW);
        int vertLanes  = (LanesSN + LanesNS);
        int totalLanes  = (horizLanes + vertLanes);
        int nL = 1;

        for (int x=0; x<totalLanes ; ++x ){

            if(LanesSN>0){
                String i = String.
            }




        }







    }
}
