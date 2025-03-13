package ui.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccountPanel extends JPanel {
    private Defaults defaults = Defaults.getDefaults();
    private PasswordVaultGUI passVault;
    private JButton backButton;


    public AccountPanel(PasswordVaultGUI passVault) {
        this.passVault = passVault;
        this.setBackground(defaults.getBackgroundColor());
        this.setBounds(0, 0, 250, 250);
        
        JLabel label = new JLabel();
        label.setText("New account screen");
        label.setFont(defaults.getFont());

        this.setLayout(new FlowLayout());
        this.add(label);
        backButtonInit();
        this.add(backButton);
    }

    // EFFECTS: sets up back button
    private void backButtonInit() {
        backButton = ButtonFactory.createButton("back", 200, 100, 100, 50);
        backButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getIntro()));
    }

}
