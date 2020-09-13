package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.interfaces.BaseController;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.DAOFactory;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddTaskController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		String login = user.getLogin();
		String section = request.getParameter(Constants.KEY_SECTION);
		String description = request.getParameter(Constants.KEY_DESCRIPTION);
		String dateString = request.getParameter(Constants.KEY_DATE);
		ITaskDAO taskDAO = (ITaskDAO) DAOFactory.getDAO(Constants.TASK_DAO);
		try {
			Task task = getTask(section, description, dateString);
			taskDAO.addTask(login, task);
			response.sendRedirect(Constants.PROJECT_NAME + Constants.GET_TASKS_CONTROLLER + "?" + 
									Constants.KEY_SECTION + "=" + section);
		} catch (DAOException e) {
			forwardError(e.getMessage(), Constants.PAGE_MAIN, request, response);
		} catch (ValidationException e) {
			forwardError(e.getMessage(), Constants.PAGE_ADD_TASK, request, response);
		}
	}
	
	private Task getTask(String section, String description, String dateString) throws ValidationException {
		final long oneDayInMillis = 86400000;
		if(description.equals(Constants.EMPTY_STRING)) {
			throw new ValidationException(Constants.ERROR_NO_DESCRIPTION_TASK);
		}
		if(section.equals(Constants.KEY_TODAY)) {
			Date date = new Date(System.currentTimeMillis());
			return new Task(description, date);
		}
		if(section.equals(Constants.KEY_TOMORROW)) {
			Date date = new Date(System.currentTimeMillis() + oneDayInMillis);
			return new Task(description, date);
		} else {
			if(dateString.equals(Constants.EMPTY_STRING)) {
				throw new ValidationException(Constants.ERROR_NO_DATE_TASK);
			}
			try {
				Date date = Date.valueOf(dateString);
				return new Task(description, date);
			}catch (IllegalArgumentException e) {
				throw new ValidationException(Constants.ERROR_WRONG_DATE);
			}
		}
	}
	

}
