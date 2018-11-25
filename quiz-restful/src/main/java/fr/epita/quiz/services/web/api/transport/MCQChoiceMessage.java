package fr.epita.quiz.services.web.api.transport;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

/**
 * The class MCQChoiceMessage to expose the MCQ choice details to the external
 * users.
 * 
 * @author Shantanu
 *
 */
public class MCQChoiceMessage {

	/** The id */
	private Long id;

	/** The valid flag */
	private Boolean valid;

	/** The choice label */
	private String label;

	/**
	 * Gets the id
	 * 
	 * @return
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
	 * Gets the valid flag
	 * 
	 * @return
	 */
	public Boolean getValid() {
		return valid;
	}

	/**
	 * Sets the valid flag
	 * 
	 * @param valid
	 */
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/**
	 * Gets the choice label
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the choice label
	 * 
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * The method to set details to MCQChoice object
	 * 
	 * @param question
	 * @return
	 */
	public MCQChoice toMCQChoice(Question question) {
		MCQChoice choice = new MCQChoice();
		choice.setChoiceLabel(this.label);
		choice.setValid(valid);
		choice.setId(id);
		choice.setQuestion(question);
		return choice;

	}

}
