package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.interfaces.BaseController;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.factories.DAOFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadFile")
@MultipartConfig
public class UploadFileController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String section = request.getParameter(Constants.KEY_SECTION);
		String idTask = request.getParameter(Constants.KEY_ID_TASK);
		String filePath = getFilePath(request);
		Part filePart = request.getPart(Constants.KEY_FILE);
		String fileName = filePart.getSubmittedFileName();
		ITaskDAO taskDAO = (ITaskDAO) DAOFactory.getDAO(Constants.TASK_DAO);

		try {
			copyFile(filePath, fileName, filePart);
			taskDAO.uploadFileName(idTask, fileName);
			response.sendRedirect(Constants.PROJECT_NAME + Constants.GET_TASKS_CONTROLLER + "?" + 
					Constants.KEY_SECTION + "=" + section);
		} catch (DAOException e) {
			forwardError(e.getMessage(), Constants.PAGE_MAIN, request, response);
		} catch (ValidationException e) {
			forwardError(e.getMessage(), Constants.PAGE_MAIN, request, response);
		}
	}
	
	private void copyFile(String filePath, String fileName, Part filePart) throws ValidationException {
		File fileDir = new File(filePath);
		if(!fileDir.exists()) {
			fileDir.mkdirs();
		}
		if(fileName == null || fileName.equals(Constants.EMPTY_STRING)) {
			throw new ValidationException(Constants.ERROR_NO_FILE);
		}
		try (InputStream fileContent = filePart.getInputStream(); 
				OutputStream out = new FileOutputStream(filePath + File.separator + fileName, false)) {
			int bytes= 0;
			byte[] buff = new byte[1024];
			while((bytes= fileContent.read(buff)) != -1) {
				out.write(buff, 0, bytes);
			}
		} catch (IOException e) {
			throw new ValidationException(Constants.ERROR_UPLOAD_FILE);
		}
	}

}
