package by.gsu.epamlab.model.impl;

import java.util.HashMap;
import java.util.Map;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;

public class UserImplMemory implements IUserDAO {
	
	private static Map<String, User> users = new HashMap<String, User>();
		
	@Override
	public User getUser(String login, String password) throws DAOException {
		if (!users.containsKey(login)) {
			throw new DAOException(Constants.ERROR_WRONG_LOGIN);
		}
		User user = users.get(login);
		if (!user.getPassword().equals(password)) {
			throw new DAOException(Constants.ERROR_WRONG_PASSWORD);
		}
		return user;
	}

	@Override
	public User addAndGetUser(String login, String password, String name, String email) throws DAOException {
		synchronized (users) {
			if (users.containsKey(login)) {
				throw new DAOException(Constants.ERROR_LOGIN_EXIST);
			}
			User user = new User(login, password, name, email);
			users.put(login, user);
			return user;
		}
	}


}
