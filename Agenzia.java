package agenzia_immobiliare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Agenzia {

	private TreeMap<String, SchedaImmobile> schede = new TreeMap<String, SchedaImmobile>();
	private LinkedList<SchedaImmobile> listaSchede = new LinkedList<SchedaImmobile>();
	private TreeMap<String, Cliente> clienti = new TreeMap<String, Cliente>();
	private TreeMap<Integer, TransazioneDiVendita> transazioni = new TreeMap<Integer, TransazioneDiVendita>();
	boolean criterioLocali = false;
	boolean criterioSuperficie = false;
	int minimoL = 0;
	int massimoL = 0;
	int minimoS = 0;
	int massimoS = 0;
	int idTrasazione = 99;
	
	public SchedaImmobile creaScheda(String comune, String indirizzo, String tipologia, int locali, int superficie, String descrizione) {
		int conta = 0;
		for(SchedaImmobile scheda:schede.values())	{
			if(scheda.getComune().equals(comune) && scheda.getIndirizzo().equals(indirizzo) && scheda.getTipologia().equals(tipologia))	{
				return scheda;
			}
			else if(scheda.getComune().equals(comune))	{
				conta+=1;
			}
		}
		String codice = comune.toUpperCase()+(conta+1);
		SchedaImmobile ris = new SchedaImmobile(codice, comune, indirizzo, tipologia, locali, superficie, descrizione);
		schede.put(codice, ris);
		listaSchede.add(ris);
		return ris;
	}
	
	public SchedaImmobile ottieniScheda(String idSchedaImmobile) {
		return schede.get(idSchedaImmobile);
	}

	public void aggiornaScheda(String idSchedaImmobile, int superficie, String descrizione) {
		for(SchedaImmobile scheda: schede.values())	{
			if(scheda.getIdSchedaImmobile().equals(idSchedaImmobile))	{
				scheda.setDescrizione(descrizione);
				scheda.setSuperficie(superficie);
			}
		}
	}

	public Collection<SchedaImmobile> elencoSchedeOrdineDiInserimento(){
		return listaSchede;
	}
	
	public Collection<SchedaImmobile> elencoSchedeOrdineDiIdentificativo(){
		LinkedList<SchedaImmobile> listaTemp = new LinkedList<SchedaImmobile>(schede.values());
		Collections.sort(listaTemp);
		return listaTemp;
	}
	
	public Collection<SchedaImmobile> ricercaSchedeTesto(String daCercare){
		LinkedList<SchedaImmobile> ris = new LinkedList<SchedaImmobile>();
		for(SchedaImmobile scheda: schede.values())	{
			if(scheda.getComune().contains(daCercare)==true || scheda.getIndirizzo().contains(daCercare)==true || scheda.getDescrizione().contains(daCercare)==true)	{
				ris.add(scheda);
			}
		}
		return ris;
	}
	
	public void impostaCriterio(char criterio, boolean impostato, int min, int max){
		if(criterio == 'L')	{
			criterioLocali = impostato;
			minimoL = min;
			massimoL = max;
		} else if(criterio == 'S')	{
			criterioSuperficie = impostato;
			minimoS = min;
			massimoS = max;
		}
	}

	public Collection<SchedaImmobile> ricercaSchedeCriteri(){
		LinkedList<SchedaImmobile> ris = new LinkedList<SchedaImmobile>();
		for(SchedaImmobile scheda: schede.values())	{
			boolean flag = true;
			if(criterioLocali == true && (scheda.getLocali()<minimoL || scheda.getLocali()>massimoL))	{
				flag=false;
			}
			if(criterioSuperficie == true && (scheda.getSuperficie()<minimoS || scheda.getSuperficie()>massimoS))	{
				flag = false;
			}
			if(flag==true)	{
				ris.add(scheda);
			}
		}
		Collections.sort(ris);
		return ris;
	}

	public Cliente nuovoCliente(String codiceFiscale, String cognome, String nome) {
		for(Cliente c:clienti.values())	{
			if(c.getCodiceFiscale().equals(codiceFiscale))	{
				return c;
			}
		}
		Cliente ris = new Cliente(codiceFiscale, nome, cognome);
		clienti.put(codiceFiscale, ris);
		return ris;
	}
	
	public Collection<Cliente> elencoClientiOrdineDiCodiceFiscale(){
		LinkedList<Cliente> listaTemp = new LinkedList<Cliente>(clienti.values());
		Collections.sort(listaTemp);
		return listaTemp;
	}
	
	public int nuovaTransazioneDiVendita(String idSchedaImmobile, String codiceFiscaleVenditore, String codiceFiscaleAcquirente, double importo) throws EccezioneVenditaNonFinalizzabile {
		if(schede.get(idSchedaImmobile) != null && clienti.get(codiceFiscaleVenditore)!= null && clienti.get(codiceFiscaleAcquirente)!=null)	{
			idTrasazione += 1;
			TransazioneDiVendita transazione = new TransazioneDiVendita(idTrasazione,idSchedaImmobile, codiceFiscaleVenditore, codiceFiscaleAcquirente, importo);
			transazioni.put(idTrasazione, transazione);
			return idTrasazione;
		}
		else {
			throw new EccezioneVenditaNonFinalizzabile();
		}
	}

	public SchedaImmobile schedaImmobileTransazione(int idTransazione) {
		return schede.get(transazioni.get(idTransazione).getIdSchedaImmobile());
	}
	
	public Venditore venditoreTransazione(int idTransazione) {
		Venditore vend = (Venditore) clienti.get(transazioni.get(idTransazione).getCodiceFiscaleVenditore());
		System.out.println(vend.getCodiceFiscale());
		return vend;
	}
	
	public Acquirente acquirenteTransazione(int idTransazione) {
		return (Acquirente) clienti.get(transazioni.get(idTransazione).getCodiceFiscaleAcquirente());
	}
	
	public double importoTransazione(int idTransazione) {
		return transazioni.get(idTransazione).getImporto();
	}

	public String stampaTransazioniOrdineDiImportoIdScheda() {
		LinkedList<TransazioneDiVendita> listaTemp = new LinkedList<TransazioneDiVendita>(transazioni.values());
		Collections.sort(listaTemp);
		String ris="";
		for(TransazioneDiVendita transazione: transazioni.values())	{
			ris+=transazione.toString()+"\n";
		}
		return ris.substring(0, ris.length()-1);
	}
	
	public double calcolaIntroiti(double percentuale) {
		return 0.0;
	}
	
	public void leggiDaFile(String nomeFile) {
		try {
			FileReader fr = new FileReader(nomeFile);
			BufferedReader br = new BufferedReader(fr);
			
			String riga = br.readLine();
			do {
				String[] elementi = riga.split(";");
				if(elementi[0].equals("I"))	{
					creaScheda(elementi[1], elementi[2], elementi[3], Integer.parseInt(elementi[4]), Integer.parseInt(elementi[5]), elementi[6]);
				}
				else if(elementi[0].equals("C"))	{
					nuovoCliente(elementi[1], elementi[2], elementi[3]);
				}
				else if(elementi[0].equals("T"))	{
					try {
						nuovaTransazioneDiVendita(elementi[1], elementi[2], elementi[3], Double.parseDouble(elementi[4]));
					} catch (EccezioneVenditaNonFinalizzabile e) {
						e.printStackTrace();
					}
				}
				riga = br.readLine();
			} while (riga!=null);
			
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
