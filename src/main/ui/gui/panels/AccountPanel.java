package ui.gui.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Account;
import model.User;
import ui.Saving;
import ui.gui.ButtonFactory;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

public class AccountPanel extends Panel {
    private String[] colNames = {"Username", "Website", "Password"};
    private MainPanel main;
    private HorizontalButtonPanel buttonPanel;
    private JTable table;
    private JScrollPane sp;
    private JLabel message;
    private GridBagConstraints c;

    public AccountPanel(PasswordVaultGUI passVault, MainPanel main) {
        super(passVault);
        this.main = main;
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        // this.setPreferredSize(getPreferredSize());
        
        table = new JTable(accountsToArray(), colNames);
        // table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(600, 300)); 

        message = LabelFactory.createLabel("<html><p>You have " + passVault.getUser().totalAccounts() + " accounts for " 
                + passVault.getUser().totalWebsites() + " websites.<br/> Would you like to modify any accounts?</p></html>");
        
        c.gridx = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        this.add(sp, c);
        c.gridy = 1;
        this.add(message, c);
        buttonInit();
    }

    @Override
    public void refreshPanel() {
        table.setModel(new javax.swing.table.DefaultTableModel(accountsToArray(), colNames));
        sp.setPreferredSize(new Dimension(600, 300)); // Adjust the size as needed
        message.setText("<html><p>You have " + passVault.getUser().totalAccounts() +
                " accounts for " + passVault.getUser().totalWebsites() +
                " websites.<br/> Would you like to modify any accounts?</p></html>");

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
        c.gridy = 2;
        this.add(buttonPanel, c);
    }

    // ATTRIBUTION: https://coderanch.com/t/685520/java/Populate-JTable-ArrayList
    private String[][] accountsToArray() {
        User user = passVault.getUser();
        List<Account> accounts = user.getListOfAccounts();
        String[][] arr = new String[accounts.size()][colNames.length];

        
        int i = 0;
        for (Account account : accounts) {
            String pass = account.getPassword().getPassword();
            String username = account.getUsername();
            String website = account.getWebsite().getUrl();
            arr[i][0] = username;
            arr[i][1] = website;
            arr[i][2] = pass;
            i++;
        }
        return arr;
    }
}
