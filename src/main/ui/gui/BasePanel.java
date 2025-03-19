package ui.gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;

public class BasePanel extends Panel {

    private FlowLayout fl = new FlowLayout();

    public BasePanel(PasswordVaultGUI passVault) {
        super(passVault);
        this.setLayout(fl);
        JLabel baseMessage = LabelFactory.createLabel("<html><p>Welcome " + passVault.getUsersName() 
                + ".<br/> You have " + passVault.getUser().totalAccounts() + " accounts for " 
                + passVault.getUser().totalWebsites() + " websites.</p></html>", 0, 0, 200, 300);
        this.add(baseMessage);
    }
}
