package agenzia_immobiliare;

public class SchedaImmobile implements Comparable<SchedaImmobile> {

	private String idScheda;
	private String comune;
	private String indirizzo;
	private String tipologia;
	private int numLocali;
	private int superficie;
	private String descrizione;
	
	public String getIdSchedaImmobile() {
		return idScheda;
	}
	
	public String getComune() {
		return comune;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getTipologia() {
		return tipologia;
	}

	public int getLocali() {
		return numLocali;
	}

	public int getSuperficie() {
		return superficie;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public SchedaImmobile(String idScheda, String comune, String indirizzo, String tipologia, int numLocali, int superficie, String descrizione) {
		this.idScheda = idScheda;
		this.comune = comune;
		this.indirizzo = indirizzo;
		this.tipologia = tipologia;
		this.numLocali = numLocali;
		this.superficie = superficie;
		this.descrizione = descrizione;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public int compareTo(SchedaImmobile o) {
		return this.idScheda.compareTo(o.getIdSchedaImmobile());
	}
}
