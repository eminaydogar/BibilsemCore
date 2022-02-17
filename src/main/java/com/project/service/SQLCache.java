package com.project.service;

public class SQLCache {

	public static class SELECT {
      public static final String MESSAGE_DEFINITION_VERIFICATION ="SELECT * FROM MESSAGE_DEFINITION WHERE USER_ID=? AND MESSAGE_TYPE='VERIFICATION' AND EDATE>=?";
      public static final String BY_USERNAME="Select * from user_definition where username=?";
	}
}
