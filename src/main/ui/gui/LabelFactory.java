package ui.gui;

import java.awt.Dimension;
import javax.swing.JLabel;

// A factory class for creating labels based on https://refactoring.guru/design-patterns/factory-method
public class LabelFactory {
    private static Defaults defaults = Defaults.getDefaults();
    
    // EFFECTS: sets the sizing of the given JLabel
    private static void setSizing(int x, int y, int width, int height, JLabel label) {
        label.setBounds(x, y, width, height);
        label.setPreferredSize(new Dimension(width, height));
        label.setMaximumSize(new Dimension(width, height));
    }

    // EFFECTS: Creates a label with specified location and size
    public static JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        setSizing(x, y, width, height, label);
        fontAndColor(label);
        return label;
    }

    // EFFECTS: creates a header label at specified location and size
    public static JLabel createLabel(String text, int x, int y, int width, int height, boolean header) {
        if (header) {
            JLabel label = new JLabel(text);
            setSizing(x, y, width, height, label);
            label.setFont(defaults.getHeaderFont());
            label.setForeground(defaults.getFontColor());
            return label;
        } else {
            return createLabel(text, x, y, width, height);
        }
    }

    // EFFECTS: creates a header label
    public static JLabel createLabel(String text, boolean header) {
        if (header) {
            JLabel label = new JLabel(text);
            label.setFont(defaults.getHeaderFont());
            label.setForeground(defaults.getFontColor());
            return label;
        } else {
            return createLabel(text);
        }
    }

    // EFFECTS: Creates a label with text in it
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        fontAndColor(label);
        return label;
    }

     // EFFECTS: Creates a label, with nothing in it
    public static JLabel createLabel() {
        JLabel label = new JLabel();
        fontAndColor(label);
        return label;
    }

    // EFFECTS: sets font and color to label
    private static void fontAndColor(JLabel label) {
        label.setFont(defaults.getFont());
        label.setForeground(defaults.getFontColor());
    }
}
