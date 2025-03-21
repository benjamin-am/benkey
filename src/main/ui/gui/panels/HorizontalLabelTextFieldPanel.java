package ui.gui.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import ui.gui.PasswordVaultGUI;

public class HorizontalLabelTextFieldPanel extends Panel{
    List<Component> comps;
    BoxLayout boxLayout;

    public HorizontalLabelTextFieldPanel(PasswordVaultGUI passVault, List<Component> comps) {
        super(passVault);
        this.comps = comps;
        boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(boxLayout);
        compsInit(comps);
    }

    private void compsInit(List<Component> comps) {
        for (Component comp : comps) {
            if (comp instanceof JTextField) {
                JTextField textField = (JTextField) comp;
                Dimension size = new Dimension(200, 30); // Set desired width & height
                textField.setMinimumSize(size);
                textField.setPreferredSize(size);
                textField.setMaximumSize(size);
            }
            addCompToPanel(comp);
        }
    }

    private void addCompToPanel(Component comp) {
        addSpace();
        this.add(comp);
    }

    private void addSpace() {
        this.add(Box.createHorizontalStrut(10)); 
    }

}
