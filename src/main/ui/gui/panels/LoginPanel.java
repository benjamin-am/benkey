package ui.gui.panels;

import ui.Login;
import ui.gui.ButtonFactory;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import model.User;

// Panel for benkey login page
// ATTRIBUTION: https://www.youtube.com/watch?v=Hiv3gwJC5kw&t=1651s
// brocode helped me with ActionListener and general set up
public class LoginPanel extends Panel implements ActionListener {
    private JPasswordField password;
    private JTextField userIDField;
    private JButton backButton;
    private JButton loginButton;
    private JLabel messageLabel;
    private GridBagConstraints gbc;


    // EFFECTS: creates a LoginPanel JPanel
    public LoginPanel(PasswordVaultGUI passVault) {
        super(passVault);
        
        GridBagLayout gl = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gl);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        JLabel welcomeNote = LabelFactory.createLabel("benkey Vault Login", true);
        this.add(welcomeNote, gbc);
        textInit();
        buttonInit();
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        this.add(messageLabel, gbc);
    }

    private void initLabelTextPairs(JLabel label, JTextField textfield, int x, int y) {
        List<Component> listComp = new ArrayList<>();
        listComp.add(label);
        listComp.add(textfield);
        HorizontalLabelTextFieldPanel panel = new HorizontalLabelTextFieldPanel(passVault, listComp);
        gbc.gridy = y;
        gbc.gridx = x;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        this.add(panel, gbc);
    }

    private void textInit() {
        JLabel userLabel = LabelFactory.createLabel("Username:", 50, 150, 100, 20);
        JLabel passwordLabel = LabelFactory.createLabel("Password:", 50, 200, 100, 20);
        messageLabel = LabelFactory.createLabel("");
        userIDField = new JTextField();
        password = new JPasswordField();
        initLabelTextPairs(userLabel, userIDField, 0, 1);
        initLabelTextPairs(passwordLabel, password, 0, 2);
    }

    // EFFECTS: Initialize buttons
    private void buttonInit() {
        backButtonInit();
        loginButtonInit();
    }

    // MODIFIES: this
    // EFFECTS: sets up back button
    private void backButtonInit() {
        backButton = ButtonFactory.createButton("Back", 0, 0, 100, 50);
        backButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getIntro()));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(backButton, gbc);
    }

    // MODIFIES: this
    // EFFECTS: sets up login button
    private void loginButtonInit() {
        loginButton = ButtonFactory.createButton("Login", 0, 0, 100, 50, this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(loginButton, gbc);
    }

    // EFFECTS: Performs actions on buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String user = userIDField.getText();
            String pass = String.valueOf(password.getPassword());
            loginAttempt(user, pass);  
        }
    }

    // MODIFIES: this
    // EFFECTS: attempt to login to user account
    // ATTRIBUTION: timer so you can see success message https://stackoverflow.com/questions/1006611/java-swing-timer
    private void loginAttempt(String user, String pass) {
        try {
            User username = Login.loginToAccount(user);
            if (username.getPassword().getPassword().equals(pass)) {
                messageLabel.setText("Login successful!");
                messageLabel.setForeground(Color.green);
                Timer timer = new Timer(1000, e -> {
                    passVault.userSignIn(username);
                    messageLabel.setText("");
                    userIDField.setText("");
                    password.setText("");
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                messageLabel.setText("Incorrect password!");
                messageLabel.setForeground(Color.pink);
            }
        } catch (IOException e1) {
            messageLabel.setText("Username doesn't exist!");
            messageLabel.setForeground(Color.pink);
        } 
    }
}
