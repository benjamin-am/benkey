package ui;

import java.io.File;
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

    // REQUIRES: a user's username must be unique, unless user is overwriting their profile
    // MODIFIES: user
    // EFFECTS: Save user to file
    public static void saveAccount(User user, Scanner scanner) {
        JsonWriter write = new JsonWriter(path + user.getUsername() + ext);
        try {
            write.open();
            write.write(user);
            System.out.println("Account information saved!");
            write.close();
        } catch (FileNotFoundException e) {
            System.out.println("Username contains illegal character. Please enter a new username!");
            String input = scanner.next();
            user.setUsername(input);
            System.out.println("Your new username is " + user.getUsername());
            saveAccount(user, scanner);
        }        
    }

    // Attribution: https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
    // wanted to find a way to see if a file exists, so I don't have multiple usernames for User
    // EFFECTS: checks if user profile already exists
    public static boolean checkUserExists(String user) {
        return new File(path + user + ext).isFile();
    }
    
}
