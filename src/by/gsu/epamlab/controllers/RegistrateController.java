package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.BaseController;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.DAOFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrateController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String login = request.getParameter(Constants.KEY_LOGIN);
		String password = request.getParameter(Constants.KEY_PASSWORD);
		String name = request.getParameter(Constants.KEY_NAME);
		String email = request.getParameter(Constants.KEY_EMAIL);
		String inputResult = getInputResult(login, password);
		if(!Constants.OK.equals(inputResult)) {
			forwardError(inputResult, Constants.PAGE_REGISTRATE, request, response);
			return;
		}
		IUserDAO userDAO = (IUserDAO) DAOFactory.getDAO(Constants.USER_DAO);
		try {
			User user = userDAO.addAndGetUser(login.trim(), password, name, email);
			HttpSession session = request.getSession();
			session.setAttribute(Constants.KEY_USER, user);
			response.sendRedirect(Constants.PROJECT_NAME + Constants.GET_TASKS_CONTROLLER);
		}catch (DAOException e) {
			forwardError(e.getMessage(), Constants.PAGE_REGISTRATE, request, response);
		}
	}
}
