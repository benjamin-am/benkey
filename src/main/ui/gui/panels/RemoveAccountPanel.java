package ui.gui.panels;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;


import ui.gui.ButtonFactory;
import ui.gui.ImageHandler;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

import java.util.*;

// Remove an account from a UserProfile on the GUI - Panel
public class RemoveAccountPanel extends Panel implements ActionListener {
    private MainPanel main;
    private JButton account;
    private JButton back;
    private JTextField username;
    private JTextField website;
    private HorizontalButtonPanel buttons;
    private JLabel successImage;
    private ImageHandler image;
    private ImageIcon imageSkull;

    // EFFECTS: initializes RemoveAccountPanel construction, initial values set and components added
    public RemoveAccountPanel(PasswordVaultGUI passVault, MainPanel main) {
        super(passVault);
        this.main = main;

        this.setLayout(new GridLayout(5, 1));
        this.add(LabelFactory.createLabel("Enter account information below: ", true));
        textInit();
        buttonInit();
        image = new ImageHandler();
        imageSkull = image.getImage("images/skull.png", 100, 75);
        successImage = new JLabel(imageSkull);
        this.add(successImage);
        this.setPreferredSize(getPreferredSize());
        successImage.setIcon(null);
    }

    // MODIFIES: this
    // EFFECTS: add text to panel after initialized
    private void initLabelTextPairs(JLabel label, JTextField textfield) {
        List<Component> listComp = new ArrayList<>();
        listComp.add(label);
        listComp.add(textfield);
        HorizontalLabelTextFieldPanel panel = new HorizontalLabelTextFieldPanel(passVault, listComp);
        this.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: initialize text labels
    private void textInit() {
        JLabel userLabel = LabelFactory.createLabel("Username:", 50, 150, 100, 20);
        username = new JTextField();
        initLabelTextPairs(userLabel, username);

        JLabel websiteLabel = LabelFactory.createLabel("Website:", 50, 200, 100, 20);
        website = new JTextField();
        initLabelTextPairs(websiteLabel, website);
    }

    // MODIFIES: this
    // EFFECTS: set up buttons
    private void buttonInit() {
        account = ButtonFactory.createButton("Remove Account", 0, 0, 200, 50, false);
        account.addActionListener(this);

        back = ButtonFactory.createButton("Back", 0, 0, 200, 50, false);
        back.addActionListener(e -> main.changeScreen(MainPanel.getAccount()));
        
        this.buttons = new HorizontalButtonPanel(passVault, List.of(account, back));
        this.add(buttons);
    }

    // MODIFIES: user
    // EFFECTS: Removes account from account list of user
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == account) {
            String user = username.getText();
            String websiteName = website.getText();
            if (user == "" || websiteName == "") {
                return;
            }
            passVault.removeAccount(user, websiteName);
            successImage.setIcon(imageSkull);
            Timer timer = new Timer(1000, time -> {
                successImage.setIcon(null);
            });
            timer.setRepeats(false);
            timer.start();
            main.refreshPanel();
            username.setText("");
            website.setText("");
        }
    }
}



