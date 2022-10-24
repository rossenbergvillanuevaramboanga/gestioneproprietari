package it.prova.gestioneproprietari.dao.automobile;

import java.util.List;

import it.prova.gestioneproprietari.dao.IBaseDAO;
import it.prova.gestioneproprietari.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile>{
	
	
	
	//restituisce una lista di automobili i cui proprietari hanno un codice fiscale che inizia con una string a piacere
		public List<Automobile> findAllByCodiceFiscaleIniziaCon(String iniziale) throws Exception;
		
		//restituisce una lista di automobili con errori, cioè possedute da soggetti minori
		public List<Automobile> findAllByError() throws Exception;
	

}
