package ui.gui;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class MainPanel extends Panel {
    private Map<String, JPanel> panels;
    private CardLayout cl;
    private JPanel cardPanel;
    private static final String BASE = "base";
    private static final String ACCOUNT = "account";
    private static final String INTRO = "intro";
    private static final String HOME = "home";

    // Getters
    public static String getBase() {
        return BASE;
    }

    public static String getAccount() {
        return ACCOUNT;
    }

    public static String getIntro() {
        return INTRO;
    }

    public static String getHome() {
        return HOME;
    }

    public MainPanel(PasswordVaultGUI passVault) {
        super(passVault);
        panels = new HashMap<>();
        createPanels();
        setUpCardPanel();
        this.add(cardPanel);
        changeScreen(BASE);
    }

    // MODIFIES: this
    // EFFECTS: creates panel instances
    private void createPanels() {
        panels.put(BASE, new BasePanel(passVault));
        panels.put(ACCOUNT, new AccountPanel(passVault));
        
    }

    // MODIFIES: this
    // EFFECTS: Generates the panels, and adds them to card panel
    private void setUpCardPanel() {
        this.cl = new CardLayout();
        this.cardPanel = new JPanel(cl);
        
        for (String key : panels.keySet()) {
            cardPanel.add(panels.get(key), key);
        }
    }

    // EFFECTS: Change Screen in card layout
    // ATTRIBUTION: https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
    public void changeScreen(String card) {
        System.out.println("Switching to: " + card);
        cl.show(cardPanel, card);
    }

}
