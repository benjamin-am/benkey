package ui.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Account;
import model.User;

public class AccountPanel extends Panel {
    private String[] colNames = {"Username", "Website", "Password"};

    public AccountPanel(PasswordVaultGUI passVault) {
        super(passVault);
        this.setLayout(new GridLayout(2, 1));
        this.setPreferredSize(getPreferredSize());
        JTable table = new JTable(accountsToArray(), colNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // table.setRowHeight(25); 
        // table.setPreferredScrollableViewportSize(new Dimension(500, 200));  
        JScrollPane sp = new JScrollPane(table);
        JLabel message = LabelFactory.createLabel("<html><p>You have " + passVault.getUser().totalAccounts() + " accounts for " 
                + passVault.getUser().totalWebsites() + " websites.<br/> Would you like to modify any accounts?</p></html>", 0, 0, 150, 100);
        this.add(sp);
        this.add(message);
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
