package ui.gui.panels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import ui.gui.PasswordVaultGUI;

public class HorizontalButtonPanel extends Panel {
    Map<String, JButton> buttons;
    BoxLayout boxLayout;

    public HorizontalButtonPanel(PasswordVaultGUI passVault, List<JButton> buttons) {
        super(passVault);
        this.buttons = new HashMap<>();
        boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(boxLayout);
        buttonInit(buttons);

    }

    private void buttonInit(List<JButton> buttons) {
        for (JButton button : buttons) {
            this.buttons.put(button.getText(), button);
            addButtonToPanel(button);
        }
    }

    // REQUIRES: button.getText() is unique
    public void addButton(JButton button) {
        this.buttons.put(button.getText(), button);
    }

    private void addButtonToPanel(JButton button) {
        addSpace();
        this.add(button);
    }

    private void addSpace() {
        this.add(Box.createHorizontalStrut(5)); 
    }

}
