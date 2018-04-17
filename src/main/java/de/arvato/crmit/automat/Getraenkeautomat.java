package de.arvato.crmit.automat;

import de.arvato.crmit.automat.ex.GetraenkeautomatException;

public class Getraenkeautomat {
	
	private Kasse kasse;
	private Getraenkefach getraenkefach;
	
	public Getraenkeautomat() {
		super();
		kasse = new Kasse();
		getraenkefach = new Getraenkefach();
	}
	
	public GetraenkUndWechselgeld kaufen(Getraenkewunsch auswahl, Muenze...einzahlung) throws GetraenkeautomatException {
		GetraenkUndWechselgeld getraenkUndWechselgeld;
		Getraenk getraenk = null;
		try {
			getraenk = getraenkefach.getGetraenk(auswahl.getTyp());
			Muenze[] wechselgeld = kasse.wechselgeldAusgeben(auswahl.getTyp(), einzahlung);
			getraenkUndWechselgeld = new GetraenkUndWechselgeld(getraenk, wechselgeld);
			return getraenkUndWechselgeld;
		} finally {
			if (getraenk != null) { // Exception bei Wechselgeld
				getraenkefach.zuruecklegen(getraenk);
			}
		}
	}

}
