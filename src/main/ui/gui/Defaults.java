package ui.gui;

import java.awt.Font;
import java.awt.Color;

// Attempt at using Singleton based on https://refactoring.guru/design-patterns/singleton
public class Defaults {
    private final Font font;
    private static Defaults defaults;
    private Color background;
    private Color fontColor;
    private Font headerFont;


    // EFFECTS: initialize Defaults
    private Defaults() {
        font = new Font("Lucida Sans", Font.PLAIN, 18);
        headerFont = new Font("Lucida Sans", Font.BOLD, 30);
        background = Color.decode("#2D2D2D");
        fontColor = Color.decode("#E1E1E1");
    }

    // EFFECTS: returns Defaults object
    public static Defaults getDefaults() {
        if (defaults == null) {
            defaults = new Defaults();
        }

        return defaults;
    }

    // EFFECTS: returns font
    public Font getFont() {
        return font;
    }

    // EFFECTS: returns font
    public Font getHeaderFont() {
        return headerFont;
    }

    // EFFECTS: returns font
    public Color getFontColor() {
        return fontColor;
    }

    // EFFECTS: returns font
    public Color getBackgroundColor() {
        return background;
    }
}
