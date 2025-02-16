package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WebsiteTest {
    private Website w1;

    @BeforeEach
    void runBefore() {
        w1 = new Website("Test", "test.com");
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
}

