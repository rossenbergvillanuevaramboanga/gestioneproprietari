package it.prova.gestioneproprietari.dao.proprietario;

import it.prova.gestioneproprietari.dao.IBaseDAO;
import it.prova.gestioneproprietari.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario>{
	
	//restituisce una lista di automobili i cui proprietari hanno un codice fiscale che inizia con una  string a piacere
	
	//restituisce una lista di automobili con errori, cioè possedute da soggetti minori

}
