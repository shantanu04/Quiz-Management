package fr.epita.quiz.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The datamodel class for MCQChoice
 * 
 * @author Shantanu
 *
 */

@Entity
public class MCQChoice {

	/**
	 * The id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * The valid boolean flag
	 */
	private Boolean valid;

	/**
	 * The choice label
	 */
	private String choiceLabel;

	/**
	 * The question to which the choice belongs
	 */
	@ManyToOne
	private Question question;

	/**
	 * Default constructor
	 */
	public MCQChoice() {
		// Default constructor
	}

	/**
	 * Gets the id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the choice label
	 * 
	 * @return the choiceLabel
	 */
	public String getChoiceLabel() {
		return choiceLabel;
	}

	/**
	 * Sets the choice label
	 * 
	 * @param choiceLabel
	 *            the choiceLabel to set
	 */
	public void setChoiceLabel(String choiceLabel) {
		this.choiceLabel = choiceLabel;
	}

	/**
	 * Gets the question
	 * 
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * Sets the question
	 * 
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * Gets the valid flag
	 * 
	 * @return the valid
	 */
	public Boolean getValid() {
		return valid;
	}

	/**
	 * Sets the valid flag
	 * 
	 * @param valid
	 *            the valid to set
	 */
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}
