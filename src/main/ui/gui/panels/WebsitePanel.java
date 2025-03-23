package ui.gui.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.User;
import model.Website;
import ui.gui.ButtonFactory;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

public class WebsitePanel extends TableViewPanel {
    private JLabel message;
    private String text;

    // Constructs a website table panel
    // ATTRIBUTION: https://coderanch.com/t/346577/java/JTable-set-default-auto-sorted
    public WebsitePanel(PasswordVaultGUI passVault, MainPanel main) {
        super(passVault, main);
        this.colNames = new String[]{"Website", "Number Accounts"};
        table = new JTable(listToArray(), colNames);
        sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(600, 300)); 
        super.tablePreferences(table);
        table.setAutoCreateRowSorter(true);
        table.getRowSorter().toggleSortOrder(1);
        text = "<html><p>You have " 
                + passVault.getUser().totalAccounts() 
                + " accounts for " 
                + passVault.getUser().totalWebsites() 
                + " websites.<br/> You have the most accounts for "
                + passVault.getUser().maxAccountsWebsite().getName() + " with " 
                + passVault.getUser().numberOfAccountsOnWebsite(passVault.getUser().maxAccountsWebsite()) 
                + " accounts.</p></html>";
        message = LabelFactory.createLabel(text);
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

    // ATTRIBUTION: https://coderanch.com/t/685520/java/Populate-JTable-ArrayList
    // EFFECTS: converts list of websites to an array for table view
    @Override
    public String[][] listToArray() {
        User user = passVault.getUser();
        List<Website> websites = user.listAllWebsites();
        String[][] arr = new String[websites.size()][colNames.length];

        int i = 0;
        for (Website website : websites) {
            String websiteName = website.getName();
            arr[i][0] = websiteName;
            arr[i][1] = String.valueOf(user.numberOfAccountsOnWebsite(website));
            i++;
        }
        return arr;
    }

    // MODIFIES: this
    // EFFECTS: button initalization
    @Override
    public void buttonInit() {
        JButton back = ButtonFactory.createButton("Back", 0, 0, 200, 50, false);
        back.addActionListener(e -> main.changeScreen(MainPanel.getBase()));

        buttonPanel = new HorizontalButtonPanel(passVault, List.of(back));
        gbc.gridy = 2;
        this.add(buttonPanel, gbc);
    }

    // MODIFIES: this
    // EFFECTS: refreshes the panel information
    @Override
    public void refreshPanel() {
        table.setModel(new DefaultTableModel(listToArray(), colNames));
        sp.setPreferredSize(new Dimension(600, 300)); 
        message.setText(text);
        revalidate();
        repaint();
    }

}
