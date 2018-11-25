package fr.epita.maths.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

/**
 * The test class TestJPAWithEMF
 * 
 * @author Shantanu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContextWithEM.xml" })
public class TestJPAWithEMF {

	/** The entity manager instance */
	@PersistenceContext
	EntityManager em;

	/**
	 * The test method
	 */
	@Test
	@Transactional
	public void test() {
		// given
		Question question = new Question();
		question.setQuestionLabel("What is JPA?");
		MCQChoice choice1 = new MCQChoice();
		choice1.setChoiceLabel("it is a dependency injection framework");
		choice1.setValid(false);

		MCQChoice choice2 = new MCQChoice();
		choice2.setChoiceLabel("it is a specification to normalize persistence in java");
		choice2.setValid(true);

		choice1.setQuestion(question);
		choice2.setQuestion(question);

		// when
		persistQuestionAndChoices(question, choice1, choice2);

		// then
		TypedQuery<Question> searchQuery = em.createQuery("from Question as q where q.questionLabel = :qlabel",
				Question.class);
		searchQuery.setParameter("qlabel", question.getQuestionLabel());

		Assert.assertNotEquals(0, searchQuery.getResultList().size());

		TypedQuery<MCQChoice> searchQueryMCQ = em.createQuery("from MCQChoice as m where m.question = :question",
				MCQChoice.class);
		searchQueryMCQ.setParameter("question", question);
		Assert.assertEquals(2, searchQueryMCQ.getResultList().size());

		// TODO log

	}

	/**
	 * The method to persist question and choices
	 * 
	 * @param question
	 * @param choices
	 */
	@Transactional(propagation = Propagation.NESTED)
	private void persistQuestionAndChoices(Question question, MCQChoice... choices) {
		em.persist(question);
		for (MCQChoice choice : choices) {
			em.persist(choice);
		}

	}

}
