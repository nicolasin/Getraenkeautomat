package de.arvato.crmit.automat;

import java.util.List;

import com.google.common.collect.ComparisonChain;

public class Muenzschacht implements Comparable<Muenzschacht> {
	
	private Muenze muenzTyp;
	private int anzahlMuenzen;
	
	public Muenzschacht(Muenze muenzTyp) {
		super();
		this.muenzTyp = muenzTyp;
	}
	
	public void setAnzahlMuenzen(int anzahlMuenzen) {
		this.anzahlMuenzen = anzahlMuenzen;
	}

	public int wechselgeldAusgeben(int zuVielBezahlt, List<Muenze> wechselgeld) {
		int anzahlMuenzenMoeglich = muenzTyp.getAnzahlMuenzenFuerWert(zuVielBezahlt);
		int anzahlAusgabe = Math.min(anzahlMuenzenMoeglich, anzahlMuenzen);
		anzahlMuenzen -= anzahlAusgabe;
		for (int i = 0; i < anzahlAusgabe; i++) {
			wechselgeld.add(muenzTyp);
		}
		return zuVielBezahlt - muenzTyp.getWertFuerAnzahlMuenzen(anzahlAusgabe);
	}

	@Override
	public int compareTo(Muenzschacht o) {
		return ComparisonChain.start().compare(muenzTyp, o.muenzTyp).result();
	}

}
