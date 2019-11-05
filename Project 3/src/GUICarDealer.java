import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;
/***********************************************************************************************************************
 * CIS 162 Project 3
 * GUICarDealer extends JFrame and implements ActionListener
 * Maintains the GUI1024 for the red box rental store
 *
 * @author Keilani Bailey and Shayla Hinkley
 * @version Project 3: November 4th, 2019
 **********************************************************************************************************************/

public class GUICarDealer extends JFrame implements ActionListener
{
    /** Holds menu bar */
    private JMenuBar menus;

    /** menus in the menu bar */
    private JMenu fileMenu;
    private JMenu actionMenu;

    /** menu items in each of the menus */
    private JMenuItem openSerItem;
    private JMenuItem exitItem;
    private JMenuItem saveSerItem;
    private JMenuItem saveTextItem;
    private JMenuItem boughtCarItem;
    private JMenuItem boughtTruckItem;
    private JMenuItem saveSerText;
    private JMenuItem openSerText;
    private JMenuItem boughtScreen;
    private JMenuItem soldScreen;
    private JMenuItem overdueScreen;


    private JMenuItem soldItem;

    /** Holds the list engine */
    private ListEngine DList;
    private JPanel panel;

    /** Holds JListArea */
    private JTable jListArea;

    /** Scroll pane */
    private JScrollPane scrollList;

    /*****************************************************************
     *
     * A constructor that starts a new GUI1024 for the rental store
     *
     *****************************************************************/
    public GUICarDealer(){

        //adding menu bar and menu items
        menus = new JMenuBar();
        fileMenu = new JMenu("File");
        actionMenu = new JMenu("Action");
        openSerItem = new JMenuItem("Open File");
        exitItem = new JMenuItem("Exit");
        saveSerItem = new JMenuItem("Save File");
        saveSerText = new JMenuItem("Save Text");
        openSerText = new JMenuItem("Open Text");
        boughtScreen = new JMenuItem("Bought Screen");
        soldScreen = new JMenuItem("Sold Screen");
        overdueScreen = new JMenuItem("30 Days overDue Screen");
        boughtTruckItem = new JMenuItem("Bought Truck");
        boughtCarItem = new JMenuItem("Bought Car");

        soldItem = new JMenuItem("Sold Car or Truck");

        //adding items to bar
        fileMenu.add(openSerItem);
        fileMenu.add(saveSerItem);
        fileMenu.addSeparator();
        fileMenu.add(openSerText);
        fileMenu.add(saveSerText);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        fileMenu.addSeparator();
        fileMenu.add(boughtScreen);
        fileMenu.add(soldScreen);
        fileMenu.add(overdueScreen);
        actionMenu.add(boughtTruckItem);
        actionMenu.add(boughtCarItem);

        actionMenu.add(soldItem);

        menus.add(fileMenu);
        menus.add(actionMenu);

        //adding actionListener
        openSerItem.addActionListener(this);
        saveSerItem.addActionListener(this);
        exitItem.addActionListener(this);
        boughtTruckItem.addActionListener(this);
        boughtCarItem.addActionListener(this);
        soldItem.addActionListener(this);
        saveSerText.addActionListener(this);
        openSerText.addActionListener(this);
        boughtScreen.addActionListener(this);
        soldScreen.addActionListener(this);
        overdueScreen.addActionListener(this);

        setJMenuBar(menus);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        panel = new JPanel();
        DList = new ListEngine();
        DList.displayMode(1);
        jListArea = new JTable(DList);

        //jListArea.setDefaultRenderer(Object.class, new BorderColorRenderer());
        JScrollPane scrollList = new JScrollPane(jListArea);
        scrollList.setPreferredSize(new Dimension(800,300));
        panel.add(scrollList);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
        setSize(950,450);
    }

    /*****************************************************************
     * This method handles event-handling code for the GUI1024
     *
     * @param e - Holds the action event parameter
     *****************************************************************/
    public void actionPerformed(ActionEvent e) {

        Object comp = e.getSource();

        //save files
        if (saveSerItem == comp || saveSerText == comp) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showSaveDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                if (saveSerItem == e.getSource()) {
                    DList.saveDatabase(filename);
                }
                if (saveSerText == e.getSource()) {
                    DList.saveAsText(filename);
                }
            }
        }

        //open files
        if(openSerItem == comp || openSerText == comp) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if(status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                if(openSerItem == e.getSource()) {
                    DList.loadDatabase(filename);
                }
                if(openSerText == e.getSource()) {
                    DList.loadFromText(filename);
                }
            }
        }

        //MenuBar options
        if(e.getSource() == boughtTruckItem){
            Auto auto = new Truck();
            BoughtTruckDialog dialog = new BoughtTruckDialog(this, auto);
            if(dialog.getCloseStatus() == BoughtTruckDialog.OK){

                //only adds the auto to the list if these qualities are met
                if(auto.getBoughtCost() >= 0.00 && auto.getAutoName() != null && auto.getTrim() != null) {
                    DList.add(auto);
                }

                //error message to try again because incorrect fields
                else {
                    JOptionPane.showMessageDialog(null, "Incorrect fields found. Please try again.", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(e.getSource() == boughtCarItem) {

            Auto auto = new Car();
            BoughtCarDialog dialog = new BoughtCarDialog(this, auto);
            if(dialog.getCloseStatus() == BoughtCarDialog.OK) {
                DList.add(auto);
            }
        }

        //allows user to only sell auto from bought screen
        if (soldItem == e.getSource() && DList.currentMode() == 1) {
            try {
                int index = jListArea.getSelectedRow();
                Auto unit = DList.get(index);
                DList.remove(index);
                SoldOnDialog dialog = new SoldOnDialog(this, unit);
                JOptionPane.showMessageDialog(null, " For the sales person, be sure to thank " + unit.getNameOfBuyer() +
                        " \nfor buying the " + unit.getAutoName() + ", the price difference was:\t " + unit.getSoldBoughtCost(unit.getSoldPrice()) + " dollars.");
            } catch(IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Please select an auto from the list.", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }

        //error message if user tries to sell auto from any other screen but bought screen
        if(soldItem == e.getSource() && DList.currentMode() != 1) {
            try {
                throw new IllegalArgumentException();
            } catch(IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Must be on bought screen to sell an auto", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }

        //overdue screen button follows with actions
        if(e.getSource() == overdueScreen) {
            DList.displayMode(2);
            DList.fireTableStructureChanged();
        }

        //sold screen button follows with actions
        if(e.getSource() == soldScreen) {
            DList.displayMode(3);
            DList.fireTableStructureChanged();
        }

        //bought screen button follows with actions
        if(e.getSource() == boughtScreen) {
            DList.displayMode(1);
            DList.fireTableStructureChanged();
        }
   }

    public static void main(String[] args) {
        new GUICarDealer();
    }
}