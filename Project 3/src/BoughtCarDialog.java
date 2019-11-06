import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BoughtCarDialog extends JDialog implements ActionListener {

    //JTextFields
    private JTextField txtName;
    private JTextField txtDate;
    private JTextField txtTrimPackage;
    private JTextField txtTurbo;
    private JTextField txtCost;

    //JButtons
    private JButton okButton;
    private JButton cancelButton;

    /** helper instance variable in checking turbo */
    private int checkTurbo;

    /** ComboBox string at the top of the dialog box*/
    private JComboBox<String> combobox;

    /** checks the close status of the dialog */
    private int closeStatus;

    /** instance variable for auto class */
    private Auto auto;

    //integers set to OK and Cancel
    static final int OK = 0;
    static final int CANCEL = 1;

    /*********************************************************
     Instantiate a Custom Dialog as 'modal' and wait for the
     user to provide data and click on a button.

     @param parent reference to the JFrame application
     @param auto an instantiated object to be filled with data
     *********************************************************/

    public BoughtCarDialog(JFrame parent, Auto auto) {
        // call parent and create a 'modal' dialog
        super(parent, true);

        this.auto = auto;
        setTitle("Bought dialog box");
        closeStatus = CANCEL;
        setSize(400,200);

        // prevent user from closing window
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // instantiate and display two text fields
        txtName = new JTextField(30);
        txtDate = new JTextField(15);
        txtTurbo = new JTextField(15);
        txtTrimPackage = new JTextField(15);
        txtCost = new JTextField(15);

        String[] autoStrings = { "Car"};

        //putting in today's date in the text box
        Date boughtDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = dateFormat.format(boughtDate);

        combobox = new JComboBox<>(autoStrings);
        txtDate.setText(strDate);
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(7,2));

        textPanel.add(new JLabel(""));
        textPanel.add(combobox);
        textPanel.add(new JLabel(""));
        textPanel.add(new JLabel(""));

        textPanel.add(new JLabel("Name of Car: "));
        textPanel.add(txtName);
        textPanel.add(new JLabel("bought on Date: "));
        textPanel.add(txtDate);
        textPanel.add(new JLabel("Trim Package (EX or LX)"));
        textPanel.add(txtTrimPackage);
        textPanel.add(new JLabel("Turbo (True or false)"));
        textPanel.add(txtTurbo);
        textPanel.add(new JLabel("Amount Paid for"));
        textPanel.add(txtCost);

        getContentPane().add(textPanel, BorderLayout.CENTER);

        // Instantiate and display two buttons
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setVisible (true);
    }

    /**************************************************************
     Respond to either button clicks
     @param e the action event that was just fired
     **************************************************************/
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();

        // if OK clicked the fill the object
        if (button == okButton) {
            // save the information in the object
            closeStatus = OK;
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            GregorianCalendar temp = new GregorianCalendar();
            Date d = null;


            //if error is caught in the text date throw error
            try {
                d = df.parse(txtDate.getText());
                temp.setTime(d);
                auto.setBoughtOn(temp);
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(null, "Date format must be in the form MM/dd/yyyy", "Alert", JOptionPane.ERROR_MESSAGE);
            }

            //sets the auto name
            try {
                if(txtName.getText().equalsIgnoreCase("")) {
                    throw new IllegalArgumentException();
                } else {
                    auto.setAutoName(txtName.getText());
                }
            } catch (IllegalArgumentException ex1) {
                JOptionPane.showMessageDialog(null, "Please enter name of buyer", "Alert", JOptionPane.ERROR_MESSAGE);
            }


            //throws error if invalid input in the trim package
            //sets the trim package
            try {
                if(txtTrimPackage.getText().equalsIgnoreCase("LX")) {
                    ((Car) auto).setTrim("LX");
                } else if(txtTrimPackage.getText().equalsIgnoreCase("EX")) {
                    ((Car) auto).setTrim("EX");
                }
                else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e1) {
                JOptionPane.showMessageDialog(null, "Trim must be EX or LX", "Alert", JOptionPane.ERROR_MESSAGE);
            }


            //throws error if four or four text line is not true or false
            //sets the turbo
            try {
                if (txtTurbo.getText().equalsIgnoreCase("true")) {
                    ((Car) auto).setTurbo(true);
                    checkTurbo = 1;
                } else if (txtTurbo.getText().equalsIgnoreCase("false")) {
                    ((Car) auto).setTurbo(false);
                    checkTurbo = 1;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException ex) {
                checkTurbo = 0;
                JOptionPane.showMessageDialog(null, "Four by four must be true or false", "Alert", JOptionPane.ERROR_MESSAGE);
            }

            //throws error is the cost is less than 0
            //sets the bought cost
            try {
                if(txtCost.getText() == null) {
                    throw new IllegalArgumentException();
                }
                auto.setBoughtCost(Double.parseDouble(txtCost.getText()));

            }catch (IllegalArgumentException e2) {
                auto.setBoughtCost(-1);
                JOptionPane.showMessageDialog(null, "Price cannot be negative. Price cannot be blank", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }
        dispose();
    }

    /**************************************************************
     Return a String to let the caller know which button
     was clicked

     @return an int representing the option OK or CANCEL
     **************************************************************/
    public int getCloseStatus(){
        return closeStatus;
    }

    /**************************************************************
     Getter method to get if the turbo input is valid

     @return an int representing valid or non valid input
     **************************************************************/
    public int getCheckTurbo() {
        return checkTurbo;
    }

    /**************************************************************
     Setter method to see if the turbo input is valid

     @param n int - represents the valid or non valid input
     **************************************************************/
    public void setCheckTurbo(int n) {
        checkTurbo = n;
    }
}


