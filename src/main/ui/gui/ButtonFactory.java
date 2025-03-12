package ui.gui;

import javax.swing.JButton;

// Button Factory based on https://refactoring.guru/design-patterns/factory-method
public class ButtonFactory {
    private static Defaults defaults = Defaults.getDefaults();
    
    // EFFECTS: Creates a button to use in a JPanel or JFrame
    public static JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(defaults.getFont());
        button.setFocusable(false);
        return button;
    }

    
}
