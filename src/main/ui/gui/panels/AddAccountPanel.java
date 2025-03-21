package ui.gui.panels;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ui.gui.ButtonFactory;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;


public class AddAccountPanel extends Panel implements ActionListener {
    private MainPanel main;
    private JButton account;
    private JButton back;
    private JTextField username;
    private JTextField password;
    private JTextField website;
    private HorizontalButtonPanel buttons;

    public AddAccountPanel(PasswordVaultGUI passVault, MainPanel main) {
        super(passVault);
        this.main = main;

        this.setLayout(new GridLayout(5, 1));
        this.add(LabelFactory.createLabel("Enter account information below: ", true));
        textInit();
        buttonInit();
        this.setPreferredSize(getPreferredSize());

    }

    private void initLabelTextPairs(JLabel label, JTextField textfield) {
        List<Component> listComp = new ArrayList<>();
        listComp.add(label);
        listComp.add(textfield);
        HorizontalLabelTextFieldPanel panel = new HorizontalLabelTextFieldPanel(passVault, listComp);
        this.add(panel);
    }

    private void textInit() {
        JLabel userLabel = LabelFactory.createLabel("Username:", 50, 150, 100, 20);
        username = new JTextField();
        initLabelTextPairs(userLabel, username);

        JLabel passwordLabel = LabelFactory.createLabel("Password:", 50, 200, 100, 20);
        password = new JTextField();
        initLabelTextPairs(passwordLabel, password);

        JLabel websiteLabel = LabelFactory.createLabel("Website:", 50, 200, 100, 20);
        website = new JTextField();
        initLabelTextPairs(websiteLabel, website);
    }

    // MODIFIES: this
    // EFFECTS: set up buttons
    private void buttonInit() {
        account = ButtonFactory.createButton("Add Account", 0, 0, 200, 50, false);
        account.addActionListener(this);

        back = ButtonFactory.createButton("Back", 0, 0, 200, 50, false);
        back.addActionListener(e -> main.changeScreen(MainPanel.getAccount()));
        
        this.buttons = new HorizontalButtonPanel(passVault, List.of(account, back));
        this.add(buttons);
    }

    // EFFECTS: Performs actions on buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == account) {
            String user = username.getText();
            String pass = password.getText();
            String websiteName = website.getText();
            passVault.addAccount(user, pass, websiteName); 
            main.refreshPanel();
            username.setText("");
            password.setText("");
            website.setText("");
        }
    }

}
