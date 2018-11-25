package fr.epita.quiz.services.web.api.transport;

import java.util.List;

/**
 * The class QuestionMessage to expose the question details to the external
 * users.
 * 
 * @author Shantanu
 *
 */
public class QuestionMessage {

	/** The id */
	private Long id;

	/** The question label */
	private String questionLabel;

	/** The mcq choices list */
	private List<MCQChoiceMessage> mcqChoices;

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

	/**
	 * Gets the list of mcq choices
	 * 
	 * @return
	 */
	public List<MCQChoiceMessage> getMcqChoices() {
		return mcqChoices;
	}

	/**
	 * Sets the list of mcq choices
	 * 
	 * @param mcqChoices
	 */
	public void setMcqChoices(List<MCQChoiceMessage> mcqChoices) {
		this.mcqChoices = mcqChoices;
	}

	/**
	 * The toString method
	 */
	@Override
	public String toString() {
		return "QuestionMessage [id=" + id + ", questionLabel=" + questionLabel + ", mcqChoices=" + mcqChoices + "]";
	}
}
