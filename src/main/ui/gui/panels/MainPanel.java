package ui.gui.panels;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import ui.gui.PasswordVaultGUI;

public class MainPanel extends Panel {
    private Map<String, Panel> panels;
    private CardLayout cl;
    private JPanel cardPanel;
    private static final String BASE = "base";
    private static final String ACCOUNT = "account";
    private static final String ADD = "add";
    private static final String REMOVE = "remove";
    private static final String WEBSITE = "website";


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
        panels.put(ACCOUNT, new AccountPanel(passVault, this));
        panels.put(ADD, new AddAccountPanel(passVault, this));
        panels.put(REMOVE, new RemoveAccountPanel(passVault, this));
        panels.put(WEBSITE, new WebsitePanel(passVault, this));
    }

    // EFFECTS: returns panel from collection
    public Panel getPanel(String label) {
        return this.panels.get(label);
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

      // Getters
    public static String getBase() {
        return BASE;
    }

    public static String getAccount() {
        return ACCOUNT;
    }

    public static String getAdd() {
        return ADD;
    }

    public static String getRemove() {
        return REMOVE;
    }

    public static String getWebsite() {
        return WEBSITE;
    }

    @Override 
    public void refreshPanel() {
        for (Panel panel : panels.values()) {
            panel.refreshPanel();
        }

        revalidate();
        repaint();
    }

}
