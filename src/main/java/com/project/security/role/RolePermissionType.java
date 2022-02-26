package com.project.security.role;

public enum RolePermissionType {
	READ_SELF("READ_SELF"), 
	WRITE_SELF("WRITE_SELF"),
	UPDDATE_SELF("UPDATE_SELF"),
	READ_ALL("READ_ALL"),
	WRITE_ALL("READ_ALL"),
	UPDDATE_ALL("READ_ALL"),
	DELETE("DELETE");

	private final String value;

	RolePermissionType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
