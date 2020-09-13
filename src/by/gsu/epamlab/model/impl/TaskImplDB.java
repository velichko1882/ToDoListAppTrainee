package by.gsu.epamlab.model.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsSQL;
import by.gsu.epamlab.enums.Operation;
import by.gsu.epamlab.enums.Section;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;

public class TaskImplDB implements ITaskDAO {
	
	private static final Logger LOGGER = Logger.getLogger(TaskImplDB.class.getName());
	private static final int LOGIN_IND = 1, ID_IND = 1, DESCRIPTION_IND = 2, DATE_IND = 3, 
			FILE_NAME_IND = 4, UPLOAD_FILE_NAME_IND = 1, ID_TASK_IND = 2;

	@Override
	public List<Task> getTasks(String login, String sect) throws DAOException {
		List<Task> tasks = new ArrayList<Task>();
		Section section = Section.valueOf(sect);
		String selectTasksQuery = section.getSelectQuery();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DataBaseManager.getConnection();
			ps = con.prepareStatement(selectTasksQuery);
			ps.setString(LOGIN_IND, login);
			rs = ps.executeQuery();
			while(rs.next()) {
				int idTask = rs.getInt(ID_IND);
				String description = rs.getString(DESCRIPTION_IND);
				Date date = rs.getDate(DATE_IND);
				String fileName = rs.getString(FILE_NAME_IND);
				tasks.add(new Task(idTask, description, date, fileName));
			}
			return tasks;
		}catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.ERROR_SQL);
		}finally {
			DataBaseManager.closeResultSet(rs);
			DataBaseManager.closeStatement(ps);
			DataBaseManager.closeConnection(con);
		}
	}

	@Override
	public void addTask(String login, Task task) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataBaseManager.getConnection();
			ps = con.prepareStatement(ConstantsSQL.ADD_TASK_QUERY);
			ps.setString(LOGIN_IND, login);
			ps.setString(DESCRIPTION_IND, task.getDescription());
			ps.setDate(DATE_IND, task.getDate());
			ps.executeUpdate();
		}catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.ERROR_SQL);
		}finally {
			DataBaseManager.closeStatement(ps);
			DataBaseManager.closeConnection(con);
		}
	}

	@Override
	public void editTask(String[] idTasks, String operationParam) throws DAOException {
		Operation operation = Operation.valueOf(operationParam.toUpperCase().replace(' ', '_'));
		String operationQuery = operation.getUpdateQuery();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataBaseManager.getConnection();
			ps = con.prepareStatement(operationQuery);
			for (String idTask : idTasks) {
				int id = Integer.parseInt(idTask);
				ps.setInt(ID_IND, id);
				ps.addBatch();
			}
			ps.executeBatch();
		}catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.ERROR_SQL);
		}finally {
			DataBaseManager.closeStatement(ps);
			DataBaseManager.closeConnection(con);
		}
	}

	@Override
	public void uploadFileName(String idTask, String fileName) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataBaseManager.getConnection();
			ps = con.prepareStatement(ConstantsSQL.UPLOAD_FILE_QUERY);
			ps.setString(UPLOAD_FILE_NAME_IND, fileName);
			int id = Integer.parseInt(idTask);
			ps.setInt(ID_TASK_IND, id);
			ps.executeUpdate();
		}catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.ERROR_SQL);
		}finally {
			DataBaseManager.closeStatement(ps);
			DataBaseManager.closeConnection(con);
		}
	}

	@Override
	public void deleteFileName(String idTask) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataBaseManager.getConnection();
			ps = con.prepareStatement(ConstantsSQL.DELETE_FILE_QUERY);
			int id = Integer.parseInt(idTask);
			ps.setInt(ID_IND, id);
			ps.executeUpdate();
		}catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.ERROR_SQL);
		}finally {
			DataBaseManager.closeStatement(ps);
			DataBaseManager.closeConnection(con);
		}
		
	}
	
	
	
}
