package ui.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;

import model.*;

// ATTRIBUTION: DrawingEditor, Alarm Controller, and Swing documentation
// https://stackoverflow.com/questions/17477891/how-do-i-use-cardlayout-for-my-java-program-for-login-and-menu-items
// https://www.youtube.com/watch?v=XBFT0N-Qbm4
public class PasswordVaultGUI extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private boolean loggedIn;
    private User user;
    private Map<String, JPanel> panels;
    private CardLayout cl;
    private JPanel cardPanel;
    private Defaults defaults = Defaults.getDefaults();
    private static final String LOGIN = "login";
    private static final String NEW_ACCOUNT = "account";
    private static final String INTRO = "intro";
    private static final String HOME = "home";


    // EFFECTS: Constructs PasswordVaultGUI
    public PasswordVaultGUI() {
        super("benkey");
        this.panels = new HashMap<>();
        initJFrame();
    }

    // EFFECTS: Initialize the JFrame and panels for the GUI
    private void initJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setLayout(new BorderLayout(5, 5));
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        createPanels();
        setUpCardPanel();
        this.add(cardPanel);
        changeScreen(INTRO);
        this.setVisible(true);
        // setFocusablePanels();
    }

    // MODIFIES: this
    // EFFECTS: creates panel instances
    public void createPanels() {
        panels.put(INTRO, new IntroPanel(this));
        panels.put(LOGIN, new LoginPanel(this));
        panels.put(NEW_ACCOUNT, new AccountPanel(this));
        panels.put(HOME, new HomePanel(this));
    }

    // // MODIFIES: this
    // // EFFECTS: sets panels as focusable
    // public void setFocusablePanels() {
    //     for (JPanel panel : panels.values()) {
    //         panel.requestFocus();
    //         panel.setFocusable(true);
    //     }
    // }

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
        cl.show(cardPanel, card);
    }

    // MODIFIES: this
    // EFFECTS: set user, loggedin now true. swap to home panel.
    public void userSignIn(User user) {
        this.user = user;
        this.loggedIn = true;
        changeScreen(HOME);
    }

    // getters
    public static String getNewAccount() {
        return NEW_ACCOUNT;
    }

    public static String getLogin() {
        return LOGIN;
    }
    
    public static String getIntro() {
        return INTRO;
    }

}
