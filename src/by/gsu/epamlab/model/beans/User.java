package by.gsu.epamlab.model.beans;

import java.util.List;

public class User {

	private String login;
	private String password;
	private String name;
	private String email;
	
	private List<Task> tasks;
	
	public User() {
		super(); //why super call explicity?
	}

	public User(String login, String password, String name, String email) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
