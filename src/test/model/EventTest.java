package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// ATTRIBUTION: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git as per 
// "Add these classes to your model package and do not modify them in any way, other than to fix checkstyle issues." in phase 4

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
	private Event e1;
	private Event e2;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Sensor open at door");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
        e1 = new Event("Sensor open at door");   // (1)
        e2 = new Event("SCREAM");   // (1)
	}

    @Test
    public void testHashCode() {
        assertEquals(e.hashCode(), e.hashCode());
        assertTrue(e2.hashCode() != e1.hashCode());
        assertFalse(e2.equals(null));
        assertFalse(e2.equals(d));
    }
	
	@Test
	public void testEvent() throws ParseException {
		assertEquals("Sensor open at door", e.getDescription());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("1999-06-08");
        assertTrue(e.getDate().after(date));
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
	}
}
