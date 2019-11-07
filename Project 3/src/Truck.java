import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
     * @return String the aspects of the truck
     ****************************************************************************************************************/
    @Override
    public String toString() {

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        String boughtDate = "";

        boughtDate = DateFormat.getDateInstance(DateFormat.SHORT).format(getBoughtOn().getTime());

        String soldDate = null;



        if(getSoldOn() != null) {
            soldDate = DateFormat.getDateInstance(DateFormat.SHORT).format(getBoughtOn().getTime());
            return ("Truck," + getAutoName() + "," + boughtDate + "," + getBoughtCost() + "," + getTrim()
                    + "," + soldDate + "," + getNameOfBuyer() + "," + getSoldPrice() + "," + isFourByFour());
        } else {
            return ("Truck," + getAutoName() + "," + boughtDate + "," + getBoughtCost() + "," + getTrim()
                    + "," + "null" + "," + getNameOfBuyer() + "," + getSoldPrice() + "," + isFourByFour());
        }



    }
}
