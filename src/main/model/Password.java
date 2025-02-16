package model;

// A password, stores an encrypted password
public class Password {
    private String encryptedPassword;

    /* 
    REQUIRES: password length is greater than 0, encrypted password > 0;
    EFFECTS: sets up a new password and encrypted password
    */
    public Password(String password) {
        this.encryptedPassword = encryptPassword(password);
    }

    public void setPassword(String pass) {
        this.encryptedPassword = encryptPassword(pass);
    }

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

}
