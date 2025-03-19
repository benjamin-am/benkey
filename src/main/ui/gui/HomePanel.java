package ui.gui;

import java.awt.GridLayout;

public class HomePanel extends Panel {
    private GridLayout gl = new GridLayout(1, 2);
    private MenuPanel menu;
    private MainPanel main;

    public HomePanel(PasswordVaultGUI passVault) {
        super(passVault);
        this.setLayout(gl);
        gl.setHgap(5);
        main = new MainPanel(passVault);
        menu = new MenuPanel(passVault, main);
        this.add(menu);
        this.add(main);
    }
}
