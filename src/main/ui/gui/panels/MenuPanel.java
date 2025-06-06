package ui.gui.panels;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import ui.Saving;
import ui.gui.ButtonFactory;
import ui.gui.PasswordVaultGUI;

// Buttons to select what panel to show in the Main Panel
public class MenuPanel extends Panel {
    private BoxLayout boxLayout;
    private JButton account;
    private JButton passwords;
    private JButton website;
    private JButton logout;
    private MainPanel main;
    private JButton save;

    // EFFECTS: initializes Menu Panel construction, initial values set and components added
    public MenuPanel(PasswordVaultGUI passVault, MainPanel main) {
        super(passVault, 100, 500);
        this.main = main;
        boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        buttonInit();
    }

    // MODIFIES: this
    // EFFECTS: initialize and add buttons to panel
    private void buttonInit() {
        account = ButtonFactory.createButton("Accounts", 0, 0, 200, 50, false);
        account.addActionListener(e -> main.changeScreen(MainPanel.getAccount()));
        passwords = ButtonFactory.createButton("Passwords", 0, 0, 200, 50, false);
        passwords.addActionListener(e -> main.changeScreen(MainPanel.getPassword()));
        website = ButtonFactory.createButton("Websites", 0, 0, 200, 50, false);
        website.addActionListener(e -> main.changeScreen(MainPanel.getWebsite()));
        save = ButtonFactory.createButton("Save", 0, 0, 200, 50, false);
        save.addActionListener(e -> Saving.saveAccount(passVault.getUser()));
        logout = ButtonFactory.createButton("Logout", 0, 0, 200, 50, false);
        logout.addActionListener(e -> passVault.logout());
        addButton(account);
        addButton(passwords);
        addButton(website);
        addButton(save);
        addButton(logout);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to panel
    private void addButton(JButton button) {
        addSpace();
        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds space to panel
    private void addSpace() {
        this.add(Box.createVerticalStrut(5)); 
    }

}
