import java.text.DateFormat;
import java.util.GregorianCalendar;

/***********************************************************************************************************************
 * CIS 162 Project 3
 * Car class that extends auto and sets different aspects of the car automobile
 *
 * @author Keilani Bailey and Shayla Hinkley
 * @version Project 3: October 20th, 2019
 **********************************************************************************************************************/
public class Car extends Auto {

//    // add constructor
//    // add getter, setter methods
//    // Hint: You will need to override the getSoldBoughtCost function here (more info on this later in this document)

    /** trim package of the car */
    private String trim;

    /** car does or does not turbo package */
    private boolean turbo;

    /****************************************************************************************************************
     *Constructor
     *
     ****************************************************************************************************************/
    public Car() {
    }

    /****************************************************************************************************************
     *Constructor
     * @param boughtOn GregorianCalendar - date the car was bought on
     * @param name String - the name of the car
     ****************************************************************************************************************/
    public Car(GregorianCalendar boughtOn,  String name,
               String nameOfBuyer, String trim, boolean turbo) {
        super(boughtOn, name, nameOfBuyer);
        this.trim = trim;
        this.turbo = turbo;
    }

    /****************************************************************************************************************
     *Getter method for the cost of the car
     *
     * @return getCost type double - returns the cost of the car
     ****************************************************************************************************************/
    public double getCost() {
        return 42;
    }

    /****************************************************************************************************************
     *Getter method for the trim of the car
     *
     * @return getTrim type String - returns the trim of the car
     ****************************************************************************************************************/
    public String getTrim() {
        return trim;
    }

    /****************************************************************************************************************
     *Setter method for the trim of the car
     *
     * @param trim String - the trim package of the car
     ****************************************************************************************************************/
    public void setTrim(String trim) {
        this.trim = trim;
    }

    /****************************************************************************************************************
     *Method that returns if the car has the turbo package
     *
     * @return turbo type boolean - if the car has turbo
     ****************************************************************************************************************/
    public boolean isTurbo() {
        return turbo;
    }

    /****************************************************************************************************************
     *Setter method for turbo package of the car
     *
     * @param turbo boolean - if the car has turbo or not
     ****************************************************************************************************************/
    public void setTurbo(boolean turbo) {
        this.turbo = turbo;
    }

    /****************************************************************************************************************
     *toString method that lays out the aspects of the car
     *
     *
     * @return trim type String - the trim package of the car
     * @return turbo type boolean - if the car has turbo or not
     * @return autoName type String - the name of the car
     ****************************************************************************************************************/
    @Override
    public String toString() {
        return "Car    " +
                "trim='" + trim + '\'' +  "    " +
                ", turbo=" + turbo + "    " +
                ", autoName='" + autoName + '\'' + "    " +
                ' ';
    }

//    @Override
//    public double getBoughtCost() {
//       return
//    }
}