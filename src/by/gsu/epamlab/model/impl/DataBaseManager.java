package by.gsu.epamlab.model.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.gsu.epamlab.constants.Constants;

public class DataBaseManager {
	
	private static final Logger LOGGER = Logger.getLogger(DataBaseManager.class.getName());
	
	public static Connection getConnection() {
		try {
			Class.forName(Constants.DB_DRIVER);
			return DriverManager.getConnection(Constants.DB_URL, 
					Constants.DB_LOGIN, Constants.DB_PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new IllegalStateException(Constants.ERROR_DB_CONNECT);
		}
	}
	
	public static void closeResultSet (ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
	}
	
	public static void closeStatement(Statement ... statements) {
		for (Statement stmt : statements) {
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					LOGGER.log(Level.SEVERE, e.toString(), e);
				}
			}
		}
	}
	
	public static void closeConnection(Connection con) {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
	}
	
}
