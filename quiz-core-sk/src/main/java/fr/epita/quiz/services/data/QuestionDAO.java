package fr.epita.quiz.services.data;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Question;

/**
 * The Question DAO class to perform operations related to Questions
 * 
 * @author Shantanu
 *
 */

@Repository
public class QuestionDAO extends GenericDAO<Question> {

	/** The logger */
	private static final Logger LOGGER = LogManager.getLogger(QuestionDAO.class);

	/**
	 * The search method
	 */
	@Override
	public List<Question> search(Question questionCriteria) {
		LOGGER.debug("Start searching for Question :" + questionCriteria.getQuestionLabel());

		String hqlString = "from Question as q where q.questionLabel like :qlabel";
		TypedQuery<Question> searchQuery = em.createQuery(hqlString, Question.class);
		if (questionCriteria.getQuestionLabel() == null || questionCriteria.getQuestionLabel().equals("")) {
			searchQuery.setParameter("qlabel", null);
		} else {
			searchQuery.setParameter("qlabel", "%" + questionCriteria.getQuestionLabel() + "%");
		}

		return searchQuery.getResultList();
	}

	/**
	 * The search all method
	 * 
	 * @return
	 */
	public List<Question> searchAll() {
		LOGGER.debug("Start searching for ALL questions");
		String hqlString = "from Question";
		TypedQuery<Question> searchQuery = em.createQuery(hqlString, Question.class);

		return searchQuery.getResultList();
	}

	/**
	 * The get type method
	 */
	@Override
	public Class<Question> getType() {
		return Question.class;
	}

}
