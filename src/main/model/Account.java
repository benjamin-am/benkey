package model;

// An can only have one account-website combo to store passwords. 
public class Account {
    // fields

    /* 
    REQUIRES: website, username, password are all length > 1.
    EFFECTS: sets up a new Account. Website is name of the website for the account;
            userName is username for that website; password is password for that website;
    */
    public Account(String website, String userName, String password) {
        //stub
    }

    public Password getPassword() {
        //stub 
        return new Password();
    }

    public Website getWebsite() {
        //stub 
        return new Website();
    }

    public String getUsername() {
        //stub 
        return "";
    }

    public void setPassword(String password) {
        // stub
    }

    public void setUsername(String username) {
        // stub
    }

    public void setWebsite(String website) {
        //stub
    }

}
