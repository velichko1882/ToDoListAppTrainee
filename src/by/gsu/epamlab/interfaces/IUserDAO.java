package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.model.beans.User;

public interface IUserDAO {
	
	public User getUser(String login, String password) throws DAOException;
	
	public User addAndGetUser(String login, String password, String name, String email) throws DAOException;

}
