package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Majority of these tests were derived from JsonSerializationDemo
public class JsonWriterTest {
    private Account a1;
    private Account a2;
    private Account a3;
    private Website w1;
    private Website w2;

    @BeforeEach
    void runBefore() {
        w1 = new Website("test", "test.com");
        w2 = new Website("test1", "test.com1");
        a1 = new Account(w2, "a", "a");
        a2 = new Account(w1, "c", "d");
        a3 = new Account(w1, "f", "g");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User("Ben", "BadPass");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
            writer.write(user);
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testUserNoAccounts() {
        try {
            User user = new User("Ben", "BadPass");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccounts.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccounts.json");
            user = reader.read();
            assertEquals("Ben", user.getUsername());
            assertEquals(0, user.totalAccounts());
            assertEquals(0, user.totalWebsites());
            assertEquals("BadPassC1", user.getPassword().getEncryptPassword());
            assertEquals("BadPass", user.getPassword().getPassword());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testUserWithAccounts() {
        try {
            User user = new User("Erica", "BadPass2");
            user.addAccount(a1);
            user.addAccount(a2);
            user.addAccount(a3);
            JsonWriter writer = new JsonWriter("./data/testWriterWithAccounts.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWithAccounts.json");
            user = reader.read();
            assertEquals("Erica", user.getUsername());
            assertEquals(3, user.totalAccounts());
            assertEquals(2, user.totalWebsites());
            assertEquals("BadPass2C1", user.getPassword().getEncryptPassword());
            assertEquals("BadPass2", user.getPassword().getPassword());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
