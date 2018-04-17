package de.arvato.crmit.automat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.arvato.crmit.automat.ex.EinzahlungZuGeringException;
import de.arvato.crmit.automat.ex.GetraenkeautomatException;
import de.arvato.crmit.automat.ex.WechselgeldAusgabeNichtMoeglichException;

public class Kasse {
	
	private List<Muenzschacht> muenzschaechte = new ArrayList<>();
	
	public Kasse() {
		super();
		init();
	}

	private void init() {
		for (Muenze m : Muenze.values()) {
			Muenzschacht schacht = new Muenzschacht(m);
			schacht.setAnzahlMuenzen(20);
			muenzschaechte.add(schacht);
		}
	}
	
	public Muenze[] wechselgeldAusgeben(GetraenkeTyp getraenkeTyp, Muenze...einzahlung) throws GetraenkeautomatException {
		int einzahlungGesamt = Muenze.getWertInCent(einzahlung);
		int getraenkePreis = getraenkeTyp.getPreisInCent();
		
		if (einzahlungGesamt < getraenkePreis) {
			throw new EinzahlungZuGeringException(getraenkePreis, einzahlungGesamt);
		}
		
		int zuVielBezahlt = einzahlungGesamt - getraenkePreis;
		
		List<Muenze> wechselgeld = new ArrayList<>();
		
		List<Muenzschacht> muenzschaechteSorted = muenzschaechte.stream().sorted().collect(Collectors.toList());
		for (Muenzschacht schacht : muenzschaechteSorted) {
			zuVielBezahlt = schacht.wechselgeldAusgeben(zuVielBezahlt, wechselgeld);
		}
		if (zuVielBezahlt > 0) {
			throw new WechselgeldAusgabeNichtMoeglichException();
		}
		
		return wechselgeld.toArray(new Muenze[wechselgeld.size()]);
	}
	
	public void setMuenzschaechte(List<Muenzschacht> muenzschaechte) {
		this.muenzschaechte = muenzschaechte;
	}

}
