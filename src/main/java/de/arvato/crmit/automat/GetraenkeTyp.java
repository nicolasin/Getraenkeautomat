package de.arvato.crmit.automat;

public class GetraenkeTyp {
	
	private String bezeichnung;
	private int preisInCent;
	
	public GetraenkeTyp(String bezeichnung, int preisInCent) {
		super();
		this.bezeichnung = bezeichnung;
		this.preisInCent = preisInCent;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public int getPreisInCent() {
		return preisInCent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetraenkeTyp other = (GetraenkeTyp) obj;
		if (bezeichnung == null) {
			if (other.bezeichnung != null)
				return false;
		} else if (!bezeichnung.equals(other.bezeichnung))
			return false;
		return true;
	}
	
}
