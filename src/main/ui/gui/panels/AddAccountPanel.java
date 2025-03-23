package ui.gui.panels;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import ui.gui.ButtonFactory;
import ui.gui.LabelFactory;
import ui.gui.PasswordVaultGUI;

// Add Accounts to the User profile on the GUI - Panel
public class AddAccountPanel extends Panel implements ActionListener {
    private MainPanel main;
    private JButton account;
    private JButton back;
    private JTextField username;
    private JTextField password;
    private JTextField website;
    private HorizontalButtonPanel buttons;
    private ImageHandler image;
    private JLabel successImage;
    private ImageIcon imageCheck;

    // EFFECTS: initializes Add Account Panel construction with GridLayout
    public AddAccountPanel(PasswordVaultGUI passVault, MainPanel main) {
        super(passVault);
        this.main = main;
        image = new ImageHandler();
        this.setLayout(new GridLayout(6, 1));
        this.add(LabelFactory.createLabel("Enter account information below: ", true));
        textInit();
        buttonInit();
        imageCheck = image.getImage("images/check.png", 100, 75);
        successImage = new JLabel(imageCheck);
        this.add(successImage);
        this.setPreferredSize(getPreferredSize());
        successImage.setIcon(null);
    }

    // MODIFIES: this
    // EFFECTS: adds text to JPanel
    private void initLabelTextPairs(JLabel label, JTextField textfield) {
        List<Component> listComp = new ArrayList<>();
        listComp.add(label);
        listComp.add(textfield);
        HorizontalLabelTextFieldPanel panel = new HorizontalLabelTextFieldPanel(passVault, listComp);
        this.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: initialize text for the JPanel
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

    // MODIFIES: this
    // EFFECTS: Performs actions on buttons
    // ATTRIBUTION: timer so you can see success message https://stackoverflow.com/questions/1006611/java-swing-timer
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == account) {
            String user = username.getText();
            String pass = password.getText();
            String websiteName = website.getText();
            passVault.addAccount(user, pass, websiteName); 
            successImage.setIcon(imageCheck);
            Timer timer = new Timer(1000, time -> {
                successImage.setIcon(null);
            });
            timer.setRepeats(false);
            timer.start();
            main.refreshPanel();
            username.setText("");
            password.setText("");
            website.setText("");
        }
    }

}
