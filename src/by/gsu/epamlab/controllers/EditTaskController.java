package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.enums.Operation;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.interfaces.BaseController;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.DAOFactory;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditTaskController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String section = request.getParameter(Constants.KEY_SECTION);
		String operation = request.getParameter(Constants.KEY_OPERATION);
		String[] idTasks = request.getParameterValues(Constants.KEY_ID_TASK);
		ITaskDAO taskDAO = (ITaskDAO) DAOFactory.getDAO(Constants.TASK_DAO);
		try {
			if(idTasks == null || idTasks.length == 0) {
				throw new ValidationException(Constants.ERROR_NO_TASK_SELECTED);
			}
			taskDAO.editTask(idTasks, operation);
			if(operation.toUpperCase().equals(Operation.REMOVE.toString())) {
				deleteTasksFile(request, idTasks);
			}
			response.sendRedirect(Constants.PROJECT_NAME + Constants.GET_TASKS_CONTROLLER + "?" + 
									Constants.KEY_SECTION + "=" + section);
		} catch (DAOException e) {
			forwardError(e.getMessage(), Constants.PAGE_MAIN, request, response);
		} catch (ValidationException e) {
			forwardError(e.getMessage(), Constants.PAGE_MAIN, request, response);
		}
	}
	
	//it could be better to use doDelete() for this purpose 
	private void deleteTasksFile(HttpServletRequest request, String[] idTasks) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		String login = user.getLogin();
		String usersFilesPath = Constants.UPLOAD_FILES_DIRECTORY + request.getServletContext().getContextPath() +
				File.separator + login + File.separator;
		for (String idTask : idTasks) {
			String filePath = usersFilesPath + idTask;
			deleteFile(filePath);
		}
	}

}
