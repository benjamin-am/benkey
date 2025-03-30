package ui.gui.panels;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;

import ui.gui.ButtonFactory;
import ui.gui.ImageHandler;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

// Intro Panel is the first panel that users are presented with when opening the app
public class IntroPanel extends Panel {
    private JButton loginButton;
    private JButton accountButton;
    private ImageHandler image;

    // EFFECTS: initializes Intro Panel construction, initial values set and components added
    public IntroPanel(PasswordVaultGUI passVault) {
        super(passVault);
        image = new ImageHandler();
        
        JLabel label = LabelFactory.createLabel("<html>Welcome to benkey." 
                        + "<br/> Please login or create a new user account.</html>");
        
        buttonInit();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE; 
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        this.add(label, c);
        c.gridwidth = 1;
        c.gridy = 1;
        this.add(accountButton, c);
        c.gridx = 1;
        this.add(loginButton, c);
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 2;
        this.add(new JLabel(image.getImage("images/entrance.jpg", 300, 300)), c);
    }


    // EFFECTS: run button initialization methods
    private void buttonInit() {
        loginButtonInit();
        newAccountButtonInit();
    }

    // MODIFIES: this
    // EFFECTS: sets up new account button
    private void newAccountButtonInit() {
        accountButton = ButtonFactory.createButton("New User", 200, 200, 200, 50, false);
        accountButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getNewAccount()));
    }

    // MODIFIES: this
    // EFFECTS: sets up login button
    private void loginButtonInit() {
        loginButton = ButtonFactory.createButton("Login", 200, 100, 200, 50, false);
        loginButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getLogin()));
    }
    

}
