package by.gsu.epamlab.enums;

import static by.gsu.epamlab.constants.ConstantsSQL.*;

public enum Section {
	TODAY(SELECT_TASKS_TODAY),
	TOMORROW(SELECT_TASKS_TOMORROW),
	SOMEDAY(SELECT_TASKS_SOMEDAY),
	FIXED(SELECT_TASKS_FIXED),
	RECYCLE_BIN(SELECT_TASKS_RECYCLE);
	
	private String selectQuery;

	private Section(String selectQuery) {
		this.selectQuery = selectQuery;
	}

	public String getSelectQuery() {
		return selectQuery;
	}
	
	
}
