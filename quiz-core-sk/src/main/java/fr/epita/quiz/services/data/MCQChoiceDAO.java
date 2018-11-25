package fr.epita.quiz.services.data;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.MCQChoice;

/**
 * The MCQChoice DAO class to perform operations related to MCQs
 * 
 * @author Shantanu
 *
 */
@Repository
public class MCQChoiceDAO extends GenericDAO<MCQChoice> {

	/** The logger */
	private static final Logger LOGGER = LogManager.getLogger(MCQChoiceDAO.class);

	/**
	 * The search method
	 */
	@Override
	public List<MCQChoice> search(MCQChoice mcqChoiceCriteria) {
		LOGGER.debug("Start searching MCQ choices for Question: ", mcqChoiceCriteria.getChoiceLabel());
		String hqlString = "from MCQChoice as m where m.question = :question";
		TypedQuery<MCQChoice> searchQuery = em.createQuery(hqlString, MCQChoice.class);
		searchQuery.setParameter("question", mcqChoiceCriteria.getQuestion());

		return searchQuery.getResultList();
	}

	/**
	 * The get type method
	 */
	@Override
	public Class<MCQChoice> getType() {
		return MCQChoice.class;
	}

}
