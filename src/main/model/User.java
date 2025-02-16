package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// A user of the password vault. Has a login (username/password) and has a list of accounts
public class User {
    private String username;
    private Password password;
    private List<Account> accounts;

    /* 
    REQUIRES: a user's username and password must be stringlength > 0
    EFFECTS: Sets up a user profile for the password vault. Creates a blank
            profile with an empty list. The user MUST remember their password.
    */
    public User(String username, String password) {
        this.username = username;
        this.password = new Password(password);
        this.accounts = new ArrayList<>();
    }

    // getters
    public String getUsername() {
        return this.username; // stub
    }

    public Password getPassword() {
        return this.password; // stub
    }

    public List<Account> getListOfAccounts() {
        return this.accounts; // stub
    }
    // setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String pass) {
        this.password = new Password(pass);
    }

    public void setAccountList(List<Account> accounts) {
        this.accounts = accounts;
    }
    /* 
    REQUIRES: can only add unique account name/website name pairs
    MODIFIES: this
    EFFECTS: adds account to the user's profile
    */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /* 
    MODIFIES: this
    EFFECTS: removes account from the user's profile
    */
    public void removeAccount(Account account) {
        this.accounts.remove(account);
    }

    /* 
    EFFECTS: returns number of accounts user has
    */
    public int totalAccounts() {
        return accounts.size();
    }

    /* 
    EFFECTS: returns number of Websites a user has an account for
    */
    public int totalWebsites() {
       return listAllWebsites().size();
    }

    /* 
    EFFECTS: returns list of distinct websites user has an account for
    */
    public List<Website> listAllWebsites() {
        List<Website> websites = new ArrayList<>();
        for (Account acc : accounts) {
            Website web = acc.getWebsite();
            if (!websites.contains(web)) {
                websites.add(web);
            }
        }
        return websites;
    }

    // EFFECTS: Returns count of accounts on a specific website
    public int numberOfAccountsOnWebsite(Website website) {
        int count = 0; //stubs
        for (Account acc : accounts) {
            if (acc.getWebsite().equals(website)) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: finds an account based on website and account name
    public Account findAccountWebsiteAccountName(String username, String website) {
        Account accountFound = null;
        for (Account acc : accounts) {
            if (username.equals(acc.getUsername()) && website.equals(acc.getWebsite().getName())) {
                accountFound = acc;
                break;
            }
        }

        return accountFound;

    }

    /* 
    EFFECTS: returns list of distinct passwords in accounts
    */
    public List<String> listAllPasswords() {
        return accounts.stream().map(a -> a.getPassword().getPassword()).distinct().collect(Collectors.toList()); //stub
    }

    /* 
    EFFECTS: returns list of account names
    */
    public List<String> listAllUsernames() {
        return accounts.stream().map(a -> a.getUsername()).distinct().collect(Collectors.toList()); //stub
    }

    /* 
    EFFECTS: returns true if password matches
    */
    public boolean verifyPassword(String pass) {
        return password.getPassword().equals(pass);
    }

}
