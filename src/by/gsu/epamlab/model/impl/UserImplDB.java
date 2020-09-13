package by.gsu.epamlab.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsSQL;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;

public class UserImplDB implements IUserDAO {
	
	private static final Logger LOGGER = Logger.getLogger(UserImplDB.class.getName());
	private static final int LOGIN_IND = 1, PASSWORD_IND = 2, NAME_IND = 3, EMAIL_IND = 4;

	@Override
	public User getUser(String login, String password) throws DAOException  {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DataBaseManager.getConnection();
			ps = con.prepareStatement(ConstantsSQL.SELECT_USER);
			ps.setString(LOGIN_IND, login);
			rs = ps.executeQuery();
			if(rs.next()) {
				String passwordFromDB = rs.getString(PASSWORD_IND);
				if(passwordFromDB.equals(password)) {
					String name = rs.getString(NAME_IND);
					String email = rs.getString(EMAIL_IND);
					return new User(login, password, name, email);
				} else {
					throw new DAOException(Constants.ERROR_WRONG_PASSWORD);
				}
			} else {
				throw new DAOException(Constants.ERROR_WRONG_LOGIN);
			}
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
	public User addAndGetUser(String login, String password, String name, String email) throws DAOException {
		Connection con = null;
		PreparedStatement psFind = null;
		PreparedStatement psAdd = null;
		ResultSet rs = null;
		try {
			con = DataBaseManager.getConnection();
			psFind = con.prepareStatement(ConstantsSQL.SELECT_USER);
			psFind.setString(LOGIN_IND, login);
			synchronized (UserImplDB.class) {
				rs = psFind.executeQuery();
				if(rs.next()) {
					throw new DAOException(Constants.ERROR_WRONG_LOGIN);
				} 
				psAdd = con.prepareStatement(ConstantsSQL.INSERT_USER);
				psAdd.setString(LOGIN_IND, login);
				psAdd.setString(PASSWORD_IND, password);
				psAdd.setString(NAME_IND, name);
				psAdd.setString(EMAIL_IND, email);
				psAdd.executeUpdate();
			}
			return new User(login, password, name, email);
		}catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.ERROR_SQL);
		}finally {
			DataBaseManager.closeResultSet(rs);
			DataBaseManager.closeStatement(psFind, psAdd);
			DataBaseManager.closeConnection(con);
		}
	}
}
