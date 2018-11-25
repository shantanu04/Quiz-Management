package fr.epita.quiz.services.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The GenericDAO class to perform operations like create, update, delete and
 * getById
 * 
 * @author Shantanu
 *
 * @param <T>
 */
public abstract class GenericDAO<T> {

	/** The logger */
	private static final Logger LOGGER = LogManager.getLogger(GenericDAO.class);

	/**
	 * The entity manager object
	 */
	@PersistenceContext
	protected EntityManager em;

	/**
	 * The update method
	 * 
	 * @param instance
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T instance) {
		LOGGER.debug("Update entity");
		em.merge(instance);
	}

	/**
	 * The delete method
	 * 
	 * @param instance
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T instance) {
		LOGGER.debug("Delete entity");
		em.remove(em.contains(instance) ? instance : em.merge(instance));

	}

	/**
	 * The create method
	 * 
	 * @param instance
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(T instance) {
		LOGGER.debug("Create entity");
		em.persist(instance);

	}

	/**
	 * The search method
	 * 
	 * @param criteriaInstance
	 * @return
	 */
	public abstract List<T> search(T criteriaInstance);

	/**
	 * The get by id method
	 * 
	 * @param id
	 * @return
	 */
	public T getById(Serializable id) {
		LOGGER.debug("Find entity by id");
		return em.find(getType(), id);
	}

	/**
	 * The abstract get type method
	 * 
	 * @return
	 */
	public abstract Class<T> getType();
}
