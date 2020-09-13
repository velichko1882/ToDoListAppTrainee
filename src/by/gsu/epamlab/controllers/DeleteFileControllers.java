package by.gsu.epamlab.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.BaseController;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.factories.DAOFactory;

@WebServlet("/deleteFile")
public class DeleteFileControllers extends BaseController {
	private static final long serialVersionUID = 1L;

	//it could be better to use doDelete() for this purpose 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String section = request.getParameter(Constants.KEY_SECTION);
		String idTask = request.getParameter(Constants.KEY_ID_TASK);
		String filePath = getFilePath(request);
		ITaskDAO taskDAO = (ITaskDAO) DAOFactory.getDAO(Constants.TASK_DAO);
		deleteFile(filePath);
		try {
			taskDAO.deleteFileName(idTask);
			response.sendRedirect(Constants.PROJECT_NAME + Constants.GET_TASKS_CONTROLLER + "?" + 
					Constants.KEY_SECTION + "=" + section);
		} catch (DAOException e) {
			forwardError(e.getMessage(), Constants.PAGE_MAIN, request, response);
		}
	}

}
