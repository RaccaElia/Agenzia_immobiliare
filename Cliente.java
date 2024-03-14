package agenzia_immobiliare;

public class Cliente implements Comparable<Cliente>{
	
	private String codiceFiscale;
	private String nome;
	private String cognome;

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public Cliente(String codiceFiscale, String nome, String cognome) {
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
	}

	@Override
	public int compareTo(Cliente o) {
		return this.getCodiceFiscale().compareTo(o.getCodiceFiscale());
	}
}
