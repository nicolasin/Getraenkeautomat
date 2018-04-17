package de.arvato.crmit.automat.ex;

public class EinzahlungZuGeringException extends GetraenkeautomatException {

	public EinzahlungZuGeringException(double preisInCent, int einzahlungGesamt) {
		super("Der Preis f�r das Getr�nk betr�gt " + preisInCent + ", es wurden aber nur " + einzahlungGesamt + " eingezahlt");
	}

	private static final long serialVersionUID = 3996567790103413648L;

}
