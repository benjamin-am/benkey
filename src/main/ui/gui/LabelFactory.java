package ui.gui;

import javax.swing.JLabel;

// A factory class for creating labels based on https://refactoring.guru/design-patterns/factory-method
public class LabelFactory {
    private static Defaults defaults = Defaults.getDefaults();
    
    // EFFECTS: Creates a label
    public static JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(defaults.getFont());
        return label;
    }

    // EFFECTS: Creates a label
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(defaults.getFont());
        return label;
    }

    // EFFECTS: Creates a label
    public static JLabel createLabel() {
        JLabel label = new JLabel();
        label.setFont(defaults.getFont());
        return label;
    }
}
