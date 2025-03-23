package ui.gui.panels;

import javax.swing.JPanel;

import ui.gui.Defaults;
import ui.gui.PasswordVaultGUI;

// Abstract panel to set up defaults for all panels
public abstract class Panel extends JPanel {
    protected Defaults defaults = Defaults.getDefaults();
    protected PasswordVaultGUI passVault;

    // EFFECTS: set default background, bounds, and password vault field
    public Panel(PasswordVaultGUI passVault) {
        this.passVault = passVault;
        this.setBackground(defaults.getBackgroundColor());
        this.setBounds(0, 0, Defaults.getWidth(), Defaults.getHeight());
    }

    // EFFECTS: overloaded constructor, to set width and height
    public Panel(PasswordVaultGUI passVault, int width, int height) {
        this.passVault = passVault;
        this.setBackground(defaults.getBackgroundColor());
        this.setBounds(0, 0, width, height);
    }

    // EFFECTS: refresh the panel if information is updated
    public void refreshPanel() {
        revalidate();
        repaint();
    }

}
