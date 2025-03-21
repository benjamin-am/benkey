package ui.gui.panels;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import ui.gui.ButtonFactory;
import ui.gui.PasswordVaultGUI;

public class MenuPanel extends Panel {
    BoxLayout boxLayout;
    JButton account;
    JButton passwords;
    JButton website;
    MainPanel main;

    public MenuPanel(PasswordVaultGUI passVault, MainPanel main) {
        super(passVault, 100, 500);
        this.main = main;
        boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        buttonInit();
    }

    private void buttonInit() {
        account = ButtonFactory.createButton("Accounts", 0, 0, 200, 50, false);
        account.addActionListener(e -> main.changeScreen(MainPanel.getAccount()));
        passwords = ButtonFactory.createButton("Passwords", 0, 0, 200, 50, false);
        website = ButtonFactory.createButton("Websites", 0, 0, 200, 50, false);
        addButton(account);
        addButton(passwords);
        addButton(website);
    }

    private void addButton(JButton button) {
        addSpace();
        this.add(button);
    }

    private void addSpace() {
        this.add(Box.createVerticalStrut(5)); 
    }

}
