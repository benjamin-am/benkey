package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// A user of the password vault. Has a login (username/password) and has a list of accounts
// A user can only have one account-website combo to store passwords. 
public class User implements Writable {
    private String username;
    private Password password;
    private List<Account> accounts;
    private EventLog el = EventLog.getInstance();

    /* 
    REQUIRES: a user's username and password must be stringlength > 0
    EFFECTS: Sets up a user profile for the password vault. Creates a blank
            profile with an empty list. The user MUST remember their password.
    */
    public User(String username, String password) {
        this.username = username;
        this.password = new Password(password);
        this.accounts = new ArrayList<>();
        el.logEvent(new Event("Created new user " + username));
    }

 /* 
    REQUIRES: a user's username and password must be stringlength > 0
    EFFECTS: Sets up a user profile for the password vault. Creates a blank
            profile with an empty list. The user MUST remember their password.
    */
    public User(String username, Password password) {
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
        el.logEvent(new Event("Accessed user " + username));
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
        el.logEvent(new Event("Set new username to " + username));
    }

    public void setPassword(String pass) {
        this.password = new Password(pass);
        el.logEvent(new Event("Set new password for " + username));
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
        el.logEvent(new Event("Added account " 
                    + account.getUsername() + " for website " 
                    + account.getWebsite().getName() 
                    + " for user " + username));
    }

    /* 
    REQUIRES: can only add unique account name/website name pairs
    MODIFIES: this
    EFFECTS: adds account to the user's profile
    */
    public void addAccount(Account account, boolean readFromData) {
        if (readFromData) {
            this.accounts.add(account);
            el.logEvent(new Event("Read account from json for " 
                        + account.getUsername() + " for website " 
                        + account.getWebsite().getName() 
                        + " for user " + username));
        } else {
            addAccount(account);
        }
    }

    /* 
    MODIFIES: this
    EFFECTS: removes account from the user's profile
    */
    public void removeAccount(Account account) {
        this.accounts.remove(account);
        el.logEvent(new Event("Removed account " 
                    + account.getUsername() + " for website " 
                    + account.getWebsite().getName() 
                    + " for user " + username));
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


    // EFFECTS: returns website with max (highest) accounts
    public Website maxAccountsWebsite() {
        int maxAccounts = 0;
        Website maxWebsite = null;
        for (Website website : listAllWebsites()) {
            if (numberOfAccountsOnWebsite(website) > maxAccounts) {
                maxWebsite = website;
                maxAccounts = numberOfAccountsOnWebsite(website);
            }
        }
        return maxWebsite;
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
        int count = 0; 
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

    // EFFECTS: returns list of accounts on a specific website
    public List<Account> findAccountsOnWebsite(Website website) {
        List<Account> accountsFound = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getWebsite().equals(website)) {
                accountsFound.add(acc);
            }
        }
        return accountsFound;
    }

    /* 
    EFFECTS: returns list of distinct passwords in accounts
    */
    public List<String> listAllPasswords() {
        return accounts.stream().map(a -> a.getPassword().getPassword()).distinct().collect(Collectors.toList()); 
    }

    /* 
    EFFECTS: returns list of account names
    */
    public List<String> listAllUsernames() {
        return accounts.stream().map(a -> a.getUsername()).distinct().collect(Collectors.toList()); 
    }

    /* 
    EFFECTS: returns true if password matches
    */
    public boolean verifyPassword(String pass) {
        return password.getPassword().equals(pass);
    }

    // taken from JSONSERIALIZATIONDEMO
    //EFFECTS: turns object into JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("user", username);
        json.put("password", password.toJson());
        json.put("accounts", accountsToJson());
        return json;
    }

    // taken from JSONSERIALIZATIONDEMO
    //EFFECTS: turns list of accounts into JSON
    public JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Account a : accounts) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: if website exists in list of websites, return it, else make a new one and return it
    public Website websiteGenerator(String url, String name) {
        Website website = null;
        for (Website web : listAllWebsites()) {
            if (web.getUrl().equals(url)) {
                website = web;
            }
        }
        if (website == null) {
            website = new Website(name, url);
        }
        return website;
    }

    // EFFECTS: generate a random string for passwords 
    public static String generateRandomPassword() {
        String availableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" 
                                + "1234567890!@#$%^&*";
        Random rand = new Random();
        String generated = "";
        for (int i = 0; i < 20; i++) {
            int randInt = rand.nextInt(availableChars.length());
            generated = generated + availableChars.charAt(randInt);
        }
        return generated;
    }

    // EFFECTS: check if user has any accounts
    public boolean userHasNoAccounts() {
        return totalAccounts() == 0;
    }

}
