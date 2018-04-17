package de.arvato.crmit.automat;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetraenkeTypTest {

	@Test
	public void testHashCode() {
		GetraenkeTyp t = new GetraenkeTyp(null, 0);
		int hashCode = t.hashCode();
	}

	@Test
	public void testEqualsObject() {
		GetraenkeTyp t1 = new GetraenkeTyp("foo", 0);
		
		boolean equals = t1.equals(null);
		assertFalse(equals);
		equals = t1.equals(new String("foo"));
		assertFalse(equals);
		
		GetraenkeTyp t2 = new GetraenkeTyp(null, 0);
		equals = t2.equals(t1);
		assertFalse(equals);
		
		GetraenkeTyp t3 = new GetraenkeTyp("bar", 0);
		equals = t3.equals(t1);
		assertFalse(equals);
		
		equals = t1.equals(t1);
		assertTrue(equals);
	}

}
