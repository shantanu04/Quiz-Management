package fr.epita.quiz.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The datamodel class for Question
 * 
 * @author Shantanu
 *
 */

@Entity
public class Question {

	/**
	 * The id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * The question label
	 */
	private String questionLabel;

	/**
	 * Default constructor
	 */
	public Question() {
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
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the question label
	 * 
	 * @return
	 */
	public String getQuestionLabel() {
		return questionLabel;
	}

	/**
	 * Sets the question label
	 * 
	 * @param questionLabel
	 */
	public void setQuestionLabel(String questionLabel) {
		this.questionLabel = questionLabel;
	}

}
