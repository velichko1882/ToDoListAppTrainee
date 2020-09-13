package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.BaseController;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.DAOFactory;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetTasksController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		String login = user.getLogin();
		String section = request.getParameter(Constants.KEY_SECTION);
		if (section == null) {
			section = Constants.KEY_TODAY;
		}
		ITaskDAO taskDAO = (ITaskDAO) DAOFactory.getDAO(Constants.TASK_DAO);
		
		try {
			List<Task> tasks = taskDAO.getTasks(login, section);
			request.setAttribute(Constants.KEY_TASKS, tasks);
			request.setAttribute(Constants.KEY_SECTION, section);
			forward(Constants.PAGE_MAIN, request, response);
		} catch (DAOException e) {
			forwardError(e.getMessage(), Constants.PAGE_MAIN, request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
