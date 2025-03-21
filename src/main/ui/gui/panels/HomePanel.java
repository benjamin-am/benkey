package ui.gui.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import ui.gui.*;

public class HomePanel extends Panel {
    private GridBagLayout gl = new GridBagLayout();
    private MenuPanel menu;
    private MainPanel main;

    public HomePanel(PasswordVaultGUI passVault) {
        super(passVault);
        this.setLayout(gl);
        GridBagConstraints c = new GridBagConstraints();
        
        main = new MainPanel(passVault);
        menu = new MenuPanel(passVault, main);
        c.gridx = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridy = 0;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        this.add(menu, c);

        c.weightx = 0.7;
        c.gridx = 1;
        this.add(main, c);
    }
}
