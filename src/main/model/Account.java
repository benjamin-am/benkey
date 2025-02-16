package model;

// An account contains a website, a username and a password
public class Account {
    // fields
    private Website website;
    private String username;
    private Password password;

    /* 
    REQUIRES: website, username, password are all length > 1.
             website string is everything between www. and .com.
    EFFECTS: sets up a new Account. Website is name of the website for the account;
            userName is username for that website; password is password for that website;
    */
    public Account(Website website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = new Password(password);
    }

    public Password getPassword() {
        return this.password;
    }

    public Website getWebsite() {
        return this.website;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = new Password(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

}
