package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Majority of these tests were derived from JsonSerializationDemo
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
            user.getUsername();
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderUserNoAccounts() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyAccounts.json");
        try {
            User user = reader.read();
            assertEquals("Ben", user.getUsername());
            assertEquals(0, user.totalAccounts());
            assertEquals(0, user.totalWebsites());
            assertEquals("BadPassC1", user.getPassword().getEncryptPassword());
            assertEquals("BadPass", user.getPassword().getPassword());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderUserWithAccounts() {
        JsonReader reader = new JsonReader("./data/testWriterWithAccounts.json");
        try {
            User user = reader.read();
            assertEquals("Erica", user.getUsername());
            assertEquals(3, user.totalAccounts());
            assertEquals(2, user.totalWebsites());
            assertEquals("BadPass2C1", user.getPassword().getEncryptPassword());
            assertEquals("BadPass2", user.getPassword().getPassword());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}