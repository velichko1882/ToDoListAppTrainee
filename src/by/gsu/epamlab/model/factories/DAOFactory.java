package by.gsu.epamlab.model.factories;

import java.util.HashMap;
import java.util.Map;

import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.impl.TaskImplDB;
import by.gsu.epamlab.model.impl.UserImplDB;
import by.gsu.epamlab.model.impl.UserImplMemory;

public class DAOFactory {
	
	private static Map<String, Object> map = new HashMap<String, Object>();
	static {
		//map.put(IUserDAO.class.getName(), new UserImplMemory());
		map.put(IUserDAO.class.getName(), new UserImplDB());
		map.put(ITaskDAO.class.getName(), new TaskImplDB());
	}

	public static Object getDAO(String className) {
		return map.get(className);
	}
}
