package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordTest {
    private Password p1;


    @BeforeEach
    void runBefore() {
        p1 = new Password("pass");
    }

    @Test
    void testConstructor() {
        assertEquals("pass", p1.getPassword());
        assertEquals("passC1", p1.getEncryptPassword());
    }

    @Test
    void testSetPassword() {
        assertEquals("pass", p1.getPassword());
        assertEquals("passC1", p1.getEncryptPassword());
        p1.setPassword("HUH");
        assertEquals("HUH", p1.getPassword());
        assertEquals("HUHC1", p1.getEncryptPassword());
    }

    @Test
    void testGetPassword() {
        assertEquals("pass", p1.getPassword());
        p1.setPassword("TEST");
        assertEquals("TEST", p1.getPassword());
    }

    @Test
    void testGetEncryptPassword() {
        assertEquals("passC1", p1.getEncryptPassword());
    }

    // // not the intended use for decrypt, it's supposed to be a private method
    // @Test
    // void testDecryptPassword() {
    //     assertEquals("what", p1.decryptPassword("whatC1"));
    // }

    // // not the intended use for encrypt, it's supposed to be a private method
    // @Test
    // void testEncryptPassword() {
    //     assertEquals("whatC1", p1.encryptPassword("what"));
    // }



}
