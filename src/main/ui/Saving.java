package ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.User;
import persistence.JsonWriter;

// A saving class to help Password vault use the new persistence classes
public class Saving {
    private static String path = "./data/";
    private static String ext = ".json";

    // EFFECTS: nothing, class isn't meant to be initalized
    private Saving() {
    }

    // MODIFIES: user
    // EFFECTS: Save user to file
    public static void saveAccount(User user, Scanner scanner) {
        JsonWriter write = new JsonWriter(path + user.getUsername() + ext);
        try {
            write.open();
            write.write(user);
            System.out.println("Account information saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Username contains illegal character. Please enter a new username!");
            String input = scanner.next();
            user.setUsername(input);
            System.out.println("Your new username is " + user.getUsername());
            saveAccount(user, scanner);
        }        
    }
}
