package de.arvato.crmit.automat;

public enum Muenze {
	
	ZWEI_EURO(200),
	EIN_EURO(100),
	FUENFZIG_CENT(50),
	ZWANZIG_CENT(20),
	ZEHN_CENT(10);
	
	private int wertInCent;

	Muenze(int wertInCent) {
		this.wertInCent = wertInCent;
	}
	
	public static int getWertInCent(Muenze...muenzen) {
		int wert = 0;
		for (Muenze m : muenzen) {
			wert += m.wertInCent;
		}
		return wert;
	}
	
	public int getAnzahlMuenzenFuerWert(int wert) {
		return Math.floorDiv(wert, wertInCent);
	}
	
	public int getWertFuerAnzahlMuenzen(int anzahl) {
		return wertInCent * anzahl;
	}
	
}
