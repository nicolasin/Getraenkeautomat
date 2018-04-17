package de.arvato.crmit.automat;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.arvato.crmit.automat.ex.EinzahlungZuGeringException;
import de.arvato.crmit.automat.ex.GetraenkNichtVerfuegbarException;
import de.arvato.crmit.automat.ex.GetraenkeautomatException;

public class GetraenkeautomatTest implements GetreankeautomatTestKonstantenI {
	
	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Test
	public void testKaufen() throws GetraenkeautomatException {
		Getraenkeautomat a = new Getraenkeautomat();
		Getraenkewunsch w = new Getraenkewunsch(COLA);
		GetraenkUndWechselgeld kaufen = a.kaufen(w, Muenze.ZWEI_EURO);
		assertNotNull(kaufen);
		assertNotNull(kaufen.getGetraenk());
		assertEquals(COLA, kaufen.getGetraenk().getTyp());
		assertNotNull(kaufen.getWechselgeld());
	}
	
	@Test
	public void testKaufenZuWenigGeld() throws GetraenkeautomatException {
		Getraenkeautomat a = new Getraenkeautomat();
		Getraenkewunsch w = new Getraenkewunsch(COLA);
		expected.expect(EinzahlungZuGeringException.class);
		a.kaufen(w, Muenze.ZEHN_CENT);
	}
	
	@Test
	public void testKaufenFalscherTyp() throws GetraenkeautomatException {
		Getraenkeautomat a = new Getraenkeautomat();
		Getraenkewunsch w = new Getraenkewunsch(new GetraenkeTyp("Foo", 100));
		expected.expect(GetraenkNichtVerfuegbarException.class);
		a.kaufen(w, Muenze.ZEHN_CENT);
	}

}
