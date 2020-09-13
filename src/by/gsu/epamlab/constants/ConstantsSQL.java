package by.gsu.epamlab.constants;

public class ConstantsSQL {
	
	public static final String SELECT_USER = "SELECT * FROM users WHERE login=?"; 
	public static final String INSERT_USER = "INSERT INTO users (login, password, name, email) VALUES (?,?,?,?);"; 
	public static final String SELECT_TASKS_TODAY = "SELECT idTask, description, date, fileName FROM tasks WHERE userLogin = ? "
			+ "AND date <= curdate() AND fixed = 0 AND inRecycle = 0;"; 
	public static final String SELECT_TASKS_TOMORROW = "SELECT idTask, description, date, fileName FROM tasks WHERE userLogin = ? "
			+ "AND date = (curdate() + 1) AND fixed = 0 AND inRecycle = 0;";
	public static final String SELECT_TASKS_SOMEDAY = "SELECT idTask, description, date, fileName FROM tasks WHERE userLogin = ? "
			+ "AND date > (curdate() + 1) AND fixed = 0 AND inRecycle = 0;";
	public static final String SELECT_TASKS_FIXED = "SELECT idTask, description, date, fileName FROM tasks WHERE userLogin = ? "
			+ "AND fixed = 1 AND inRecycle = 0;";
	public static final String SELECT_TASKS_RECYCLE = "SELECT idTask, description, date, fileName FROM tasks WHERE userLogin = ? "
			+ "AND inRecycle = 1;";
	public static final String ADD_TASK_QUERY = "INSERT INTO tasks (userLogin, description, date) values (?,?,?);"; 
	public static final String FIXED_TASK_QUERY = "UPDATE tasks SET fixed = 1 WHERE idTask = ?;";
	public static final String RECYCLE_BIN_TASK_QUERY = "UPDATE tasks SET inRecycle = 1 WHERE idTask = ?;";
	public static final String NOT_FIXED_TASK_QUERY = "UPDATE tasks SET fixed = 0 WHERE idTask = ?;";
	public static final String RESTORE_TASK_QUERY = "UPDATE tasks SET fixed = 0, inRecycle = 0 WHERE idTask = ?;";
	public static final String REMOVE_TASK_QUERY = "DELETE FROM tasks WHERE idTask = ?;";
	public static final String UPLOAD_FILE_QUERY = "UPDATE tasks SET fileName = ? WHERE idTask = ?;";
	public static final String DELETE_FILE_QUERY = "UPDATE tasks SET fileName = 'no file' WHERE idTask = ?;";

}
