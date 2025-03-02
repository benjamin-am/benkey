package ui;

import java.io.IOException;

import model.User;
import persistence.JsonReader;

// A login Class. Not meant to be initialized, just meant to use the new persistence model
public class Login {
    private static String path = "./data/";
    private static String ext = ".json";

    // EFFECTS: nothing, class isn't meant to be initalized
    private Login() {
    }

    // EFFECTS: return user, read from file.
    public static User loginToAccount(String user) throws IOException {
        JsonReader read = new JsonReader(path + user + ext);
        User userLoad = read.read();
        return userLoad;
    }
}
