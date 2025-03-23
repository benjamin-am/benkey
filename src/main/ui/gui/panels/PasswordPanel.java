package ui.gui.panels;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JLabel;

import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

// Simple Password view of a user account
public class PasswordPanel extends Panel {
    private JLabel textLabel;

    // Initializes Password Panel
    public PasswordPanel(PasswordVaultGUI passVault, MainPanel main) {
        super(passVault);
        this.setLayout(new FlowLayout());
        textInit();
    }

    // ATTRIBUTION: https://www.geeksforgeeks.org/stringbuilder-class-in-java-with-examples/
    // MODIFIES: this
    // EFFECTS: creates the message with all passwords and count of distinct
    private void textInit() {
        if (passVault.getUser().userHasNoAccounts()) {
            textLabel = LabelFactory.createLabel("You have no accounts thus no passwords!");
        } else {
            List<String> passwords = passVault.getUser().listAllPasswords();
        
            StringBuilder text = new StringBuilder("<html><p>You have " + passwords.size() + " distinct passwords.<br/>"
                                                    + "Your passwords are: <br/>"
                                                    + "<br/>");
            for (String password : passwords) {
                text.append(password).append("<br/>");  
            }
            text.append("</p></html>");
            textLabel = LabelFactory.createLabel(text.toString());
        }

        this.add(textLabel);
    }

    // MODIFES: this
    // EFFECTS: refreshes panel
    @Override
    public void refreshPanel() {
        this.remove(textLabel);
        textInit();
        revalidate();
        repaint();
    }
}
