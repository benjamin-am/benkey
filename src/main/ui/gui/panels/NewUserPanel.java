package ui.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.User;
import ui.Saving;
import ui.gui.ButtonFactory;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

public class NewUserPanel extends Panel implements ActionListener {
    private JButton backButton;
    private JButton account;
    private JTextField password;
    private JTextField username;
    private HorizontalButtonPanel buttons;
    private GridBagLayout gl;
    private GridBagConstraints gbc;
    private JLabel hiddenLabel;


    public NewUserPanel(PasswordVaultGUI passVault) {
        super(passVault);
        gl = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gl);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        this.add(LabelFactory.createLabel("Enter user information ", true), gbc);  
        textInit();
        buttonInit();
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridy = 4;
        gbc.anchor = gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.fill = GridBagConstraints.BOTH;
        hiddenLabel = LabelFactory.createLabel("", 0, 0, 400, 40);
        this.add(hiddenLabel, gbc);
        // this.setPreferredSize(getPreferredSize());
    }

    private void initLabelTextPairs(JLabel label, JTextField textfield, int x, int y) {
        List<Component> listComp = new ArrayList<>();
        listComp.add(label);
        listComp.add(textfield);
        HorizontalLabelTextFieldPanel panel = new HorizontalLabelTextFieldPanel(passVault, listComp);
        gbc.gridy = y;
        gbc.gridx = x;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(panel, gbc);
    }

    private void textInit() {
        JLabel userLabel = LabelFactory.createLabel("Username:");
        username = new JTextField();
        initLabelTextPairs(userLabel, username, 0, 1);

        JLabel websiteLabel = LabelFactory.createLabel("Password:");
        password = new JTextField();
        initLabelTextPairs(websiteLabel, password, 0, 2);
    }


    // EFFECTS: sets up back button
    private void buttonInit() {
        backButton = ButtonFactory.createButton("Back", 200, 100, 100, 50, false);
        backButton.addActionListener(e -> passVault.changeScreen(PasswordVaultGUI.getIntro()));

        account = ButtonFactory.createButton("Create Account", 0, 0, 200, 50, false);
        account.addActionListener(this);
        
        this.buttons = new HorizontalButtonPanel(passVault, List.of(account, backButton));
        gbc.gridx = 0;
        gbc.gridy = 3; 
        gbc.anchor = GridBagConstraints.CENTER; 
        this.add(buttons, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == account) {
            String user = username.getText();
            String pass = password.getText();
            if (Saving.checkUserExists(user)) {
                hiddenLabel.setText("Username already taken.");
                hiddenLabel.setForeground(Color.pink);
                username.setText("");
            } else {
                User userNew = new User(user, pass);
                passVault.userSignIn(userNew);
            }
        }
    }

}
