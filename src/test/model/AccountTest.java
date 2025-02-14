package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
    private Account a1;
    private Account a2;
    private Account a3;
    private Password p1;
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
    void testConstructor() {
        assertEquals(w2, a1.getWebsite());
        assertEquals("a", a1.getPassword().getPassword());
        assertEquals("a", a1.getUsername());
        
        assertEquals(w1, a2.getWebsite());
        assertEquals("d", a2.getPassword().getPassword());
        assertEquals("c", a2.getUsername());

        assertEquals(w1, a3.getWebsite());
        assertEquals("g", a3.getPassword().getPassword());
        assertEquals("f", a3.getUsername());
    }

    @Test
    void testGetWebsite() {
        assertEquals(w2, a1.getWebsite());
        assertEquals(w1, a2.getWebsite());
        assertEquals(w1, a3.getWebsite());
    }

    @Test
    void testGetUsername() {
        assertEquals("a", a1.getUsername());
        assertEquals("c", a2.getUsername());
        assertEquals("f", a3.getUsername());
    }

    @Test
    void testGetPassword() {
        assertEquals(new Password("a").getEncryptPassword(), a1.getPassword().getEncryptPassword());
        assertEquals("d", a2.getPassword().getPassword());
        assertEquals("g", a3.getPassword().getPassword());
    }

    @Test
    void testSetPassword() {
        assertEquals("g", a3.getPassword().getPassword());

        a3.setPassword("bad");
        assertEquals("bad", a3.getPassword().getPassword());
        a3.setPassword("basdasdassda");
        a3.setPassword("pppppp");
        assertEquals("pppppp", a3.getPassword().getPassword());
    }

    @Test
    void testSetUsername() {
        assertEquals("a", a1.getUsername());

        a1.setUsername("badUsername");
        assertEquals("badUsername", a1.getUsername());
        a1.setUsername("b");
        a1.setUsername("dasdsad");
        assertEquals("dasdsad", a1.getUsername());
    }

    @Test
    void testSetWebsite() {
        assertEquals(w2, a1.getWebsite());
        
        a1.setWebsite(w1);
        assertEquals(w1, a1.getWebsite());
        a1.setWebsite(w1);
        a1.setWebsite(w2);
        assertEquals(w2, a1.getWebsite());
    }


}
