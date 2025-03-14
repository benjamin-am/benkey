package ui.gui;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

public class IntroPanel extends Panel {
    private JButton loginButton;
    private JButton accountButton;

    public IntroPanel(PasswordVaultGUI passVault) {
        super(passVault);
        
        JLabel label = LabelFactory.createLabel("<html>Welcome to benkey.<br/> Please login or create a new account.</html>");
        
        buttonInit();

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.add(label);
        this.add(accountButton);
        this.add(loginButton);
    }

    private void buttonInit() {
        loginButtonInit();
        newAccountButtonInit();
    }

    // EFFECTS: sets up new account button
    private void newAccountButtonInit() {
        accountButton = ButtonFactory.createButton("New Account", 200, 200, 200, 50, false);
        accountButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getNewAccount()));
    }

    // EFFECTS: sets up login button
    private void loginButtonInit() {
        loginButton = ButtonFactory.createButton("Login", 200, 100, 200, 50, false);
        loginButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getLogin()));
    }
    

}
