package it.prova.gestioneproprietari.service.proprietario;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.dao.proprietario.ProprietarioDAO;
import it.prova.gestioneproprietari.model.Proprietario;

public class ProprietarioServiceImpl implements ProprietarioService {

	private ProprietarioDAO proprietarioDAO;
	
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO) {
		// TODO Auto-generated method stub
		this.proprietarioDAO = proprietarioDAO;
	}

	@Override
	public List<Proprietario> listAllProprietari() throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			proprietarioDAO.setEntityManager(entityManager);
			return proprietarioDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Proprietario caricaSingoloProprietario(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			proprietarioDAO.setEntityManager(entityManager);
			return proprietarioDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Proprietario proprietario) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			proprietarioDAO.setEntityManager(entityManager);
			proprietarioDAO.update(proprietario);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void inserisciNuovo(Proprietario proprietario) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			proprietarioDAO.setEntityManager(entityManager);
			proprietarioDAO.insert(proprietario);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void rimuovi(Proprietario proprietario) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			proprietarioDAO.setEntityManager(entityManager);
			proprietarioDAO.delete(proprietario);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public int contaQuantiProprietariHannoAutoImmatricolateDopo(int input) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			proprietarioDAO.setEntityManager(entityManager);
			return proprietarioDAO.countByProprietariWhereAnnoImmatricolazioneFrom(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	

}
