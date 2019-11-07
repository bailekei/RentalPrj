import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/***********************************************************************************************************************
 * CIS 162 Project 3
 * ListEngine class that extends AbstractTableModel and implements TableModel
 * Helper class in retrieving data from the auto list to display in the table
 *
 * @author Keilani Bailey and Shayla Hinkley
 * @version Project 3: October 20th, 2019
 **********************************************************************************************************************/
public class ListEngine extends AbstractTableModel {

    /** master array list that holds all the autos */
    private ArrayList<Auto> listAutos;

    /** temporary array list that holds data needed for the GUI */
    private ArrayList<Auto> tempList;

    /** names for the columns in the bought mode */
    private String[] columnNamesBought = {"Auto Name", "Bought Cost",
            "Bought Date", "Trim Package ", "Four by Four", "Turbo"};

    /** names for the columns in the sold mode */
    private String[] soldOnColumns = {"Auto Name", "Bought Cost",
            "Bought Date", "Buyer's Name", "Sold For", "Sold On"};

    /** names for the columns in the overdue mode*/
    private String[] overdueColumns = {"Auto Name", "Bought Cost",
            "Bought Date", "Days Overdue"};

    //integer used to establish sold on display mode
    public static final int SOLDON_VIEW = 3;

    //integer used to establish overdue display mode
    public static final  int OVERDUE_VIEW = 2;

    //integer used to establish bought on display mode
    public static final int BOUGHTON_VIEW = 1;

    //the current display mode
    public int currentView = 1;

    private Auto auto;


    /****************************************************************************************************************
     *This method creates the correct column labels depending on what display mode the user is in
     *
     * @param col int - the column of the table
     ****************************************************************************************************************/
    @Override
    public String getColumnName(int col) {

        //gives the column names if bought on view
        if(currentView == BOUGHTON_VIEW) {
            return columnNamesBought[col];
        }

        //gives the column names if the view is overdue view
        if(currentView == OVERDUE_VIEW) {
            return overdueColumns[col];
        }

        //gives the column names if the view is sold on view
        if(currentView == SOLDON_VIEW) {
            return soldOnColumns[col];
        }
        return columnNamesBought[col];
    }

    /****************************************************************************************************************
     *Constructor that establishes the master auto list
     *
     ****************************************************************************************************************/
    public ListEngine() {
        super();
        listAutos = new ArrayList<Auto>();
        createList();
    }

    /****************************************************************************************************************
     *Method that removes an auto from a chosen index
     *
     * @param i int - the index that the user wants to remove the auto from
     ****************************************************************************************************************/
    public void remove(int i) {

        //removes an auto from the list and updates the table
        tempList.remove(i);
        fireTableDataChanged();
    }

    /****************************************************************************************************************
     *Method that adds an auto to the list and signals that the table has been changed
     *
     * @param a Auto - the auto that the user wants to add to the master list
     ****************************************************************************************************************/
    public void add(Auto a) {

        //adds the auto to the master list
        listAutos.add(a);

        //refreshes the table
        displayMode(currentView);
        fireTableDataChanged();
    }

    /****************************************************************************************************************
     *Method that gets the auto that at a given index in the arraylist
     *
     * @param i int - the index that you want to pull the auto from
     * @return Auto - the auto in the given index
     ****************************************************************************************************************/
    public Auto get(int i) {

        //if the screen is the bought screen
        if(currentView == BOUGHTON_VIEW) {
            return tempList.get(i);
        }

        //if the screen is the overdue screen
        if(currentView == OVERDUE_VIEW) {
            return tempList.get(i);
        }

        //if the screen is the sold on screen
        if(currentView == SOLDON_VIEW) {
            return tempList.get(i);
        }
        return listAutos.get(i);
    }

    /****************************************************************************************************************
     *Method that returns the size of the arraylist
     *
     * @return int size
     ****************************************************************************************************************/
    public int getSize() {

        //if the screen is the bought screen
        if(currentView == BOUGHTON_VIEW) {
            return tempList.size();
        }

        //if the screen is the overdue screen
        if(currentView == OVERDUE_VIEW) {
            return tempList.size();
        }

        //if the screen is the sold screen
        if(currentView == SOLDON_VIEW) {
            return tempList.size();
        }
        return listAutos.size();
    }

    /****************************************************************************************************************
     *Method that returns the size of the rows based on how many items are in the arraylist
     *
     * @return int - the number of rows
     ****************************************************************************************************************/
    @Override
    public int getRowCount() {

        //if the screen is the bought screen
        if(currentView == BOUGHTON_VIEW) {
            return tempList.size();
        }

        //if the screen is the sold screen
        if(currentView == SOLDON_VIEW) {
            return tempList.size();
        }

        //if the the screen is the overdue screen
        if(currentView == OVERDUE_VIEW) {
            return tempList.size();
        }
        return listAutos.size();
    }

    /****************************************************************************************************************
     *Method that returns the column length based of the length of the array of column list names
     *
     * @return int - the number of columns
     ****************************************************************************************************************/
    @Override
    public int getColumnCount() {

        //if the screen is the bought screen
        if(currentView == BOUGHTON_VIEW) {
            return columnNamesBought.length;
        }

        //if the screen is the sold on screen
        if(currentView == SOLDON_VIEW) {
            return soldOnColumns.length;
        }

        //if the screen is the overdue screen
        if(currentView == OVERDUE_VIEW) {
            return overdueColumns.length;
        }
        return columnNamesBought.length;
    }

    /****************************************************************************************************************
     *Method that puts each element in the given row and column in the table
     *
     * @param row int - the row of the table or index of the arraylist
     * @param col int - the column of the table
     *
     * @return data of the arraylist corresponding to the given column
     ****************************************************************************************************************/
    @Override
    public Object getValueAt(int row, int col) {

        //getting the values to put in the screen for the bought screen
        if(currentView == BOUGHTON_VIEW) {
            switch (col) {
                case 0:
                    return (tempList.get(row).getAutoName());

                case 1:
                    return (tempList.get(row).getBoughtCost());

                case 2:
                    return (DateFormat.getDateInstance(DateFormat.SHORT)
                            .format(tempList.get(row).getBoughtOn().getTime()));

                case 3:
                    return (tempList.get(row).getTrim());

                case 4:
                case 5:
                    if (tempList.get(row) instanceof Truck)
                        if (col == 4)
                            return (((Truck) tempList.get(row)).isFourByFour());
                        else
                            return "";

                    else {
                        if (col == 5)
                            return (((Car) tempList.get(row)).isTurbo());
                        else
                            return "";
                    }
                default:
                    throw new RuntimeException("JTable row,col out of range: " + row + " " + col);
            }
        }

        //getting the values to put in the screen for overdue screen
        if(currentView == OVERDUE_VIEW) {
            switch(col) {
                case 0:
                    return (tempList.get(row).getAutoName());

                case 1:
                    return (tempList.get(row).getBoughtCost());

                case 2:
                    return (DateFormat.getDateInstance(DateFormat.SHORT)
                        .format(tempList.get(row).getBoughtOn().getTime()));

                case 3:
                    return (numberOverdueDays(tempList.get(row).getBoughtOn()));

                default:
                    return null;
            }
        }


        //getting the values to put in the screen for the sold screen
        if(currentView == SOLDON_VIEW) {
            switch(col) {
                case 0:
                    return (tempList.get(row).getAutoName());

                case 1:
                    return (tempList.get(row).getBoughtCost());

                case 2:
                    return (DateFormat.getDateInstance(DateFormat.SHORT)
                            .format(tempList.get(row).getBoughtOn().getTime()));

                case 3:
                    return (tempList.get(row).getNameOfBuyer());

                case 4:
                    return (tempList.get(row).getSoldPrice());

                case 5:
                    return (DateFormat.getDateInstance(DateFormat.SHORT)
                            .format(tempList.get(row).getSoldOn().getTime()));

                default:
                    return null;
            }
        }
        return null;
    }

    /****************************************************************************************************************
     *Method that allows the user to save the database under a given file name
     *
     * @param filename String - the file name that the user wants to save the file under
     ****************************************************************************************************************/
    public void saveDatabase(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(listAutos);
            os.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in saving db");
        }
    }

    /****************************************************************************************************************
     *Method that allows the user to load a database from a file
     *
     * @param filename String - the name of the file that the user wants to load the database from
     ****************************************************************************************************************/
    public void loadDatabase(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fis);

            listAutos = (ArrayList<Auto>) is.readObject();
            fireTableDataChanged();
            is.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error in loading db");
        }
    }

    /****************************************************************************************************************
     * Method for saving data as a text file
     *
     * @param filename Name of the file where the data is being loaded from
     ****************************************************************************************************************/
    public boolean saveAsText(String filename) {
       try {
           FileWriter writer = new FileWriter(filename);

           for(Auto auto: listAutos) {
               writer.write(auto.toString() + "\n");
           }

           writer.close();

       } catch (IOException ex) {
           ex.printStackTrace();
       }
       return false;
    }

    /****************************************************************************************************************
     * Method for laading the data as a text file
     *
     * @param filename Name of the file where the data is being stored in
     *****************************************************************************************************************/
    public void loadFromText(String filename) {
        listAutos = new ArrayList<Auto>();

        try {
            BufferedReader inFS = new BufferedReader(new FileReader(filename));

            String str;

            while((str = inFS.readLine()) != null) {
                String[] values = str.split(",");

                if(values[0] == "Truck") {
                    Auto auto = processTruck(values);
                    listAutos.add(auto);
                }
                if(values[0] == "Car") {
                    Auto auto = processCar(values);
                    listAutos.add(auto);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /****************************************************************************************************************
     *Method that tests given parts of the program
     *
     ****************************************************************************************************************/
    public void createList() {

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar temp1 = new GregorianCalendar();
        GregorianCalendar temp2 = new GregorianCalendar();
        GregorianCalendar temp3 = new GregorianCalendar();
        GregorianCalendar temp4 = new GregorianCalendar();
        GregorianCalendar temp5 = new GregorianCalendar();
        GregorianCalendar temp6 = new GregorianCalendar();

        try {
            Date d1 = df.parse("3/20/2019");
            temp1.setTime(d1);
            Date d2 = df.parse("9/20/2019");
            temp2.setTime(d2);
            Date d3 = df.parse("12/20/2018");
            temp3.setTime(d3);
            Date d4 = df.parse("9/20/2019");
            temp4.setTime(d4);
            Date d5 = df.parse("1/20/2010");
            temp5.setTime(d5);
            Date d6 = df.parse("10/20/2019");
            temp6.setTime(d6);


            Car Car1 = new Car(temp3, "Outback", null, "LX", false);
            Car Car2 = new Car(temp2, "Chevy", null, "EX", false);
            Car Car3 = new Car(temp6, "Focus", null, "EX", true);
            Truck Truck1 = new Truck(temp4, "F150", null, "LX", false);
            Truck Truck2 = new Truck(temp1, "F250", null, "LX", false);
            Truck Truck3 = new Truck(temp5, "F350", null, "EX", true);

            add(Car1);
            add(Car2);
            add(Car3);
            add(Truck1);
            add(Truck2);
            add(Truck3);
        } catch (ParseException e) {
            throw new RuntimeException("Error in testing, creation of list");
        }
    }

    /****************************************************************************************************************
     *Method that changes the display mode of the GUI and sorts the lists accordingly
     *
     * @param view int - the number of the display screen
     ****************************************************************************************************************/
    public void displayMode(int view) {
        currentView = view;

        switch(currentView) {

            //case that the view is bought on
            case BOUGHTON_VIEW:
                tempList = (ArrayList<Auto>)listAutos.stream().filter(auto -> auto.soldOn == null).collect(Collectors.toList());
                tempList.sort(Comparator.comparing(o -> o.getBoughtOn()));
            break;

            //case that view is overdue
            case OVERDUE_VIEW:
                tempList.clear();
                GregorianCalendar boughtDate;
                for(Auto auto : listAutos) {
                    boughtDate = auto.getBoughtOn();
                    if(numberOverdueDays(boughtDate) != -1) {
                        tempList.add(auto);
                    }
                }
                tempList.sort((Auto x1, Auto x2) -> numberOverdueDays(x2.getBoughtOn()) - numberOverdueDays(x1.getBoughtOn()));
            break;

            //case that view is sold on
            case SOLDON_VIEW:
                tempList = (ArrayList<Auto>)listAutos.stream().filter(auto -> auto.soldOn != null).collect(Collectors.toList());
                tempList.sort(Comparator.comparing(Auto::getNameOfBuyer));
            break;
        }
        fireTableDataChanged();
    }

    /****************************************************************************************************************
     *Method that tells whether the cell is editable or not
     *
     * @param i int - the row or index of the list
     * @param i1 int - the column of the table
     ****************************************************************************************************************/
    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    /****************************************************************************************************************
     *Method that returns the current mode of the screen
     *
     * @return currentView int - the current view of the screen
     ****************************************************************************************************************/
    public int currentMode() {
        return currentView;
    }


    /****************************************************************************************************************
     *Method that collects the overdue days
     *
     * @return numDays int - the number of overdue days
     ****************************************************************************************************************/
    public int numberOverdueDays(GregorianCalendar n) {
        GregorianCalendar compareDate;
        compareDate = n;

        //getting today's date
        Date todayDate = Calendar.getInstance().getTime();

        //converting Date to Gregorian Calendar
        GregorianCalendar tempDate = new GregorianCalendar();
        tempDate.setTime(todayDate);

        //calculating the differences in days
        long todayDateinM = Calendar.getInstance().getTimeInMillis();
        long compareDateinM = compareDate.getTimeInMillis();
        long diffinM = Math.abs(todayDateinM - compareDateinM);
        float diffInDays = (diffinM / (1000 * 60 * 60 * 24));

        int numOfDays = (int) Math.abs(diffInDays);

        //if the number of days is greater than or equal to 90 temp date comes after
        if(numOfDays >= 90 && tempDate.after(compareDate)) {
            return numOfDays;
        }
        return -1;
    }


    /****************************************************************************************************************
     *Method that compares bought and sold on dates
     *
     * @param boughtDate GregorianCalendar - the date the auto was bought
     * @param soldDate GregorianCalendar - the date that the auto was sold
     *
     * @return int 1 - sold date comes after bought date
     * @return int -1 sold date comes before bought date
     ****************************************************************************************************************/
    public int compareDates( GregorianCalendar boughtDate,GregorianCalendar soldDate) {
        GregorianCalendar sDate = soldDate;
        GregorianCalendar bDate = boughtDate;

        try {
            if (soldDate.after(boughtDate)) {
                return 1;
            } else {
                return -1;
            }
        }catch (NullPointerException ex) {

        }
        return 0;
    }

    /****************************************************************************************************************
     *Method that is used in the save text file to enter data for a car auto and add to list
     *
     * @param values String Array - an array of auto data
     * @return car Car - a car auto
     ****************************************************************************************************************/

    public Car processCar(String [] values) {
        Car car = new Car();
        car.setAutoName(values[1]);

        try {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            GregorianCalendar temp = new GregorianCalendar();
            Date d = null;
            d = df.parse(values[2]);
            temp.setTime(d);
            car.setBoughtOn(temp);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        car.setBoughtCost(Double.parseDouble(values[3]));
        car.setTrim(values[4]);

        try {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            GregorianCalendar temp = new GregorianCalendar();
            Date d = null;
            if (values[5] != "null"){
                d = df.parse(values[5]);
                temp.setTime(d);
                car.setSoldOn(temp);
            } else {
                car.setSoldOn(null);
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        car.setNameOfBuyer(values[6]);
        car.setSoldPrice(Double.parseDouble(values[7]));
        car.setTurbo(Boolean.parseBoolean(values[8]));

        return car;
    }

    /****************************************************************************************************************
     *Method that is used in the save text file to enter data for a truck auto and add to list
     *
     * @param values String Array - an array of auto data
     * @return truck Truck - a truck auto
     ****************************************************************************************************************/
    public Truck processTruck(String [] values) {
        Truck truck = new Truck();

        try {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            GregorianCalendar temp = new GregorianCalendar();
            Date d = null;
            d = df.parse(values[2]);
            temp.setTime(d);
            truck.setBoughtOn(temp);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        truck.setBoughtCost(Double.parseDouble(values[3]));
        truck.setTrim(values[4]);

        try {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            GregorianCalendar temp = new GregorianCalendar();
            Date d = null;
            if(values[5] != "null") {
                d = df.parse(values[5]);
                temp.setTime(d);
                truck.setSoldOn(temp);
            } else {
                truck.setSoldOn(null);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        truck.setNameOfBuyer(values[6]);
        truck.setSoldPrice(Double.parseDouble(values[7]));
        truck.setFourByFour(Boolean.parseBoolean(values[8]));

        return truck;
    }


}
