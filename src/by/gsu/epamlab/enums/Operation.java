package by.gsu.epamlab.enums;

import static by.gsu.epamlab.constants.ConstantsSQL.*;

public enum Operation {
	FIXED(FIXED_TASK_QUERY),
	RECYCLE_BIN(RECYCLE_BIN_TASK_QUERY),
	NOT_FIXED(NOT_FIXED_TASK_QUERY),
	RESTORE(RESTORE_TASK_QUERY),
	REMOVE(REMOVE_TASK_QUERY);
	
	private String updateQuery;

	private Operation(String updateQuery) {
		this.updateQuery = updateQuery;
	}

	public String getUpdateQuery() {
		return updateQuery;
	}
	
}
