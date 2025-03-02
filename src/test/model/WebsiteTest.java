package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WebsiteTest {
    private Website w1;
    private Website w2;
    private Website w3;
    private Website w4;
    private Website w5;
    private Website w6;
    private Website w7;
    private Password p1;

    @BeforeEach
    void runBefore() {
        w1 = new Website("Test", "test.com");
        w2 = new Website("Test", "test.com");
        w3 = new Website(null, "example.com");
        w4 = new Website("Example", null);
        w5 = new Website(null, null);
        w6 = new Website("Example", "doesn'tmatter.com");
        w7 = new Website("Example", null);
        p1 = new Password();
    }

    @Test
    void testConstructor() {
        assertEquals("Test", w1.getName());
        assertEquals("test.com", w1.getUrl());
    }

    @Test
    void testNameSetter() {
        w1.setName("anotherName");
        assertEquals("anotherName", w1.getName());
        w1.setName("anotherName");
        w1.setName("anotherName");
        assertEquals("anotherName", w1.getName());
        
        w1.setName("anotherName");
        w1.setName("a");
        assertEquals("a", w1.getName());
    }

    @Test
    void testUrlSetter() {
        w1.setUrl("another.com");
        assertEquals("another.com", w1.getUrl());
        w1.setUrl("another.com");
        w1.setUrl("another.com");
        assertEquals("another.com", w1.getUrl());
        
        w1.setUrl("anotherName");
        w1.setUrl("a");
        assertEquals("a", w1.getUrl());
    }

    @Test
    void testHashCode() {
        assertEquals(w2.hashCode(), w1.hashCode());
        assertEquals(w1.hashCode(), w1.hashCode());
        assertEquals(w2.hashCode(), w2.hashCode());
        assertEquals(w3.hashCode(), w3.hashCode());
        assertEquals(w4.hashCode(), w4.hashCode());
        assertEquals(w5.hashCode(), w5.hashCode());
        assertNotEquals(w2.hashCode(), w3.hashCode());
        assertNotEquals(w2.hashCode(), w4.hashCode());
        assertNotEquals(w2.hashCode(), w5.hashCode());
    }

    @Test
    void testEquals() {
        assertFalse(w1.equals(w5));
        assertFalse(w1.equals(w4));
        assertFalse(w1.equals(w5));
        assertTrue(w5.equals(w5));
        assertTrue(w4.equals(w4));
        assertTrue(w3.equals(w3));
        assertFalse(w4.equals(w5));
        assertFalse(w3.equals(w5));
        assertFalse(w3.equals(w4));
        assertFalse(w1.equals(p1));
        assertFalse(w1.equals(null));
        assertFalse(w4.equals(w6));
        assertTrue(w4.equals(w7));
        assertFalse(w6.equals(w4));
    }
}

