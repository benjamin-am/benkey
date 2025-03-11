package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
        this.setBackground(Color.red);
        this.setBounds(0, 0, 250, 250);
        
        JLabel label = new JLabel();
        label.setText("Welcome to benkey. Please login or create a new account.");
        label.setFont(defaults.getFont());

        buttonInit();

        this.setLayout(new FlowLayout());
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
        accountButton = new JButton("New Account");
        accountButton.setBounds(200, 200, 100, 50);
        accountButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getNewAccount()));
        accountButton.setFont(defaults.getFont());
    }

    // EFFECTS: sets up login button
    private void loginButtonInit() {
        loginButton = new JButton("Login");
        loginButton.setBounds(200, 100, 100, 50);
        loginButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getLogin()));
        loginButton.setFont(defaults.getFont());
    }
    

}
