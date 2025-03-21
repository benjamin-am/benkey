package ui.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
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
    private GridBagConstraints c;
    private JLabel hiddenLabel;


    public NewUserPanel(PasswordVaultGUI passVault) {
        super(passVault);
        gl = new GridBagLayout();
        c = new GridBagConstraints();
        this.setLayout(gl);
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        this.add(LabelFactory.createLabel("Enter user information ", true), c);  
        textInit();
        buttonInit();
        c = new GridBagConstraints();
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridy = 4;
        c.anchor = c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.BOTH;
        hiddenLabel = LabelFactory.createLabel("", 0, 0, 400, 40);
        this.add(hiddenLabel, c);
        // this.setPreferredSize(getPreferredSize());
    }

     private void initLabelTextPairs(JLabel label, JTextField textfield, int x, int y) {
        List<Component> listComp = new ArrayList<>();
        listComp.add(label);
        listComp.add(textfield);
        HorizontalLabelTextFieldPanel panel = new HorizontalLabelTextFieldPanel(passVault, listComp);
        c.gridy = y;
        c.gridx = x;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(panel, c);
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
        c.gridx = 0;
        c.gridy = 3; 
        c.anchor = GridBagConstraints.CENTER; 
        this.add(buttons, c);
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
