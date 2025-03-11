package ui.gui;

import java.awt.Font;

// Attempt at using Singleton based on https://refactoring.guru/design-patterns/singleton
public class Defaults {
    private final Font font;
    private static Defaults defaults;


    // EFFECTS: initialize Defaults
    private Defaults() {
        font = new Font("Lucida Sans", Font.PLAIN, 18);

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
}
