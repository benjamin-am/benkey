package ui.gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

// Button Factory based on https://refactoring.guru/design-patterns/factory-method
public class ButtonFactory {
    private static Defaults defaults = Defaults.getDefaults();
    
    // EFFECTS: Creates a button to use in a JPanel or JFrame
    public static JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        setBoundsFontFocus(x, y, width, height, button);
        return button;
    }

    // EFFECTS: Creates a button to use in a JPanel or JFrame, with no painted border
    public static JButton createButton(String text, int x, int y, int width, int height, boolean border) {
        JButton button = new JButton(text);
        setBoundsFontFocus(x, y, width, height, button);
        button.setBorderPainted(border);
        button.setBackground(defaults.getButtonColor());
        return button;
    }

    // EFFECTS: Creates a button to use in a JPanel or JFrame
    public static JButton createButton(String text, int x, int y, int width, int height, ActionListener al) {
        JButton button = new JButton(text);
        setBoundsFontFocus(x, y, width, height, button);
        button.addActionListener(al);
        return button;
    }

    // MODIFIES: button
    // EFFECTS: sets bounds of button, sets font, and other defaults
    private static void setBoundsFontFocus(int x, int y, int width, int height, JButton button) {
        button.setBounds(x, y, width, height);
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setFont(defaults.getFont());
        button.setFocusable(false);
        button.setBackground(defaults.getButtonColor());
    }    
}
