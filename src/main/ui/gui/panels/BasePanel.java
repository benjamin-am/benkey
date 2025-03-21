package ui.gui.panels;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

public class BasePanel extends Panel {

    private FlowLayout fl = new FlowLayout();
    private JLabel baseMessage;

    public BasePanel(PasswordVaultGUI passVault) {
        super(passVault);
        this.setLayout(fl);
        baseMessageInit();
        this.add(baseMessage);
    }

    private void baseMessageInit() {
        baseMessage = LabelFactory.createLabel("<html><p>Welcome " + passVault.getUsersName() 
                + ".<br/> You have " + passVault.getUser().totalAccounts() + " accounts for " 
                + passVault.getUser().totalWebsites() + " websites.</p></html>", 0, 0, 200, 300);
    }

    @Override
    public void refreshPanel() {
        this.remove(baseMessage);
        baseMessageInit();
        this.add(baseMessage);
        revalidate();
        repaint();
    }
}
