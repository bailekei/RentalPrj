import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
     * @return String the aspects of the car
     ****************************************************************************************************************/
    @Override
    public String toString() {

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        String boughtDate = "";

        boughtDate = DateFormat.getDateInstance(DateFormat.SHORT).format(getBoughtOn().getTime());

        String soldDate = null;



        if(getSoldOn() != null) {
            soldDate = DateFormat.getDateInstance(DateFormat.SHORT).format(getSoldOn().getTime());
            return ("Car," + getAutoName() + "," + boughtDate + "," + getBoughtCost() + "," + getTrim()
                    + "," + soldDate + "," + getNameOfBuyer() + "," + getSoldPrice() + "," + isTurbo());

        } else {
            return ("Car," + getAutoName() + "," + boughtDate + "," + getBoughtCost() + "," + getTrim()
                    + "," + "null" + "," + getNameOfBuyer() + "," + getSoldPrice() + "," + isTurbo());
        }

    }
}