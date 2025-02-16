package ui; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import model.*;
// Most of PasswordVault is based on TellerApp, PuppyCaller ui code from class

public class PasswordVault {
    private Scanner scanner;
    private boolean loggedIn;
    private User user;
    
    public PasswordVault() {
        System.out.println("Welcome to benkey, a super cool password vault");
        printDivider();
        init();
        System.out.println("Would you like to login or make a new user profile?");
        printDivider();
        displayLoginMenu();
        String input = "";
        while (!(input.equals("q") || input.equals("l") || input.equals("n"))) {
            input = scanner.next();
            input = input.toLowerCase();
        }
        
        loginSwitch(input);
        
        
        if (!loggedIn) {
            return;
        } 

        
        while (loggedIn) {
            displayMenu();
            input = this.scanner.next();
            menuSwitch(input);
        }

        System.out.println("Good bye " + this.user.getUsername());
        printDivider();
    }

    private void menuSwitch(String input) {
        input = input.toLowerCase();
        switch (input) {
            case "q":
                logout();
                return;
            case "a":
                addNewAccount();
                break;
            case "v":
                viewAccounts();
                break;
            case "p":
                createNew();
                break;
            case "w":
                printWebsites();
                break;
            case "m":
                displayMenu();
                break;
        }
        System.out.println("Would you like to continue? (Y/N)");
        input = scanner.next().toLowerCase();
        if (input.equals("n")) {
            logout();
        } else {
            return;
        }
    }

    private void logout() {
        loggedIn = false;
        System.out.println("logging out...");
    }

    public void viewAccounts() {
        System.out.println("You have " + user.totalAccounts() + " accounts");
        System.out.println("Here are all your accounts!");
        printAccounts();
        System.out.println("Would you like to modify any? (Y/N)");
        String input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            modifyAccounts();
        } else if (input.equals("n")) {
            return;
        }


    }

    public void modifyAccounts() {
        System.out.println("Please select an option");
        System.out.println("Enter 'R' to remove account");
        System.out.println("Enter 'U' to update account website, username, or password");
        System.out.println("Enter 'Q' to quit and logout");
        String input;
        input = scanner.next().toLowerCase();
        processModifyAccountInput(input);
    }

    public void processModifyAccountInput(String input) {
        switch (input) {
            case "r":
                removeAccountFromUser();
                return;
            case "u":
                addNewAccount();
                break;
            case "q":
                return;
    }


    private void removeAccountFromUser() {
        String input;
        System.out.println("Please enter account name: ");
        input = scanner.next().toLowerCase();
        if (user.getListOfAccounts().contains(input)) {
    }

    public void printAccounts() {
        for (Account acc : user.getListOfAccounts()) {
            System.out.println("Account name: " + acc.getUsername() 
                             + "for website: " + acc.getWebsite().getName());
        }
    }

    public void printAccountsWithPassword() {
        for (Account acc : user.getListOfAccounts()) {
            System.out.println("Account name: " 
                        + acc.getUsername() 
                        + "for website: " 
                        + acc.getWebsite().getName() 
                        + "with password: "
                         + acc.getPassword().getPassword());
        }
    }

    public void printWebsites() {
        for (Website web : user.listAllWebsites()) {
            System.out.println("Website name: " 
                        + web.getName() 
                        + " with URL: " 
                        + web.getUrl()
                        + " with " + user.numberOfAccountsOnWebsite(web)
                        + " account(s)!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new account to user profile
    public void addNewAccount() {
        System.out.println("Please enter the url of the website: ");
        String url = scanner.next();
        System.out.println("Please enter the name of the website: ");
        String name = scanner.next();
        Website website = new Website(name, url);

        System.out.println("Please enter the username of the account: ");
        String user = scanner.next();
        System.out.println("Would you like us to randomize a password? (Y/N) ");
        String input = scanner.next().toLowerCase();
        String pass = "";
        if (input.equals("y")) {
            pass = generateRandomPassword();
            System.out.println("Password is set to " + pass);
        } else {
            System.out.println("Please enter password");
            pass = scanner.next();
        }
        Account account = new Account(website, user, pass);
        System.out.println("New account created for " 
                    + website.getName() + " with username " + user + " and password " + pass);
        this.user.addAccount(account);
        System.out.println("Would you like to add another account (Y/N)?");
        input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            addNewAccount();
        } 
    }

    // generate a random string 
    public String generateRandomPassword() {
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

    // EFFECTS: login controls
    private void loginSwitch(String input) {
        switch (input) {
            case "q":
                return;
            case "l":
                login();
                break;
            case "n":
                createNew();
                break;
        }
    }
    
    // EFFECTS: print usage menu
    private void displayMenu() {
        System.out.println("What would you like to do today?");
        printDivider();
        System.out.println("Enter 'A' to add new account");
        System.out.println("Enter 'V' to view or manage accounts");
        System.out.println("Enter 'P' to view passwords");
        System.out.println("Enter 'W' to view websites");
        System.out.println("Enter 'U' to view usernames");
        System.out.println("Enter 'M' to display menu again");
        System.out.println("Enter 'Q' to quit and logout");
    }

    // EFFECTS: print login menu statements
    private void displayLoginMenu() {
        System.out.println("Enter 'L' to login");
        System.out.println("Enter 'N' to create a new user profile");
        System.out.println("Enter 'Q' to quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a new user profile
    public void createNew() {
        System.out.println("Create new account!");
        printDivider();
        System.out.println("Please enter your username");
        String user = scanner.next();
        System.out.println("Please enter your password");
        String pass = scanner.next();
        this.user = new User(user, pass);
        loggedIn = true;
        welcomeUser();
    }

    // EFFECTS: prints welcome message
    private void welcomeUser() {
        System.out.println("Welcome " + this.user.getUsername());
    }

    // MODIFIES: this
    // EFFECTS: initialize scanner
    public void init() {
        this.scanner = new Scanner(System.in);
    }

    // since no persistence, just test account for now
    // REQUIRES: never run if loggedIn is true
    // EFFECTS: attempts to login
    public void login() {
        printDivider();
        System.out.println("Please enter your login! (press q to quit)");
        while (!loggedIn) {
            String input = this.scanner.next();
            if (input.equals("testAccount")) {
                this.user = new User("ben", "funtest");
                initializeTestUser();
                this.loggedIn = true;
            } else if (input.equals("q")) {
                return;
            }
            System.out.println("Hmm... username not found. Please try again!");
        }
        welcomeUser();
    }

    // EFFECTS: sets up dummy accounts and passwords for test user
    public void initializeTestUser() {
        Website netflix = new Website("netflix", "netflix.com");
        Website amazon = new Website("amazon", "amazon.com");
        Website youtube = new Website("youtube", "youtube.com");

        Account a1 =  new Account(netflix, "bensNetflix", "badNetflixPassword");
        Account a2 =  new Account(amazon, "bensAmazon", "AmazingAmazonPassword123$#");
        Account a3 =  new Account(youtube, "bensYoutube", "MediocreYoutube123");
        user.addAccount(a1);
        user.addAccount(a2);
        user.addAccount(a3);
    }
        
    // EFFECTS: prints out a divider 
    public void printDivider() {
        System.out.println("--__--__--__--__--__--__--__--__--__--__--__--__--");
    }

    public boolean isLoggedIn() {
        return false;
    }

    public List<Website> getWebsites() {
        return new ArrayList<Website>();
    }

    public List<Account> getAccountNames() {
        return new ArrayList<Account>();
    }

    public Map<Account, Password> getAccountPasswords() {
        return new HashMap<>();
    }
}
