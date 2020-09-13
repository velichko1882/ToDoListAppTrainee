package by.gsu.epamlab.constants;

import java.io.File;

import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.interfaces.IUserDAO;

public class Constants {

	public static final String USER_DAO = IUserDAO.class.getName();
	public static final String TASK_DAO = ITaskDAO.class.getName();
	
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/todolist";
	public static final String DB_LOGIN = "web";
	public static final String DB_PASSWORD = "111";
	
	public static final String KEY_LOGIN = "login";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_NAME = "name";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_USER = "user";
    public static final String KEY_SECTION = "section";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_OPERATION = "operation";
    public static final String KEY_FILE_ACTION = "action";
    public static final String KEY_ID_TASK = "idTask";
    public static final String KEY_DATE = "date";
    public static final String KEY_TODAY = "TODAY";
    public static final String KEY_TOMORROW = "TOMORROW";
    public static final String KEY_TASKS = "tasks";
    public static final String KEY_FILE = "file";
    public static final String KEY_FILE_NAME = "fileName";
	
	public static final String ERROR_DB_CONNECT = "Error connecting to database.";
	public static final String ERROR_SQL = "Вatabase query error.";
	public static final String ERROR_NULL = "Login or password is missing.";
    public static final String ERROR_EMPTY = "Login or password is empty.";
    public static final String ERROR_WRONG_LOGIN = "No user with this login.";
    public static final String ERROR_WRONG_PASSWORD = "Wrong password.";
    public static final String ERROR_LOGIN_EXIST = "User with such login exists, please specify another login.";
    public static final String ERROR_NO_TASKS = "There are no tasks in this section.";
    public static final String ERROR_NO_DESCRIPTION_TASK = "You did not enter a task description, please do it.";
    public static final String ERROR_NO_DATE_TASK = "You did not enter a task date, please do it.";
    public static final String ERROR_WRONG_DATE = "Wrong date format.";
    public static final String ERROR_NO_TASK_SELECTED = "You have not chosen any task, please make a choice.";
    public static final String ERROR_NO_FILE = "No file selected.";
    public static final String ERROR_UPLOAD_FILE = "Error loading file.";
    public static final String ERROR_DOWNLOAD_FILE = "Error downloading file";

    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_REGISTRATE = "/registrate.jsp";
    public static final String PAGE_MAIN = "/main.jsp";
    public static final String PROJECT_NAME = "/web01";
    public static final String PAGE_TASKS = "/tasks.jsp";
    public static final String PAGE_ADD_TASK = "/addTask.jsp";
    public static final String GET_TASKS_CONTROLLER = "/getTasks";

 
    public static final String EMPTY_STRING = "";
    public static final String OK = "ok";
    public static final String NO_FILE = "no file";
    public static final String UPLOAD_FILES_DIRECTORY = File.separator + "home" + File.separator + "valentin" + 
    		File.separator + "eclipse-workspace" + File.separator + "uploadFiles";
    
}
