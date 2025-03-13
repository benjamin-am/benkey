package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroPanel extends JPanel {
    private JButton loginButton;
    private JButton accountButton;
    private Defaults defaults = Defaults.getDefaults();
    private PasswordVaultGUI passVault;

    public IntroPanel(PasswordVaultGUI passVault) {
        this.passVault = passVault;
        this.setBackground(defaults.getBackgroundColor());
        this.setBounds(0, 0, 250, 250);
        
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
        accountButton = ButtonFactory.createButton("New Account", 200, 200, 100, 50);
        accountButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getNewAccount()));
    }

    // EFFECTS: sets up login button
    private void loginButtonInit() {
        loginButton = ButtonFactory.createButton("Login", 200, 100, 100, 50);
        loginButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getLogin()));
    }
    

}
