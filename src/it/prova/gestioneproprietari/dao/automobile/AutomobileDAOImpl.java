package it.prova.gestioneproprietari.dao.automobile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileDAOImpl implements AutomobileDAO {

	private EntityManager entityManager;

	@Override
	public List<Automobile> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();
	}

	@Override
	public Automobile get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(Automobile.class, id);
	}

	@Override
	public void update(Automobile o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);

	}

	@Override
	public void insert(Automobile o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);

	}

	@Override
	public void delete(Automobile o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(o));

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		this.entityManager = entityManager;

	}

	@Override
	public List<Automobile> findAllByCodiceFiscaleIniziaCon(String iniziale) throws Exception {
		// TODO Auto-generated method stub
		if (iniziale == null || iniziale.isEmpty()) {
			throw new Exception("Problema valore in input");
		}
		TypedQuery<Automobile> query = entityManager
				.createQuery("select distinct a from Automobile a left join fetch a.proprietario where codicefiscale like ?1", Automobile.class);
		query.setParameter(1, iniziale + "%");
		return query.getResultList();
	}

	@Override
	public List<Automobile> findAllByError() throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Automobile> query = entityManager.createQuery(
				" select distinct a from Automobile a left join fetch a.proprietario where year(current_date) - year(datadinascita) < 18 ",
				Automobile.class);
		return query.getResultList();
	}

}
