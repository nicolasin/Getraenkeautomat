package de.arvato.crmit.automat;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.arvato.crmit.automat.ex.EinzahlungZuGeringException;
import de.arvato.crmit.automat.ex.GetraenkeautomatException;
import de.arvato.crmit.automat.ex.WechselgeldAusgabeNichtMoeglichException;

public class KasseTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Test
	public void testWechselgeldAusgeben() throws GetraenkeautomatException {
		Kasse k = new Kasse();
		GetraenkeTyp typ = new GetraenkeTyp("foo", 120);
		Muenze[] wechselgeld = k.wechselgeldAusgeben(typ, Muenze.ZWEI_EURO);

		List<Muenze> asList = Arrays.asList(wechselgeld);
		int sum = Muenze.getWertInCent(wechselgeld);
		long anzahlFuenfzigCent = asList.stream().filter(m -> m.equals(Muenze.FUENFZIG_CENT)).count();
		long anzahlZwanzigCent = asList.stream().filter(m -> m.equals(Muenze.ZWANZIG_CENT)).count();
		long anzahlZehnCent = asList.stream().filter(m -> m.equals(Muenze.ZEHN_CENT)).count();

		assertEquals(80, sum);
		assertEquals(1, anzahlFuenfzigCent);
		assertEquals(1, anzahlZwanzigCent);
		assertEquals(1, anzahlZehnCent);
		
		wechselgeld = k.wechselgeldAusgeben(typ, Muenze.EIN_EURO, Muenze.ZWANZIG_CENT);
		assertEquals(0, wechselgeld.length);
		
		List<Muenzschacht> schaechte = new ArrayList<>();
		for (Muenze m : Muenze.values()) {
			Muenzschacht s = new Muenzschacht(m);
			switch (m) {
			case FUENFZIG_CENT:
				s.setAnzahlMuenzen(0);
				break;
			default:
				s.setAnzahlMuenzen(10);
				break;
			}
			schaechte.add(s);
		}
		k.setMuenzschaechte(schaechte);
		
		
		wechselgeld = k.wechselgeldAusgeben(typ, Muenze.ZWEI_EURO);
		
		asList = Arrays.asList(wechselgeld);
		anzahlFuenfzigCent = asList.stream().filter(m -> m.equals(Muenze.FUENFZIG_CENT)).count();
		anzahlZwanzigCent = asList.stream().filter(m -> m.equals(Muenze.ZWANZIG_CENT)).count();
		anzahlZehnCent = asList.stream().filter(m -> m.equals(Muenze.ZEHN_CENT)).count();
		
		assertEquals(0, anzahlFuenfzigCent);
		assertEquals(4, anzahlZwanzigCent);
		assertEquals(0, anzahlZehnCent);
	}

	@Test
	public void testWechselgeldAusgebenNichtGenugEingezahlt() throws GetraenkeautomatException {
		Kasse k = new Kasse();
		GetraenkeTyp typ = new GetraenkeTyp("foo", 120);
		expected.expect(EinzahlungZuGeringException.class);
		k.wechselgeldAusgeben(typ, Muenze.EIN_EURO);
	}

	@Test
	public void testWechselgeldAusgebenNichtMoeglich() throws GetraenkeautomatException {
		Kasse k = new Kasse();
		
		List<Muenzschacht> schaechte = new ArrayList<>();
		for (Muenze m : Muenze.values()) {
			Muenzschacht s = new Muenzschacht(m);
			s.setAnzahlMuenzen(0);
			schaechte.add(s);
		}
		k.setMuenzschaechte(schaechte);

		GetraenkeTyp typ = new GetraenkeTyp("foo", 120);
		expected.expect(WechselgeldAusgabeNichtMoeglichException.class);
		k.wechselgeldAusgeben(typ, Muenze.ZWEI_EURO);
	}

}
