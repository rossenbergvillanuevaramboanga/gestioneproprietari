package it.prova.gestioneproprietari.dao.proprietario;


import it.prova.gestioneproprietari.dao.IBaseDAO;
import it.prova.gestioneproprietari.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario>{
	
	//Conta Quanti Proprietari possiedono automobili immatricolate da un certo anno, fornito in input in poi
	
	public int countByProprietariWhereAnnoImmatricolazioneFrom(int input) throws Exception;
	
	

}
