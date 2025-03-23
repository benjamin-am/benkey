package ui.gui.panels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import ui.gui.PasswordVaultGUI;

// Creates a panel of Horizontally placed buttons
public class HorizontalButtonPanel extends Panel {
    Map<String, JButton> buttons;
    BoxLayout boxLayout;

    // EFFECTS: initializes HorizontalButtonPanel construction, initial values set and components added
    public HorizontalButtonPanel(PasswordVaultGUI passVault, List<JButton> buttons) {
        super(passVault);
        this.buttons = new HashMap<>();
        boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(boxLayout);
        buttonInit(buttons);

    }

    // MODIFIES: this
    // EFFECTS: Creates the Panel with buttons on it, with the list of buttions provided
    private void buttonInit(List<JButton> buttons) {
        for (JButton button : buttons) {
            this.buttons.put(button.getText(), button);
            addButtonToPanel(button);
        }
    }


    // REQUIRES: button.getText() is unique
    // MODIFIES: this
    // EFFECTS: adds button to buttons collection
    public void addButton(JButton button) {
        this.buttons.put(button.getText(), button);
    }

    // MODIFIES: this
    // EFFECTS: adds button to panel with space
    private void addButtonToPanel(JButton button) {
        addSpace();
        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds horizontal space
    private void addSpace() {
        this.add(Box.createHorizontalStrut(5)); 
    }

}
