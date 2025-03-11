package ui.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


public class LoginPanel extends JPanel {
    private Defaults defaults = Defaults.getDefaults();
    private PasswordVaultGUI passVault;
    private JPasswordField password;
    private JButton backButton;


    public LoginPanel(PasswordVaultGUI passVault) {
        password = new JPasswordField();
        this.passVault = passVault;
        this.setBackground(Color.red);
        this.setBounds(0, 0, 250, 250);
        
        JLabel label = new JLabel();
        label.setText("Login screen");
        label.setFont(defaults.getFont());

        this.setLayout(new FlowLayout());
        this.add(label);
        backButtonInit();
        this.add(backButton);
    }

    // EFFECTS: sets up login button
    private void backButtonInit() {
        backButton = new JButton("Back");
        backButton.setBounds(200, 100, 100, 50);
        backButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getIntro()));
        backButton.setFont(defaults.getFont());
    }

}
