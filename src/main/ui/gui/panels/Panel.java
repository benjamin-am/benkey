package ui.gui.panels;

import javax.swing.JPanel;

import ui.gui.Defaults;
import ui.gui.PasswordVaultGUI;

public abstract class Panel extends JPanel {
    protected Defaults defaults = Defaults.getDefaults();
    protected PasswordVaultGUI passVault;

    public Panel(PasswordVaultGUI passVault) {
        this.passVault = passVault;
        this.setBackground(defaults.getBackgroundColor());
        this.setBounds(0, 0, Defaults.getWidth(), Defaults.getHeight());
    }

    public Panel(PasswordVaultGUI passVault, int width, int height) {
        this.passVault = passVault;
        this.setBackground(defaults.getBackgroundColor());
        this.setBounds(0, 0, width, height);
    }

    public void refreshPanel() {
        revalidate();
        repaint();
    }

}
