import java.io.Serializable;
import java.util.GregorianCalendar;
/***********************************************************************************************************************
 * CIS 162 Project 3
 * Auto class that sets different aspects of the automobile
 *
 * @author Keilani Bailey and Shayla Hinkley
 * @version Project 3: October 20th, 2019
 **********************************************************************************************************************/
public abstract class Auto implements Serializable {

    /** unique identifier used in deserialization of object to ensure that loaded class is
     *compatible with the serialized object */
    private static final long serialVersionUID = 1L;

    /** The date the dealer bought the Auto */
    protected GregorianCalendar boughtOn;

    /** The date the Auto was sold on to the customer/buyer */
    protected GregorianCalendar soldOn;

    /**  The Name of the Auto e.g., Ford F510 Pontiac Vibe */
    protected String autoName;

    /** The Name of the buyer */
    protected String nameOfBuyer;

    /** The price of the Auto, i.e., what the dealer paid */
    protected double boughtCost;

    /** The price of the Auto, i.e., what the dealer sold the auto for */
    protected double soldPrice;

    /** The trim package of the auto, i.e., 1x, ex, sl */
    protected String trim;

    public Auto() {
    }

    public abstract double getCost();

    /****************************************************************************************************************
     *Constructor
     * @param boughtOn GregorianCalendar - the date that the dealer bought the auto
     * @param name String - the name of the auto
     * @param nameOfBuyer String - the name of the buyer of the auto
     ****************************************************************************************************************/
    public Auto(GregorianCalendar boughtOn, String name, String nameOfBuyer) {
        this.boughtOn = boughtOn;
        this.autoName = name;
        this.nameOfBuyer = nameOfBuyer;
    }

    /****************************************************************************************************************
     *Getter method for serialVersionUID
     *
     * @return serialVersionUID type long - identifier used in the deserialization of the object
     ****************************************************************************************************************/
    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    /****************************************************************************************************************
     *Getter method for the date that the dealer bought the auto on
     *
     * @return boughtOn type GregorianCalendar - date that the dealer bought the auto
     ****************************************************************************************************************/
    public GregorianCalendar getBoughtOn() {
        return boughtOn;
    }

    /****************************************************************************************************************
     *Setter method for the date that the dealer bought the auto
     *
     * @param boughtOn GregorianCalendar - the date that the dealer bought the auto
     ****************************************************************************************************************/
    public void setBoughtOn(GregorianCalendar boughtOn) {
        this.boughtOn = boughtOn;
    }

    /****************************************************************************************************************
     * Getter method for the date that the auto was sold on
     *
     * @return soldOn type GregorianCalendar - the date that the auto was sold on
     ****************************************************************************************************************/
    public GregorianCalendar getSoldOn() {
        return soldOn;
    }

    /****************************************************************************************************************
     * Setter method for the date that the auto was sold on
     *
     * @param soldOn GregorianCalendar - the date the auto was sold on
     ****************************************************************************************************************/
    public void setSoldOn(GregorianCalendar soldOn) {
        this.soldOn = soldOn;
    }

    /****************************************************************************************************************
     *Getter method for the price the auto was sold for
     *
     * @return soldPrice type double - the price the auto was bought for
     ****************************************************************************************************************/
    public double getSoldPrice() {
        return soldPrice;
    }

    /****************************************************************************************************************
     *Setter method for the price the auto was sold for
     *
     * @param soldPrice double - the price the auto was sold for
     ****************************************************************************************************************/
    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    /****************************************************************************************************************
     *Getter method for the name of the auto
     *
     * @return autoName type String - the name of the auto
     ****************************************************************************************************************/
    public String getAutoName() {
        return autoName;
    }

    /****************************************************************************************************************
     *Setter method for the auto name
     *
     * @param autoName String - the name of the auto
     ****************************************************************************************************************/
    public void setAutoName(String autoName) {
        this.autoName = autoName;
    }

    /****************************************************************************************************************
     *Getter method for the name of the buyer
     *
     * @return nameOfBuyer type String - the name of the buyer of the auto
     ****************************************************************************************************************/
    public String getNameOfBuyer() {
        return nameOfBuyer;
    }

    /****************************************************************************************************************
     *Setter method for the name of the buyer
     *
     * @param nameOfBuyer String - the name of the buyer of the auto
     ****************************************************************************************************************/
    public void setNameOfBuyer(String nameOfBuyer) {
        this.nameOfBuyer = nameOfBuyer;
    }

    /****************************************************************************************************************
     *Getter method for the price the dealer bought the auto for
     *
     * @return boughtCost type double - the price the dealer bought the auto for
     ****************************************************************************************************************/
    public double getBoughtCost() {
        return boughtCost;
    }

    /****************************************************************************************************************
     *Setter method for the price the dealer bought the auto for
     *
     * @param boughtCost double - the price the dealer bought the auto for
     ****************************************************************************************************************/
    public void setBoughtCost(double boughtCost) {
        this.boughtCost = boughtCost;
    }

    /****************************************************************************************************************
     *Getter method for the trim package of the auto
     *
     * @return String trim  - the trim package of the auto
     ****************************************************************************************************************/
    public String getTrim() {
        return trim;
    }

    /****************************************************************************************************************
     *Setter method for the trim of the package of the auto
     *
     * @param trim String - the trim of the package of the auto
     ****************************************************************************************************************/
    public void setTrim(String trim) {
        this.trim = trim;
    }
}