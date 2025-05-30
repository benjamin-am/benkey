package ui.gui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.User;
import ui.gui.ButtonFactory;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

// Main account view panel
public class AccountPanel extends Panel {
    private String[] colNames;
    private MainPanel main;
    private HorizontalButtonPanel buttonPanel;
    private JTable table;
    private JScrollPane sp;
    private JLabel message;
    private GridBagConstraints gbc;

    // EFFECTS: initializes Account Panel construction with GridBagLayout
    public AccountPanel(PasswordVaultGUI passVault, MainPanel main) {
        super(passVault);
        this.colNames = new String[]{"Username", "Website", "Password"};
        this.main = main;
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        
        table = new JTable(accountsToArray(), colNames);
        sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(600, 300)); 
        tablePreferences(table);

        setMessage();
        
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sp, gbc);
        gbc.gridy = 1;
        this.add(message, gbc);
        buttonInit();
    }

    // MODIFIES: this
    // EFFECTS: sets the main message in the panel, checks if user has accounts first
    private void setMessage() {
        if (passVault.getUser().userHasNoAccounts()) {
            message = LabelFactory.createLabel("You have no accounts! Make one!");
        } else {
            message = LabelFactory.createLabel("<html><p>You have " 
                + passVault.getUser().totalAccounts() 
                + " accounts for " 
                + passVault.getUser().totalWebsites() 
                + " websites.<br/> Would you like to modify any accounts?</p></html>");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up table preferences and colours
    private void tablePreferences(JTable table) {
        table.setBackground(new Color(40, 44, 52)); 
        table.setForeground(Color.WHITE); 
        
        table.setAutoCreateRowSorter(true);
        table.getRowSorter().toggleSortOrder(1);

        table.setSelectionBackground(new Color(60, 63, 65)); 
        table.setSelectionForeground(Color.WHITE);

        table.setGridColor(new Color(75, 80, 90));

        table.getTableHeader().setBackground(new Color(30, 33, 40));
        table.getTableHeader().setForeground(Color.WHITE);

        sp.getViewport().setBackground(new Color(40, 44, 52)); 
        sp.setBackground(new Color(40, 44, 52)); 
    }

    // MODIFIES: this
    // EFFECTS: refreshes panel after a modification
    @Override
    public void refreshPanel() {
        table.setModel(new DefaultTableModel(accountsToArray(), colNames));
        sp.setPreferredSize(new Dimension(600, 300)); 
        message.setText("<html><p>You have " + passVault.getUser().totalAccounts() 
                + " accounts for " 
                + passVault.getUser().totalWebsites() 
                + " websites.<br/> Would you like to modify any accounts?</p></html>");

        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: set up buttons
    private void buttonInit() {
        JButton account = ButtonFactory.createButton("Add", 0, 0, 200, 50, false);
        account.addActionListener(e -> main.changeScreen(MainPanel.getAdd()));

        JButton back = ButtonFactory.createButton("Back", 0, 0, 200, 50, false);
        back.addActionListener(e -> main.changeScreen(MainPanel.getBase()));

        JButton remove = ButtonFactory.createButton("Remove", 0, 0, 200, 50, false);
        remove.addActionListener(e -> main.changeScreen(MainPanel.getRemove()));

        buttonPanel = new HorizontalButtonPanel(passVault, List.of(account, remove, back));
        gbc.gridy = 2;
        this.add(buttonPanel, gbc);
    }

    // ATTRIBUTION: https://coderanch.com/t/685520/java/Populate-JTable-ArrayList
    // EFFECTS: transforms list from User into an array for viewing
    private String[][] accountsToArray() {
        User user = passVault.getUser();
        List<Account> accounts = user.getListOfAccounts();
        String[][] arr = new String[accounts.size()][colNames.length];

        
        int i = 0;
        for (Account account : accounts) {
            String pass = account.getPassword().getPassword();
            String username = account.getUsername();
            String website = account.getWebsite().getName();
            arr[i][0] = username;
            arr[i][1] = website;
            arr[i][2] = pass;
            i++;
        }
        return arr;
    }
}
