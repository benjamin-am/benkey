package ui.gui.panels;

import ui.Login;
import ui.gui.ButtonFactory;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import model.User;

// Panel for benkey login page
// ATTRIBUTION: https://www.youtube.com/watch?v=Hiv3gwJC5kw&t=1651s
// brocode helped me with ActionListener and general set up
// TODO:
// ATTRIBUTION: https://www.youtube.com/watch?v=IyfB0u9g2x0
// this brocode video helped me with keybindings
public class LoginPanel extends Panel implements ActionListener {
    private JPasswordField password;
    private JTextField userIDField;
    private JButton backButton;
    private JButton loginButton;
    private JLabel messageLabel;


    // EFFECTS: creates a LoginPanel JPanel
    public LoginPanel(PasswordVaultGUI passVault) {
        super(passVault);
        
        JLabel welcomeNote = LabelFactory.createLabel("benkey Vault Login", 100, 25, 300, 150, true);
        JLabel userLabel = LabelFactory.createLabel("Username:", 50, 150, 100, 20);
        JLabel passwordLabel = LabelFactory.createLabel("Password:", 50, 200, 100, 20);
        messageLabel = LabelFactory.createLabel("", 150, 300, 500, 50);
        userIDField = new JTextField();
        password = new JPasswordField();
        userIDField.setBounds(160, 150, 150, 20);
        password.setBounds(160, 200, 150, 20);
        this.setLayout(null);
        this.add(userLabel);
        this.add(userIDField);
        this.add(passwordLabel);
        this.add(password);
        this.add(messageLabel);
        this.add(welcomeNote);
        buttonInit();
        this.add(loginButton);
        this.add(backButton);
    }

    // EFFECTS: Initialize buttons
    private void buttonInit() {
        backButtonInit();
        loginButtonInit();
    }

    // MODIFIES: this
    // EFFECTS: sets up back button
    private void backButtonInit() {
        backButton = ButtonFactory.createButton("Back", 140, 250, 100, 50);
        backButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getIntro()));
    }

    // MODIFIES: this
    // EFFECTS: sets up login button
    private void loginButtonInit() {
        loginButton = ButtonFactory.createButton("Login", 260, 250, 100, 50, this);
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
