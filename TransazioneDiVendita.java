package agenzia_immobiliare;

public class TransazioneDiVendita implements Comparable<TransazioneDiVendita>{
	private int idTransazione;
	private String idSchedaImmobile;
	private String codiceFiscaleVenditore;
	private String codiceFiscaleAcquirente;
	private double importo;
	
	public TransazioneDiVendita(int idTransazione, String idSchedaImmobile, String codiceFiscaleVenditore, String codiceFiscaleAcquirente, double importo) {
		this.idTransazione = idTransazione;
		this.idSchedaImmobile = idSchedaImmobile;
		this.codiceFiscaleVenditore = codiceFiscaleVenditore;
		this.codiceFiscaleAcquirente = codiceFiscaleAcquirente;
		this.importo = importo;
	}
	
	public int getIdTransazione() {
		return idTransazione;
	}
	
	public String getIdSchedaImmobile() {
		return idSchedaImmobile;
	}

	public String getCodiceFiscaleVenditore() {
		return codiceFiscaleVenditore;
	}

	public String getCodiceFiscaleAcquirente() {
		return codiceFiscaleAcquirente;
	}

	public double getImporto() {
		return importo;
	}

	@Override
	public int compareTo(TransazioneDiVendita o) {
		if(this.importo == o.getImporto())	{
			return this.idSchedaImmobile.compareTo(o.getIdSchedaImmobile());
		}
		return (int) (this.importo-o.getImporto());
	}

	@Override
	public String toString() {
		return idTransazione+" "+idSchedaImmobile+" "+codiceFiscaleVenditore+" "+codiceFiscaleAcquirente+" "+importo;
	}
	
	//identificativo transazione ed immobile, codice fiscale venditore ed acquirente, e importo
	
	
}
