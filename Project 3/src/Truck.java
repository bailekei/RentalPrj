import java.text.DateFormat;
import java.util.GregorianCalendar;
/***********************************************************************************************************************
 * CIS 162 Project 3
 * Truck class that extends Auto and creates the aspects of the truck automobile
 *
 * @author Keilani Bailey and Shayla Hinkley
 * @version Project 3: October 20th, 2019
 **********************************************************************************************************************/
public class Truck extends Auto {


    private boolean FourByFour;

    /****************************************************************************************************************
     *Constructor
     *
     ****************************************************************************************************************/
    public Truck() {
        super();
    }

    /****************************************************************************************************************
     *Constructor
     *
     * @param boughtOn GregorianCalendar - the date the truck was bought on
     * @param name String - the name of the truck
     * @param nameOfBuyer String - the name of the buyer
     * @param trimPackage String - the name of the trim package on the truck
     * @param fourByFour boolean - if the truck is a four by four
     ****************************************************************************************************************/
    public Truck(GregorianCalendar boughtOn, String name,
                 String nameOfBuyer, String trimPackage, boolean fourByFour) {
        super(boughtOn, name, nameOfBuyer);
        trim = trimPackage;
        FourByFour = fourByFour;
    }

    /****************************************************************************************************************
     *Getter method for the cost of the truck
     *
     * @return 42 type double - the cost of the truck
     ****************************************************************************************************************/
    public double getCost() {

        return 42;
    }

    /****************************************************************************************************************
     *Method that tells whether the truck is a four by four or not
     *
     * @return fourByFour type boolean - the truck is a four by four or not
     ****************************************************************************************************************/
    public boolean isFourByFour() {
        return FourByFour;
    }

    /****************************************************************************************************************
     *Setter method for if the truck is a four by four or not
     *
     * @param fourByFour boolean - if the truck is a four by four
     ****************************************************************************************************************/
    public void setFourByFour(boolean fourByFour) {

        FourByFour = fourByFour;
    }

    /****************************************************************************************************************
     *toString method that tells the aspects of the truck
     *
     * @return trim type String - the trim package of the truck
     * @return FourbyFour type boolean - if the truck is a four by four
     * @return autoName type String - the name of the truck
     ****************************************************************************************************************/
    @Override
    public String toString() {
        return "Truck     " +
                "trim=" + trim + "    " +
                ", FourByFour=" + FourByFour + "     " +
                ", autoName='" + autoName + '\'' + "     " +
                ' ';
    }
}
