package ui;

import java.util.Scanner;

import ui.gui.PasswordVaultGUI;

public class Main {
    private static Scanner scan;
    public static void main(String[] args) throws Exception {
        scan = new Scanner(System.in);
        System.out.println("Launch UI or GUI? (answer U/G for respective interface)");
        String input = scan.next().toLowerCase();
        if (input.equals("u")) {
            new PasswordVault();
        } else {
            new PasswordVaultGUI();
        }
    }
}
