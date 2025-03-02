package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.*;

// Most of this class is based off of the JsonReader example JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // identical
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses user from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String username = jsonObject.getString("user");

        JSONObject passwordObject = jsonObject.getJSONObject("password");
        String password = passwordObject.getString("password");
        Password pass = new Password();
        pass.setEncryptedPassword(password);

        User user = new User(username, pass);
        addAccounts(user, jsonObject);
        return user;
    }

    // MODIFIES: user
    // EFFECTS: parses accounts from JSON object and adds them to user
    private void addAccounts(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(user, nextAccount);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses account from JSON object and adds it to user
    private void addAccount(User user, JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        JSONObject websiteObject = jsonObject.getJSONObject("website");
        String website = websiteObject.getString("nameWebsite");
        String url = websiteObject.getString("url");
        Website site = new Website(website, url);

        JSONObject passwordObject = jsonObject.getJSONObject("password");
        String password = passwordObject.getString("password");
        Password pass = new Password();
        pass.setEncryptedPassword(password);
        Account account = new Account(site, username, pass);
        user.addAccount(account);
    }
}
