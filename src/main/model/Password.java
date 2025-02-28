package model;

import org.json.JSONObject;

import persistence.Writable;

// A password, stores an encrypted password
public class Password implements Writable {
    private String encryptedPassword;

    /* 
    REQUIRES: password length is greater than 0, encrypted password > 0;
    EFFECTS: sets up a new password and encrypted password
    */
    public Password(String password) {
        this.encryptedPassword = encryptPassword(password);
    }

    // MODIFIES: this
    // EFFECTS: sets the encrypted password for the instance by encrypting the string
    public void setPassword(String pass) {
        this.encryptedPassword = encryptPassword(pass);
    }

    // MODIFIES: this
    // EFFECTS: sets the encrypted password for the instance
    public void setEncryptedPassword(String pass) {
        this.encryptedPassword = pass;
    }

    // EFFECTS: returns a decrypted password
    public String getPassword() {
        return decryptPassword();
    }

    public String getEncryptPassword() {
        return this.encryptedPassword;
    }

    /* 
    EFFECTS: creates an encrypted password
    */
    private String encryptPassword(String password) {
        return password + "C1";
    }

    /* 
    EFFECTS: decrypts an encrypted password
    */
    private String decryptPassword() {
        int size = encryptedPassword.length();
        return encryptedPassword.substring(0, size - 2);
    }

    // taken from JSONSERIALIZATIONDEMO
    //EFFECTS: turns object into JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("password", encryptedPassword);
        return json;
    }

}
