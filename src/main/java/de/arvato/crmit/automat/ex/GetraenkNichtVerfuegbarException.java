package de.arvato.crmit.automat.ex;

public class GetraenkNichtVerfuegbarException extends GetraenkeautomatException {

	private static final long serialVersionUID = -5313609513637012917L;

	public GetraenkNichtVerfuegbarException(String bezeichnung) {
		super("Vom Typ " + bezeichnung + " ist kein Getr�nk verf�gbar!");
	}

}
