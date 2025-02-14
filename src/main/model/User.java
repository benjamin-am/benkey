package model;

import java.util.ArrayList;
import java.util.List;

// A user of the password vault. Has a login (username/password) and has a list of accounts
public class User {

    /* 
    REQUIRES: a user's username and password must be stringlength > 0
    EFFECTS: Sets up a user profile for the password vault. Creates a blank
            profile with an empty list. The user MUST remember their password.
    */
    public User(String username, String password) {
        //stub
    }

    // getters
    public String getUsername() {
        return null; // stub
    }

    public Password getPassword() {
        return null; // stub
    }

    public List<Account> getListOfAccounts() {
        return null; // stub
    }
    // setters
    public void setUsername() {
        // stub
    }

    public void setPassword() {

    }

    public void setAccountList() {

    }
    /* 
    MODIFIES: this
    EFFECTS: adds account to the user's profile
    */
    public void addAccount(Account account) {
        //stub
    }

    /* 
    MODIFIES: this
    EFFECTS: removes account from the user's profile
    */
    public void removeAccount(Account account) {
        //stub
    }

    /* 
    EFFECTS: returns number of accounts user has
    */
    public int totalAccounts() {
        return 1;
    }

    /* 
    EFFECTS: returns number of Websites a user has an account for
    */
    public int totalWebsites() {
        return 1;
    }

    /* 
    EFFECTS: returns list of distinct websites user has an account for
    */
    public List<Website> listAllWebsites() {
        return null; //stub
    }

    /* 
    EFFECTS: returns list of distinct usernames user in accounts
    */
    public List<String> listAllUsernames() {
        return null; //stub
    }

    /* 
    EFFECTS: returns true if password matches
    */
    public boolean verifyPassword(String pass) {
        return false;
    }

}
