package it.prova.gestioneproprietari.service.automobile;

import java.util.List;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.model.Automobile;

public interface AutomobileService {
	
	public List<Automobile> listAllAutomobili() throws Exception;
	
	public Automobile caricaSingolaAutomobile(Long id) throws Exception;
	
	public void aggiorna(Automobile automobile) throws Exception;
	
	public void inserisciNuovo(Automobile automobile) throws Exception;
	
	public void rimuovi(Automobile automobile) throws Exception;
	
	// Metodi da implementare
	public List<Automobile> cercaTutteConProprietarioCodiceFiscaleIniziaCon(String iniziale) throws Exception;
	
	public List<Automobile> cercaTutteConErrore() throws Exception;

	public void setAutomobileDAO(AutomobileDAO automobileDAOInstance);
	
	
	

}
