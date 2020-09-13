package by.gsu.epamlab.model.beans;

import java.sql.Date;

public class Task {
	
	private int idTask;
	private String description;
	private Date date;
	private boolean fixed;
	private boolean inRecycle;
	private String fileName;
	
	public Task() {
		super(); //why super call explicity?
	}
	
	public Task(String description, Date date) {
		super();
		this.description = description;
		this.date = date;
	}

	public Task(int idTask, String description, Date date) {
		super();
		this.idTask = idTask;
		this.description = description;
		this.date = date;
	}
	
	public Task(int idTask, String description, Date date, String fileName) {
		super();
		this.idTask = idTask;
		this.description = description;
		this.date = date;
		this.fileName = fileName;
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public boolean isInRecycle() {
		return inRecycle;
	}

	public void setInRecycle(boolean inRecycle) {
		this.inRecycle = inRecycle;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
