package fr.epita.quiz.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.QuizDataService;
import fr.epita.quiz.services.web.api.transport.MCQChoiceMessage;
import fr.epita.quiz.services.web.api.transport.QuestionMessage;

/**
 * The class QuestionResource exposes all the basic operations for creating,
 * updating, searching and deleting the MCQs.
 * 
 * @author Shantanu
 *
 */
@Path("questions")
public class QuestionResource {

	/** The logger */
	private static final Logger LOGGER = LogManager.getLogger(QuestionResource.class);

	/** The path string */
	static final String PATH = "questions";

	/**
	 * The quiz data service object.
	 */
	@Inject
	QuizDataService dataService;

	/**
	 * The method to create a question and its choices.
	 * 
	 * @param message
	 * @return
	 * @throws URISyntaxException
	 */
	@POST
	@Path("/")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response createQuestion(QuestionMessage message) throws URISyntaxException {
		LOGGER.info("Create a question with choices");
		Question question = toQuestion(message);
		List<MCQChoice> mcqChoiceList = toMCQChoiceList(question, message.getMcqChoices());
		dataService.createQuestionWithChoices(question, mcqChoiceList);
		return Response.created(new URI(PATH + "/" + String.valueOf(question.getId()))).build();

	}

	/**
	 * The method to find questions and choices based on a query parameter string.
	 * 
	 * @param inputString
	 * @return
	 */
	@GET
	@Path("/")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response findQuestionsAndChoices(@QueryParam("s") String inputString) {

		List<QuestionMessage> questionMessages = new ArrayList<QuestionMessage>();
		Question question = new Question();
		question.setQuestionLabel(inputString);
		Map<Question, List<MCQChoice>> map = dataService.findQuestionsAndChoices(question);
		for (Entry<Question, List<MCQChoice>> entry : map.entrySet()) {
			QuestionMessage qm = new QuestionMessage();
			qm.setQuestionLabel(entry.getKey().getQuestionLabel());
			qm.setId(entry.getKey().getId());
			addMCQChoiceListToQuestionMessage(entry.getValue(), qm);
			questionMessages.add(qm);
		}

		return Response.ok(questionMessages).build();
	}

	/**
	 * The method to find all the multiple choice questions.
	 * 
	 * @return
	 */
	@GET
	@Path("/findAll")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response findAllMCQs() {
		LOGGER.info("Find all multiple choice questions");
		List<QuestionMessage> questionMessages = new ArrayList<QuestionMessage>();
		Map<Question, List<MCQChoice>> map = dataService.findAllQuestionsWithMCQChoices();
		for (Entry<Question, List<MCQChoice>> entry : map.entrySet()) {
			QuestionMessage qm = new QuestionMessage();
			qm.setQuestionLabel(entry.getKey().getQuestionLabel());
			qm.setId(entry.getKey().getId());
			addMCQChoiceListToQuestionMessage(entry.getValue(), qm);
			questionMessages.add(qm);
		}

		return Response.ok(questionMessages).build();
	}

	/**
	 * The method to only get one question based upon the id.
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response getOneQuestion(@PathParam("id") String id) {

		Question question = dataService.getQuestionById(Long.valueOf(id));
		if (question == null) {
			return Response.status(Status.NOT_FOUND).entity("{\"message\" : 'Not found'}").build();
		}
		QuestionMessage message = new QuestionMessage();
		message.setId(question.getId());
		message.setQuestionLabel(question.getQuestionLabel());

		return Response.ok(message).build();
	}

	/**
	 * The method to update questions and choices.
	 * 
	 * @param message
	 * @return
	 */
	@PUT
	@Path("/updateMcq")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response updateQuestionAndChoices(QuestionMessage message) {
		LOGGER.info("Update a question with its choices");
		Question question = dataService.getQuestionById(Long.valueOf(message.getId()));
		if (question == null) {
			return Response.status(Status.NOT_FOUND).entity("{\"message\" : 'Question not found in database'}").build();
		}
		List<MCQChoice> mcqChoiceList = toMCQChoiceList(question, message.getMcqChoices());
		question.setQuestionLabel(message.getQuestionLabel());
		dataService.updateQuestionAndChoices(question, mcqChoiceList);

		return Response.ok(message).build();
	}

	/**
	 * The method to delete a question and its choices
	 * 
	 * @param message
	 * @return
	 */
	@DELETE
	@Path("/deleteMcq")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response deleteQuestionAndChoices(QuestionMessage message) {
		LOGGER.info("Delete a question with choices");
		Question question = dataService.getQuestionById(Long.valueOf(message.getId()));
		if (question == null) {
			return Response.status(Status.NOT_FOUND).entity("{\"message\" : 'Question not found in database'}").build();
		}
		Boolean result = dataService.deleteQuestionWithChoices(question);
		if (result == Boolean.FALSE)
			return Response.status(Status.BAD_REQUEST).build();

		return Response.ok(message).build();
	}

	/**
	 * The method to convert QuestionMessage object to Question object.
	 * 
	 * @param qm
	 * @return
	 */
	private static Question toQuestion(QuestionMessage qm) {
		Question question = new Question();
		question.setQuestionLabel(qm.getQuestionLabel());
		return question;
	}

	/**
	 * The method to convert Question object to QuestionMessage object.
	 * 
	 * @param question
	 * @return
	 */
	private static QuestionMessage fromQuestion(Question question) {
		QuestionMessage questionMessage = new QuestionMessage();
		questionMessage.setId(question.getId());
		questionMessage.setQuestionLabel(question.getQuestionLabel());
		return questionMessage;
	}

	/**
	 * The method to convert MCQChoiceMessage list to MCQChoice list.
	 * 
	 * @param question
	 * @param mcqChoices
	 * @return
	 */
	private static List<MCQChoice> toMCQChoiceList(Question question, List<MCQChoiceMessage> mcqChoices) {
		List<MCQChoice> mcqChoiceList = new ArrayList<>();
		for (MCQChoiceMessage choiceMessage : mcqChoices) {
			MCQChoice mcqChoice = choiceMessage.toMCQChoice(question);
			mcqChoiceList.add(mcqChoice);
		}

		return mcqChoiceList;
	}

	/**
	 * The method to convert MCQChoice object to MCQChoiceMessage.
	 * 
	 * @param mcqChoice
	 * @return
	 */
	private static MCQChoiceMessage fromMCQChoice(MCQChoice mcqChoice) {
		MCQChoiceMessage choiceMessage = new MCQChoiceMessage();
		choiceMessage.setId(mcqChoice.getId());
		choiceMessage.setLabel(mcqChoice.getChoiceLabel());
		choiceMessage.setValid(mcqChoice.getValid());

		return choiceMessage;
	}

	/**
	 * The method to add MCQChoice list to QuestionMessage object.
	 * 
	 * @param list
	 * @param qm
	 */
	private void addMCQChoiceListToQuestionMessage(List<MCQChoice> list, QuestionMessage qm) {
		List<MCQChoiceMessage> resultList = new ArrayList<>();
		for (MCQChoice choice : list) {
			resultList.add(fromMCQChoice(choice));
		}
		qm.setMcqChoices(resultList);
	}

}
