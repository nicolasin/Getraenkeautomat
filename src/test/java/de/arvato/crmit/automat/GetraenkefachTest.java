package de.arvato.crmit.automat;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.arvato.crmit.automat.ex.GetraenkNichtVerfuegbarException;
import de.arvato.crmit.automat.ex.GetraenkeautomatException;

public class GetraenkefachTest implements GetreankeautomatTestKonstantenI {
	
	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Test
	public void testGetGetraenk() throws GetraenkeautomatException {
		Getraenkefach g = new Getraenkefach();
		
		int anzahlVorher = g.getGetraenke().get(COLA);
		
		Getraenk getraenk = g.getGetraenk(COLA);
		
		int anzahlNachher = g.getGetraenke().get(COLA);
		
		assertNotNull(getraenk);
		assertEquals(COLA, getraenk.getTyp());
		assertEquals(anzahlNachher, anzahlVorher - 1);
	}

	@Test
	public void testGetGetraenkUnbekannterTyp() throws GetraenkeautomatException {
		Getraenkefach g = new Getraenkefach();
		GetraenkeTyp typ = new GetraenkeTyp("Foo", 120);
		
		expected.expect(GetraenkNichtVerfuegbarException.class);
		Getraenk getraenk = g.getGetraenk(typ);
	}

	@Test
	public void testGetGetraenkFachLeer() throws GetraenkeautomatException {
		Getraenkefach g = new Getraenkefach();
		g.getGetraenke().put(COLA, 1);
		
		assertEquals(1, (int)g.getGetraenke().get(COLA));
		Getraenk getraenk = g.getGetraenk(COLA);
		assertEquals(0, (int)g.getGetraenke().get(COLA));
		expected.expect(GetraenkNichtVerfuegbarException.class);
		getraenk = g.getGetraenk(COLA);
	}

	@Test
	public void testZuruecklegen() throws GetraenkeautomatException {
		Getraenkefach g = new Getraenkefach();
		int anzahlVorher = g.getGetraenke().get(COLA);
		Getraenk getraenk = g.getGetraenk(COLA);
		int anzahlNachher = g.getGetraenke().get(COLA);
		assertEquals(anzahlVorher - 1, anzahlNachher);
		g.zuruecklegen(getraenk);
		anzahlNachher = g.getGetraenke().get(COLA);
		assertEquals(anzahlVorher, anzahlNachher);
	}

}
