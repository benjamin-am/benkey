package model;

// An can only have one account-website combo to store passwords. 
public class Account {
    // fields

    /* 
    REQUIRES: website, username, password are all length > 1.
             website string is everything between www. and .com.
    EFFECTS: sets up a new Account. Website is name of the website for the account;
            userName is username for that website; password is password for that website;
    */
    public Account(Website website, String userName, String password) {
        //stub
    }

    public Password getPassword() {
        //stub 
        return new Password("");
    }

    public Website getWebsite() {
        //stub 
        return new Website("", "");
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

    public void setWebsite(Website website) {
        //stub
    }

}
