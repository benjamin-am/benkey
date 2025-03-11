package ui.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import model.*;

// ATTRIBUTION: DrawingEditor, Alarm Controller, and Swing documentation
// https://stackoverflow.com/questions/17477891/how-do-i-use-cardlayout-for-my-java-program-for-login-and-menu-items
// https://www.youtube.com/watch?v=XBFT0N-Qbm4
public class PasswordVaultGUI extends JFrame {

    public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
    private boolean loggedIn;
    private User user;
    private JPanel cardPanel;
    private IntroPanel introPanel;
    private LoginPanel loginPanel;
    private AccountPanel newAccountPanel;
    private Defaults defaults = Defaults.getDefaults();
    private final static String LOGIN = "login";
    private final static String NEW_ACCOUNT = "account";
    private final static String INTRO = "intro";


    // EFFECTS: Constructs PasswordVaultGUI
    public PasswordVaultGUI() {
        super("benkey");
        initJFrame();
    }

    // EFFECTS: Initialize the JFrame and panels for the GUI
    private void initJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setLayout(new BorderLayout(5, 5));
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        createPanels();
        setUpCardPanel();
        this.add(cardPanel);
        this.pack();
        changeScreen(INTRO);
    }

    // MODIFIES: this
    // EFFECTS: creates panel instances
    public void createPanels() {
        this.introPanel = new IntroPanel(this);
        this.loginPanel = new LoginPanel(this);
        this.newAccountPanel = new AccountPanel(this);
        
    }

    // MODIFIES: this
    // EFFECTS: Generates the panels, and adds them to card panel
    private void setUpCardPanel() {
        this.cardPanel = new JPanel(new CardLayout());
        cardPanel.add(introPanel, INTRO);
        cardPanel.add(loginPanel, LOGIN);
        cardPanel.add(newAccountPanel, NEW_ACCOUNT);
    }

    // EFFECTS: Change Screen in card layout
    // ATTRIBUTION: https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
    public void changeScreen(String card) {
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel, card);
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
