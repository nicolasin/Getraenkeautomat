package de.arvato.crmit.automat;

public abstract class AbstractObjektMitGetraenketyp {
	
	private GetraenkeTyp typ;

	public AbstractObjektMitGetraenketyp(GetraenkeTyp typ) {
		this.typ = typ;
	}

	public GetraenkeTyp getTyp() {
		return typ;
	}

}
