package ui.gui.panels;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import ui.gui.ImageHandler;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

// Baes Panel for the Password vault after sign in.
public class BasePanel extends Panel {
    private FlowLayout fl = new FlowLayout();
    private JLabel baseMessage;

    // EFFECTS: initializes Base Panel construction, initial values set and components added
    public BasePanel(PasswordVaultGUI passVault) {
        super(passVault);
        this.setLayout(fl);
        baseMessageInit();
        this.add(baseMessage);
        ImageHandler image = new ImageHandler();
        this.add(new JLabel(image.getImage("images/vault.jpg", 400, 550)));
    }

    // MODIFIES: this
    // EFFECTS: creates the base message
    private void baseMessageInit() {
        baseMessage = LabelFactory.createLabel("<html><p>Welcome " + passVault.getUsersName() 
                + ".<br/> You have " + passVault.getUser().totalAccounts() + " accounts for " 
                + passVault.getUser().totalWebsites() + " websites.</p></html>", 0, 0, 200, 300);
    }

    // MODIFES: this
    // EFFECTS: refreshes panel
    @Override
    public void refreshPanel() {
        this.remove(baseMessage);
        baseMessageInit();
        this.add(baseMessage);
        revalidate();
        repaint();
    }
}
