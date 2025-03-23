package ui.gui.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ui.gui.PasswordVaultGUI;

// Abstract TableViewPanel for panels that have a table
public abstract class TableViewPanel extends Panel {
    protected String[] colNames;
    protected MainPanel main;
    protected JTable table;
    protected JScrollPane sp;
    protected GridBagConstraints gbc;
    protected HorizontalButtonPanel buttonPanel;
    
    // Constructor
    public TableViewPanel (PasswordVaultGUI passVault, MainPanel main) {
        super(passVault);
        this.main = main;
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

    }   

    // EFFECTS: converts a list to array for table view
    public abstract String[][] listToArray();

    // MODIFIES: this
    // EFFECTS: initialize buttons for panel
    public abstract void buttonInit();

    // MODIFIES: this
    // EFFECTS: refresh panel information
    public abstract void refreshPanel();

    // MODIFIES: this
    // EFFECTS: sets up table preferences and colours
    public void tablePreferences(JTable table) {
        table.setBackground(new Color(40, 44, 52)); 
        table.setForeground(Color.WHITE); 

        table.setSelectionBackground(new Color(60, 63, 65)); // Slightly lighter grey/blue
        table.setSelectionForeground(Color.WHITE);

        table.setGridColor(new Color(75, 80, 90));

        table.getTableHeader().setBackground(new Color(30, 33, 40));
        table.getTableHeader().setForeground(Color.WHITE);

        sp.getViewport().setBackground(new Color(40, 44, 52)); 
        sp.setBackground(new Color(40, 44, 52)); 
    }

}
