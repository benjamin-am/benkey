package ui.gui;

import javax.swing.JPanel;

public abstract class Panel extends JPanel {
    protected Defaults defaults = Defaults.getDefaults();
    protected PasswordVaultGUI passVault;

    public Panel(PasswordVaultGUI passVault) {
        this.passVault = passVault;
        this.setBackground(defaults.getBackgroundColor());
        this.setBounds(0, 0, 500, 500);
    }

}
