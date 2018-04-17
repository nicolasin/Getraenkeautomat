package de.arvato.crmit.automat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.arvato.crmit.automat.ex.GetraenkNichtVerfuegbarException;
import de.arvato.crmit.automat.ex.GetraenkeautomatException;

public class Getraenkefach {
	
	private Map<GetraenkeTyp, Integer> getraenke = new HashMap<>();
	
	public Getraenkefach() {
		super();
		init();
	}

	private void init() {
		List<String> bezeichnungen = Arrays.asList(new String[] {"Cola", "Fanta", "Wasser", "Kaffee"});
		for (String bezeichnung : bezeichnungen) {
			getraenke.put(new GetraenkeTyp(bezeichnung, 120), 20);
		}
	}
	
	public Getraenk getGetraenk(GetraenkeTyp typ) throws GetraenkeautomatException {
		int anzahlVorhanden;
		if (!getraenke.containsKey(typ) || (anzahlVorhanden = getraenke.get(typ)) == 0) {
			throw new GetraenkNichtVerfuegbarException(typ.getBezeichnung());
		}
		anzahlVorhanden -= 1;
		getraenke.put(typ, anzahlVorhanden);
		return new Getraenk(typ);
	}

	public void zuruecklegen(Getraenk getraenk) {
		GetraenkeTyp typ = getraenk.getTyp();
		Integer anzahlVorhanden = getraenke.get(typ);
		getraenke.put(typ, anzahlVorhanden + 1);
	}

	public Map<GetraenkeTyp, Integer> getGetraenke() {
		return getraenke;
	}
	
}
