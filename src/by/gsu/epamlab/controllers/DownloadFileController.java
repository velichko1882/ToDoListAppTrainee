package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.interfaces.BaseController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downloadFile")
public class DownloadFileController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = getFilePath(request);
		String fileName = request.getParameter(Constants.KEY_FILE_NAME);
		try {
			downloadFile(filePath, fileName, response);
		} catch (ValidationException e) {
			e.printStackTrace();
		}

	}

	private void downloadFile (String filePath, String fileName, HttpServletResponse response) throws ValidationException {
		//why not in constants?
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		try (InputStream fileContent = new FileInputStream(filePath + File.separator + fileName); 
				OutputStream out = response.getOutputStream()) {
			int bytes= 0;
			byte[] buff = new byte[1024];
			while((bytes= fileContent.read(buff)) != -1) {
				out.write(buff, 0, bytes);
			}
		} catch (IOException e) {
			throw new ValidationException(Constants.ERROR_DOWNLOAD_FILE);
		}
	}
}
