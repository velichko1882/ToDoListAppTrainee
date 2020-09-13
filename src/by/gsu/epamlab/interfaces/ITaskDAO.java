package by.gsu.epamlab.interfaces;

import java.util.List;

import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.model.beans.Task;

public interface ITaskDAO {
	
	List<Task> getTasks(String login, String section) throws DAOException;
	
	void addTask(String login, Task task) throws DAOException;
	
	void editTask(String[] idTasks, String operation) throws DAOException;
	
	void uploadFileName (String idTask, String fileName) throws DAOException;
	
	void deleteFileName (String idTask) throws DAOException;

}
