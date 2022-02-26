package com.project.cache;

public class BBConstant {

	public static interface TIME_TYPE {
		public static int SECOND = 0;
		public static int MINUTE = 1;
		public static int HOUR = 2;
	}

	public static interface CHANNEL_TYPE {
		public static String MAIL = "MAIL";
		public static String SMS = "SMS";

	}

	public static interface MESSAGE_TYPE {
		public static String VERIFICATION = "VERIFICATION";

	}

	public static interface USER_VERIFICATION_TYPE {
		public static String VERIFIED = "Y";
		public static String NOT_VERIFIED = "N";

	}

	public static interface RECORD_STATUS {
		public static String OK = "Y";
		public static String NOK = "N";
	}

	public static interface OperationStatus {

		public static int ERROR = -1;
		public static int SUCCESS = 0;

	}
	
	public static interface Path {

		public static String resource = "src/main/resources/";
		public static String swagger ="/swagger-ui/";

	}
	
	public static interface ERROR_TYPE {

		public static String Job = "JOB_ERROR";
		

	}

}
