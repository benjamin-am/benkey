package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.User;

// Most of this class is based off of the JsonReader example 
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
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
        // String name = jsonObject.getString("name");

        // User wr = new User(name);
        // addThingies(wr, jsonObject);
        return null;
    }

    // MODIFIES: user
    // EFFECTS: parses accounts from JSON object and adds them to user
    private void addAccounts(User user, JSONObject jsonObject) {
        // JSONArray jsonArray = jsonObject.getJSONArray("thingies");
        // for (Object json : jsonArray) {
        //     JSONObject nextThingy = (JSONObject) json;
        //     addThingy(wr, nextThingy);
        // }
    }

    // MODIFIES: user
    // EFFECTS: parses account from JSON object and adds it to user
    private void addThingy(User user, JSONObject jsonObject) {
        // String name = jsonObject.getString("name");
        // Category category = Category.valueOf(jsonObject.getString("category"));
        // Thingy thingy = new Thingy(name, category);
        // wr.addThingy(thingy);
    }
}
