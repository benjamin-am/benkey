package ui.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

import model.*;
import ui.gui.panels.HomePanel;
import ui.gui.panels.IntroPanel;
import ui.gui.panels.LoginPanel;
import ui.gui.panels.NewUserPanel;
import ui.gui.panels.Panel;

// ATTRIBUTION: DrawingEditor, Alarm Controller, and Swing documentation
// https://stackoverflow.com/questions/17477891/how-do-i-use-cardlayout-for-my-java-program-for-login-and-menu-items
// https://www.youtube.com/watch?v=XBFT0N-Qbm4
public class PasswordVaultGUI extends JFrame {
    private Defaults defaults = Defaults.getDefaults();
    private boolean loggedIn;
    private User user;
    private Map<String, Panel> panels;
    private CardLayout cl;
    private JPanel cardPanel;
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
        this.setSize(Defaults.getWidth(), Defaults.getHeight());
        this.setLocationRelativeTo(null);
        createPanels();
        setUpCardPanel();
        this.add(cardPanel);
        changeScreen(INTRO);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates panel instances
    public void createPanels() {
        panels.put(INTRO, new IntroPanel(this));
        panels.put(LOGIN, new LoginPanel(this));
        panels.put(NEW_ACCOUNT, new NewUserPanel(this));
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

    // MODIFIES: this
    // EFFECTS: set user, loggedin now true. Add home panel
    // here, since you will be logged in at this point swap to home panel.
    public void userSignIn(User user) {
        this.user = user;
        this.loggedIn = true;
        panels.put(HOME, new HomePanel(this));
        cardPanel.add(panels.get(HOME), HOME);
        changeScreen(HOME);
    }

    // MODIFIES: this
    // EFFECTS: logouts of user, goes back to intro
    public void logout() {
        this.user = null;
        this.loggedIn = false;
        changeScreen(INTRO);
    }

    // MODIFIES: this
    // EFFECTS: add account to user
    public void addAccount(String username, String password, String website) {
        Website web = new Website(website, website);
        Account account = new Account(web, username, password);
        user.addAccount(account);
    }

    // MODIFIES: this
    // EFFECTS: removes an account from user 
    public void removeAccount(String user, String website) {
        Account account = this.user.findAccountWebsiteAccountName(user, website);
        this.user.removeAccount(account);

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

    public User getUser() {
        return user;
    }

    public String getUsersName() {
        return user.getUsername();
    }

    public void setLoggedIn(boolean status) {
        this.loggedIn = status;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

}
