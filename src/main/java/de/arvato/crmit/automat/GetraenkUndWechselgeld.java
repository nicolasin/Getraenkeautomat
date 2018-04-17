package de.arvato.crmit.automat;

public class GetraenkUndWechselgeld {
	
	private Getraenk getraenk;
	private Muenze[] wechselgeld;
	
	public GetraenkUndWechselgeld(Getraenk getraenk, Muenze... wechselgeld) {
		super();
		this.getraenk = getraenk;
		this.wechselgeld = wechselgeld;
	}

	public Getraenk getGetraenk() {
		return getraenk;
	}

	public Muenze[] getWechselgeld() {
		return wechselgeld;
	}

}
