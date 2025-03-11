package ui.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class AccountPanel extends JPanel {
    private Defaults defaults = Defaults.getDefaults();
    private PasswordVaultGUI passVault;

    public AccountPanel(PasswordVaultGUI passVault) {
        this.passVault = passVault;
        this.setBackground(Color.red);
        this.setBounds(0, 0, 250, 250);
        
        JLabel label = new JLabel();
        label.setText("New account screen");
        label.setFont(defaults.getFont());

        this.setLayout(new FlowLayout());
        this.add(label);
    }

}
