package by.gsu.epamlab.interfaces;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.beans.User;

public class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void forward(String url, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	protected void forwardError(String message, String url, HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);
		forward(url, request, response);
	}
	
	protected String getInputResult(String login, String password) {
		if(login == null || password == null) {
			return Constants.ERROR_NULL;
		}
		login = login.trim();
		password = password.trim();
		if(Constants.EMPTY_STRING.equals(login) || Constants.EMPTY_STRING.equals(password)) {
			return Constants.ERROR_EMPTY;
		}
		return Constants.OK;
	}
	
	protected String getFilePath (HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		String login = user.getLogin();
		String idTask = request.getParameter(Constants.KEY_ID_TASK);
		return Constants.UPLOAD_FILES_DIRECTORY + request.getServletContext().getContextPath() +
				File.separator + login + File.separator + idTask;
	}

	protected void deleteFile (String filePath) {
		File dir = new File(filePath);
		if(dir.exists()) {
			if(dir.isDirectory()) {
				for (File file : dir.listFiles()) {
					file.delete();
				}
			}
			dir.delete();
		}
	}

}
