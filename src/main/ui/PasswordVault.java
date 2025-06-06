package ui; 

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.*;
// Most of PasswordVault is based on TellerApp, PuppyCaller ui code from class
// Password Vault allows a user to login to their Password vault, and allows the user to access and
// modify their accounts, websites, and passwords through the terminal

public class PasswordVault {
    private Scanner scanner;
    private boolean loggedIn;
    private User user;
    
    // EFFECTS: launches Password Vault loop for the user to interact with
    public PasswordVault() {
        init();
        String input = "";
        while (!(input.equals("q") || input.equals("l") || input.equals("n"))) {
            input = scanner.next().toLowerCase();
        }

        loginSwitch(input);
        if (!loggedIn) {
            return;
        }

        menuLoop();
        System.out.println("Good bye " + this.user.getUsername());
        printDivider();
    }

    // EFFECTS: go through main user menu while logged in
    public void menuLoop() {
        while (loggedIn) {
            displayMenu();
            String input = this.scanner.next();
            menuSwitch(input);
            if (!loggedIn) {
                break;
            }
            printDivider();
        }
    }

    // EFFECTS: selects an option from the main menu
    @SuppressWarnings("methodlength")
    public void menuSwitch(String input) {
        input = input.toLowerCase();
        printDivider();
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
                printAccountsWithPassword();
                printPasswords();
                break;
            case "w":
                viewWebsites();
                break;
            case "u":
                printUsernames();
                break;
            case "s":
                Saving.saveAccount(this.user, scanner);
                break;
        }
    }


    // EFFECTS: prints website information, allows user to see account information for a specific website
    public void viewWebsites() {
        if (user.totalAccounts() == 0) {
            System.out.println("You have 0 accounts, no websites to display");
            return;
        }
        printWebsites();
        System.out.println("Would you like to see account information for a specific website? (Y/N)");
        String input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            getAccountsForWebsite();
        }
    }

    // EFFECTS: gets all account information for a specific website, allows user to modify account if wanted
    public void getAccountsForWebsite() {
        System.out.println("Please enter website URL (case-sensitive): ");
        String url = scanner.next();
        System.out.println("Please enter website name (case-sensitive): ");
        String name = scanner.next();
        Website website = user.websiteGenerator(url, name);
        List<Account> accounts = user.findAccountsOnWebsite(website);
        printAccountsWithPassword(accounts);
        System.out.println("Would you like to modify any accounts? (Y/N)");
        String input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            modifyAccounts();
        }
    }
    
    // MODIFIES: this
    // EFFECTS: logout and end PasswordVault session
    public void logout() {
        this.loggedIn = false;
        System.out.println("Would you like to save? (Y/N)");
        String input = scanner.next();
        input.toLowerCase();
        if (input.equals("y")) {
            Saving.saveAccount(this.user, scanner);
        }
        System.out.println("logging out...");
    }

    // EFFECTS: view all user accounts. Also initiates modificiation if user wants to 
    //          modify their accounts.
    public void viewAccounts() {
        System.out.println("You have " + user.totalAccounts() + " accounts");
        if (user.totalAccounts() > 0) {
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
    }

    // EFFECTS: modify accounts menu and menu selection
    public void modifyAccounts() {
        printDivider();
        System.out.println("Please select an option");
        System.out.println("Enter 'R' to remove account");
        System.out.println("Enter 'U' to update account website, username, or password");
        System.out.println("Enter 'Q' to quit and logout");
        String input;
        input = scanner.next().toLowerCase();
        processModifyAccountInput(input);
    }

    // EFFECTS: process modify accounts menu and menu selection
    public void processModifyAccountInput(String input) {
        switch (input) {
            case "r":
                removeAccountFromUser();
                return;
            case "u":
                updateAccountInformationDialogue();
                break;
            case "q":
                return;
        }
    }

    // EFFECTS: prints account update information dialogue and selects account to be updated
    public void updateAccountInformationDialogue() {
        System.out.println("Which account would you like to modify?");
        Account account = findAccount();
        updateAccountInformation(account);
        System.out.println("Would you like to modify another account? (Y/N)");
        String input;
        input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            printAccounts();
            updateAccountInformationDialogue();
        }
    }


    // EFFECTS: updates account informaiton
    public void updateAccountInformation(Account account) {
        String input;
        System.out.println("Update username? (Y/N)");
        input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            updateUsername(account);
        }
        System.out.println("Update password? (Y/N)");
        input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            updatePassword(account);
        }

        System.out.println("Update website? (Y/N)");
        input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            updateWebsite(account);
        } 
    }

    // MODIFIES: account
    // EFFECTS: updates account username
    public void updateUsername(Account account) {
        String input;
        System.out.println("Enter new username: ");
        input = scanner.next();
        if (input.length() > 0) {
            account.setUsername(input);
            System.out.println("Username set to " + input);
        }
    }

    // MODIFIES: account
    // EFFECTS: updates account website
    public void updateWebsite(Account account) {
        String url;
        String name;
        System.out.println("Enter new URL: ");
        url = scanner.next();
        if (url.length() > 0) {
            System.out.println("Enter new Website Name: ");
            name = scanner.next();

            Website web = user.websiteGenerator(name, url);
            account.setWebsite(web);
            System.out.println("Website set to URL " + url + " and name " + name);
        }
    }

    // MODIFIES: account
    // EFFECTS: updates account password
    public void updatePassword(Account account) {
        String input;
        System.out.println("Would you like us to randomize a password? (Y/N) ");
        input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            String pass = User.generateRandomPassword();
            account.setPassword(pass);
            System.out.println("Password set to randomized password " + pass);
        } else {
            System.out.println("Enter new password: ");
            input = scanner.next();
            if (input.length() > 0) {
                account.setPassword(input);
                System.out.println("Password set to " + input);
            }
        }
    }

    // EFFECTS: finds an account from user profile based on account name and website name
    public Account findAccount() {
        String username;
        String website;
        System.out.println("Please enter account name (case-sensitive): ");
        username = scanner.next();
        System.out.println("Please enter website name (case-sensitive): ");
        website = scanner.next();
        Account account;
        account = user.findAccountWebsiteAccountName(username, website);
        return account;
    }

    // REQUIRES: account to be in listOfAccounts
    // MODIFIES: this
    // EFFECTS: remove account from user profile
    public void removeAccountFromUser() {
        Account accountToRemove;
        accountToRemove = findAccount();
        this.user.removeAccount(accountToRemove);
        System.out.println("Would you like to remove another account? (Y/N)");
        String input;
        input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            removeAccountFromUser();
        }
    }

    // EFFECTS: prints user's accounts
    public void printAccounts() {
        for (Account acc : user.getListOfAccounts()) {
            System.out.println("Account name: " + acc.getUsername() 
                             + " for website: " + acc.getWebsite().getName());
        }
    }

    // EFFECTS: prints user's usernames
    public void printUsernames() {
        System.out.println("You have " + user.listAllUsernames().size() + " distinct usernames");
        System.out.println("Here are your usernames");
        for (String username : user.listAllUsernames()) {
            System.out.println(username);
        }
    }

    // EFFECTS: prints list of accounts with their passwords 
    public void printAccountsWithPassword(List<Account> accounts) {
        for (Account acc : accounts) {
            System.out.println("Account name: " + acc.getUsername() 
                            + " for website: " + acc.getWebsite().getName()
                            + " with password: "
                            + acc.getPassword().getPassword());
        }
    }

    // EFFECTS: prints the user's list of accounts with their passwords 
    public void printAccountsWithPassword() {
        for (Account acc : user.getListOfAccounts()) {
            System.out.println("Account name: " 
                        + acc.getUsername() 
                        + " for website: " 
                        + acc.getWebsite().getName() 
                        + " with password: "
                        + acc.getPassword().getPassword());
        }
    }

    // EFFECTS: prints a user's passwords and count of distinct passwords
    public void printPasswords() {
        System.out.println("You have " + user.listAllPasswords().size() + " distinct passwords");
        for (String pass : user.listAllPasswords()) {
            System.out.println(pass);
        }
    }

    // EFFECTS: prints a user's websites tied to their accounts and count of accounts on website
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


    // EFFECTS: generates a new account
    public Account generateNewAccount() {
        System.out.println("Please enter the url of the website: ");
        String url = scanner.next();
        System.out.println("Please enter the name of the website: ");
        String name = scanner.next();
        Website website = user.websiteGenerator(url, name);

        System.out.println("Please enter the username of the account: ");
        String user = scanner.next();
        System.out.println("Would you like us to randomize a password? (Y/N) ");
        String input = scanner.next().toLowerCase();
        String pass = "";
        if (input.equals("y")) {
            pass = User.generateRandomPassword();
            System.out.println("Password is set to " + pass);
        } else {
            System.out.println("Please enter password");
            pass = scanner.next();
        }
        Account account = new Account(website, user, pass);
        return account;
    }

    // MODIFIES: this
    // EFFECTS: adds a new account to user profile
    public void addNewAccount() {
        Account account = generateNewAccount();
        System.out.println("New account created for " 
                    + account.getWebsite().getName() 
                    + " with username " 
                    + account.getUsername() 
                    + " and password " 
                    + account.getPassword().getPassword());
        this.user.addAccount(account);
        System.out.println("Would you like to add another account (Y/N)?");
        String input = scanner.next().toLowerCase();
        if (input.equals("y")) {
            addNewAccount();
        } 
    }


    // EFFECTS: login controls
    public void loginSwitch(String input) {
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
    public void displayMenu() {
        System.out.println("What would you like to do today?");
        printDivider();
        System.out.println("Enter 'A' to add new account");
        System.out.println("Enter 'V' to view or manage accounts");
        System.out.println("Enter 'P' to view passwords");
        System.out.println("Enter 'W' to view websites");
        System.out.println("Enter 'U' to view usernames");
        System.out.println("Enter 'S' to save user profile");
        System.out.println("Enter 'Q' to quit and logout");
    }

    // EFFECTS: print login menu statements
    public void displayLoginMenu() {
        System.out.println("Enter 'L' to login");
        System.out.println("Enter 'N' to create a new user profile");
        System.out.println("Enter 'Q' to quit");
    }

    // REQUIRES: new user has to be unique
    // MODIFIES: this
    // EFFECTS: creates a new user profile
    public void createNew() {
        System.out.println("Create new account!");
        printDivider();
        System.out.println("Please enter your username");
        String user = scanner.next();
        while (Saving.checkUserExists(user)) {
            System.out.println("That user already exists. Please enter a new username!");
            user = scanner.next();
        }
        System.out.println("Please enter your password");
        String pass = scanner.next();
        this.user = new User(user, pass);
        this.loggedIn = true;
        welcomeUser();
    }

    // EFFECTS: prints welcome message
    public void welcomeUser() {
        System.out.println("Welcome " + this.user.getUsername());
    }

    // MODIFIES: this
    // EFFECTS: initialize scanner and prints initial statements
    public void init() {
        System.out.println("Welcome to benkey, a super cool password vault");
        printDivider();
        this.scanner = new Scanner(System.in);
        System.out.println("Would you like to login or make a new user profile?");
        printDivider();
        displayLoginMenu();
    }

    // since no persistence, just test account for now
    // REQUIRES: never run if loggedIn is true
    // MODIFIES: this
    // EFFECTS: attempts to login
    public void login() {
        printDivider();
        System.out.println("Please enter your login! (press q to quit)");
        
        
        while (!loggedIn) {
            String input = this.scanner.next();
            if (input.equals("q")) {
                return;
            } 

            try {
                User user = Login.loginToAccount(input);
                if (passwordEntry(user)) {
                    this.user = user;
                    this.loggedIn = true;
                } else {
                    System.out.println("Please enter your login! (press q to quit)");
                }
            } catch (IOException e) {
                System.out.println("Hmm... username not found. Please try again!");
            }
        }
        welcomeUser();
    }

    // EFFECTS: checks if password entered is correct for user.
    private boolean passwordEntry(User user) {
        System.out.println("Please enter password");
        String input = this.scanner.next();
        if (input.equals(user.getPassword().getPassword())) {
            return true;
        } else {
            System.out.println("Incorrect password entered. Would you like to continue? (Y/N)");
            input = this.scanner.next().toLowerCase();
            if (input.equals("y")) {
                return passwordEntry(user);
            }
            return false;
        }
    }
        
    // EFFECTS: prints out a divider 
    public void printDivider() {
        System.out.println("--__--__--__--__--__--__--__--__--__--__--__--__--");
    }
}
