/*
 * Car object Class
 */

package carscrossing;

/**
 *
 * @author Pro
 */
public class Car {
    //Attribute for lane object to check in case lane
    //before lights becomes full and causes problems
boolean LaneMaxCar;

//Getter method for LaneMaxCar attribute
boolean getLaneMAxCar(){
    return LaneMaxCar;
}

//Setter  method for LaneMaxCar attribute
void setLaneMaxCar(boolean value){
    LaneMaxCar = value;
}


//
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
